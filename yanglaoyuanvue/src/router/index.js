import Vue from 'vue'
import Router from 'vue-router'
import yanglaoyuan from '@/page/yanglaoyuan'
import login from '@/page/login'
import hugongguanli from '@/components/hugongguanli'
import Outregistration from '@/components/Outregistration'
import Floor from '@/components/Floor'
import BedType from '@/components/BedType'
import Yucungl from '@/components/Yucungl'
import FoodManage from '@/components/FoodManage'
import FoodPackge from '@/components/FoodPackge'
import FoodOrderManage from '@/components/FoodOrderManage'
import FoodOrder from '@/components/FoodOrder'

import dashboard from '@/components/Dashboard'
import consult from '@/components/consult'

import Huliguanli from '@/components/Huliguanli'

import Feiyonls from '@/components/Feiyonls'
import Jiaofeidj from '@/components/Jiaofeidj'
import Qianfeigl from '@/components/Qianfeigl'
import Visit from '@/components/Visit'
import Accident from '@/components/Accident'

import Hulijibie from '@/components/Hulijibie'
import checkins from '@/components/checkins'
import checkinsadd from '@/components/checkinsadd'
import Settlement from '@/components/Settlement'



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
                path: '/Hulijibie',
                component: Hulijibie
              },
            {
                path: '/foodOrderManage',
                component: FoodOrderManage
            },
            {
                path: '/foodOrder',
                component: FoodOrder
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
                path:'/BedType',
                component: BedType
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
                path: '/checkins',
                component: checkins
              },
              {
                path: '/checkinsadd',
                component: checkinsadd
              },
              {
                path: '/jfdj',
                component: Jiaofeidj
              },
              {
                path: '/qfgl',
                component: Qianfeigl
              },
              {
                path: '/visit',
                component: Visit
              },
              {
                path: '/accident',
                component: Accident
              },
              {
                path: '/settlement',
                component: Settlement
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
