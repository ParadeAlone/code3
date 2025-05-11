
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
                <li class="breadcrumb-item"><a href="javascript:void(0)" v-html="formTitle==='save'?'菜单新增':'菜单编辑'" ></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
               <el-form ref="menus" :rules="rules" :model="menus" label-width="80px">

                <el-form-item prop="serial" label="序号">
                    <el-input v-model="menus.serial" placeholder="名称"></el-input>
                </el-form-item>

                <el-form-item prop="name" label="名称">
                    <el-input v-model="menus.name" placeholder="名称"></el-input>
                </el-form-item>

                <el-form-item prop="icon" label="图标">
                    <el-input v-model="menus.icon" placeholder="图标"></el-input>
                </el-form-item>

                <el-form-item prop="url" label="URL">
                    <el-input v-model="menus.url" placeholder="URL"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary"  @click="onSubmit">提交</el-button>
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
            formTitle:'save',
            menus: {
                serial: '',
                name: '',
                icon: '',
                url: '',
                upid:0,
                leaf:0,
            },
            rules: {
                serial: [{required: true, message: '序号输入错误', pattern: /^[1-9]\d*$/,trigger: 'blur'}],
                name: [{required: true, message: '请输入名称', trigger: 'blur'}],
                icon: [{required: true, message: '请输入图标', trigger: 'blur'}],
                url: [{required: true, message: '请输入URL', trigger: 'blur'}],

            }
        }
    },

    created(){
        this.load()
    },

    methods: {

        async load(){
            if(this.$route.query.id!==undefined){
                this.formTitle = 'update'
                let {data : res} = await this.$http.post('menus/get',this.qs.stringify({
                    id: this.$route.query.id,
                }))
                this.menus = res.data
            }
        },

        onSubmit() {
            this.$refs.menus.validate(async (valid) => {
                if (valid) {
                    let url = '/menus/insert'
                    if(this.formTitle ==='update'){
                        url = '/menus/update'
                    }
                    await this.$http.post(url, this.qs.stringify({
                        serial: this.menus.serial,
                        name: this.menus.name,
                        icon: this.menus.icon,
                        url: this.menus.url,
                        upid: this.menus.upid,
                        leaf: this.menus.leaf,
                        id: this.menus.id,
                    }))
                    await this.$router.push('menusList')
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
