package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable {
    private String invoiceId;

    private String scNo;

    private String blNo;

    private String tradeTerms;

    private Integer state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private Double allCount;

    public Double getAllCount() {
        return allCount;
    }

    public void setAllCount(Double allCount) {
        this.allCount = allCount;
    }

    private static final long serialVersionUID = 1L;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

    public String getScNo() {
        return scNo;
    }

    public void setScNo(String scNo) {
        this.scNo = scNo == null ? null : scNo.trim();
    }

    public String getBlNo() {
        return blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo == null ? null : blNo.trim();
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms == null ? null : tradeTerms.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}