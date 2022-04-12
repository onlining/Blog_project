package com.realprojecy.controller.Bean;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentBean {
    private Integer id;
    @NotBlank
    private String commentEmail;
    @NotBlank
    private String commentContent;
    @NotBlank
    private Integer commentType;
}
