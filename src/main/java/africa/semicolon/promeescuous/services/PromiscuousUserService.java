package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.config.AppConfig;
import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.request.Recipient;
import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.ActivateAccountResponse;
import africa.semicolon.promeescuous.dto.response.ApiResponse;
import africa.semicolon.promeescuous.dto.response.GetUserResponse;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promeescuous.exception.AccountActivationException;
import africa.semicolon.promeescuous.exception.UserNotFoundException;
import africa.semicolon.promeescuous.model.Address;
import africa.semicolon.promeescuous.model.User;
import africa.semicolon.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static africa.semicolon.promeescuous.dto.response.ResponseMessage.ACCOUNT_ACTIVATION_SUCCESSFUL;
import static africa.semicolon.promeescuous.dto.response.ResponseMessage.USER_REGISTRATION_SUCCESSFUL;
import static africa.semicolon.promeescuous.exception.ExceptionMessage.*;
import static africa.semicolon.promeescuous.utils.AppUtils.*;
import static africa.semicolon.promeescuous.utils.JwtUtils.extractEmailFrom;
import static africa.semicolon.promeescuous.utils.JwtUtils.validateToken;

@Service
@AllArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserServices{
    private final UserRepository userRepository;

    private final MailServices mailServices;

    private final AppConfig appConfig;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(new Address());
        User savedUser = userRepository.save(user);
        EmailNotificationRequest request = buildEmailRequest(savedUser);
        mailServices.send(request);
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage(USER_REGISTRATION_SUCCESSFUL.name());
        return registerUserResponse;
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        boolean isTestToken = token.equals(appConfig.getTestToken());
        if(isTestToken) return activateTestAccount();
        boolean isValidJwt = validateToken(token);

        if (isValidJwt) return activateAccount(token);
        throw new AccountActivationException(ACCOUNT_ACTIVATION_FAILED_EXCEPTION.getMessage());

    }

    @Override
    public GetUserResponse getUserById(Long id) {
        Optional<User> found = userRepository.findById(id);
        User user = found.orElseThrow(
                ()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage())
        );
        GetUserResponse getUserResponse = buildGetUserResponse(user);
        return getUserResponse;
    }

    @Override
    public List<GetUserResponse> getAllUsers(int page, int pageSize) {
        List<GetUserResponse> users = new ArrayList<>();
        Pageable pageable = buildPageRequest(page, pageSize);
        Page<User> usersPage = userRepository.findAll(pageable);
        List<User> foundUsers = usersPage.getContent();

//        for (User user: foundUsers){
//            GetUserResponse getUserResponse = buildGetUserResponse(user);
//            users.add(getUserResponse);
//        }
//        return users
        return foundUsers.stream()
                .map(PromiscuousUserService ::buildGetUserResponse)
                .toList();
    }

    private Pageable buildPageRequest(int page, int pageSize) {
        if (page<1&&pageSize<1)return PageRequest.of(0,10);
        if (page<1)return  PageRequest.of(0,pageSize);
        if (pageSize<1)return PageRequest.of(page,pageSize);
        return PageRequest.of(page-1, pageSize);
    }


    private ApiResponse<?> activateAccount(String token) {
        String email = extractEmailFrom(token);
        Optional<User> user = userRepository.findByEmail(email);
        User foundUser = user.orElseThrow(
                ()-> new UserNotFoundException(
                        String.format(USER_WITH_EMAIL_NOT_FOUND_EXCEPTION.getMessage(), email)
        ));

        foundUser.setActive(true);
        User savedUser = userRepository.save(foundUser);
        GetUserResponse userResponse = buildGetUserResponse(savedUser);
        var activateUserResponse = buildActivationUserResponse(userResponse);
        return ApiResponse.builder().data(activateUserResponse).build();


    }

    private static ActivateAccountResponse buildActivationUserResponse(GetUserResponse userResponse){
        return ActivateAccountResponse.builder()
                .message(ACCOUNT_ACTIVATION_SUCCESSFUL.name())
                .user(userResponse)
                .build();
    }

    private static GetUserResponse buildGetUserResponse(User savedUser){
        return GetUserResponse.builder()
                .id(savedUser.getId())
                .address(savedUser.getAddress().toString())
                .fullName(getFullName(savedUser))
                .phoneNumber(savedUser.getPhoneNumber())
                .email(savedUser.getEmail())
                .build();
    }

    private static String getFullName(User savedUser){
        return savedUser.getFirstName() + BLANK_SPACE + savedUser.getLastName();
    }

    private static ApiResponse<?> activateTestAccount() {
        ApiResponse<?> activateAccountResponse =
                ApiResponse.builder()
                        .build();

        return activateAccountResponse;
    }

    private EmailNotificationRequest buildEmailRequest(User savedUser){
        EmailNotificationRequest request =new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipients.add(recipient);
        request.setRecipients(recipients);
        request.setSubject(WELCOME_MESSAGE);
        String activationLink =
                generateActivationLink(appConfig.getBaseUrl(), savedUser.getEmail());
        String emailTemplate = getMailTemplate();

        String mailContent = String.format(emailTemplate, activationLink);

        request.setMailContent(mailContent);

        return request;
    }
}
