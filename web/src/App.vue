<template>
  <NavBar />
  <router-view />
</template>

<script>
//import { ref } from 'vue';
//import $ from 'jquery';
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"

export default {
  name: "App",
  components: {
    NavBar,
  }
}

</script>

<style>
body {
  background-image: url("@/assets/images/1_59b8f162fd-background.png");
  background-size: 100%;
}
</style>
