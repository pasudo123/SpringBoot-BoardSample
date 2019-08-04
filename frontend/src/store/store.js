import Vue from 'vue'
import Vuex from 'vuex'
import articleModule from '@/store/modules/article.module'
import commentModule from '@/store/modules/comment.module'
import * as Cookies from 'js-cookie'

import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({

    state: {},
    actions: {},
    mutations: {},
    getters: {},

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