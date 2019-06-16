package my.tset.javaweb3.entity;

import com.alibaba.fastjson.JSONObject;

public class JSONResult {
    private Integer status;
    private String msg;
    private Object data;

    private JSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static JSONResult ok() {
        return JSONResult.ok("succuess", null);
    }

    public static JSONResult ok(String msg) {
        return JSONResult.ok(msg, null);
    }

    public static JSONResult ok(Object data) {
        return JSONResult.ok("success", data);
    }

    public static JSONResult ok(String msg, Object data) {
        return new JSONResult(200, msg, data);
    }

    public static JSONResult error502(){
        return new JSONResult(502,"token已过期",null);
    }

    public static JSONResult error500(String msg){
        return new JSONResult(500,msg,null);
    }

    public String toJSONString(){
        return JSONObject.toJSONString(this);
    }
}
