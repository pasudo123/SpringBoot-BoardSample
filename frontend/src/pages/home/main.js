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

    store.dispatch(`currentUser`).then((response) => {
        console.debug(response.data);
    }).catch((error) => {
        console.debug(error.response.data);
    });

    next();
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');