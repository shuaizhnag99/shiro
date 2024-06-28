package com.example.shiro.util;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResPonseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private T data;
    private boolean sucessStatus ;

    public ResPonseResult() {
        sucessStatus = true;
        code = ShiroConstants.SUCCESS_CODE;
        msg = ShiroConstants.SUCESS_MSG;
    }

    public ResPonseResult(int code, String msg, T data  ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResPonseResult(int code, T data  ) {
        this.code = code;
        this.data = data;
    }

    public ResPonseResult(int code, String msg ) {
        this.code = code;
        this.msg = msg;

    }



    public ResPonseResult<?> okResult(int code, String msg ) {
        return okResult(code, msg, null);

    }

    public ResPonseResult<T> okResult(int code, String msg, T data ) {
        this.code = code;
        this.msg = msg;
        this.sucessStatus = true;
        this.data = data;
        return this;

    }
    public ResPonseResult<T> okResult(T data ) {

        return okResult(ShiroConstants.SUCCESS_CODE, ShiroConstants.SUCESS_MSG, data);

    }

    public ResPonseResult<?> faileResult(int code, String msg ) {
        return faileResult(code,msg,null);

    }

    public ResPonseResult<T> faileResult(int code, String msg, T data ) {
        this.code = code;
        this.msg = msg;
        this.sucessStatus = false;
        this.data = data;
        return this;

    }
    public static ResPonseResult ok(int code, String msg ){
        ResPonseResult res = new ResPonseResult();
        res.okResult(code,msg);
        return res;
    }

    public static ResPonseResult ok(String msg ){
        ResPonseResult res = new ResPonseResult();
        res.okResult(ShiroConstants.SUCCESS_CODE,msg);
        return res;
    }


    public static ResPonseResult faile(int code, String msg ){
        ResPonseResult res = new ResPonseResult();
        res.faileResult(code,msg);
        return res;
    }
    public static ResPonseResult faile( String msg ){
        ResPonseResult res = new ResPonseResult();
        res.faileResult(ShiroConstants.FAIL_CODE,msg);
        return res;
    }
}
