package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.LoginRequest;
import africa.semicolon.promeescuous.dto.request.MediaReactionRequest;
import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.request.UpdateUserRequest;
import africa.semicolon.promeescuous.dto.response.*;
import africa.semicolon.promeescuous.exception.UserNotFoundException;
import africa.semicolon.promeescuous.model.User;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserServices {
   RegisterUserResponse register(RegisterUserRequest registerUserRequest);

   ApiResponse<?> activateUserAccount(String token);

   User findUserById(Long id);

   GetUserResponse getUserById(Long id) throws UserNotFoundException;


   List<GetUserResponse> getAllUsers(int page, int pageSize);

   UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id) throws JsonPatchException;

   UploadMediaResponse uploadMedia(MultipartFile mediaToUpload);

   UploadMediaResponse uploadProfilePicture(MultipartFile mediaToUpload);

   ApiResponse<?> reactToMedia(MediaReactionRequest mediaReactionRequest);

//   UpdateUserResponse updateUserProfile(JsonPatch jsonPatch, Long id);
}
