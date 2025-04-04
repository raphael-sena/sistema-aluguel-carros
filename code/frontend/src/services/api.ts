import axios from 'axios'

// Cria a instância do Axios com a base da sua API
const api = axios.create({
  baseURL: 'http://localhost:8080', // ajuste conforme necessário
})


export default api
