package com.realprojecy.repository;

import java.util.List;

import com.realprojecy.dto.C_write;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface C_writeRepository extends JpaRepository<C_write, Integer>{
    Page<C_write> findByBbsTitleContaining(String keyword, Pageable pageable);
    }
    

