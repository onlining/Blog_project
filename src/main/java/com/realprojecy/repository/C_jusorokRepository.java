package com.realprojecy.repository;

import com.realprojecy.dto.C_jusorok;

import org.springframework.data.jpa.repository.JpaRepository;

public interface C_jusorokRepository extends JpaRepository<C_jusorok, String>{

    C_jusorok findByEmailAndPassword(String email, String password);
    
    
}

