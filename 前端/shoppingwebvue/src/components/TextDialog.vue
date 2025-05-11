<template>
  <div>
    <el-dialog  :title="titleDialog"  :visible.sync="dialogFormVisible">
      <el-form ref="reply" :model="reply" :rules="rules">
        <el-form-item prop="content">
          <el-input :value="reply.content" v-model="reply.content"  type="textarea" :placeholder="placeholder" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
    data(){
        return{
	        dialogFormVisible:false,
        }
    },
    props:{
        placeholder:{
            type: String,
            default: null,
        },
        titleDialog:{
            type: String,
            default: null,
        },
        reply: {
            type: Object,
            default: null,
        },
        rules: {
            type: Object,
            default: null,
        },
    },
    methods: {
      onSubmit(){
        this.$refs.reply.validate((valid) => {
            if (valid) {
              this.$emit("onSubmit",this.reply)
            }
        })
      },
    },
    watch: {
    }
}
</script>
<style scoped>
:deep(.el-dialog__header){
  border-bottom: 1px solid rgb(244, 237, 237)
}
</style>