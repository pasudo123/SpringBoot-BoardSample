import Vue from 'vue'
import Vuex from 'vuex'
import a0Module from '@/store/modules/a0.module'
import a1Module from '@/store/modules/a1.module'
import articleModule from '@/store/modules/article.module'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {},
    actions: {},
    mutations: {
    },
    getters: {},

    modules: {
        a0Module,
        a1Module,
        articleModule,
    }
});