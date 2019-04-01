package org.exam.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;

import java.io.Serializable;

/**
 * @author heshiyuan
 */
@ApiModel(value = "ResponseBean")
public class ResponseBean<T> implements Serializable {
    private static final long serialVersionUID = -1874202771241585841L;
    @ApiModelProperty(value = "status", dataType = "Integer")
    private Integer status = 200;
    @ApiModelProperty(value = "返回消息", dataType = "String")
    private String msg;
    @ApiModelProperty(value = "返回数据", dataType = "Object")
    private T obj;
    private ResponseBean(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private ResponseBean(BusinessEnum businessEnum, T data) {
        this.status = businessEnum.getCode();
        this.msg = businessEnum.getMessage();
        this.obj = data;
    }

    public static <T> ResponseBean ok(String msg, T data) {
        return new ResponseBean(200, msg, data);
    }

    public static <T> ResponseBean<T> ok(T data) {
        return new ResponseBean(BusinessEnum.SERVER_SUCCESS, data);
    }

    public static <T> ResponseBean<T> ok(BusinessEnum businessEnum, T data) {
        return new ResponseBean(businessEnum, data);
    }

    public static ResponseBean ok(String msg) {
        return new ResponseBean(200, msg, null);
    }

    public static ResponseBean error(BusinessEnum businessEnum) {
        return new ResponseBean(businessEnum, null);
    }

    public static ResponseBean error(BusinessException businessException) {
        return new ResponseBean(businessException.getCode(), businessException.getMsg());
    }

    public static ResponseBean error(String msg) {
        return new ResponseBean(50000, msg);
    }

    private ResponseBean(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.obj = data;
    }

    public Integer getStatus() {

        return status;
    }

    public ResponseBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public ResponseBean setObj(T data) {
        this.obj = data;
        return this;
    }
}
