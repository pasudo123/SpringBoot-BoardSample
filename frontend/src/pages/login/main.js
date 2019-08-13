
import Vue from 'vue'
import Login from '@/pages/login/Login.vue'
import router from '@/config/router'
import store from '@/store/store'

Vue.config.productionTip = false;

import '@/style/global.css'

new Vue({
    router,
    store,
    render: h => h (Login)
}).$mount('#app');