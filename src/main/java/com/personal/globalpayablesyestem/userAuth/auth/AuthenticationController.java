package com.personal.globalpayablesyestem.userAuth.auth;

import com.personal.globalpayablesyestem.country.validators.CountryIdMustExist;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/country/{countryId}/register")
    public ResponseEntity<AuthenticationResponse> register(
            @PathVariable @CountryIdMustExist String countryId,
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(countryId,request));
    }

    @PostMapping("/country/{countryId}/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @PathVariable @CountryIdMustExist String countryId,
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.registerAdmin(countryId, request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        try{
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch (Exception e) {
            throw new CredentialMisMatchError("Invalid Credentials");
        }
    }

}
