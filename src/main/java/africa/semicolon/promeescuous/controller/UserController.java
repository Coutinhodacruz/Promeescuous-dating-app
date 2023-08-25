package africa.semicolon.promeescuous.controller;


import africa.semicolon.promeescuous.dto.request.*;
import africa.semicolon.promeescuous.dto.response.*;
import africa.semicolon.promeescuous.services.UserServices;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserServices userServices;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest registerUserRequest){
        var response = userServices.register(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Long id){
        GetUserResponse response = userServices.getUserById(id);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<GetUserResponse>> getAllUser(@RequestBody FindUserRequest request){
        int page = request.getPage();
        int pageSize = request.getPageSize();
        List<GetUserResponse> response = userServices.getAllUsers(page,pageSize);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse>updateUserProfile(@ModelAttribute UpdateUserRequest updateUserRequest,@PathVariable Long id) throws JsonPatchException {
        UpdateUserResponse response = userServices.updateProfile(updateUserRequest, id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/uploadMedia")
    public ResponseEntity<UploadMediaResponse> uploadMedia(@ModelAttribute UploadMediaRequest mediaRequest){
        MultipartFile mediaToUpload = mediaRequest.getMedia();
        UploadMediaResponse response = userServices.uploadMedia(mediaToUpload);
        return ResponseEntity.ok(response);
    }
    @PostMapping("uploadProfilePicture")
    public ResponseEntity<UploadMediaResponse> uploadProfilePicture(@ModelAttribute UploadMediaRequest mediaRequest){
        MultipartFile mediaToUpload = mediaRequest.getMedia();
        UploadMediaResponse response = userServices.uploadProfilePicture(mediaToUpload);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/react/{id}")
    public ResponseEntity<?> reactToMedia(@RequestBody MediaReactionRequest mediaReactionRequest){
        ApiResponse<?> response = userServices.reactToMedia(mediaReactionRequest);
        return ResponseEntity.ok(response);
    }


}








