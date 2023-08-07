package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;

public interface UserServices {
   RegisterUserResponse register(RegisterUserRequest registerUserRequest);
}
