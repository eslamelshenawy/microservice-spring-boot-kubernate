package com.example.dbservice.service;

import com.example.dbservice.dto.LoginRequest;
import com.example.dbservice.exceptions.ErrorCodes;
import com.example.dbservice.exceptions.RuntimeBusinessException;
import com.example.dbservice.model.Language;
import com.example.dbservice.model.User;
import com.example.dbservice.procedure.LoginProcedure;
import com.example.dbservice.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static com.example.dbservice.config.Translator.translate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private LanguageRepository repository;
    @Autowired
    private LoginProcedure loginProcedure;

    public List<User> Login(LoginRequest loginRequest) {

        LOGGER.info("entry login ", ".......login function");

        // Validate the login request.
//        String message = repository.executeNativeQuery();

        String message = loginProcedure.validateLogin(loginRequest);
//        validateLoginRequest(loginRequest);

        List<User> users = new ArrayList<>();
        return users;
    }

    public static void validateLoginRequest(LoginRequest loginRequest) throws RuntimeBusinessException {

        if (loginRequest.getUsername() == null) {
            throw new RuntimeBusinessException(NOT_ACCEPTABLE, ErrorCodes.valueOf(translate("message.userNameError")), loginRequest.getUsername());
        }

        if (loginRequest.getPassword() == null) {
            throw new RuntimeBusinessException(NOT_ACCEPTABLE, ErrorCodes.valueOf(translate("message.passwordError")), loginRequest.getUsername());
        }
    }


}
