package com.ufi.euing.client.api;


import com.ufi.euing.client.business.UserService;
import com.ufi.euing.client.dto.LoginDto;
import com.ufi.euing.client.dto.UserCreateDTO;
import com.ufi.euing.client.dto.UserUpdateDto;
import com.ufi.euing.client.entity.User;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.exceptions.domain.EmailExistException;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class AuthApi {

    private static final Logger log = LoggerFactory.getLogger(AuthApi.class);

    final UserService userService;

    public AuthApi(UserService userService) {
        super();
        this.userService = userService;
    }



    @PostMapping("/create")
    public ResponseEntity<EuingApiResponse<User>> create(@RequestBody UserCreateDTO dto) {
        log.info("[POST] /api/user/create");
        EuingApiResponse<User> payload = new EuingApiResponse<>();
        try {
            return ResponseEntity.ok(payload.success(userService.create(dto)));
        }catch (EuingServiceException  | EmailExistException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()),HttpStatus.valueOf(500));
        }
    }


    @GetMapping("/user/{code}")
    public ResponseEntity<EuingApiResponse<User>> findUser(@PathVariable String code) {
        log.info("[POST] /api/user/find");
        EuingApiResponse<User> payload = new EuingApiResponse<>();
        try {
            return ResponseEntity.ok(payload.success(userService.findByUserCode(code)));
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.valueOf(e.getStatus()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto dto) {
        log.info("[POST] /api/user/login");
        try {
            return userService.login(dto.getEmail(), dto.getPassword());
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.valueOf(e.getStatus()));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam String email, @RequestParam String newPass, @RequestParam String oldPass) {
        log.info("[POST] /api/user/change-password");
        userService.changePassword(email,newPass, oldPass);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        log.info("[POST] /api/user/forgot-password");
        userService.forgotPassword(email);
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }

    @PostMapping("/update/{code}")
    public ResponseEntity<EuingApiResponse<User>> update(@PathVariable String code, @RequestBody UserUpdateDto dto) {
        log.info("[POST] /api/user/update");
        EuingApiResponse<User> payload = new EuingApiResponse<>();
        try {
            return ResponseEntity.ok(payload.success(userService.update(code, dto)));
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.valueOf(e.getStatus()));
        }

    }

}
