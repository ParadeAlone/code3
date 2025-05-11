<template>
  <div id="app">
      <!-- 路由出口 -->
      <!-- 路由匹配到的组件将渲染在这里 -->
      <router-view></router-view>
  </div>
</template>

<script>
export default {
    name:"App"
}
</script>

<style>
html, body, #app {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
</style>
