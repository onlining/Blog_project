package com.realprojecy.controller.Bean;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class WriteBean implements Serializable{
    private Integer id;
    private String bbsTitle;
    private String bbsContent;
    private MultipartFile bbsFile;
    private MultipartFile bbsVideo;
}
