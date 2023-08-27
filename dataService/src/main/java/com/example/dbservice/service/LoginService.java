package com.example.dbservice.service;

import com.example.dbservice.dto.LoginRequest;
import com.example.dbservice.model.Employee;
import com.example.dbservice.procedure.LoginProcedure;
import com.example.dbservice.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private LanguageRepository repository;
    @Autowired
    private LoginProcedure loginProcedure;

    public Map<String, Object> Login(LoginRequest loginRequest, String lang) {
        LOGGER.info("entry login ", ".......login function");
        Map<String, Object> result = loginProcedure.validateLogin(loginRequest, lang);
        return result;
    }

    public void setSession(String lang, Integer pUserId) {
        LOGGER.info("set session ", ".......Set Session");
        loginProcedure.alterSession(lang, pUserId);
    }

    public Employee getInfo(String lang,Integer pUserId) {
        LOGGER.info("get info ", ".......get info");
        return loginProcedure.getInfo(lang,pUserId);
    }
}
