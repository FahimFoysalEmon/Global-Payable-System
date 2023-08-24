package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.utils.BankAndBranchEndpointUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/private")
@Validated
public class BankController {

    private final BankService bankService;

    @PostMapping(value = BankAndBranchEndpointUtils.ADD_BANK)
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        return new ResponseEntity<>(bankService.addBank(bank), HttpStatus.OK);
    }

}
