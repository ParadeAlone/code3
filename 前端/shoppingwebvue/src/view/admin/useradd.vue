
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
                <li class="breadcrumb-item"><a href="javascript:void(0)" v-html="formTitle==='save'?'销售人员新增':'销售人员编辑'" ></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
               <el-form ref="user" :rules="rules" :model="user" label-width="80px">
                        <el-form-item label="头像" prop="filename">
                            <el-upload
                                class="avatar-uploader"
                                :action="baseURL+['/util/uploadImg']"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                        </el-form-item>

                        <el-form-item prop="uname" label="登录名">
                            <el-input v-model="user.uname" :disabled="flag" placeholder="登录名"></el-input>
                        </el-form-item>

                        <el-form-item prop="upass" label="密码">
                            <el-input type="password" v-model="user.upass" placeholder="密码"></el-input>
                        </el-form-item>


                        <el-form-item prop="tname" label="姓名">
                            <el-input v-model="user.tname" placeholder="姓名"></el-input>
                        </el-form-item>

                        <el-form-item prop="tel" label="联系电话">
                            <el-input v-model="user.tel" placeholder="联系电话"></el-input>
                        </el-form-item>



                        <el-form-item prop="roleid" label="角色">
                              <el-select v-model="user.roleid" filterable placeholder="请选择角色">
                                <el-option  v-for="(item,index) in roleList" :key="index" :label="item.name" :value="item.id">
                                </el-option>
                             </el-select>
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
            imageUrl: "",
            formTitle:'save',
            user: {
                uname: '',
                upass: '',
                filename: '',
                tname: '',
                tel: '',
                roleid: '',
                audit: "已通过",
            },
            rules: {
                filename: [{required: true, message: '请上传头像', trigger: 'blur'}],
                uname: [{required: true, message: '请填写登录名', trigger: 'blur'}],
                upass: [{required: true, message: '请填写密码', trigger: 'blur'}],
                tname: [{required: true, message: '请填写姓名', trigger: 'blur'}],
                tel: [{required: true, message: '联系电话输入错误', pattern:/^1[3456789]\d{9}$/, trigger: 'blur'}],
                roleid: [{required: true, message: '请选择角色', trigger: 'blur'}],
            },
            flag:false,
            roleList: [],
        }
    },

    created(){
        this.baseURL = this.$http.defaults.baseURL
        this.load()
        this.getRoleList()
    },
    methods: {
        async getRoleList(){
            let { data : res } = await this.$http.post('/role/selectAll',this.qs.stringify({
                notRoleId : 1
            }))
            this.roleList = res.data
        },
        async load(){
            if(this.$route.query.id!==undefined){
                this.flag = true
                this.formTitle = 'update'
                let {data : res} = await this.$http.post('user/get',this.qs.stringify({
                    id: this.$route.query.id,
                }))
                this.user = res.data
                this.imageUrl = this.baseURL+'/upload/'+this.user.filename
            }
        },
        onSubmit() {
            this.$refs.user.validate(async (valid) => {
                if (valid) {
                    let url = '/user/insert'
                    if(this.formTitle ==='update'){
                        url = '/user/update'
                    }
                    if(url==='/user/insert'){
                        this.user.status = '正常'
                        let {data : res} = await this.$http.post('/user/selectAll', this.qs.stringify({
                            uname: this.user.uname,
                        }))
                        if(res.status === '110'){
                            this.$message.error('登录名重复，请更换');
                            this.user.uname = ''
                            return false;
                        }
                    }

                    await this.$http.post(url, this.qs.stringify({
                        uname: this.user.uname,
                        filename: this.user.filename,
                        upass: this.user.upass,
                        tname: this.user.tname,
                        tel: this.user.tel,
                        roleid: this.user.roleid,
                        status: this.user.status,
                        audit: this.user.audit,
                        id: this.user.id,
                    }))
                    await this.$router.push('userList')
                }
            })
        },

        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            this.user.filename = res.data.src
        },
        beforeAvatarUpload(file) {

            let isExist = true;
            const imgFormat = ['image/jpeg', 'image/png'];
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!imgFormat.includes(file.type)) {
                isExist = false;
            }

            if (!isExist) {
                this.$message.error('上传头像格式不符合!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像大小不能超过 2MB!');
            }
            return file.type && isLt2M;
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

.el-select {
    width: 100%;
}

</style>
