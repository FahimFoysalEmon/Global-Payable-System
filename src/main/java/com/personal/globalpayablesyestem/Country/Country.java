package com.personal.globalpayablesyestem.Country;

import com.personal.globalpayablesyestem.Bank.Bank;
import com.personal.globalpayablesyestem.Bank.Branch.Branch;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String code;

    @NotEmpty
    private String currency;

    @NotNull(message = "status is mandatory")
    private boolean status;

    @Cascade(CascadeType.ALL)
    @OneToMany
    private List<Bank> banks = new ArrayList<>();

}
