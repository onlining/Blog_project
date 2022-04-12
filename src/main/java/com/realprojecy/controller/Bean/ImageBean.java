package com.realprojecy.controller.Bean;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageBean {
    private Integer id;
    private String imageName;
    private String imageCount;
    @DateTimeFormat(pattern="yyyy년MM월ss일")
    private LocalDate imageDate;
    @NotBlank
    private MultipartFile[] imageImage;
    @NotBlank
    private Integer imageType;

}
