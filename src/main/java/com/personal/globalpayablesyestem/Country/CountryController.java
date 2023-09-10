package com.personal.globalpayablesyestem.Country;


import com.personal.globalpayablesyestem.Bank.validators.BankIdMustExist;
import com.personal.globalpayablesyestem.Country.Utils.CountryEndpointUtils;
import com.personal.globalpayablesyestem.Country.validators.CountryIdMustExist;
import com.personal.globalpayablesyestem.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/private/admin")
@Validated
public class CountryController {

    private final CountryService countryService;

    @GetMapping(value = CountryEndpointUtils.GET_ALL_COUNTRIES)
    public ResponseEntity<ApiResponse> getCountries() {
        return new ResponseEntity<>(new ApiResponse("Success",countryService.getCountries(), null), HttpStatus.OK);
    }

    @PostMapping(value = CountryEndpointUtils.ADD_COUNTRY)
    public ResponseEntity<ApiResponse> addCountry(@RequestBody Country country){
        return new ResponseEntity<>(new ApiResponse("Success",countryService.addCountry(country),null), HttpStatus.OK);
    }

    @GetMapping(value = CountryEndpointUtils.GET_COUNTRY)
    public ResponseEntity<ApiResponse> getCountry(@PathVariable @CountryIdMustExist String countryId) {
        return new ResponseEntity<>(new ApiResponse("Success", countryService.getCountry(countryId), null), HttpStatus.OK);
    }


    @PutMapping(value = CountryEndpointUtils.UPDATE_COUNTRY)
    public ResponseEntity<ApiResponse> updateCountry(@PathVariable @CountryIdMustExist String countryId,
                                                  @RequestBody Country country) {
        return new ResponseEntity<>(new ApiResponse("Success", countryService.updateCountry(countryId, country), null), HttpStatus.OK);
    }


    @DeleteMapping(value = CountryEndpointUtils.DELETE_COUNTRY)
    public ResponseEntity<ApiResponse> deleteCountry(@PathVariable @CountryIdMustExist String countryId,
                                                     @PathVariable @BankIdMustExist String bankId) {
        countryService.deleteCountry(countryId, bankId);
        return new ResponseEntity<>(new ApiResponse("Success", null , null), HttpStatus.OK);
    }

}
