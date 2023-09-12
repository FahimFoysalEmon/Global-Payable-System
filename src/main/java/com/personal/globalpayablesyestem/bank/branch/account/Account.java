package com.personal.globalpayablesyestem.bank.branch.account;

import com.personal.globalpayablesyestem.bank.branch.account.utils.enums.TypeOfAccount;
import com.personal.globalpayablesyestem.bank.branch.Branch;
import com.personal.globalpayablesyestem.userAuth.user.User;
import jakarta.persistence.*;
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

    @ManyToOne
    private User user;

    @OneToOne
    private Branch assosiatedBranch;
}
