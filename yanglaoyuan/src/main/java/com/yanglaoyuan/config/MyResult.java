package com.yanglaoyuan.config;

import java.io.Serializable;
import java.util.List;

/**
 * 封装的 统一的响应对象
 * 1.controller的返回类型，全部要变成MyResult
 */
public class MyResult implements Serializable {
    private Integer code;//返回的结果(1表示正常 其他表示非正常)
    private String msg;//返回的文本提示(一般用于错误信息提示)
    private Object obj;//返回到前台的是一个实体对象
    private List<?> objs;//返回到前台的是一个集合对象

    public  MyResult(){

    }

    public MyResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyResult(Object obj) {
        this.code=1;
        this.obj = obj;
    }

    public MyResult(List<?> objs) {
        this.code=1;
        this.objs = objs;
    }

    //当代码发生错误时调用
    public static MyResult ERROR(String msg) {
        return new MyResult(-1,msg);
    }

    //当代码返回一个实体类时调用
    public static MyResult returnObj(Object obj){
        return new MyResult(obj);
    }

    //当代码返回一个集合对象时调用
    public static MyResult returnlist(List<?> objs){
        return new MyResult(objs);
    }
    /**
     * 1.分页查询 PageInfo obj=PageInfo code=1
     * 2.新增、修改 code=1
     * 3.新增失败 code=-1 msg=失败的原因
     * 5.查询所有的类型 data=查询的多条结果 code=1
     * 4.根据主键查询 obj=查询结果 code=1
     * 6.查询所有失败 msg=失败的原因 code=-1
     */

}

enum Code {

};