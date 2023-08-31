package com.personal.globalpayablesyestem.Bank.Branch;

import com.personal.globalpayablesyestem.Bank.Bank;
import com.personal.globalpayablesyestem.Bank.BankRepository;
import com.personal.globalpayablesyestem.Bank.utils.BankAndBranchEndpointUtils;
import com.personal.globalpayablesyestem.Bank.validators.BankIdMustExist;
import com.personal.globalpayablesyestem.Bank.validators.BranchIdMustExist;
import com.personal.globalpayablesyestem.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
public class BranchController {

    private final BankRepository bankRepository;
    private final BranchService branchService;

    @GetMapping(value = BankAndBranchEndpointUtils.GET_ALL_BRANCHES)
    public ResponseEntity<ApiResponse> getBranches(@PathVariable String bankId) {
        return new ResponseEntity<>(new ApiResponse("Success",branchService.getBranches(bankId), null), HttpStatus.OK);
    }

    @PostMapping(value = BankAndBranchEndpointUtils.ADD_BRANCH)
    public ResponseEntity<ApiResponse> addBranch(@PathVariable String bankId , @RequestBody Branch branch){
        return new ResponseEntity<>(new ApiResponse("Success",branchService.addBranch(bankId, branch),null), HttpStatus.OK);
    }

    @GetMapping(value = BankAndBranchEndpointUtils.GET_BRANCH)
    public ResponseEntity<ApiResponse> getBranch(@PathVariable @BankIdMustExist String bankId, @PathVariable @BranchIdMustExist String branchId) {
        return new ResponseEntity<>(new ApiResponse("Success", branchService.getBranch(bankId, branchId), null), HttpStatus.OK);
    }


    @PutMapping(value = BankAndBranchEndpointUtils.UPDATE_BRANCH)
    public ResponseEntity<ApiResponse> updateBranch(@PathVariable @BankIdMustExist String bankId,
                                                  @PathVariable @BranchIdMustExist String branchId,
                                                  @RequestBody Branch branch) {
        return new ResponseEntity<>(new ApiResponse("Success", branchService.updateBranch(bankId, branchId, branch), null), HttpStatus.OK);
    }


    @DeleteMapping(value = BankAndBranchEndpointUtils.DELETE_BRANCH)
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable @BankIdMustExist String bankId,
                                                    @PathVariable @BranchIdMustExist String branchId) {
        branchService.deleteBranch(bankId, branchId);
        return new ResponseEntity<>(new ApiResponse("Success", null , null), HttpStatus.OK);
    }

}