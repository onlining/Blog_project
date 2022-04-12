package com.realprojecy.repository;

import java.util.List;

import com.realprojecy.dto.C_comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface C_commentRepository extends JpaRepository<C_comment, Integer>{
    C_comment findByCommentEmailAndCommentContent(String commentEmail, String commentContent);
    C_comment findByCommentEmailAndCommentContentAndCommentType(String commentEmail, String commentContent, String commentType);
    List<C_comment> findByCommentType(Integer commentType);
    
}
