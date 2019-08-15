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
        currentUser: {},
    },
    actions: {
        currentUser({commit}) {
            return new Promise((resolve, reject) => {
                instance.get(`/auth/current-user`).then((response) => {
                    commit(`setCurrentUser`, response.data);
                    resolve();
                }).catch((error) => {
                    reject(error);
                })
            });
        }
    },
    mutations: {
        setCurrentUser(state, currentUser){
            state.currentUser = currentUser;
        }
    },
    getters: {
        currentUser: state => state.currentUser,
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