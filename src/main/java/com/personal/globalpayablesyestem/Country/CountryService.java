package com.personal.globalpayablesyestem.Country;

import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country addCountry(Country country) {
        if (countryRepository.existsByName(country.getName())) {
            throw new AlreadyExistException("This country already exists");
        }
        return countryRepository.save(country);
    }

    public Country getCountry(String countryId) {
        return countryRepository.findById(countryId).get();
    }

    public Country updateCountry(String countryId, Country country) {
        Country countryToBeUpdated = countryRepository.findById(countryId).get();
        if (countryToBeUpdated.getName().equals(country.getName())) {
            throw new AlreadyExistException("This country name already exists");
        }
        BeanUtils.copyProperties(country, countryToBeUpdated);
        countryToBeUpdated.setId(countryId);
        return countryRepository.save(countryToBeUpdated);
    }

    public void deleteCountry(String countryId) {
        countryRepository.deleteById(countryId);
    }
}
