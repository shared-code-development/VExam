import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      hidden: true
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      hidden: true
    },
    {
      path: '/home',
      name: '主页',
      component: Home,
      hidden: true,
      /*children: [
        {
          path: '/chat',
          name: '消息',
          component: Chat,
          hidden: true,
          meta: {
            keepAlive: false,
            requireAuth: true
          }
        }
      ]*/
    },
  ]
})
