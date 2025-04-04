<template>
    <div class="centered-form">
      <h2>Novo Pedido de Aluguel</h2>
      <input v-model="cliente" placeholder="Nome do Cliente" />
      <input v-model="veiculo" placeholder="Veículo" />
      <select v-model="tipo">
        <option disabled value="">Selecione o tipo</option>
        <option value="1">Diária</option>
        <option value="2">Semanal</option>
      </select>
      <input v-model="dataInicio" type="date" />
      <input v-model="dataFim" type="date" />
      <button @click="criarPedido">Criar Pedido</button>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue'
  import { useRouter } from 'vue-router'
  import api from '../services/api'
  
  export default defineComponent({
    setup() {
      const cliente = ref('')
      const veiculo = ref('')
      const tipo = ref<number | null>(null)
      const dataInicio = ref('')
      const dataFim = ref('')
      const router = useRouter()
  
      const criarPedido = async () => {
        try {
          const token = localStorage.getItem('token')
          await api.post('/pedidos', {
            cliente: cliente.value,
            veiculo: veiculo.value,
            tipo: tipo.value,
            dataInicio: dataInicio.value,
            dataFim: dataFim.value
          }, {
            headers: { Authorization: `Bearer ${token}` }
          })
  
          alert('Pedido criado com sucesso!')
          router.push('/dashboard')
        } catch (error) {
          alert('Erro ao criar pedido.')
        }
      }
  
      return { cliente, veiculo, tipo, dataInicio, dataFim, criarPedido }
    }
  })
  </script>
  