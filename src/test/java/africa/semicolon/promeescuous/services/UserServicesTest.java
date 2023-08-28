package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.request.UpdateUserRequest;
import africa.semicolon.promeescuous.dto.response.ApiResponse;
import africa.semicolon.promeescuous.dto.response.GetUserResponse;
import africa.semicolon.promeescuous.dto.response.UpdateUserResponse;
import africa.semicolon.promeescuous.exception.PromiscuousBaseException;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static africa.semicolon.promeescuous.utils.AppUtils.BLANK_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
@Sql(scripts = {"/db/insert.sql"})
public class UserServicesTest {
    @Autowired
    private UserServices userServices;


    @Test
    public void testThatUserCanRegister(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("dominicrotimi@gmail.com");
        registerUserRequest.setPassword("password");
        var registerUserResponse = userServices.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount(){
        ApiResponse<?> activateUserAccountResponse =
                userServices.activateUserAccount("abc1234.erytuuoi.67t75646");

        assertThat(activateUserAccountResponse).isNotNull();
    }

//    @Test
//    public void testThatExceptionIsThrownWhenUserAuthenticatesWithBadCredentials(){
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("test@email.com");
//        loginRequest.setPassword("bad_password");
//
//
//        assertThatThrownBy(()->userServices.login(loginRequest))
//                .isInstanceOf(BadCredentialsException.class);
//
//    }
//
//
//    @Test
//    public void testThatUsersCanLogin(){
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("test@email.com");
//        loginRequest.setPassword("password");
//
//        LoginResponse response = userServices.login(loginRequest);
//        assertThat(response).isNotNull();
//        String accessToken = response.getAccessToken();
//        assertThat(accessToken).isNotNull();
//    }

    @Test
    public void getUserByIdTest(){
        GetUserResponse response =userServices.getUserById(500L);
        assertThat(response).isNotNull();

    }


    @Test
    public void getAllUsers(){
        List<GetUserResponse> users = userServices.getAllUsers(1, 5);
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(5);
    }

    @Test
    public void tesThatUserCanUpdateAccount() throws JsonPatchException {
        UpdateUserRequest updateUserRequest = buildUpdateRequest();
        UpdateUserResponse response = userServices.updateProfile(updateUserRequest, 500L);
        assertThat(response).isNotNull();
        GetUserResponse userResponse = userServices.getUserById(500L);
        String fullName = userResponse.getFullName();
        String expectedFullName = new StringBuilder().append(updateUserRequest.getFirstName())
                        .append(BLANK_SPACE)
                        .append(updateUserRequest.getLastName())
                        .toString();

        assertThat(fullName).isEqualTo(expectedFullName);

    }

    private UpdateUserRequest buildUpdateRequest() {
        Set<String> interests = Set.of("Swimming", "Sports", "Cooking");
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(500L);
        updateUserRequest.setDateOfBirth(LocalDate.of(2005, Month.OCTOBER.ordinal(), 25));
        updateUserRequest.setFirstName("Boy");
        updateUserRequest.setLastName("Girl");
        updateUserRequest.setPassword("password");
        updateUserRequest.setInterests(interests);
        MultipartFile testImage = getTestImage();
        updateUserRequest.setProfileImage(testImage);


        return updateUserRequest;
    }

    private MultipartFile getTestImage(){
        // Obtain a path that points to test image
        Path path = Paths.get("C:\\Users\\Admin\\promeescuous\\src\\test\\resources\\image\\trent.jpg");
        // Create stream that can read from file pointed to by path
        try(InputStream inputStream = Files.newInputStream(path)){
            // Create a MultiPathFile using bytes from file pointed to b path
           MultipartFile image = new MockMultipartFile("test_image", inputStream);
           return image;
        }catch (Exception exception){
           throw new PromiscuousBaseException(exception.getMessage());
        }
    }


}
