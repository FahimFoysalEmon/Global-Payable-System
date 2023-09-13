package com.personal.globalpayablesyestem.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.globalpayablesyestem.bank.Bank;
import com.personal.globalpayablesyestem.userAuth.user.User;
import jakarta.persistence.*;
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
