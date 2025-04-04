<template>
  <button class="logout-btn" @click="logout">
    Sair
  </button>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'LogoutButton',
  setup() {
    const router = useRouter()

    const logout = () => {
      localStorage.clear()
      router.push('/')
    }

    return { logout }
  }
})
</script>

<style scoped>
.logout-btn {
  padding: 10px 16px;
  background-color: #e74c3c;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 15px;
  font-weight: bold;
  transition: background-color 0.2s ease;
}

.logout-btn:hover {
  background-color: #c0392b;
}
</style>
