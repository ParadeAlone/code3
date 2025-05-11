import Vue from 'vue'
import App from './App.vue'
import Cookie from "js-cookie";
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { axios } from './util/axios'
import qs  from 'qs'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import feather from 'feather-icons';
import store from './store/store'
Vue.use(ElementUI);
Vue.use(feather);

Vue.use(VueQuillEditor);
Vue.prototype.$http = axios ;
Vue.prototype.qs = qs;
Vue.prototype.Cookie = Cookie;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
