package com.personal.globalpayablesyestem.bank.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.globalpayablesyestem.bank.Bank;
import com.personal.globalpayablesyestem.bank.branch.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Branch {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String routingNumber;

    @NotEmpty
    private String swiftCode;

    @NotNull
    private boolean status;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Bank bank;

    @OneToOne
    @JsonIgnore
    private Account account;

}
