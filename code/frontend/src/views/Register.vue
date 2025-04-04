<template>
    <div class="centered-form">
      <h2>Cadastro de Cliente</h2>
  
      <input v-model="nome" placeholder="Nome" />
      <input v-model="email" placeholder="Email" type="email" />
      <input v-model="senha" placeholder="Senha" type="password" />
      <input v-model="endereco" placeholder="Endereço" />
      <input v-model="entidadeEmpregadora" placeholder="Entidade Empregadora" />
  
      <select v-model.number="tipoCliente">
        <option disabled value="">Tipo de Cliente</option>
        <option :value="0">Empresa</option>
        <option :value="1">Banco</option>
        <option :value="2">Pessoa</option>
      </select>
  
      <button @click="register">Cadastrar</button>
      <p>Já tem conta? <router-link to="/">Faça login</router-link></p>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue'
  import { useRouter } from 'vue-router'
  import api from '../services/api'
  
  export default defineComponent({
    setup() {
      const nome = ref('')
      const email = ref('')
      const senha = ref('')
      const endereco = ref('')
      const entidadeEmpregadora = ref('')
      const tipoCliente = ref<number | null>(null)
      const router = useRouter()
  
      const register = async () => {
        try {
          const payload = {
            nome: nome.value,
            email: email.value,
            senha: senha.value,
            endereco: endereco.value,
            entidadeEmpregadora: entidadeEmpregadora.value,
            tipoCliente: tipoCliente.value
          }
  
          await api.post('/clientes', payload)
  
          alert('Cliente cadastrado com sucesso!')
          router.push('/')
        } catch (error) {
          console.error(error)
          alert('Erro ao cadastrar cliente.')
        }
      }
  
      return {
        nome,
        email,
        senha,
        endereco,
        entidadeEmpregadora,
        tipoCliente,
        register
      }
    }
  })
  </script>
  