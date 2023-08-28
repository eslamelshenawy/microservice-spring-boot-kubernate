package vmware.services.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vmware.services.gateway.client.DBClient;
import vmware.services.gateway.config.auth.AuthenticateRequest;
import vmware.services.gateway.config.auth.JwtAuthenticationResponse;
import vmware.services.gateway.config.auth.UserPrincipal;
import vmware.services.gateway.dto.LoginRequest;
import vmware.services.gateway.entity.User;
import vmware.services.gateway.exceptions.ErrorCodes;
import vmware.services.gateway.exceptions.RuntimeBusinessException;
import vmware.services.gateway.service.JWTTokenProvider;
import vmware.services.gateway.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static vmware.services.gateway.exceptions.ErrorCodes.U$0002;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private DBClient dbClient;

    @PostMapping("/Login")
    public ResponseEntity signIn(@RequestBody LoginRequest loginRequest, @RequestHeader("Accept-Language") String lang) {

        Map<String, Object> loginData = dbClient.login(loginRequest, lang);

        Object employeeDataObject = loginData.get("employeeData");

        Object result = loginData.get("result");

        log.info("employeeDataObject Created {}", employeeDataObject);

        Object user_id = loginData.get("p_user_id");

        Long user_id_long = Long.parseLong(user_id.toString());

        log.info("user_id Created {}", user_id);

        if (user_id_long == 0) {

            log.info("RuntimeBusinessException {}", result);
            throw new RuntimeBusinessException(String.valueOf(result));
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(loginRequest.getPassword());
        User u1 = new User();
        u1.setId(user_id_long);
        u1.setPassword(encodedPassword);
        UserPrincipal user = new UserPrincipal(u1);

        String token = jwtTokenProvider.generateToken((UserPrincipal) user);
        log.info("Token Created {}", token);
        Map<String, Object> employeeDataMap = new HashMap<>();
        employeeDataMap.put("employee", employeeDataObject);
        employeeDataMap.put("token", new JwtAuthenticationResponse(token));
        log.info("employee {}", employeeDataMap);
        return ResponseEntity.ok(employeeDataMap);
    }
}
