import Vue from 'vue'
import Router from 'vue-router'
import yanglaoyuan from '@/page/yanglaoyuan'
import login from '@/page/login'
import hugongguanli from '@/components/hugongguanli'
import Outregistration from '@/components/Outregistration'
import Floor from '@/components/Floor'

import Yucungl from
    '@/components/Yucungl'
import FoodManage from '@/components/FoodManage'
import FoodPackge from '@/components/FoodPackge'

import dashboard from '@/components/Dashboard'
import consult from '@/components/consult'

import Huliguanli from '@/components/Huliguanli'

import Feiyonls from '@/components/Feiyonls'
import Visit from '@/components/Visit'
import Accident from '@/components/Accident'

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
            component: yanglaoyuan
            ,
            children: [
              {
                path: '/',
                component: dashboard
              },
              {
                path: '/hugongguanli',
                component: hugongguanli
              },
            {
                path: '/foodManage',
                component: FoodManage
            },
            {
                path: '/foodPackge',
                component: FoodPackge
            },
            {
                path: '/outregistration',
                component: Outregistration
              },
              {
                path:'/Floor',
                component: Floor
              },

              {
                path: '/ycgl',
                component: Yucungl
              },
              {
                path: '/outregistration',
                component: Outregistration
              },
              {
                path: '/consult',
                component: consult
              },
              {
                path: '/fyls',
                component: Feiyonls
              },
              {
                path: '/Huliguanli',
                component: Huliguanli
              },
              {
                path: '/visit',
                component: Visit
              },
              {
                path: '/accident',
                component: Accident
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
