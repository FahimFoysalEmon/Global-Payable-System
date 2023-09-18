package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.Bank;
import com.personal.globalpayablesyestem.bank.BankRepository;
import com.personal.globalpayablesyestem.bank.branch.Branch;
import com.personal.globalpayablesyestem.bank.branch.BranchRepository;
import com.personal.globalpayablesyestem.bank.branch.account.utils.enums.TypeOfAccount;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import com.personal.globalpayablesyestem.userAuth.user.User;
import com.personal.globalpayablesyestem.userAuth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;

    public Account addAccount(String bankId,  String currency, String branchId, String initialDeposit, String username) {
        Branch branch = branchRepository.findById(branchId).get();
        Bank bank = bankRepository.findById(bankId).get();
        User user = userRepository.findByUserEmail(username).get();


        boolean checkingBranch = false;

        for (Branch bankBranch : bank.getBranches()) {
            if (bankBranch.getName().equals(branch.getName())) {
                checkingBranch = true;
                break;
            }
        }

        if (!checkingBranch) {
            throw new CredentialMisMatchError("This bank does not have this branch");
        } else {
            Account account = new Account();

            account.setAccountBalance(initialDeposit);
            account.setAccountCurrency(currency);
            account.setAccountNumber(UUID.randomUUID().toString());
            account.setTypeOfAccount("SAVING"); // or TypeOfAccount.CHECKING
            account.setAccountHolderName(username);
            account.setAssosiatedBranch(branch);
            account.setAccountCurrency(currency);

            return accountRepository.save(account);
        }

    }
}
