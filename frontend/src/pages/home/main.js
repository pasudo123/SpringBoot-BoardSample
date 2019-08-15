import Vue from 'vue'
import App from '@/pages/home/App.vue'
import router from '@/config/router'
import store from '@/store/store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify);
Vue.config.productionTip = false;

import '@/style/global.css'

router.beforeEach((to, from, next) => {

    store.dispatch(`currentUser`).catch((error) => {
        console.error(error.response.data);
        /** TODO 회원을 못 찾았기 때문에 로그인 페이지 이동 여부 확인 **/
    });

    next();
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');