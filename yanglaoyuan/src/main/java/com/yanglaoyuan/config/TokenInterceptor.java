package com.yanglaoyuan.config;

import com.alibaba.fastjson.JSON;
import com.yanglaoyuan.pojo.Yanzhen;
import com.yanglaoyuan.service.yanzhenServices;
import com.yanglaoyuan.utils.ResultUtil;
import com.yanglaoyuan.utils.Token;
import com.yanglaoyuan.pojo.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    yanzhenServices ys;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        boolean tokeneq = Token.tokeneq(token);
        if (tokeneq) {
            Yanzhen select = ys.select(token);
            if (null == select) {
                tokeneq = false;
            }
        }

        if (!tokeneq) {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            MyResult err = ResultUtil.ERR(10010, "请重新登陆");
            String json = JSON.toJSONString(err);//关键
            writer.print(json);
        }
        return tokeneq;
    }
}
