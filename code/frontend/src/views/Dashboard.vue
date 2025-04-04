<template>
    <div class="dashboard-container">
        <div class="titulo-container">
            <h2 class="title">Meus Pedidos de Aluguel</h2>
            <router-link to="/new-order" class="novo-pedido-btn">+ Novo Pedido</router-link>
        </div>


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

        <h2 class="title">Meus Veículos</h2>
        <div class="veiculo-lista">
            <div v-for="veiculo in veiculos" :key="veiculo.id" class="veiculo-card">
                <p><strong>Modelo:</strong> {{ veiculo.modelo }}</p>
                <p><strong>Marca:</strong> {{ veiculo.marca }}</p>
                <p><strong>Placa:</strong> {{ veiculo.placa }}</p>
                <p><strong>Ano:</strong> {{ veiculo.ano }}</p>
            </div>
        </div>

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

interface Veiculo {
    id: number
    modelo: string
    marca: string
    placa: string
    ano: number
}

export default defineComponent({
    setup() {
        const pedidos = ref<Pedido[]>([])
        const veiculos = ref<Veiculo[]>([])

        const tipoTexto = (tipo: number): string => {
            return tipo === 1 ? 'Diária' : tipo === 2 ? 'Semanal' : 'Outro'
        }

        const carregarDados = async () => {
            try {
                const cliente = JSON.parse(localStorage.getItem('usuario') || '{}')
                const clienteId = cliente.id

                // Pedidos
                const pedidosResponse = await api.get('/pedidos')
                pedidos.value = pedidosResponse.data

                // Veículos
                const veiculosResponse = await api.get(`/veiculos/cliente/${clienteId}`)
                veiculos.value = veiculosResponse.data.veiculos || []
            } catch (error) {
                alert('Erro ao carregar dados.')
                console.error(error)
            }
        }

        onMounted(carregarDados)

        return { pedidos, veiculos, tipoTexto }
    }
})
</script>

<style scoped>
.dashboard-container {
    max-width: 1000px;
    margin: 40px auto;
    padding: 0 20px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.title {
    font-size: 30px;
    font-weight: 700;
    margin: 40px 0 20px;
    color: #2c3e50;
    text-align: left;
    border-bottom: 2px solid #42b983;
    padding-bottom: 8px;
}

.pedido-lista,
.veiculo-lista {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
    gap: 20px;
}

.pedido-card,
.veiculo-card {
    background: linear-gradient(135deg, #ffffff, #f8f9fa);
    border-radius: 12px;
    padding: 20px;
    box-shadow: 0 6px 14px rgba(0, 0, 0, 0.06);
    transition: all 0.3s ease;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border-left: 6px solid #42b983;
}

.pedido-card:hover,
.veiculo-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.08);
}

.pedido-info h3 {
    margin: 0 0 8px;
    font-size: 20px;
    color: #2c3e50;
}

.pedido-info p,
.veiculo-card p {
    margin: 6px 0;
    color: #4f4f4f;
    font-size: 15px;
}

.pedido-status {
    margin-top: 12px;
    align-self: flex-start;
}

.status {
    background-color: #42b983;
    color: white;
    padding: 6px 14px;
    border-radius: 20px;
    font-weight: bold;
    font-size: 13px;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.15);
}

.titulo-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.novo-pedido-btn {
  padding: 10px 20px;
  background-color: #0077ff;
  color: white;
  border-radius: 8px;
  font-size: 14px;
  font-weight: bold;
  text-decoration: none;
  transition: background-color 0.25s ease;
  box-shadow: 0 4px 10px rgba(0, 119, 255, 0.2);
}

.novo-pedido-btn:hover {
  background-color: #005fd4;
}



</style>