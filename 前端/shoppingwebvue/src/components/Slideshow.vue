<template>
  <div>
    <el-carousel  height="600px">
      <el-carousel-item v-for="(item,index) in imgadvList" :key="index">
        <img :src="baseURL+'/upload/'+item.filename">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>

export default {
    // 使用 props 属性：接收父组件传递过来的数据
    props: ['imgadvList', 'baseURL']
}
</script>
<style scoped>
</style>
