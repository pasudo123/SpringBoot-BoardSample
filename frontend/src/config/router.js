import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '@/views/login/Login'
import Home from '@/views/article/Home'

Vue.use(VueRouter);

export default new VueRouter({

    mode: 'history',

    routes: [
        {
            path: '/login',
            component: Login
        },
        {
            path: '/',
            component: Home,
            children: [
                {
                    path: `test`,
                    name: `testView`,
                    component: () => import(`@/views/TestView`)
                },
                {
                    path: '',
                    name: 'articleList',
                    component: () => import('@/views/article/ArticleList')
                },
                {
                    path: 'article-edit',
                    name: 'articleEdit',
                    component: () => import('@/views/article/ArticleEdit')
                },
                {
                    path: 'article-view',
                    name: 'articleView',
                    component: () => import('@/views/article/ArticleView')
                }
            ]
        },
        /** 그 이외 모든 경로들 **/
        { path: '*', redirect: '/' }
    ]
});