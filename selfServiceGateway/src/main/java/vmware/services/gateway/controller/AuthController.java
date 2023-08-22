package vmware.services.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vmware.services.gateway.config.auth.AuthenticateRequest;
import vmware.services.gateway.config.auth.JwtAuthenticationResponse;
import vmware.services.gateway.config.auth.UserPrincipal;
import vmware.services.gateway.entity.User;
import vmware.services.gateway.exceptions.RuntimeBusinessException;
import vmware.services.gateway.response.Response;
import vmware.services.gateway.service.JWTTokenProvider;
import vmware.services.gateway.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
@Slf4j
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody AuthenticateRequest authenticateRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword()));
        String token =jwtTokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
        log.info("Token Created {}",token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping({"/sign-up"})
    public ResponseEntity<Response<User>>  signupUser(@Valid @RequestBody User user) {
        try {
            return userService.addUser(user);
        } catch (RuntimeBusinessException e) {
            return ResponseEntity.badRequest().body(Response.<User>builder().ResponseMessage(e.getMessage()).build());
        }
    }


}
