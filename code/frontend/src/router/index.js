import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
import Dashboard from '@/views/Dashboard.vue'
import NewOrder from '@/views/NewOrder.vue'

const routes = [
  { path: '/', component: Login },
  { path: '/register', component: Register },
  { path: '/dashboard', component: Dashboard, meta: { requiresAuth: true } },
  { path: '/new-order', component: NewOrder, meta: { requiresAuth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// ⛔ Protege rotas privadas
router.beforeEach((to, from, next) => {
  const usuario = localStorage.getItem('usuario')
  const isAuthenticated = !!usuario

  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ path: '/' }) // redireciona para login
  } else {
    next()
  }
})

export default router
