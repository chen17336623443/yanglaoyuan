import Vue from 'vue'
import Router from 'vue-router'
import yanglaoyuan from '@/page/yanglaoyuan'
import login from '@/page/login'
import hugongguanli from '@/components/hugongguanli'
import Outregistration from '@/components/Outregistration'
import Floor from '@/components/Floor'


Vue.use(Router)

const router = new Router({
    routes: [{
            path: '/',
            redirect: '/login',
        }, {
            path: '/login',
            component: login
        },
        {
            path: '/home',
            component: yanglaoyuan,
            children: [
              {
                path: '/hugongguanli',
                component: hugongguanli
              },
              {
                path: '/outregistration',
                component: Outregistration
              },
              {
                path:'/Floor',
                component: Floor
              }
            ]
        }
    ]
})

const VueRouterPush = Router.prototype.push
Router.prototype.push = function push(to) {
        return VueRouterPush.call(this, to).catch(err => err)
    }
    // 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
    if (to.path === '/login') {
        next();
    } else {
        let token = localStorage.getItem('token');
        if (token === 'null' || token === '') {
            next('/login');
        } else {
            next();
        }
    }
});


export default router;
