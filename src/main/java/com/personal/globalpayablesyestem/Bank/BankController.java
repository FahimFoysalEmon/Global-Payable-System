package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.utils.BankAndBranchEndpointUtils;
import com.personal.globalpayablesyestem.Bank.validators.BankIdMustExist;
import com.personal.globalpayablesyestem.Country.validators.CountryIdMustExist;
import com.personal.globalpayablesyestem.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/private/admin")
@Validated
public class BankController {

    private final BankService bankService;

    @GetMapping(value = BankAndBranchEndpointUtils.GET_ALL_BANKS)
    public ResponseEntity<ApiResponse> getBanks() {
        return new ResponseEntity<>(new ApiResponse("Success",bankService.getBanks(), null), HttpStatus.OK);
    }

    @PostMapping(value = BankAndBranchEndpointUtils.ADD_BANK)
    public ResponseEntity<ApiResponse> addBank(@PathVariable @CountryIdMustExist String countryId , @RequestBody Bank bank){
        return new ResponseEntity<>(new ApiResponse("Success",bankService.addBank(countryId,bank),null), HttpStatus.OK);
    }

    @GetMapping(value = BankAndBranchEndpointUtils.GET_BANK)
    public ResponseEntity<ApiResponse> getBank(@PathVariable @CountryIdMustExist String countryId,
                                               @PathVariable @BankIdMustExist String bankId) {
        return new ResponseEntity<>(new ApiResponse("Success", bankService.getBank(countryId, bankId), null), HttpStatus.OK);
    }


    @PutMapping(value = BankAndBranchEndpointUtils.UPDATE_BANK)
    public ResponseEntity<ApiResponse> updateBank(@PathVariable @BankIdMustExist String bankId,
                                                  @RequestBody Bank bank) {
        return new ResponseEntity<>(new ApiResponse("Success", bankService.updateBank(bankId, bank), null), HttpStatus.OK);
    }


    @DeleteMapping(value = BankAndBranchEndpointUtils.DELETE_BANK)
    public ResponseEntity<ApiResponse> deleteBank(@PathVariable @CountryIdMustExist String countryId,
                                                  @PathVariable @BankIdMustExist String bankId) {
        bankService.deleteBank(countryId,bankId);
        return new ResponseEntity<>(new ApiResponse("Success", null , null), HttpStatus.OK);
    }

}
