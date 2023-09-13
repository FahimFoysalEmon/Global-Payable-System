package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.Bank;
import com.personal.globalpayablesyestem.bank.BankRepository;
import com.personal.globalpayablesyestem.bank.branch.Branch;
import com.personal.globalpayablesyestem.bank.branch.BranchRepository;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import com.personal.globalpayablesyestem.userAuth.user.User;
import com.personal.globalpayablesyestem.userAuth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;

    public Account addAccount(String bankId, String branchId, String initialDeposit, String username) {
        Branch branch = branchRepository.findById(branchId).get();
        Bank bank = bankRepository.findById(bankId).get();

        User user = userRepository.findByUserEmail(username).get();

        System.out.println("-------- user country"+user.get);

//        System.out.println("-------- bank country"+bank.getCountry().getName());

        System.out.println(initialDeposit+"-------------"+username);

        boolean checkingBranch = false;

        for (Branch bankBranch : bank.getBranches()) {
            if (bankBranch.getName().equals(branch.getName())) {
                checkingBranch = true;
                break;
            }
        }

        if (!checkingBranch) {
            throw new CredentialMisMatchError("This bank does not have this branch");
        }

        System.out.println(bank.getCountry().getName());

        Account account = new Account();
        return null;
    }
}
