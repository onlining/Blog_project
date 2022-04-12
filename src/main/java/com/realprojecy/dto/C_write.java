package com.realprojecy.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class C_write implements Serializable {
 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bbsTitle;
    private String bbsContent;
    
    // @Lob
    // @Basic(fetch = FetchType.LAZY)
    // private byte[] bbsImage;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] bbsVideo;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] bbsFile;
    public static Object builder() {
        return null;
    }

}

