package com.personal.globalpayablesyestem.Bank;

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

    @GetMapping(value = "/banks")
    public ResponseEntity<String> getBank() {
        return ResponseEntity.ok("Demo all banks");
    }

    @PostMapping(value = "/bank")
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        return new ResponseEntity<>(bankService.addBank(bank), HttpStatus.OK);
    }

}
