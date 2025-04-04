<template>
  <div class="centered-form">
    <h2>Novo Pedido de Aluguel</h2>

    <label for="veiculo-select">Veículo</label>
    <select id="veiculo-select" v-model="veiculoId">
      <option disabled value="">Selecione um veículo</option>
      <option v-for="v in veiculos" :key="v.id" :value="v.id">
        {{ v.modelo }} - {{ v.placa }}
      </option>
    </select>

    <label for="data-inicio">Data e Hora de Início</label>
    <input id="data-inicio" type="datetime-local" v-model="dataInicio" />

    <label for="data-fim">Data e Hora de Fim</label>
    <input id="data-fim" type="datetime-local" v-model="dataFim" />

    <button @click="criarPedido">Criar Pedido</button>
  </div>
</template>


<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '../services/api'

interface Veiculo {
  id?: number 
  ano: number
  marca: string
  modelo: string
  placa: string
  clienteId: number
}


export default defineComponent({
  setup() {
    const router = useRouter()
    const veiculos = ref<Veiculo[]>([])
    const veiculoId = ref<number | null>(null)
    const dataInicio = ref('')
    const dataFim = ref('')

    const cliente = JSON.parse(localStorage.getItem('usuario') || '{}')

    const carregarVeiculos = async () => {
      try {
        const res = await api.get(`/veiculos`)
        veiculos.value = res.data || []
      } catch (error) {
        alert('Erro ao carregar veículos')
        console.error(error)
      }
    }

    const criarPedido = async () => {
      try {
        await api.post('/pedido-aluguel', {
          clienteId: cliente.id,
          veiculoId: veiculoId.value,
          dataInicio: dataInicio.value,
          dataFim: dataFim.value
        })
        alert('Pedido criado com sucesso!')
        router.push('/dashboard')
      } catch (error) {
        alert('Erro ao criar pedido.')
        console.error(error)
      }
    }

    onMounted(carregarVeiculos)

    return {
      veiculos,
      veiculoId,
      dataInicio,
      dataFim,
      criarPedido
    }
  }
})
</script>
