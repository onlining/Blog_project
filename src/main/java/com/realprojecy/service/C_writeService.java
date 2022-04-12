package com.realprojecy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.realprojecy.controller.Bean.CommentBean;
import com.realprojecy.controller.Bean.WriteBean;
import com.realprojecy.dto.C_comment;
import com.realprojecy.dto.C_image;
import com.realprojecy.dto.C_write;
import com.realprojecy.repository.C_commentRepository;
import com.realprojecy.repository.C_imageRepository;
import com.realprojecy.repository.C_writeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class C_writeService {
    @Autowired
    C_writeRepository c_writeRepository;

    @Autowired
    C_commentRepository c_commentRepository;

    @Autowired
    C_imageRepository c_imageRepository;

    @Autowired
    C_imageService c_imageService;

    @Autowired
    C_commentService c_commentService;

    public Page<C_write> findAll(int page, int page_max){
        Page<C_write> c_writeList = c_writeRepository.findAll(
           PageRequest.of(page,page_max, Sort.by(Sort.Direction.ASC,"bbsTitle"))
        );
        // Page<C_write> c_writeList2 = c_writeRepository.findByBbsTitleContaining(keyword
        //    PageRequest.of(page,page_max, Sort.by(Sort.Direction.ASC,"bbsTitle"))
        // );
        return c_writeList;

    }
    public C_write oneWrite(Integer id){
        return c_writeRepository.getById(id);
    }
    
    @Transactional
    public void deleteWrite(Integer id){
        // C_comment c_comment = c_commentService.oneComment(id);
        List<C_comment> c_comment1 = c_commentService.typeComment(id);
        for (C_comment c_comment: c_comment1 ){
            c_commentService.deleteComment(c_comment.getId());
        }
        List<C_image> c_image1 = c_imageService.getImagesType(id);
        for (C_image c_image : c_image1){
            c_imageService.deleteImage(c_image.getId());
        }
  
        c_writeRepository.deleteById(id);
    
    }
    // public Page<C_write> findAll2(String keyword, int page, int page_max){
    //     Page<C_write> c_writeList2 = c_writeRepository.findByBbsTitleContaining(keyword,
    //        PageRequest.of(page,page_max, Sort.by(Sort.Direction.ASC,"bbsTitle"))
    //     );
    //     // Page<C_write> c_writeList2 = c_writeRepository.findByBbsTitleContaining(keyword
    //     //    PageRequest.of(page,page_max, Sort.by(Sort.Direction.ASC,"bbsTitle"))
    //     // );
    //     return c_writeList2;

    // }
    // public int update(String bbsTitle, WriteBean writebean) throws IOException{
    //     Optional<C_write> c_write = c_writeRepository.findById(bbsTitle);
    //     if(!(c_write.isPresent()))
    //         return 0;

    //     C_write c_write3 = c_write.get();
    //     c_write3.setBbsTitle(writebean.getBbsTitle());
    //     c_write3.setBbsContent(writebean.getBbsContent());
    //     MultipartFile bbsImage = writebean.getBbsImage();
    //     if(!bbsImage.isEmpty()){
    //         c_write3.setBbsImage(bbsImage.getBytes());
    //     }
    //     c_writeRepository.save(c_write3);
    //     return 1;
        
    // }
    public C_write saveWrite(C_write c_write){
        return c_writeRepository.save(c_write);
    }

    public Optional<C_write> getProductId(Integer id){
        return c_writeRepository.findById(id);
    }

    public Page<C_write> searchPosts(String keyword, int page, int page_max){
        Page<C_write> c_writeList2 = c_writeRepository.findByBbsTitleContaining(keyword,
        PageRequest.of(page,page_max, Sort.by(Sort.Direction.ASC,"bbsTitle"))
        );
    //     );
    //        PageRequest.of(page,page_max, Sort.by(Sort.Direction.ASC,"bbsTitle"))
    //     );
        // List<C_write> c_write3 = c_writeRepository.findByBbsTitleContaining(keyword,);
        // List<C_write> c_writelist = C_writeService.findAll2();
        // if(c_write3.isEmpty()) return c_writelist;
        // for(C_write c_write : c_write3){
        //     c_writelist.add(this.findAll2(c_write));
        // }

        return c_writeList2;
    }

    // private C_write findAll2(C_write c_write) {
    //     return null;
    // }

    // public static List<C_write> findAll2(){
    //     List <C_write> c_writeList2 = c_writeRepository.findAll();
    //     return c_writeList2;

    // }
    
}
