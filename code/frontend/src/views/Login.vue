<template>
    <div class="centered-form">
      <h2>Login</h2>
      <input v-model="email" placeholder="Email" type="email" />
      <input v-model="password" placeholder="Senha" type="password" />
      <button @click="login">Entrar</button>
      <p>Não tem conta? <router-link to="/register">Cadastre-se</router-link></p>
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
      const router = useRouter()
  
      const login = async () => {
        try {
          const response = await api.post('/clientes/login', {
            email: email.value,
            senha: password.value
          })
  
          const cliente = response.data
  
          // Armazena os dados do usuário logado no localStorage
          localStorage.setItem('usuario', JSON.stringify(cliente))
  
          // Redireciona para o dashboard
          router.push('/dashboard')
        } catch (error) {
          alert('Email ou senha incorretos')
          console.error(error)
        }
      }
  
      return { email, password, login }
    }
  })
  </script>
  