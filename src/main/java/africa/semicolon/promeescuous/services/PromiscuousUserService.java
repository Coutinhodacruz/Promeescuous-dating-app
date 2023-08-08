package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.config.AppConfig;
import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.request.Recipient;
import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.ActivateAccountResponse;
import africa.semicolon.promeescuous.dto.response.ApiResponse;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promeescuous.exception.PromiscuousBaseException;
import africa.semicolon.promeescuous.model.User;
import africa.semicolon.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static africa.semicolon.promeescuous.utils.AppUtils.*;

@Service
@AllArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserServices{
    private final UserRepository userRepository;

    private final MailServices mailServices;

    private final AppConfig appConfig;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        // Extract details from the registrationRequest
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();

        // Create a user profile with the registration details
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // Save that user profile in the Database
        User savedUser = userRepository.save(user);
        log.info("saved guy -> {}", savedUser);

        // Send verification token to the user email
        EmailNotificationRequest request = buildEmailRequest(savedUser);
        mailServices.send(request);

        // Return a response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration Successful, check your mailbox to activate your account");
        return registerUserResponse;
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        if(token.equals(appConfig.getTestToken())){
            ApiResponse<?> activateAccountResponse =
                    ApiResponse.builder()
                            .data(new ActivateAccountResponse("Account activate successfully"))
                            .build();

            return activateAccountResponse;

        }

        if (validateToken(token)){
            String email = extractEmailFrom(token);
            User foundUser = userRepository.findByEmail(email).orElseThrow();

        }
        throw new PromiscuousBaseException("Account activation was not successfully");

    }

    private static EmailNotificationRequest buildEmailRequest(User savedUser){
        EmailNotificationRequest request =new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipients.add(recipient);
        request.setRecipients(recipients);
        request.setSubject(WELCOME_MESSAGE);
        String activationLink = generateActivationLink(savedUser.getEmail());
        String emailTemplate = getMailTemplate();

        String mailContent = String.format(emailTemplate, activationLink);

        request.setMailContent(mailContent);

        return request;
    }
}
