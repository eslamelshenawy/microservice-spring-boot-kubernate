package vmware.services.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Response<User>> addUser(User input) {
        // Check if the email already exists in the database
        Optional<User> existingUser = userRepository.findByEmail(input.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeBusinessException(NOT_ACCEPTABLE, U$0002, input.getEmail());
        }
        input.setPassword(new BCryptPasswordEncoder().encode(input.getPassword()));
        User user = userRepository.save(input);
        Response<User> response = Response.<User>builder().ResponseMessage("success add User").data(user).ResponseCode(200).build();
        return ResponseEntity.ok(response);
    }

}
