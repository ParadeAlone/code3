<template>
    <div>
      <el-date-picker
      v-model="innerValue"
      type="date"
      value-format="yyyy-MM-dd"
      placeholder="选择日期">
    </el-date-picker>
    </div>
</template>
<script>
export default {
    data(){
        return{

        }
    },
    props:{
        value:{
          type:String,
          default: null,
        }
    },
    computed: {
        innerValue: {
            get() {
                return this.value;
            },
            set(val) {
                this.$emit('input', val);
            }
        }
    }
}
</script>
<style scoped>

</style>