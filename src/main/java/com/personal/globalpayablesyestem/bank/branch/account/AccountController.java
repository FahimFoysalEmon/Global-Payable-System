package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.branch.account.utils.endpoint.AccountEndpointUtils;
import com.personal.globalpayablesyestem.bank.branch.account.utils.enums.TypeOfAccount;
import com.personal.globalpayablesyestem.bank.validators.BankIdMustExist;
import com.personal.globalpayablesyestem.bank.validators.BranchIdMustExist;
import com.personal.globalpayablesyestem.common.ApiResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/private/user")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(AccountEndpointUtils.ADD_ACCOUNT)
    public ResponseEntity<ApiResponse> addAccount(@PathVariable @BankIdMustExist String bankId,
                                                  @PathVariable @BranchIdMustExist String branchId,
                                                  @RequestParam @NotEmpty @NotNull String currency,
                                                  @RequestParam @NotEmpty @NotNull String initialDeposit){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(new ApiResponse("Success", accountService.addAccount(bankId, currency ,branchId, initialDeposit, auth.getName()), null), HttpStatus.OK);
    }


    @PostMapping(AccountEndpointUtils.BANK_DEPOSIT)
    public ResponseEntity<ApiResponse> bankDeposit(@PathVariable @BankIdMustExist String bankId,
                                                   @PathVariable @BranchIdMustExist String branchId,
                                                   @RequestParam @NotEmpty @NotNull String amount,
                                                   @RequestParam @NotEmpty @NotNull String currency,
                                                   @RequestParam @NotEmpty @NotNull TypeOfAccount typeOfAccount) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(new ApiResponse("Amount Successfully Deposited", accountService.bankDeposit(bankId, branchId, amount, currency, typeOfAccount, auth.getName()), null), HttpStatus.OK);
    }


    @GetMapping(AccountEndpointUtils.GET_BALANCE)
    public ResponseEntity<ApiResponse> getAccBal(@PathVariable @BankIdMustExist String bankId,
                                                 @PathVariable @BranchIdMustExist String branchId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(new ApiResponse("Success", accountService.getAccBal(auth, bankId, branchId), null), HttpStatus.OK);
    }

}
