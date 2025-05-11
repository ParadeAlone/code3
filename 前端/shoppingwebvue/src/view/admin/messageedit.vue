
<template>
  <div>
      <div class="page-header">
        <div class="page-block">
          <div class="row align-items-center">
            <div class="col-md-12">
              <div class="page-header-title">
                <!-- <h5>首页</h5> -->
              </div>
              <ul class="breadcrumb">
                <li class="breadcrumb-item"><a href="mainList"><i
                      class="feather icon-home"></i></a></li>
                <li class="breadcrumb-item"><a href="javascript:void(0)" >留言回复</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
               <el-form ref="message" :rules="rules" :model="message" label-width="80px">

                    <el-form-item prop="hfcontent" label="回复内容">
                        <el-input v-model="message.hfcontent" placeholder="回复内容"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button size="small" type="primary" @click="onSubmit">提交</el-button>

                    </el-form-item>
                </el-form>
            </div>
        </div>
      </div>
  </div>
</template>
<script>

export default {
    data() {
        return {
            baseURL:"",
            message: {
                hfcontent: '',
            },
            rules: {
                hfcontent: [{required: true, message: '请填写回复内容', trigger: 'blur'}],
            },
        }
    },

    created(){
        this.baseURL = this.$http.defaults.baseURL
    },
    methods: {
        onSubmit() {
            this.$refs.message.validate(async (valid) => {
                if (valid) {
                    await this.$http.post("/message/update", this.qs.stringify({
                        hfcontent: this.message.hfcontent,
                        id: this.$route.query.id,
                    }))
                    await this.$router.push('messageList')
                }
            })
        },
    }
}
</script>
<style scoped>
.breadcrumb-item a{
  text-decoration: none;
}

.card {
    padding: 20px;
    border-radius: 2px;
    box-shadow: 0 0 0 1px #e2e5e8;
    border: none;
    margin-bottom: 30px;
    transition: all 0.5s ease-in-out;
    --bs-body-color: #686c71;
}

.bg-purple {
  background: white  !important;
}


:deep(.el-input__inner){
    height: 32px !important;
    line-height: 32px !important;
    font-size: 12px !important;
}

.el-row {
    margin: 10px;
}



:deep(.el-pagination.is-background .el-pager li:not(.disabled).active){
    background-color: #746ca7;
    color: #FFF;
}

.el-pager li:hover {
    color: #746ca7 !important;
}

.page-header .page-header-title + .breadcrumb > .breadcrumb-item a :first-child{
    color: #746ca7;
}

.el-button--primary {
    background-color: #746ca7;
    border-color: #746ca7;
}

.el-button--primary:hover {
    background-color: #746ca7;
    border-color: #746ca7;
}

.el-button--primary:focus, .el-button--primary:hover {
    background: #746ca7;
    border-color: #746ca7;
    color: #FFF;
}
:deep(.avatar-uploader .el-upload){
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

:deep(.avatar-uploader .el-upload:hover){
    border-color: #409EFF;
}

:deep(.avatar-uploader-icon) {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

:deep(.el-upload.el-upload--text img){
    width: 178px;
    height: 178px;
}

</style>
