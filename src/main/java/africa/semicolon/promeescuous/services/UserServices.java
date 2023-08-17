package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.LoginRequest;
import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.request.UpdateUserRequest;
import africa.semicolon.promeescuous.dto.response.*;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface UserServices {
   RegisterUserResponse register(RegisterUserRequest registerUserRequest);

   ApiResponse<?> activateUserAccount(String token);

   GetUserResponse getUserById(Long id);


   List<GetUserResponse> getAllUsers(int page, int pageSize);


   LoginResponse login(LoginRequest loginRequest);

   UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id);

//   UpdateUserResponse updateUserProfile(JsonPatch jsonPatch, Long id);
}
