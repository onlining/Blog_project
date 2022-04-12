package com.realprojecy.repository;

import java.util.List;

import com.realprojecy.dto.C_image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface C_imageRepository extends JpaRepository<C_image, Integer> {
    List <C_image> findByImageType(Integer imageType);
    

}
