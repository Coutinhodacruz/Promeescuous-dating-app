package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promeescuous.model.User;
import africa.semicolon.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserServices{
    private final UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        // Extract details from the
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();

        // Create a user profile with the registration details
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // Save that user profile in the Database
        User savedUser = userRepository.save(user);
        log.info("saved guy -> {}", savedUser);

        //
        String emailResponse = MockEmailService.sendEmail(savedUser.getEmail());
        log.info("email sending response -> {}", emailResponse);

        // Return a response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration Successful, check your email inbox for verification");
        return registerUserResponse;
    }
}
