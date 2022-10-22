import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Base64 from 'js-base64'
createApp(App).use(Base64).use(store).use(router).mount('#app')
