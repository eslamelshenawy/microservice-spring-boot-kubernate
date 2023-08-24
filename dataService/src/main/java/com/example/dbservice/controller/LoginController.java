package com.example.dbservice.controller;

import com.example.dbservice.dto.LoginRequest;
import com.example.dbservice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/Login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;

    @PostMapping("/")
    public Map<String, Object> Login(@RequestBody LoginRequest loginRequest , @RequestHeader("Accept-Language") String lang)  {
        return loginService.Login(loginRequest ,lang);
    }

}
