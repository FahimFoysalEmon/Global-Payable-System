package com.personal.globalpayablesyestem.auth;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
