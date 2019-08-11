import Vue from 'vue'
import App from '@/pages/home/App.vue'
import router from '@/config/router'
import store from '@/store/store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify);

import DateFilter from '@/common/date.filter'
import ErrorFilter from '@/common/error.filter'

Vue.config.productionTip = false;

Vue.filter("date", DateFilter);
Vue.filter("error", ErrorFilter);

import '@/style/global.css'

router.beforeEach((to, from, next) => {
    console.debug("전역가드 호출");



    next();
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');