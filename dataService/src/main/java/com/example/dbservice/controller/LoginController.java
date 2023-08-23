package com.example.dbservice.controller;

import com.example.dbservice.dto.LoginRequest;
import com.example.dbservice.model.Language;
import com.example.dbservice.model.User;
import com.example.dbservice.service.LanguageService;
import com.example.dbservice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/Login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;

    @PostMapping("/")
    public List<User> Login(@RequestBody LoginRequest loginRequest)  {
        return loginService.Login(loginRequest);
    }

}
