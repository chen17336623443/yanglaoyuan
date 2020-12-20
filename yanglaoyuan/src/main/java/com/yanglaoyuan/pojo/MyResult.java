package com.yanglaoyuan.pojo;

import java.io.Serializable;
import java.util.Collection;


public class MyResult<T> implements Serializable {
    //返回的结果(1表示正常 其他表示非正常)默认是请求成功
    private Integer code = 1;
    //返回的文本提示(一般用于错误信息提示),默认请求成功
    private String msg = "请求成功";
    //返回到前台的是一个实体对象
    private T object;
    //返回到前台的是一个集合对象
    private Collection<T> collection;

    public MyResult(Integer code, String msg, T object, Collection<T> collection) {
        this.code = code;
        this.msg = msg;
        this.object = object;
        this.collection = collection;
    }

    public MyResult() {
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

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection = collection;
    }
}
