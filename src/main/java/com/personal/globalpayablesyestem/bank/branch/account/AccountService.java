package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.Bank;
import com.personal.globalpayablesyestem.bank.BankRepository;
import com.personal.globalpayablesyestem.bank.branch.Branch;
import com.personal.globalpayablesyestem.bank.branch.BranchRepository;
import com.personal.globalpayablesyestem.bank.branch.account.utils.enums.TypeOfAccount;
import com.personal.globalpayablesyestem.common.exception.DataMismatchException;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import com.personal.globalpayablesyestem.userAuth.user.User;
import com.personal.globalpayablesyestem.userAuth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

        User user = userRepository.findByUserEmail(username).get();
        Branch branch = branchRepository.findById(branchId).get();

        boolean branchIfExists = checkingBranch(bankId, branchId);

        for (Account account : user.getAccounts()) {
            if (account.getAssosiatedBranch().getName().equals(branch.getName())) {
                throw new CredentialMisMatchError("This user has already an account in this branch");
            }
        }

        if (!branchIfExists) {
            throw new CredentialMisMatchError("This bank does not have this branch");
        } else {
            Account account = new Account();

            account.setAccountBalance(initialDeposit);
            account.setAccountCurrency(currency);
            account.setAccountNumber(UUID.randomUUID().toString());
            account.setTypeOfAccount(TypeOfAccount.SAVING); // or TypeOfAccount.CHECKING
            account.setAccountHolderName(username);
            account.setAssosiatedBranch(branch);
            account.setUser(user);

            return accountRepository.save(account);
        }

    }


    public Account bankDeposit(String bankId, String branchId, String amount, String currency ,TypeOfAccount typeOfAccount, String username) {

        boolean branchIfExists = checkingBranch(bankId, branchId);

        if (!branchIfExists) {
            throw new CredentialMisMatchError("This bank does not have this branch");
        } else {
            Account account = accountRepository.findByAccountHolderName(username);
            if (account.getTypeOfAccount().equals(typeOfAccount) && account.getAccountCurrency().equals(currency)) {
                if (checkingIfAccHasAssociatedWithThisBranch(branchId, account)) {
                    account.setAccountBalance(String.valueOf(Double.parseDouble(account.getAccountBalance())+Double.parseDouble(amount)));
                    return accountRepository.save(account);
                } {
                  throw new DataMismatchException("Account is not in this branch");
                }
            } else {
                throw new DataMismatchException("Account type or currency did not match");
            }
        }
    }

    private boolean checkingIfAccHasAssociatedWithThisBranch(String branchId, Account account) {
        Branch branch = branchRepository.findById(branchId).get();
        return account.getAssosiatedBranch().equals(branch);
    }


    private boolean checkingBranch(String bankId, String branchId) {

        Bank bank = bankRepository.findById(bankId).get();
        Branch branch = branchRepository.findById(branchId).get();

        boolean checkingBranch = false;

        for (Branch bankBranch : bank.getBranches()) {
            if (bankBranch.getName().equals(branch.getName())) {
                checkingBranch = true;
                break;
            }
        }
        return checkingBranch;
    }

    public Account getAccBal(String username, String bankId, String branchId) {
        if (checkingBranch(bankId,branchId)) {
            Account account = accountRepository.findByAccountHolderName(username);
            if (account != null) {
                if (checkingIfAccHasAssociatedWithThisBranch(branchId, account)) {
                    return account;
                }
                throw new DataMismatchException("Account is not in this branch");
            }
            throw new DataMismatchException("Account is Invalid");
        }
        throw new DataMismatchException("Information is not correct");
    }
}
