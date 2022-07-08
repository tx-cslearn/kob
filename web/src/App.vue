<template>
  <div>
    <div>Bot昵称：{{ bot_name }}</div>
    <div>Bot战力：{{ bot_rating }}</div>
  </div>
  <router-view></router-view>
</template>

<script>
import { ref } from 'vue';
import $ from 'jquery';

export default {
  name: "App",
  setup: () => {
    let bot_name = ref("");
    let bot_rating = ref("");

    $.ajax({
      url: "http://localhost:9999/pk/getbotinfo/",
      type: "get",
      success: resp => {
        bot_name.value = resp.name;
        bot_rating.value = resp.rating;
      }
    });

    return {
      bot_name,
      bot_rating
    }
  }
}
</script>

<style>
body {
  background-image: url("@/assets/1_59b8f162fd-background.png");
  background-size: cover;
}
</style>
