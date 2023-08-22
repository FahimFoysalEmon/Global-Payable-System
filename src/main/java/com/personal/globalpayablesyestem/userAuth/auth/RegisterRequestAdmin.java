package com.personal.globalpayablesyestem.userAuth.auth;

import com.personal.globalpayablesyestem.userAuth.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestAdmin {

    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPassword;
    private Role role;

}
