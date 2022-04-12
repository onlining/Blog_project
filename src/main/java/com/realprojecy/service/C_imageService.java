package com.realprojecy.service;

import java.util.List;
import java.util.Optional;

import com.realprojecy.dto.C_image;
import com.realprojecy.repository.C_commentRepository;
import com.realprojecy.repository.C_imageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class C_imageService {

    @Autowired
    C_imageRepository c_imageRepository;

    public C_image saveImage(C_image c_image){
        return c_imageRepository.save(c_image);

    }
    @Transactional
    public void deleteImage(Integer id){
        c_imageRepository.deleteById(id);
    }

    public C_image oneImage(Integer id){
        return c_imageRepository.getById(id);
    }


    public Optional<C_image> getImageID(Integer id){
        return c_imageRepository.findById(id);
    }

    public List <C_image> getImagesType(Integer imageType){
        return c_imageRepository.findByImageType(imageType);
    }

    // public C_image imageID(MultipartFile imageImage){
    //     return c_imageRepository.findByImageImage(imageImage);
    // }
    
}
