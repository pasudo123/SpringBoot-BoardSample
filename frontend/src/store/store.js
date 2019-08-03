import Vue from 'vue'
import Vuex from 'vuex'
import articleModule from '@/store/modules/article.module'
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
            // getState: (key) => Cookies.getJSON(key),
            // setState: (key, state) => Cookies.set(key, state, {expires:3, secure: true})
        })
    ],

    modules: {
        articleModule,
    }
});