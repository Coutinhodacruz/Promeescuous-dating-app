package africa.semicolon.promeescuous.controller;


import africa.semicolon.promeescuous.dto.request.FindUserRequest;
import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import africa.semicolon.promeescuous.dto.response.GetUserResponse;
import africa.semicolon.promeescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promeescuous.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findById")
    public ResponseEntity<GetUserResponse> getUserById(@RequestBody FindUserRequest request){
        long id = request.getId();
        GetUserResponse response = userServices.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<GetUserResponse>> getAllUser(@RequestBody FindUserRequest request){
        int page = request.getPage();
        int pageSize = request.getPageSize();
        List<GetUserResponse> response = userServices.getAllUsers(page,pageSize);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }



}








