package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private String id;

    private String productNo;

    private String productImage;

    private String description;

    private String factoryId;

    private String factoryName;

    private Integer price;

    private Integer sizeLenght;

    private Integer sizeWidth;

    private Integer sizeHeight;

    private String color;

    private String packing;

    private String packingUnit;

    private Integer type20;

    private Integer type40;

    private Integer type40hc;

    private Integer qty;

    private Integer cbm;

    private Integer mpsizeLenght;

    private Integer mpsizeWidth;

    private Integer mpsizeHeight;

    private String remark;

    private Date inputTime;

    private String createBy;

    private String createDept;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage == null ? null : productImage.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSizeLenght() {
        return sizeLenght;
    }

    public void setSizeLenght(Integer sizeLenght) {
        this.sizeLenght = sizeLenght;
    }

    public Integer getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Integer sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public Integer getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Integer sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing == null ? null : packing.trim();
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit == null ? null : packingUnit.trim();
    }

    public Integer getType20() {
        return type20;
    }

    public void setType20(Integer type20) {
        this.type20 = type20;
    }

    public Integer getType40() {
        return type40;
    }

    public void setType40(Integer type40) {
        this.type40 = type40;
    }

    public Integer getType40hc() {
        return type40hc;
    }

    public void setType40hc(Integer type40hc) {
        this.type40hc = type40hc;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getCbm() {
        return cbm;
    }

    public void setCbm(Integer cbm) {
        this.cbm = cbm;
    }

    public Integer getMpsizeLenght() {
        return mpsizeLenght;
    }

    public void setMpsizeLenght(Integer mpsizeLenght) {
        this.mpsizeLenght = mpsizeLenght;
    }

    public Integer getMpsizeWidth() {
        return mpsizeWidth;
    }

    public void setMpsizeWidth(Integer mpsizeWidth) {
        this.mpsizeWidth = mpsizeWidth;
    }

    public Integer getMpsizeHeight() {
        return mpsizeHeight;
    }

    public void setMpsizeHeight(Integer mpsizeHeight) {
        this.mpsizeHeight = mpsizeHeight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
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