<template>
    <li>
      Pedido #{{ pedido.id }} - Veículo: {{ pedido.veiculo }} - Status: {{ pedido.status }}
    </li>
  </template>
  
  <script>
  export default {
    props: ['pedido']
  }
  </script>
  