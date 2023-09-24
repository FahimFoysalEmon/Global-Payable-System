package com.personal.globalpayablesyestem.userAuth.user;

import com.personal.globalpayablesyestem.country.Country;
import com.personal.globalpayablesyestem.country.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
