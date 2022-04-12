package com.realprojecy.controller.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.realprojecy.controller.Bean.CommentBean;
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
@RequestMapping("/totalmenu")
public class ListController {
    
    Logger logger = LoggerFactory.getLogger(ListController.class);
    @Autowired
    C_writeService c_writeService;

    @Autowired
    C_commentService c_commentService;

    @Autowired
    C_imageService c_imageService;

    @GetMapping("/list")
    String listtotalmenu(@RequestParam(value="page_num",required=false) Integer pageNum, Model model){
        int page_num = (pageNum==null)?1: pageNum;
        int page_max=3;
        Page<C_write> list = c_writeService.findAll(page_num-1, page_max);
        model.addAttribute("list", list);
        model.addAttribute("page_total", list.getTotalPages());
        return "totalmenu/list";
    }
    // /getmappitng("/list를 2개 만들어야 하는건지 궁금")
    @GetMapping("/list/search")
    // @RequestParam(value="keyword") String keyword, Model model
    String Search(@RequestParam(value="keyword") String keyword, Integer pageNum, Model model){
        int page_num = (pageNum==null)?1: pageNum;
        int page_max=1;
        Page<C_write> list = c_writeService.searchPosts(keyword, page_num-1, page_max);
        model.addAttribute("list", list);

        return  "totalmenu/list2";

    }

    
    
    @GetMapping("/edit/{id}")
    String edit(@ModelAttribute CommentBean commentBean, Model model){
        // List<C_comment> list = c_commentService.findList();
        // model.addAttribute("list2", list);
        C_comment c_comment = c_commentService.getCommetCommentEmail(commentBean.getId()).get();
        model.addAttribute("comment", c_comment);
        return "/totalmenu/edit";
    }
    @GetMapping("/delete/{id}")
    String delete(@PathVariable Integer id, Model model){
        
        // C_comment c_comment = c_commentService.oneComment(id);
        // C_comment c_comment1 = c_commentService.typeComment(c_comment.getCommentType());
        // c_commentService.deleteComment(c_comment1.getCommentType());
        // c_imageService.deleteImage(id);
        c_writeService.deleteWrite(id);


        return "redirect:/totalmenu/list";
    }

    @PostMapping("/edit/{id}")
    String editOk(@ModelAttribute CommentBean commentBean, Model model){
        int result = c_commentService.update(commentBean.getId(), commentBean);
        logger.info("result=" + result);
        C_comment c_comment = c_commentService.getCommetCommentEmail(commentBean.getId()).get();

        return "redirect:/totalmenu/detail/" + c_comment.getCommentType();
    }

    // @GetMapping("/display/{id}")
    // @ResponseBody
    // void showImage(@PathVariable Integer id, HttpServletResponse response, Optional<C_write> c_write) throws IOException{
    //     c_write = c_writeService.getProductId(id);
    //     response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
    //     response.getOutputStream().write(c_write.get().getBbsImage());
    //     response.getOutputStream().close();
    // }
    @GetMapping("/display1/{id}")
    @ResponseBody
    void showImage(@PathVariable Integer id, HttpServletResponse response, Optional<C_image> c_image) throws IOException{
        c_image = c_imageService.getImageID(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(c_image.get().getImageImage());
        response.getOutputStream().close();
    }
    
    @GetMapping("/display2/{id}")
    @ResponseBody
    void showFile(@PathVariable Integer id, HttpServletResponse response, Optional<C_write> c_write) throws IOException{
        c_write = c_writeService.getProductId(id);
        response.setContentType("application/octet-stream");
        response.getOutputStream().write(c_write.get().getBbsFile());
        response.getOutputStream().close();
    }

    @GetMapping("/display3/{id}")
    @ResponseBody
    void showVideo(@PathVariable Integer id, HttpServletResponse response, Optional<C_write> c_write) throws IOException{
        c_write = c_writeService.getProductId(id);
        response.setContentType("video/mp4");
        response.getOutputStream().write(c_write.get().getBbsVideo());
        response.getOutputStream().close();
    }

    

    @GetMapping("/detail/{id}")
    String detailwrite(@RequestParam(value="page_num", required=false) Integer pageNum, @PathVariable Integer id, @ModelAttribute CommentBean commentBean, Model model){
        Optional<C_write> c_write = c_writeService.getProductId(id);
        // Optional<C_image> c_image = c_imageService.getImageID(id);
        List<C_image> c_image = c_imageService.getImagesType(id);
        int page_num=(pageNum==null)? 1: pageNum;
        int page_max=10;
        Page<C_comment> list = c_commentService.findAll(page_num-1, page_max);
        model.addAttribute("list", list);
        model.addAttribute("page_total", list.getTotalPages());
         // c_writeService.saveWrite(c_write);
        // C_comment c_comment = c_commentService.comment(commentBean.getCommentEmail(), commentBean.getCommentContent());
        // c_commentService.saveComment(c_comment);
        
        model.addAttribute("c_write", c_write.get());
        // model.addAttribute("c_image", c_image.get().getImageImage());
        model.addAttribute("c_image", c_image);
        // logger.info("asfs" + c_image);
        // -여기가 오류난다고 오류 메시지가 떴는데 이유를 알 수 없음
        return "totalmenu/detail";

    }
    @PostMapping("/detail/{id}")
    String detailwriteOk(@Validated @PathVariable Integer id,  @ModelAttribute CommentBean commentBean, WriteBean writeBean, Model model){
        // Optional<C_write> c_write = c_writeService.getProductBbsTitle(bbsTitle);
        // C_write c_write = new C_write();
        C_comment c_comment = new C_comment();
        c_comment.setCommentEmail(commentBean.getCommentEmail());
        c_comment.setCommentContent(commentBean.getCommentContent());
        c_comment.setCommentType(id);
        // C_comment c_comment = c_commentService.comment(commentBean.getCommentEmail(), commentBean.getCommentContent());
        c_commentService.saveComment(c_comment);
        
        // model.addAttribute("c_write", c_write.get());
        return "redirect:/totalmenu/detail/" + id;
    }
}
