package com.realprojecy.service;

import java.util.List;
import java.util.Optional;

import com.realprojecy.controller.Bean.CommentBean;
import com.realprojecy.dto.C_comment;
import com.realprojecy.repository.C_commentRepository;
import com.realprojecy.repository.C_writeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class C_commentService {

    @Autowired
    C_commentRepository c_commentRepository;


    public C_comment saveComment(C_comment c_comment){
        return c_commentRepository.save(c_comment);
    }

    public Page<C_comment> findAll(int page2, int page_max2){
        Page<C_comment> c_commentList= c_commentRepository.findAll(
            PageRequest.of(page2, page_max2, Sort.by(Sort.Direction.ASC,"commentEmail"))
        
        );
        return c_commentList;
    }
    @Transactional
    public void deleteComment(Integer id){
        c_commentRepository.deleteById(id);
       
    }

    public List<C_comment> typeComment(Integer commentType){
        return c_commentRepository.findByCommentType(commentType);
    }
    
    public C_comment oneComment(Integer id){
        return c_commentRepository.getById(id);
    }

  


    public List<C_comment> findList(){
        List<C_comment> list = c_commentRepository.findAll();
        return list;
    }

    public int update(Integer id, CommentBean commentBean){
        Optional<C_comment> comment = c_commentRepository.findById(id);
        if(!(comment.isPresent()))
            return 0;

        C_comment realcomment = comment.get();
        realcomment.setCommentEmail(commentBean.getCommentEmail());
        realcomment.setCommentContent(commentBean.getCommentContent());
        c_commentRepository.save(realcomment);
        return 1;

    }

    public C_comment comment(String commentEmail, String commentContent){
        return c_commentRepository.findByCommentEmailAndCommentContent(commentEmail, commentContent);
    }
    public Optional<C_comment> getCommetCommentEmail(Integer id){
        return c_commentRepository.findById(id);

    }

    public C_comment commentlist(String commentEmail, String commentContent, String commentType){
        return c_commentRepository.findByCommentEmailAndCommentContentAndCommentType(commentEmail, commentContent, commentType);
    }

    

}
