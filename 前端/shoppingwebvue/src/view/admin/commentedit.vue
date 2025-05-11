
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
                <li class="breadcrumb-item"><a href="javascript:void(0)">评价回复</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
               <el-form ref="comment" :rules="rules" :model="comment" label-width="80px">
                  <el-form-item prop="hfcontent" label="回复内容">
                      <el-input v-model="comment.hfcontent" type="textarea" placeholder="回复内容"></el-input>
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
            comment: {
                hfcontent: '',
                id: this.$route.query.id,
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
            this.$refs.comment.validate(async (valid) => {
                if (valid) {
                    await this.$http.post('/comment/update', this.qs.stringify({
                        hfcontent: this.comment.hfcontent,
                        id: this.comment.id,
                    }))
                    await this.$router.push('commentList')
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

</style>
