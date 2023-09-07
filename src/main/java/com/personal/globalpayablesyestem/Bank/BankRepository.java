package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.Branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {
    boolean existsByName(String name);

}
