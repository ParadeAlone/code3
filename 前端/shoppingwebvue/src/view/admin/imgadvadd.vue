
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
                <li class="breadcrumb-item"><a href="javascript:void(0)" v-html="formTitle==='save'?'轮播图新增':'轮播图编辑'" ></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
                <el-form ref="imgadv" :rules="rules" :model="imgadv" label-width="80px">
                    <el-form-item label="图片" prop="filename">
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

                    <el-form-item prop="url" label="URL">
                        <el-input v-model="imgadv.url"  placeholder="URL"></el-input>
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
            imgadv: {
                filename: '',
                url: '',
            },
            rules: {
                filename: [{required: true, message: '请上传图片', trigger: 'blur'}],
                url: [{required: true, message: '请填写URL', trigger: 'blur'}],
            },
        }
    },
    created(){
        this.baseURL = this.$http.defaults.baseURL
        this.load()
    },
    methods: {
        async load(){
            if(this.$route.query.id!==undefined){
                this.formTitle = 'update'
                let {data : res} = await this.$http.post('/imgadv/get',this.qs.stringify({
                    id: this.$route.query.id,
                }))
                this.imgadv = res.data
                this.imageUrl = this.baseURL + '/upload/' + this.imgadv.filename
            }
        },
        onSubmit() {
            this.$refs.imgadv.validate(async (valid) => {
                if (valid) {
                    let url = '/imgadv/insert'
                    if(this.formTitle ==='update'){
                        url = '/imgadv/update'
                    }
                    await this.$http.post(url, this.qs.stringify({
                        url: this.imgadv.url,
                        filename: this.imgadv.filename,
                        id: this.imgadv.id,
                    }))
                    await this.$router.push('imgadvList')
                }
            })
        },

        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            this.imgadv.filename = res.data.src
        },
        beforeAvatarUpload(file) {

            let isExist = true;
            const imgFormat = ['image/jpeg', 'image/png'];
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!imgFormat.includes(file.type)) {
                isExist = false;
            }

            if (!isExist) {
                this.$message.error('上传图片格式不符合!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
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

:deep(.el-upload.el-upload--text img){
    width: 178px;
    height: 178px;
}

</style>
