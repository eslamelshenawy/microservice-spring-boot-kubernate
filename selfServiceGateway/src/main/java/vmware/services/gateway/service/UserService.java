package vmware.services.gateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vmware.services.gateway.config.auth.AuthenticateRequest;
import vmware.services.gateway.config.auth.JwtAuthenticationResponse;
import vmware.services.gateway.config.auth.UserPrincipal;
import vmware.services.gateway.entity.User;
import vmware.services.gateway.exceptions.RuntimeBusinessException;
import vmware.services.gateway.repository.UserRepository;
import vmware.services.gateway.response.Response;

import static vmware.services.gateway.exceptions.ErrorCodes.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {RuntimeBusinessException.class, Exception.class})
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  JWTTokenProvider jwtTokenProvider;
    private  final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public  ResponseEntity Login(AuthenticateRequest authenticateRequest, String lang) {
        LOGGER.info("entry login ", ".......login function");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword()));
        String token =jwtTokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
        log.info("Token Created {}",token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

}
