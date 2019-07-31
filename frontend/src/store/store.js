import Vue from 'vue'
import Vuex from 'vuex'
import articleModule from '@/store/modules/article.module'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {},
    actions: {},
    mutations: {},
    getters: {},
    modules: {
        articleModule,
    }
});