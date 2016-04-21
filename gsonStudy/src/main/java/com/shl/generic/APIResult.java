package com.shl.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackson on 16/4/12.
 */
public class APIResult <T> {

    private Integer result = 0;

    private String msg = "haah";

    private T data;
    public APIResult(){
        System.out.println(getClass());
    }
    public APIResult(T data){
        this.data =data;
    }



    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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
}
