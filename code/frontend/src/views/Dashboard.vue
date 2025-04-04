<template>
    <div class="dashboard-container">
      <h2 class="title">Meus Pedidos de Aluguel</h2>
  
      <div class="pedido-lista">
        <div v-for="pedido in pedidos" :key="pedido.id" class="pedido-card">
          <div class="pedido-info">
            <h3>Pedido #{{ pedido.id }}</h3>
            <p><strong>Veículo:</strong> {{ pedido.veiculo }}</p>
            <p><strong>Tipo:</strong> {{ tipoTexto(pedido.tipo) }}</p>
            <p><strong>Período:</strong> {{ pedido.dataInicio }} até {{ pedido.dataFim }}</p>
          </div>
          <div class="pedido-status">
            <span class="status">{{ pedido.status }}</span>
          </div>
        </div>
      </div>
  
      <router-link to="/new-order" class="novo-pedido-btn">+ Novo Pedido</router-link>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, onMounted } from 'vue'
  import api from '../services/api'
  
  interface Pedido {
    id: number
    cliente: string
    veiculo: string
    tipo: number
    dataInicio: string
    dataFim: string
    status: string
  }
  
  export default defineComponent({
    setup() {
      const pedidos = ref<Pedido[]>([])
  
      const carregarPedidos = async () => {
        try {
          const response = await api.get('/pedidos')
          pedidos.value = response.data
        } catch (error) {
          alert('Erro ao buscar pedidos.')
        }
      }
  
      const tipoTexto = (tipo: number): string => {
        return tipo === 1 ? 'Diária' : tipo === 2 ? 'Semanal' : 'Outro'
      }
  
      onMounted(carregarPedidos)
  
      return { pedidos, tipoTexto }
    }
  })
  </script>
  
  <style scoped>
  .dashboard-container {
    max-width: 900px;
    margin: 40px auto;
    padding: 0 20px;
  }
  
  .title {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 24px;
    color: #2c3e50;
  }
  
  .pedido-lista {
    display: flex;
    flex-direction: column;
    gap: 16px;
  }
  
  .pedido-card {
    display: flex;
    justify-content: space-between;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.07);
    padding: 20px;
    transition: all 0.2s ease-in-out;
  }
  
  .pedido-card:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 14px rgba(0, 0, 0, 0.1);
  }
  
  .pedido-info h3 {
    margin: 0 0 8px;
    font-size: 20px;
    color: #333;
  }
  
  .pedido-info p {
    margin: 4px 0;
    color: #555;
  }
  
  .pedido-status {
    display: flex;
    align-items: center;
  }
  
  .status {
    background-color: #42b983;
    color: white;
    padding: 8px 14px;
    border-radius: 20px;
    font-weight: bold;
    font-size: 14px;
  }
  
  .novo-pedido-btn {
    display: inline-block;
    margin-top: 30px;
    padding: 12px 24px;
    background-color: #0077ff;
    color: white;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    text-decoration: none;
    transition: background-color 0.2s ease;
  }
  
  .novo-pedido-btn:hover {
    background-color: #005fd4;
  }
  </style>
  