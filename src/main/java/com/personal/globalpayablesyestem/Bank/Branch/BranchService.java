package com.personal.globalpayablesyestem.Bank.Branch;

import com.personal.globalpayablesyestem.Bank.BankRepository;
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
}
