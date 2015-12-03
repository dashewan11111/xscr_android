package com.adult.android.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderMsg implements Serializable {
    private Long id;

    private Long orderId;

    private String msg;

    private Short type;

    private Date createTime;

    private String createBy;

    private String msgEn;

    private static final long serialVersionUID = 1L; 
    public OrderMsg(){}
    public OrderMsg(Long id,Long orderId,String msg,Short type,Date createTime,String createBy,String msgEn){
    	this.setId(id);
    	this.setCreateBy(createBy);
    	this.setCreateTime(createTime);
    	this.setOrderId(orderId);
    	this.setMsg(msg);
    	this.setMsgEn(msgEn);
    	this.setType(type);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getMsgEn() {
        return msgEn;
    }

    public void setMsgEn(String msgEn) {
        this.msgEn = msgEn == null ? null : msgEn.trim();
    }
}