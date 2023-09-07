package com.personal.globalpayablesyestem.Bank.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    boolean existsByName(String name);
    Branch findByBankIdAndId(String bankId, String branchId);

}
