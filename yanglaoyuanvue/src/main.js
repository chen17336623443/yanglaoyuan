//程序主要文件,主要用于引用全局组件

import Vue from 'vue'
import App from './App' /*子组件页面 src/App.Vue*/
import router from './router' /*路由 src/router/index.js*/
/*引用axios*/
import axios from 'axios'
import http from './util/http'
/* 3、全局导入element-ui */
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
/*4.文字图标*/
import * as socketApi from './util/socket'
// fade/zoom 等
import 'element-ui/lib/theme-chalk/base.css';
//引入qs
import qs from 'qs'
Vue.prototype.$qs = qs;
// collapse 展开折叠
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';
Vue.component(CollapseTransition.name, CollapseTransition)
    //定义一个时间过滤器
Vue.filter("FormatDate", function(date, fmt) {
    return formatDate(date, fmt);
});
/*将axios对象加载为Vue的全局对象  子组件可以通过this.$axios来访问对象*/
Vue.prototype.$axios = axios;
Vue.prototype.$http = http;
Vue.use(ElementUI);
Vue.prototype.$socketApi = socketApi
Vue.config.productionTip = false

function formatDate(date, fmt) {
    date = new Date(date);
    if (typeof(fmt) === "undefined") {
        fmt = "yyyy-MM-dd HH:mm:ss";
    }
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    let o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'H+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    }
    for (let k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : ('00' + str).substr(str.length));
        }
    }
    return fmt
};
/*整个工程的根组件 (祖先组件)*/
let vm = new Vue({
    el: '#app',
    /*挂载在index.html*/
    router,
    components: { App },
    template: '<App/>' /*模板中的内容替换了index.html中的挂载元素*/
})
