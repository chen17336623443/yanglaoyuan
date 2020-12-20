package com.yanglaoyuan.utils;

import com.yanglaoyuan.pojo.MyResult;

import java.util.Collection;

public class ResultUtil<T> {

    /**
     * 请求错误,内容自定义
     *
     * @param msg
     * @return
     */
    public static MyResult ERR(String msg) {
        MyResult result = new MyResult();
        result.setMsg(msg);
        result.setCode(-1);
        return result;
    }

    /**
     * 请求错误,自定义
     *
     * @param msg
     * @param code
     * @return
     */
    public static MyResult ERR(Integer code, String msg) {
        MyResult result = new MyResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    //------------------------请求成功----------------------------------

    /**
     * 请求成功,无返回对象
     *
     * @param
     * @return
     */
    public static <T> MyResult<T> OK() {
        MyResult result = new MyResult();
        return result;
    }

    /**
     * 请求成功,自定义msg
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(String msg) {
        MyResult result = new MyResult();
        result.setMsg(msg);
        return result;
    }


    /**
     * 请求成功,返回集合
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(Collection<T> collection) {
        MyResult result = new MyResult();
        result.setCollection(collection);
        return result;
    }

    /**
     * 请求成功,返回集合,自定义反回消息
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(Collection<T> collection, String msg) {
        MyResult result = new MyResult();
        result.setMsg(msg);
        result.setCollection(collection);
        return result;
    }

    /**
     * 请求成功,返回集合,自定义返回消息,自定义返回code
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(Collection<T> collection, String msg, Integer code) {
        MyResult result = new MyResult();
        result.setMsg(msg);
        result.setCode(code);
        result.setCollection(collection);
        return result;
    }

    /**
     * 对请求成功,返回对象
     *
     * @param Object
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(T Object) {
        MyResult result = new MyResult();
        result.setObject(Object);
        return result;
    }

    /**
     * 请求成功,返回对象,自定义返回消息
     *
     * @param Object
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(T Object, String msg) {
        MyResult result = new MyResult();
        result.setMsg(msg);
        result.setObject(Object);
        return result;
    }

    /**
     * 请求成功,返回对象,自定义返回消息,自定义code
     *
     * @param Object
     * @param <T>
     * @return
     */
    public static <T> MyResult<T> OK(T Object, String msg, Integer code) {
        MyResult result = new MyResult();
        result.setMsg(msg);
        result.setCode(code);
        result.setObject(Object);
        return result;
    }
}
