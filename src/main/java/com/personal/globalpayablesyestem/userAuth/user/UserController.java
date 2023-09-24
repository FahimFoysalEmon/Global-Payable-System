package com.personal.globalpayablesyestem.userAuth.user;

import com.personal.globalpayablesyestem.common.ApiResponse;
import com.personal.globalpayablesyestem.userAuth.user.userEndpointUtil.UserEndpointUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping(UserEndpointUtils.ALL_USERS)
    public ResponseEntity<ApiResponse> getAllUsers(){
        return new ResponseEntity<>(new ApiResponse("Success",userService.getAllUsers(),null), HttpStatus.OK);
    }


    @GetMapping(UserEndpointUtils.ALL_COUNTRIES)
    public ResponseEntity<ApiResponse> getAllCountries(){
        return new ResponseEntity<>(new ApiResponse("Success", userService.getAllCountries(), null), HttpStatus.OK);
    }


}
