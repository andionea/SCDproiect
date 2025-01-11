import { createApp } from 'vue'
import App from './App.vue'
import { loadFonts } from './plugins/webfontloader'
import router from './index'
import vuetify from './plugins/vuetify'

loadFonts()

createApp(App)
  .use(router)
  .use(vuetify)
  .mount('#app')

