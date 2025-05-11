
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
                <li class="breadcrumb-item"><a href="javascript:void(0)">商品入库</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
                  <el-form ref="inventory" :rules="rules" :model="inventory" label-width="80px">
                    <el-form-item prop="num" label="入库数量">
                        <el-input v-model="inventory.num" placeholder="入库数量"></el-input>
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
            inventory: {
                num: '',
            },
            rules: {
                num: [{required: true, message: '入库数量格式输入错误', pattern:/^[1-9]\d*$/, trigger: 'blur'}],
            },
        }
    },
    methods: {
        onSubmit() {
            this.$refs.inventory.validate(async (valid) => {
                if (valid) {
                    await this.$http.post('/inventory/insert', this.qs.stringify({
                        productid: this.$route.query.productid,
                        num: this.inventory.num,
                        flag: 'in',
                    }))
                    await this.$router.push('productList')
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
