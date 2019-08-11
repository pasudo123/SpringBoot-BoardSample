import Vue from 'vue'
import Vuex from 'vuex'
import articleModule from '@/store/modules/article.module'
import commentModule from '@/store/modules/comment.module'
import instance from '@/config/request'
import * as Cookies from 'js-cookie'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({

    state: {
        principal: {},
    }
    ,
    actions: {
        currentUser() {
            return new Promise((resolve, reject) => {
                instance.get(`/current-user`).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    reject(error);
                })
            });
        }
    },
    mutations: {},
    getters: {

    },

    plugins: [
        createPersistedState({
            paths:[`articleModule`],
        })
    ],

    modules: {
        articleModule,
        commentModule,
    }
});