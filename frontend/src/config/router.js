
import Vue from "vue"
import VueRouter from "vue-router"

Vue.use(VueRouter);

export default new VueRouter({

    mode: 'history',

    routes: [
        {
            path: "login",
            name: "login",
            component: () => import("@/pages/login/Login")
        },
        {
            path: "/home",
            name: "home",
            component: () => import("@/pages/home/Article"),
        },
        {
            path: "/article-edit",
            name: "articleEdit",
            component: () => import("@/pages/home/ArticleEdit"),
        },
        {
            path: "/article-view",
            name: "articleView",
            component: () => import("@/pages/home/ArticleView"),
        },
    ]
});