package com.sly.domain.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class Module implements Serializable{
    private String id   ;
    private String parentId  ;
    private String parentName;
    private String name      ;
    private int layerNum  ;
    private int isLeaf    ;
    private String ico       ;
    private String cpermission;
    private String curl      ;
    private String ctype     ;
    private String state     ;
    private String belong    ;
    private String cwhich    ;
    private int quoteNum  ;
    private String remark    ;
    private int orderNo   ;
}
