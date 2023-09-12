package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.branch.account.utils.endpoint.AccountEndpointUtils;
import com.personal.globalpayablesyestem.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/private/user")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(AccountEndpointUtils.ADD_ACCOUNT)
    public ResponseEntity<ApiResponse> addAccount(@PathVariable String branchId){
        return new ResponseEntity<>(new ApiResponse("Success", accountService.addAccount(branchId), null), HttpStatus.OK);
    }

}
