package vmware.services.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vmware.services.gateway.config.auth.AuthenticateRequest;
import vmware.services.gateway.dto.LoginRequest;
import vmware.services.gateway.service.UserService;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @PostMapping("/Login")
    public ResponseEntity Login(@RequestBody LoginRequest loginRequest , @RequestHeader("Accept-Language") String lang ,@RequestBody AuthenticateRequest authenticateRequest)  {
        return UserService.Login2(loginRequest ,lang ,authenticateRequest);
    }

}
