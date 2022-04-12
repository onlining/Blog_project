package com.realprojecy.controller.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.executable.ValidateOnExecution;

import com.realprojecy.controller.Bean.CommentBean;
import com.realprojecy.controller.Bean.ImageBean;
import com.realprojecy.controller.Bean.WriteBean;
import com.realprojecy.dto.C_comment;
import com.realprojecy.dto.C_image;
import com.realprojecy.dto.C_write;
import com.realprojecy.service.C_commentService;
import com.realprojecy.service.C_imageService;
import com.realprojecy.service.C_writeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/nav")
public class NavController {
    Logger logger= LoggerFactory.getLogger(ListController.class);
    @Autowired
    C_writeService c_writeService;

    @Autowired
    C_commentService c_commentService;

    @Autowired
    C_imageService c_imageService;

    @GetMapping("/write")
    String addWrite(@ModelAttribute WriteBean writeBean, ImageBean imageBean, CommentBean commentBean){
        
        return "nav/write";
    }

    @PostMapping("/saveWrite")
    String saveWrite(@Validated @ModelAttribute WriteBean writeBean, ImageBean imageBean) throws IOException{
        C_write c_write = null;
        // List<C_image> c_imagelist = new ArrayList<>();
        if(writeBean.getId()==null){
            c_write = new C_write();

        }else{
            c_write = c_writeService.getProductId(writeBean.getId()).get();
         

        }
        
        c_write.setBbsTitle(writeBean.getBbsTitle());
        c_write.setBbsContent(writeBean.getBbsContent());
        MultipartFile bbsFile = writeBean.getBbsFile();
        if(!bbsFile.isEmpty()){
            c_write.setBbsFile(bbsFile.getBytes());
        }
        MultipartFile bbsVideo = writeBean.getBbsVideo();
        if(!bbsVideo.isEmpty()){
            c_write.setBbsVideo(bbsVideo.getBytes());
        }
      
        C_write c_write2 = c_writeService.saveWrite(c_write);
        for (MultipartFile imageImage : imageBean.getImageImage()){
            if(!imageImage.isEmpty()){
                C_image c_image = new C_image();
                c_image.setImageImage(imageImage.getBytes());
                c_image.setImageType(c_write2.getId());
                // c_imagelist.add(c_image);
                c_imageService.saveImage(c_image);
                

            }
        }
        
        
        
      
        
       
        // C_comment c_comment = c_commentService.comment(commentBean.getCommentEmail(), commentBean.getCommentContent());
        // c_commentService.saveComment(c_comment);
        return "redirect:/totalmenu/list/";
    
        
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Integer id, @ModelAttribute WriteBean writeBean, Model model){
        Optional<C_write> c_write = c_writeService.getProductId(id);
        C_write my_C_write = c_write.get();
        writeBean.setBbsTitle(my_C_write.getBbsTitle());
        writeBean.setBbsContent(my_C_write.getBbsContent());
    
        return "nav/detail";
    }

    @GetMapping("/delete/{id}")
    String delete(@PathVariable Integer id, Model model){
        // c_writeService.getProductId(id2);
        C_comment c_comment = c_commentService.oneComment(id);

        c_commentService.deleteComment(id);
        return "redirect:/totalmenu/detail/" + c_comment.getCommentType();
    }

  
    
  

}
