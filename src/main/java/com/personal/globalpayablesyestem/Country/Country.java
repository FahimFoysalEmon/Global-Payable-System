package com.personal.globalpayablesyestem.Country;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



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

    @NotEmpty
    private String phone;

    @NotNull(message = "status is mandatory")
    private boolean status;

}
