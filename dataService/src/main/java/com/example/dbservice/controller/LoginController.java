package com.example.dbservice.controller;

import com.example.dbservice.dto.LoginRequest;
import com.example.dbservice.model.Employee;
import com.example.dbservice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;

    @PostMapping("/Login")
    public Map<String, Object> Login(@RequestBody LoginRequest loginRequest , @RequestHeader("Accept-Language") String lang)  {
        return loginService.Login(loginRequest ,lang);
    }
    @PostMapping("/alter")
    public void SetSession(@RequestHeader("Accept-Language") String lang ,@RequestParam("p_user_id") Integer pUserId )  {
        loginService.setSession(lang, pUserId);
    }

    @GetMapping(value ="/info/{pUserId}")
    public Employee getInfo(@RequestHeader("Accept-Language") String lang ,@PathVariable Integer pUserId){
        return loginService.getInfo(lang,pUserId);
    }


}
