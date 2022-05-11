package com.tdar.demo.base;

import lombok.Data;

@Data
public class R {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "Success";

    private static final int FAIL_CODE = 1;
    private static final String FAIL_MSG = "Failure";

    protected static final int TOKEN_INVALID_CODE = -1;
    protected static final String TOKEN_INVALID_MSG  = "Token expired, login required";

    private int code;
    private String msg;
    private Object data;

    public R() {}

    public static R success()
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(SUCCESS_MSG);
        return r;
    }

    public static R success(String msg)
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        return r;
    }

    public static R success(Object data)
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(SUCCESS_MSG);
        r.setData(data);
        return r;
    }

    public static R success(String msg, Object data)
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R fail()
    {
        R r = new R();
        r.setCode(FAIL_CODE);
        r.setMsg(FAIL_MSG);
        return r;
    }

    public static R fail(String msg)
    {
        R r = new R();
        r.setCode(FAIL_CODE);
        r.setMsg(msg);
        return r;
    }

    public static R fail(int code, String msg)
    {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static R fail(String msg, Object data)
    {
        R r = new R();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
