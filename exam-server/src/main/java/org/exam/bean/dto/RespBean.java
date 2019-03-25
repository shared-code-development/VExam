package org.exam.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exam.enums.BusinessEnum;

import java.io.Serializable;

/**
 * @author heshiyuan
 */
@ApiModel(value = "RestModel")
public class RespBean<T> implements Serializable {
    @ApiModelProperty(value = "status", dataType = "Integer")
    private Integer status = 200;
    @ApiModelProperty(value = "返回消息", dataType = "String")
    private String msg;
    @ApiModelProperty(value = "返回数据", dataType = "Object")
    private T obj;

    private RespBean() {
    }
    private RespBean(BusinessEnum businessEnum, T data) {
        this.status = businessEnum.getCode();
        this.msg = businessEnum.getMessage();
        this.obj = data;
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static <T> RespBean ok(String msg, T data) {
        return new RespBean(200, msg, data);
    }

    public static <T> RespBean<T> ok(T data) {
        return new RespBean(BusinessEnum.SERVER_SUCCESS, data);
    }

    public static <T> RespBean<T> ok(BusinessEnum businessEnum,T data) {
        return new RespBean(businessEnum, data);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(BusinessEnum businessEnum) {
        return new RespBean(businessEnum, null);
    }


    private RespBean(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.obj = data;
    }

    public Integer getStatus() {

        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(T data) {
        this.obj = data;
        return this;
    }
}
