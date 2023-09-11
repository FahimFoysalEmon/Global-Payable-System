package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.branch.account.enums.TypeOfAccount;
import com.personal.globalpayablesyestem.bank.branch.Branch;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String accountNumber;
    private String accountBalance;
    private String accountHolderName;

    private TypeOfAccount typeOfAccount;

    @OneToOne
    private Branch assosiatedBranch;
}
