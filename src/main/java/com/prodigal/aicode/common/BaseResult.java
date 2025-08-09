package com.prodigal.aicode.common;

import com.prodigal.aicode.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: prodigal-ai-code
 * @author: Lang
 * @description: 响应类基类
 **/
@Data
@Schema(description = "统一响应结果")
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "响应码", example = "200")
    private int code;
    @Schema(description = "响应状态",example = "true")
    private boolean status;
    @Schema(description = "响应消息", example = "操作成功")
    private String msg="巭(gu)孬(nao)嫑(biao)哔哔···";
    @Schema(description = "响应数据")
    private T data;

    public BaseResult() {
    }
    public BaseResult(Integer code, Boolean status, String msg, T data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public BaseResult(Integer code, Boolean status, T data) {
        this(code, status, "", data);
    }
    public BaseResult(ErrorCode errorCode) {
        this(errorCode.getCode(), false, errorCode.getMessage(),null);
    }
    public static BaseResult success() {
        return new BaseResult(0, true, "成功", null);
    }

    public static BaseResult error() {
        BaseResult baseResult = new BaseResult();
        return baseResult.status(false);
    }

    public  BaseResult<T> code(int code){
        this.code = code;
        return this;
    }
    public  BaseResult<T> status(boolean status){
        this.status = status;
        return this;
    }
    public  BaseResult<T> msg(String msg){
        this.msg = msg;
        return this;
    }
    public  BaseResult<T> data(T data){
        this.data = data;
        return this;
    }
}
