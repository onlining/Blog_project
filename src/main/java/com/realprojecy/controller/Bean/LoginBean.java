package com.realprojecy.controller.Bean;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginBean {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
