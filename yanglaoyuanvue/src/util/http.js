//对axios的统一封装
import axios from 'axios'; //导入
import qs from 'qs';
//加载、消息提示、弹窗提醒
import { Loading, Message, Notification } from 'element-ui'
import router from '@/router'


let loading;

function startLoading() {}

function stopLoading() {}
//统一的跨域访问前缀
axios.defaults.baseURL = "http://localhost:8089/";
axios.defaults.timeout = 15000; //5秒

//请求拦截器配置（拦截器会在向后台发送请求之前执行）
axios.interceptors.request.use((config) => {
    //请求前，先打开加载的窗口
    startLoading();
    //其他设置，比如设置请求头token(口令)
    //这里单独对put请求方式进行了参数序列化
    if (config.method === "put") {
        console.log("进入了put请求方式....");
        //将put请求的参数值：用qs序列化处理了
        config.data = qs.stringify(config.data, { arrayFormat: 'repeat' });
        config.headers = {
            // 'Content-Type': 'text/plain'
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }
    config.headers.token = localStorage.getItem('token');
    return config;
}, (error) => {
    console.log("axios请求错误：", error);
    return Message.error({ message: '请求出错了，请查看控制台日志!' });
});

/* 响应拦截设置
   1、因为Springboot后台使用了统一的响应主体，所以这个部分可以直接统一处理
*/
axios.interceptors.response.use((res) => {
    stopLoading(); //取消加载状态框
    /*  const message = res.data.msg || '服务器错误，请查看后台日志信息...'; //后端返回的message */

    //验证失败为10010,需要重新登录
    if (res.data.code === 10010) {
        Message.error({ message: '身份验证过期，请重新登录！' });
        router.push({
            path: '/login'
                //登录成功后跳入浏览的当前页面
                // query: {redirect: router.currentRoute.fullPath}
        })

    }

    if (res.status === 200) {
        /* 返回码为1*/
        if (res.data.code === 1) {
            /* 且携带返回提示信息*/
            /*   if (res.data.msg) {
                  Message.success({ message: message, duration: 4000 });
              } */
        } else if (res.data.msg) {
            Message.warning({ message: message, duration: 4000 });
        }
        //服务器错误
    } else if (res.status === 500) {
        Message.warning({ message: message });
        return Promise.reject(new Error(message))
    } else {
        Message.error({ message: "网络错误。。。" });
        return Promise.reject(new Error("网络错误。。。"))
    }
    //还可以返回res.data   那么Vue中获取后台响应的数据，不需要加data
    //return res.data;
    return res.data;
}, error => {
    stopLoading(); //取消加载状态框
    Notification.error({
        title: "响应失败",
        message: "请查看F12的参数提交情况",
        dangerouslyUseHTMLString: true,
    });
    return Promise.reject(new Error(error));
})

//导出axios
export default axios;