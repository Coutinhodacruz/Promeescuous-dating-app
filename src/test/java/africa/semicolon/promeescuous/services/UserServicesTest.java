package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.ApiResponse;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserServices userServices;

    private RegisterUserRequest registerUserRequest;

    @BeforeEach
    void setUp(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("xogov34623@vreaa.com");
        registerUserRequest.setPassword("password");
    }

    @Test
    public void testThatUserCanRegister(){
        // User submit form by calling register method
        RegisterUserResponse registerUserResponse = userServices.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount(){
        RegisterUserResponse response = userServices.register(registerUserRequest);
        assertNotNull(response);

        ApiResponse<?> activateUserAccountResponse =
                userServices.activateUserAccount("abc1234.erytuuoi.67t75646");

        assertThat(activateUserAccountResponse).isNotNull();


    }
}
