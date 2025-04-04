<template>
    <nav class="navbar">
      <div class="nav-left">
        <router-link to="/dashboard" class="nav-link">Dashboard</router-link>
        <router-link to="/new-order" class="nav-link">Novo Pedido</router-link>
      </div>
      <div class="nav-right">
        <LogoutButton />
      </div>
    </nav>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue'
  import LogoutButton from './LogoutButton.vue'

  
  export default defineComponent({
    components: {
      LogoutButton
    }
  })
  </script>
  
  <style scoped>
  .navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #2c3e50;
    padding: 16px 24px;
    color: white;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }
  
  .nav-link {
    color: white;
    text-decoration: none;
    margin-right: 20px;
    font-weight: bold;
    font-size: 16px;
    transition: color 0.2s ease;
  }
  
  .nav-link:hover {
    color: #42b983;
  }
  
  .nav-right {
    display: flex;
    align-items: center;
  }
  </style>
  