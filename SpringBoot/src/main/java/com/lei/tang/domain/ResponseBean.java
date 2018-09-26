package com.lei.tang.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tanglei
 * @date 18/9/25
 */
@Getter
@Setter
public class ResponseBean {

    private int code;

    private String msg;

    private Object data;

    public ResponseBean() {
    }

    public ResponseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBean ok(Object data){
        return new ResponseBean(0, "OK", data);
    }
}
