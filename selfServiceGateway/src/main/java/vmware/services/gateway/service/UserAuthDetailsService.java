package vmware.services.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import vmware.services.gateway.client.DBClient;
import vmware.services.gateway.dto.LoginRequest;
import vmware.services.gateway.entity.User;
import vmware.services.gateway.config.auth.UserPrincipal;
import vmware.services.gateway.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserPrincipal loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + s + "Not Found in DB"));
        return UserPrincipal.create(user);

    }
}
