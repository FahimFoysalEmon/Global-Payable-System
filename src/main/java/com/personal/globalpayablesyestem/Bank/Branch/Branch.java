package com.personal.globalpayablesyestem.Bank.Branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.globalpayablesyestem.Bank.Bank;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JsonIgnore
    private Bank bank;

}
