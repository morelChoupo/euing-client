package com.ufi.euing.client.business.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.ufi.euing.client.business.EmailService;
import com.ufi.euing.client.business.UserService;
import com.ufi.euing.client.dto.UserCreateDTO;
import com.ufi.euing.client.dto.UserUpdateDto;
import com.ufi.euing.client.entity.User;
import com.ufi.euing.client.entity.UserPrincipal;
import com.ufi.euing.client.exceptions.domain.EmailExistException;
import com.ufi.euing.client.exceptions.domain.LoginException;
import com.ufi.euing.client.repositories.UserRepository;
import com.ufi.euing.client.utils.JWTTokenProvider;
import com.ufi.euing.client.utils.others.GenerateCodeUtils;
import com.ufi.euing.client.utils.others.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.ufi.euing.client.constants.SecurityConstant.JWT_TOKEN_HEADER;

import static com.ufi.euing.client.entity.Roles.ROLE_USER;

import java.time.LocalDateTime;



@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    final UserRepository userRepository;
    final JWTTokenProvider jwtProvider;
    final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, JWTTokenProvider jwtProvider, EmailService emailService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.emailService = emailService;
    }


    @Override
    public User create(UserCreateDTO user) throws EmailExistException {

        if (user.getUsrEmail() == null || user.getUsrEmailRec() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        if(userRepository.findUserByUsrEmail(user.getUsrEmail())!= null) {
            throw new EmailExistException("Email already exists with email " + user.getUsrEmail());
        }

        if(userRepository.findUserByUsrEmailRec(user.getUsrEmailRec())!= null) {
            throw new EmailExistException("Email of recuperation is already exists with email recuperation " + user.getUsrEmailRec());
        }

        String code = GenerateCodeUtils.generateUserCode();

        User newUser = new User();
        newUser.setModifPassword(false);
        newUser.setActive(true);
        newUser.setNotLocked(true);
        newUser.setUsrCode(code);
        newUser.setJoinDate(LocalDateTime.now());
        newUser.setModifDate(LocalDateTime.now());
        newUser.setCountry(user.getCountry());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setUsrEmail(user.getUsrEmail());
        newUser.setUsrEmailRec(user.getUsrEmailRec());
        newUser.setUsrNom(user.getUsrNom());
        newUser.setUsrPrenom(user.getUsrPrenom());
        newUser.setUsrPassword(Tools.encodePassword(user.getUsrPassword()));
        newUser.setRole(ROLE_USER.name());
        newUser.setAuthorities(ROLE_USER.getAuthorities());
        return userRepository.save(newUser);
    }

    public boolean isValid(String phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber swissNumberProto = null;
        try {
            swissNumberProto = phoneUtil.parse(phone, "CM");
        } catch (NumberParseException e) {
            LOGGER.error("NumberParseException was thrown: {} ", e.toString());
        }
        return phoneUtil.isValidNumber(swissNumberProto);
    }

    @Override
    public User update(String userCode, UserUpdateDto userDTO) {
        User retrieveUser = userRepository.findUserByUsrCode(userCode);
        if(retrieveUser == null) throw  new UsernameNotFoundException("No user found by userCode " + userCode);

        retrieveUser.setModifDate(LocalDateTime.now());
        retrieveUser.setUsrPrenom(userDTO.getUsrPrenom());
        retrieveUser.setUsrNom(userDTO.getUsrNom());
        retrieveUser.setUsrEmail(userDTO.getUsrEmail());
        retrieveUser.setUsrEmailRec(userDTO.getUsrEmailRec());
        retrieveUser.setCountry(userDTO.getCountry());
        retrieveUser.setPhoneNumber(userDTO.getPhoneNumber());

        return userRepository.save(retrieveUser);
    }

    @Override
    public ResponseEntity<User> login(String email, String password) {
        User user = userRepository.findUserByUsrEmail(email);
        if(user == null){
            throw  new UsernameNotFoundException("Invalid email " + email);
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        // checking password
        if (!BCrypt.checkpw(password, user.getUsrPassword()))
            throw new LoginException("Login failed");

        // update user
        user.setLastLoginDate(LocalDateTime.now());
        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Access-Control-Expose-Headers", JWT_TOKEN_HEADER);
        headers.add(JWT_TOKEN_HEADER, jwtProvider.generateJwtToken(userPrincipal));

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @Override
    public void changePassword(String email, String newPass, String oldPass) {
        if (email == null || oldPass == null || newPass ==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            User user = userRepository.findUserByUsrEmail(email);

            if(!BCrypt.checkpw(oldPass, user.getUsrPassword()))
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);

            user.setUsrPassword(Tools.encodePassword(newPass));
            user.setModifDate(LocalDateTime.now());
            user.setUsrLastTimeUpdate(LocalDateTime.now());
            userRepository.save(user);
            emailService.SendMail(user.getUsrNom(), user.getUsrPrenom(), newPass, email);
        }


    @Override
    public void forgotPassword(String email) {
        if (email == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        User user = userRepository.findUserByUsrEmailRec(email);

        if(user == null) throw  new UsernameNotFoundException("No user found by email " + email);

        String password = GenerateCodeUtils.generatePassword();

        emailService.SendMail(user.getUsrNom(), user.getUsrPrenom(), password, email);

        user.setUsrPassword(Tools.encodePassword(password));
        user.setModifDate(LocalDateTime.now());
        user.setUsrLastTimeUpdate(LocalDateTime.now());
        user.setModifPassword(true);
        userRepository.save(user);
    }

    @Override
    public User findByUserCode(String code) {
        User retrieveUser = userRepository.findUserByUsrCode(code);
        if(retrieveUser == null) throw  new UsernameNotFoundException("No user found by userCode " + code);
        return retrieveUser;
    }

}
