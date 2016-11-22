package com.xiaoming.dto;

import java.util.Date;

/**
 * Created by xiaoming on 21/11/2016.
 */
public class ApiAttributeDTO {
    private Long   id;
    private Long   apiId;
    private String isDTO;
    private String dtoClassName;
    private String argumentType;
    private String argumentDesc;
    private int    orderNo;
    private Date   gmtCreated;
    private String creator;
    private Date   gmtModified;
    private String modifier;

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getIsDTO() {
        return isDTO;
    }

    public void setIsDTO(String isDTO) {
        this.isDTO = isDTO;
    }

    public String getDtoClassName() {
        return dtoClassName;
    }

    public void setDtoClassName(String dtoClassName) {
        this.dtoClassName = dtoClassName;
    }

    public String getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(String argumentType) {
        this.argumentType = argumentType;
    }

    public String getArgumentDesc() {
        return argumentDesc;
    }

    public void setArgumentDesc(String argumentDesc) {
        this.argumentDesc = argumentDesc;
    }
}
