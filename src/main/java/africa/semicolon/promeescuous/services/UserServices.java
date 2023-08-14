package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.ApiResponse;
import africa.semicolon.promeescuous.dto.response.GetUserResponse;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promeescuous.model.User;

import java.util.List;

public interface UserServices {
   RegisterUserResponse register(RegisterUserRequest registerUserRequest);

   ApiResponse<?> activateUserAccount(String token);

   GetUserResponse getUserById(Long id);


   List<GetUserResponse> getAllUsers(int page, int pageSize);
}
