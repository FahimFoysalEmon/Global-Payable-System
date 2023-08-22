package com.personal.globalpayablesyestem.Bank;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/private")
public class BankController {

    @GetMapping(value = "/banks")
    public ResponseEntity<String> getBank() {
        return ResponseEntity.ok("Demo all banks");
    }

}
