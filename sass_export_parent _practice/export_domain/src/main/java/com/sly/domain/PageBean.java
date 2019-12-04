package com.sly.domain;

import com.sly.domain.company.Company;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean implements Serializable {
    private int pageNum; //当前页码
    private int pageSize; //每页显示条数
    private List list;//每页数据
    private long total;//总条数
    private int pages;//总页数
    private int prePage;//上一页
    private int nextPage;//下一页
    private int startRow;//起始页码
    private int endRow;//结束页码

    public   PageBean(Integer pageNum,Integer pageSize,Long total,List list){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;

        this.pages =  (total%pageSize==0?(int)(total/pageSize):(int)(total/pageSize+1));
        if(pageNum == 1){
            this.prePage = 1;
        }else {
            this.prePage = pageNum-1;
        }
        if(pageNum == pages){
            this.nextPage = pageNum;
        }else {
            this.nextPage = pageNum+1;
        }
        calStartAndEnd(pages,pageNum);
    }

    private void calStartAndEnd(int pages, Integer pageNum) {
        if(pages<5){
            this.startRow = 1;
            this.endRow = 5;
        }else {
            if(pageNum<=3){
                this.startRow = 1;
                this.endRow = 5;
            }else if((pages-pageNum)<=2){
                this.startRow = pages-4;
                this.endRow = pages;
            }else {
                this.startRow = pageNum-2;
                this.endRow = pageNum+2;
            }    }
    }


}
