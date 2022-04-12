package com.realprojecy.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class C_jusorok implements Serializable{

    @Id
    @NotNull
    private String email;
    private String password;
    private String passwordconfirm;
    private String name;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String address;

}
