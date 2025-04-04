<template>
    <div class="user-info">
      <div class="avatar">{{ inicial }}</div>
      <span class="email">{{ email }}</span>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, computed } from 'vue'
  
  export default defineComponent({
    name: 'UserInfo',
    setup() {
      const usuario = JSON.parse(localStorage.getItem('usuario') || '{}')
      const email = usuario.email || 'Desconhecido'
      const inicial = computed(() => email.charAt(0).toUpperCase())
  
      return { email, inicial }
    }
  })
  </script>
  
  <style scoped>
  .user-info {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-right: 16px;
  }
  
  .avatar {
    background-color: #42b983;
    color: white;
    font-weight: bold;
    border-radius: 50%;
    width: 32px;
    height: 32px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .email {
    font-size: 14px;
    color: white;
  }
  </style>
  