package com.personal.globalpayablesyestem.Bank.Branch;

import com.personal.globalpayablesyestem.Bank.Bank;
import com.personal.globalpayablesyestem.Bank.BankRepository;
import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BranchService {

    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;
    public List<Branch> getBranches() {
        return branchRepository.findAll();
    }

    public Branch addBranch(String bankId, Branch branch) {
        Bank bank = bankRepository.findById(bankId).get();

        // Check if the branch already exists in the bank
        boolean branchExists = bank.getBranches().stream()
                .anyMatch(existingBranch -> existingBranch.getName().equals(branch.getName()));

        if (branchExists) {
            throw new AlreadyExistException("Branch already exists in the bank");
        }

        // If the branch doesn't exist, add it to the bank and save

        Branch savedBranch = branchRepository.save(branch);

        bank.getBranches().add(savedBranch);
        bankRepository.save(bank);

        return savedBranch;
    }

    public Branch getBranch(String bankId, String branchId) {
        Bank bank = bankRepository.findById(bankId).orElse(null); // Use 'orElse' to handle the case where the bank is not found
        if (bank != null) {
            List<Branch> branches = bank.getBranches();
            for (Branch branch : branches) {
                if (branch.getId().equals(branchId)) {
                    return branch;
                }
            }
        }
        throw new CredentialMisMatchError("Credential Mismatch Error");
    }


    public Branch updateBranch(String bankId, String branchId, Branch branch) {
        Branch branchToBeUpdated = branchRepository.findByBankIdAndId(bankId, branchId);
        branchToBeUpdated.setName(branch.getName());
        branchToBeUpdated.setStatus(branch.isStatus());
        branchToBeUpdated.setSwiftCode(branch.getSwiftCode());
        branchToBeUpdated.setRoutingNumber(branch.getRoutingNumber());

        return branchRepository.save(branchToBeUpdated);
    }


    public void deleteBranch(String bankId, String branchId) {
        branchRepository.deleteById(branchId);
    }
}
