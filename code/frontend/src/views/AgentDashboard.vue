<template>
    <div class="dashboard-container">
      <h2 class="title">Pedidos de Aluguel Pendentes</h2>
  
      <div v-if="pedidos.length === 0" class="sem-pedidos">
        Nenhum pedido pendente no momento.
      </div>
  
      <div class="pedido-lista">
        <div v-for="pedido in pedidos" :key="pedido.id" class="pedido-card">
          <div class="pedido-info">
            <h3>Pedido #{{ pedido.id }}</h3>
            <p><strong>Veículo ID:</strong> {{ pedido.veiculoId }}</p>
            <p><strong>Cliente ID:</strong> {{ pedido.clienteId }}</p>
            <p><strong>Período:</strong> {{ pedido.dataInicio }} até {{ pedido.dataFim }}</p>
            <p><strong>Status:</strong> {{ pedido.status }}</p>
          </div>
          <div class="acoes">
            <button @click="atualizarStatus(pedido.id, 'APROVADO')" class="aprovado">Aprovar</button>
            <button @click="atualizarStatus(pedido.id, 'REJEITADO')" class="rejeitado">Rejeitar</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, onMounted } from 'vue'
  import api from '../services/api'
  
  interface Pedido {
    id: number
    clienteId: number
    veiculoId: number
    dataInicio: string
    dataFim: string
    status: string
  }
  
  export default defineComponent({
    setup() {
      const pedidos = ref<Pedido[]>([])
  
      const carregarPedidos = async () => {
        try {
          const res = await api.get('/pedido-aluguel')
          // Filtra apenas os pedidos com status "PENDENTE"
          pedidos.value = res.data.filter((p: Pedido) => p.status === 'Pendente')
        } catch (error) {
          alert('Erro ao carregar pedidos.')
          console.error(error)
        }
      }
  
      const atualizarStatus = async (id: number, novoStatus: string) => {
        try {
          const pedido = pedidos.value.find(p => p.id === id)
          if (!pedido) return
  
          await api.put(`/pedido-aluguel/${id}`, {
            clienteId: pedido.clienteId,
            veiculoId: pedido.veiculoId,
            dataInicio: pedido.dataInicio,
            dataFim: pedido.dataFim,
            status: novoStatus
          })
  
          alert(`Pedido #${id} ${novoStatus.toLowerCase()} com sucesso!`)
          await carregarPedidos()
        } catch (error) {
          alert('Erro ao atualizar status do pedido.')
          console.error(error)
        }
      }
  
      onMounted(carregarPedidos)
  
      return { pedidos, atualizarStatus }
    }
  })
  </script>
  
  <style scoped>
  .dashboard-container {
    max-width: 900px;
    margin: 40px auto;
    padding: 0 20px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  
  .title {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 24px;
    color: #2c3e50;
  }
  
  .sem-pedidos {
    font-size: 18px;
    color: #888;
    text-align: center;
    margin-top: 40px;
  }
  
  .pedido-lista {
    display: flex;
    flex-direction: column;
    gap: 16px;
  }
  
  .pedido-card {
    background: #fff;
    border-left: 5px solid #42b983;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.07);
    padding: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .pedido-info h3 {
    margin: 0 0 8px;
    color: #333;
  }
  
  .pedido-info p {
    margin: 4px 0;
    color: #555;
    font-size: 15px;
  }
  
  .acoes {
    display: flex;
    flex-direction: column;
    gap: 8px;
  }
  
  .aprovado {
    background-color: #2ecc71;
    color: white;
    border: none;
    padding: 8px 14px;
    border-radius: 6px;
    font-weight: bold;
    cursor: pointer;
  }
  
  .rejeitado {
    background-color: #e74c3c;
    color: white;
    border: none;
    padding: 8px 14px;
    border-radius: 6px;
    font-weight: bold;
    cursor: pointer;
  }
  
  .aprovado:hover {
    background-color: #27ae60;
  }
  
  .rejeitado:hover {
    background-color: #c0392b;
  }
  </style>
  