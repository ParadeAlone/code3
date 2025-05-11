
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
                <li class="breadcrumb-item"><a href="javascript:void(0)">修改密码</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">


              <el-form ref="pwd" :rules="rules" :model="pwd" label-width="80px">
                        <el-form-item prop="upass" label="旧密码">
                            <el-input type="password" v-model="pwd.upass"
                                        placeholder="旧密码"></el-input>
                        </el-form-item>
                        <el-form-item prop="newupass" label="新密码">
                            <el-input type="password" v-model="pwd.newupass"
                                        placeholder="新密码"></el-input>
                        </el-form-item>
                        <el-form-item prop="rnewupass" label="确认密码">
                            <el-input type="password" v-model="pwd.rnewupass"
                                        placeholder="确认密码"></el-input>
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
            baseURL: "",
            pwd: {
                upass: '',
                newupass: '',
                rnewupass: '',
            },
            rules: {
                upass: [{required: true, message: '请填写旧密码', trigger: 'blur'}],
                newupass: [{required: true, message: '请填写新密码', trigger: 'blur'}],
                rnewupass: [{required: true, message: '请填写确认密码', trigger: 'blur'}],
            },
            user:{
                upass: '',
            },
        }
    },
    created() {
        this.baseURL = this.$http.defaults.baseURL
        this.load()
    },
    methods: {
        async load() {
            let sessionadmin = JSON.parse(sessionStorage.getItem("sessionadmin"))
            let {data: res} = await this.$http.post('/user/get', this.qs.stringify({
                id: sessionadmin.id,
            }))
            this.user = res.data
        },
        onSubmit() {
            this.$refs.pwd.validate(async (valid) => {
                if (valid) {
                    if(this.user.upass !== this.pwd.upass){
                        this.$message.warning("旧密码输入错误")
                        return;
                    }
                    if(this.pwd.rnewupass !== this.pwd.newupass){
                        this.$message.warning("确认密码输入错误")
                        return;
                    }
                    await this.$http.post('/user/editupass', this.qs.stringify({
                        id: this.user.id,
                        newupass: this.pwd.newupass,
                    }))
                    await this.$message.success("编辑成功")
                    await this.load()
                }
            })
        },


    }
};
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

.el-upload.el-upload--text img{
    width: 175px;
}

</style>
