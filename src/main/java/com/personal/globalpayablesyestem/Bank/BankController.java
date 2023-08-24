package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.utils.BankAndBranchEndpointUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping(value = BankAndBranchEndpointUtils.ADD_BANK)
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank,
                                        @AuthenticationPrincipal OAuth2ResourceServerProperties.Jwt jwt) {
        return new ResponseEntity<>(bankService.addBank(bank), HttpStatus.OK);
    }

}
