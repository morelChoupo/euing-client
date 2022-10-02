package com.ufi.euing.client.business;

import com.ufi.euing.client.dto.UserCreateDTO;
import com.ufi.euing.client.dto.UserUpdateDto;
import com.ufi.euing.client.entity.User;
import com.ufi.euing.client.exceptions.domain.EmailExistException;
import org.springframework.http.ResponseEntity;



public interface UserService {

    User create(UserCreateDTO user) throws EmailExistException;

    User update(String userCode, UserUpdateDto userDTO);

    ResponseEntity<User> login(String principal, String password);

    void changePassword(String email,String newPass, String oldPass);

    void forgotPassword(String email);

    User findByUserCode(String code);




}
