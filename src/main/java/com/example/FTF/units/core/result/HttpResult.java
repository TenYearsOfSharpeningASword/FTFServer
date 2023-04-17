package com.example.FTF.units.core.result;

import java.util.HashMap;

public class HttpResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private static final String CODE = "code";

    private static final String DATA = "data";

    private static final String MSG = "msg";

    private HttpResult(){

    }
    public HttpResult(int code, String msg){
        super.put(CODE, code);
        super.put(MSG, msg);
    }
    public HttpResult(int code, String msg, Object data){
        super.put(CODE, code);
        super.put(MSG, msg);
        if (data!= null) {
            super.put(DATA, data);
        }
    }
    public static HttpResult success(){
        return HttpResult.success("操作成功");
    }

    public static HttpResult success(Object data){
        return HttpResult.success("操作成功", data);
    }

    public static HttpResult success(String msg){
        return HttpResult.success(msg, null);
    }
    public static HttpResult success(String msg, Object data){
        return new HttpResult(HttpCode.OK, msg, data);
    }

    public static HttpResult error(){
        return HttpResult.error("操作失败");
    }
    public static HttpResult error(Object data){
        return HttpResult.error("操作失败", data);
    }
    public static HttpResult error(String msg){
        return HttpResult.error(msg, null);
    }
    public static HttpResult error(String msg, Object data){
        return new HttpResult(HttpCode.ERROR, msg, data);
    }

    public static HttpResult toResult(boolean result){
        return result? HttpResult.success() : HttpResult.error();
    }

    public static HttpResult toResult(int result){
        return result > 0 ? HttpResult.success() : HttpResult.error();
    }
}
