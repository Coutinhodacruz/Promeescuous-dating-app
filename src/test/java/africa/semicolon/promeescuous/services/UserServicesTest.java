package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.ApiResponse;
import africa.semicolon.promeescuous.dto.response.GetUserResponse;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promeescuous.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class UserServicesTest {
    @Autowired
    private UserServices userServices;

    private RegisterUserRequest registerUserRequest;

    private RegisterUserResponse registerUserResponse;

    @BeforeEach
    void setUp(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("coutinhodacruz10@gmail.com");
        registerUserRequest.setPassword("password");

    }

    @Test
    public void testThatUserCanRegister(){
        // User submit form by calling register method
        registerUserResponse = userServices.register(registerUserRequest);

        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount(){
        registerUserRequest.setEmail("test@gmail.com");
        registerUserResponse = userServices.register(registerUserRequest);
        assertNotNull(registerUserResponse);

        ApiResponse<?> activateUserAccountResponse =
                userServices.activateUserAccount("abc1234.erytuuoi.67t75646");

        assertThat(activateUserAccountResponse).isNotNull();

    }

    @Test

    public void getUserByIdTest(){
        userServices.register(registerUserRequest);
        GetUserResponse response =userServices.getUserById(1L);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(registerUserRequest.getEmail());


    }

    @Test
    public void getAllUsers(){
        registerTestUsers();

        List<GetUserResponse> users = userServices.getAllUsers(1, 5);
        assertThat(users).isNotNull();
        log.info("users-->{}", users);
        assertThat(users.size()).isEqualTo(5);
    }

    private void registerTestUsers() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("coutinho@gmail.com");
        request.setPassword("password");
        userServices.register(request);

        request.setEmail("boy@gmail.com");
        request.setPassword("password");
        userServices.register(request);

        request.setEmail("love@gmail.com");
        request.setPassword("password");
        userServices.register(request);

        request.setEmail("hate@gmail.com");
        request.setPassword("password");
        userServices.register(request);

        request.setEmail("life@gmail.com");
        request.setPassword("password");
        userServices.register(request);

        request.setEmail("freedom@gmail.com");
        request.setPassword("password");
        userServices.register(request);
    }
}
