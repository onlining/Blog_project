package com.realprojecy.service;

import javax.validation.constraints.Email;

import com.realprojecy.dto.C_jusorok;

import com.realprojecy.repository.C_jusorokRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    C_jusorokRepository c_jusorokRepository;

    public C_jusorok login(String email, String password){
        return c_jusorokRepository.findByEmailAndPassword(email, password);
    }
    
}
