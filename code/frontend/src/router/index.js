import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Dashboard from '../views/Dashboard.vue'
import NewOrder from '../views/NewOrder.vue'

const routes = [
  { path: '/', component: Login },
  { path: '/register', component: Register },
  { path: '/dashboard', component: Dashboard },
  { path: '/new-order', component: NewOrder },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
