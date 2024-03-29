package com.personal.globalpayablesyestem.bank.branch.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByAccountHolderName(String username);

    Account findByAccountNumber(String accountNumber);

}
