package com.personal.globalpayablesyestem.Bank.Branch;

import com.personal.globalpayablesyestem.Bank.Bank;
import com.personal.globalpayablesyestem.Bank.BankRepository;
import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
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
        bank.getBranches().add(branch);
        bankRepository.save(bank);

        return branch;
    }

    public Branch getBranch(String bankId, String branchId) {
        return branchRepository.findByBankIdAndId(bankId, branchId);
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
