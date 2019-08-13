import Vue from 'vue'
import App from '@/pages/error/App.vue'
import router from '@/config/router'
import store from '@/store/store'


Vue.config.productionTip = false;


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');