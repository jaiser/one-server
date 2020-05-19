/**
 * Copyright (C), 2020, 中电福富信息科技有限公司
 * FileName: RespEnum
 * Author:   xujiajun
 * Date:     2020/5/14 13:47
 */
package com.jaiser.oneserver.enums;

import com.jaiser.oneserver.constants.OperateConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 
 * @Author xujiajun
 * @Date 2020/5/14  13:47
 */
@ApiModel("响应类型枚举类")
public enum RespEnum {
    /**
     * 成功
     */
    RESP_OK(0, OperateConstant.OPERATE_SUCCESS),
    /**
     * 失败
     */
    RESP_FAILED(-1, OperateConstant.OPERATE_FAILED),
    /**
     * 错误
     */
    RESP_UNKNOW_ERROR(-1000, OperateConstant.OPERATE_UNKNOW_ERROR),
    /**
     * 异常
     */
    RESP_EXCEPTOIN(-1001, OperateConstant.OPERATE_EXCEPTION),
    ;

    @ApiModelProperty("操作状态")
    private Integer code;
    @ApiModelProperty("操作结果0")
    private String msg;

    RespEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}