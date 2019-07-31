import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '@/views/Home'

Vue.use(VueRouter);

export default new VueRouter({

    mode: 'history',

    routes: [
        {
            path: '/',
            component: Home,
            children: [
                {
                    path: '',
                    name: 'articleList',
                    component: () => import('@/views/ArticleList')
                },
                {
                    path: 'article-edit',
                    name: 'articleEdit',
                    component: () => import('@/views/ArticleEdit')
                },
                {
                    path: 'article-view',
                    name: 'articleView',
                    component: () => import('@/views/ArticleView')
                }
            ]
        },
    ]
});