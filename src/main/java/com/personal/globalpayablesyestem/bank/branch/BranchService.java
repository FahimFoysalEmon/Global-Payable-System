package com.personal.globalpayablesyestem.bank.branch;

import com.personal.globalpayablesyestem.bank.Bank;
import com.personal.globalpayablesyestem.bank.BankRepository;
import com.personal.globalpayablesyestem.bank.exceptions.AlreadyExistException;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    public Branch updateBranch(String branchId, Branch branch) {
        Branch branchToBeUpdated = branchRepository.findById(branchId).get();
        BeanUtils.copyProperties(branch, branchToBeUpdated);
        branchToBeUpdated.setId(branchId);
        return branchRepository.save(branchToBeUpdated);
    }

    @Transactional
    public void deleteBranch(String bankId, String branchId) {
        Branch branch = branchRepository.findById(branchId).get();

        Bank bank = bankRepository.findById(bankId).get();

        System.out.println(bank.getName());

        List<Branch> branchesToBeSaved = new ArrayList<>();

        bank.getBranches().forEach(branchToBeAdded -> {
            if (!branchToBeAdded.getName().equals(branch.getName())) {
                branchesToBeSaved.add(branchToBeAdded);
            }
        });

        bank.setBranches(branchesToBeSaved);

        bankRepository.save(bank);

        branchRepository.delete(branch);
    }
}
