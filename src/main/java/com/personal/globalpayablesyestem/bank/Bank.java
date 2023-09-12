package com.personal.globalpayablesyestem.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.globalpayablesyestem.bank.branch.Branch;
import com.personal.globalpayablesyestem.country.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Bank {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String id;

    @NotEmpty
    private String name;

    @NotNull
    private boolean status;

    @ManyToOne(cascade = jakarta.persistence.CascadeType.REMOVE)
    @JsonIgnore
    private Country country;

    @Cascade(CascadeType.ALL)
    @OneToMany
    private List<Branch> branches = new ArrayList<>();

    public List<Branch> getBranches() {
        return branches;
    }

}
