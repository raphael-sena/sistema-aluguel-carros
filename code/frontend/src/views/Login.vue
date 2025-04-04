<template>
  <div class="centered-form">
    <h2>Login</h2>
    
    <input v-model="email" placeholder="Email" type="email" />
    <input v-model="password" placeholder="Senha" type="password" />
    
    <label for="tipoLogin">Tipo de Login:</label>
    <select id="tipoLogin" v-model="tipoLogin">
      <option value="cliente">Cliente</option>
      <option value="agente">Agente</option>
    </select>

    <button @click="login">Entrar</button>

    <p>Não tem conta? <router-link to="/register">Cadastre-se como Cliente</router-link></p>
    <p>É um agente? <router-link to="/agent-register">Cadastre-se como Agente</router-link></p>
  </div>
</template>


<script lang="ts">
import { defineComponent, ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../services/api'

export default defineComponent({
  setup() {
    const email = ref('')
    const password = ref('')
    const tipoLogin = ref('cliente')
    const router = useRouter()

    const login = async () => {
      try {
        const endpoint = tipoLogin.value === 'agente' ? '/agentes/login' : '/clientes/login'
        const response = await api.post(endpoint, {
          email: email.value,
          senha: password.value
        })

        localStorage.setItem('usuario', JSON.stringify(response.data))
        localStorage.setItem('tipo', tipoLogin.value)
        router.push(tipoLogin.value === 'agente' ? '/agent-dashboard' : '/dashboard')
      } catch (error) {
        alert('Falha no login. Verifique os dados.')
        console.error(error)
      }
    }

    return { email, password, tipoLogin, login }
  }

})
</script>