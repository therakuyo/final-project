package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.UserEntity;
import textbasedgame.finalproject.exceptions.UserAlreadyExistsException;
import textbasedgame.finalproject.repositories.UserRepository;
import textbasedgame.finalproject.request.SignupRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void add(SignupRequest signupRequest) throws UserAlreadyExistsException {

        if (this.userRepository.existsByEmail(signupRequest.getEmail())) {

            throw new UserAlreadyExistsException("User already exists", signupRequest.getEmail());
        }

        UserEntity user = new UserEntity();

        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());

        String encodedPassword = new BCryptPasswordEncoder().encode(signupRequest.getPassword());
        user.setPassword(encodedPassword);

        user.setBirthDate(signupRequest.getBirthDate());
        user.setAge(signupRequest.getAge());
        user.setRoles(signupRequest.getRoles());

        this.userRepository.save(user);

    }

}
