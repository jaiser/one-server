/**
 * Copyright (C), 2020, 中电福富信息科技有限公司
 * FileName: CommonResp
 * Author:   xujiajun
 * Date:     2020/5/14 13:24
 */
package com.jaiser.oneserver.utils;

import com.jaiser.oneserver.constants.OperateConstant;
import com.jaiser.oneserver.enums.RespEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description: 
 * @Author xujiajun
 * @Date 2020/5/14  13:24
 */
@ApiModel("共用响应体")
public class CommonResp<T> implements Serializable {
    private static final long serialVersionUID = -3732040101599541132L;

    @ApiModelProperty(value = "响应编码", notes = "0为正常")
    private Integer code = 0;
    private String msg = OperateConstant.OPERATE_SUCCESS;
    private String respTs = String.valueOf(System.currentTimeMillis());
    private T data = null;

    public static CommonResp getInstance() {
        return new CommonResp();
    }

    public static <T> CommonResp<T> getInstance(Class<T> responseType){return new CommonResp();}

    public CommonResp success(String msg) {
        this.code = RespEnum.RESP_OK.getCode();
        this.msg = msg;
        return this;
    }

    public CommonResp<T> success(T data, String msg) {
        this.code = RespEnum.RESP_OK.getCode();
        this.msg = msg;
        this.data = data;
        return this;
    }

    public CommonResp<T> success(T data) {
        this.code = RespEnum.RESP_OK.getCode();
        this.msg = RespEnum.RESP_OK.getMsg();
        this.data = data;
        return this;
    }
    public CommonResp error() {
        this.code = RespEnum.RESP_UNKNOW_ERROR.getCode();
        this.msg = RespEnum.RESP_UNKNOW_ERROR.getMsg();;
        return this;
    }

    public CommonResp error(String msg) {
        this.code = RespEnum.RESP_UNKNOW_ERROR.getCode();
        this.msg = msg;
        return this;
    }
    public CommonResp error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRespTs() {
        return respTs;
    }

    public void setRespTs(String respTs) {
        this.respTs = respTs;
    }
}