<template>
    <div class="centered-form">
      <h2>Cadastro de Agente</h2>
      <input v-model="nome" placeholder="Nome" />
      <input v-model="email" placeholder="Email" type="email" />
      <input v-model="senha" placeholder="Senha" type="password" />
      <button @click="cadastrar">Cadastrar</button>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue'
  import api from '../services/api'
  import { useRouter } from 'vue-router'
  
  export default defineComponent({
    setup() {
      const nome = ref('')
      const email = ref('')
      const senha = ref('')
      const router = useRouter()
  
      const cadastrar = async () => {
        try {
          await api.post('/agentes', {
            nome: nome.value,
            email: email.value,
            senha: senha.value
          })
          alert('Agente cadastrado com sucesso!')
          router.push('/')
        } catch (error) {
          alert('Erro ao cadastrar agente.')
        }
      }
  
      return { nome, email, senha, cadastrar }
    }
  })
  </script>
  