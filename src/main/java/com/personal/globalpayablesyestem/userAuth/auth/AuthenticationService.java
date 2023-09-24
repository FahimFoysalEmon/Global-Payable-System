package com.personal.globalpayablesyestem.userAuth.auth;

import com.personal.globalpayablesyestem.country.CountryRepository;
import com.personal.globalpayablesyestem.userAuth.config.JwtService;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import com.personal.globalpayablesyestem.userAuth.user.UserRepository;
import com.personal.globalpayablesyestem.userAuth.user.User;
import com.personal.globalpayablesyestem.userAuth.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final CountryRepository countryRepository;

    public AuthenticationResponse register(RegisterRequest request) {

        if (!countryRepository.existsByName(request.getCountry())) {
            throw new CredentialMisMatchError("No country with this name");
        }

        var user = User.builder()
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .userEmail(request.getUserEmail())
                .country(request.getCountry())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public AuthenticationResponse registerAdmin(RegisterRequest requestAdmin) {

        if (!countryRepository.existsByName(requestAdmin.getCountry())) {
            throw new CredentialMisMatchError("No country with this name");
        }

        var user = User.builder()
                .userName(requestAdmin.getUserName())
                .userPhone(requestAdmin.getUserPhone())
                .userEmail(requestAdmin.getUserEmail())
                .country(requestAdmin.getCountry())
                .userPassword(passwordEncoder.encode(requestAdmin.getUserPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUserEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
