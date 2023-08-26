package vmware.services.gateway.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException;
}
