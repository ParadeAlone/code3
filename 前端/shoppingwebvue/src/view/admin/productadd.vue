
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
                <li class="breadcrumb-item"><a href="javascript:void(0)"  v-html="formTitle==='save'?'商品新增':'商品编辑'" ></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
               <el-form ref="product" :rules="rules" :model="product" label-width="80px">
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

                        <el-form-item prop="name" label="名称">
                            <el-input v-model="product.name"  placeholder="名称"></el-input>
                        </el-form-item>

                        <el-row :gutter="20">
                            <el-col :span="12">
                                <div class="grid-content bg-purple">
                                    <el-form-item prop="categoryid1" label="类别">
                                            <el-select v-model="product.categoryid1"  @change="getchildcategory" filterable placeholder="请选择一级类别">
                                            <el-option
                                            v-for="(item,index) in categoryList"
                                            :key="index"
                                            :label="item.name"
                                            :value="item.id">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                </div>
                                </el-col>
                            <el-col :span="12">
                                <div class="grid-content bg-purple">
                                        <el-form-item prop="categoryid2">
                                            <el-select v-model="product.categoryid2" filterable placeholder="请选择二级类别">
                                            <el-option
                                            v-for="(item,index) in childCategoryList"
                                            :key="index"
                                            :label="item.name"
                                            :value="item.id">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                </div>
                                </el-col>
                        </el-row>


                         <el-form-item prop="price" label="价格">
                            <el-input v-model="product.price" placeholder="价格"></el-input>
                        </el-form-item>

                        <el-form-item prop="tprice" label="特价">
                            <el-input v-model="product.tprice" placeholder="特价"></el-input>
                        </el-form-item>

                        <el-form-item prop="content" label="描述">
                            <my-editor :content="product.content" @getContent="getContent"></my-editor>
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
import MyEditor from '@/components/WangEditor.vue'
import DatePicker from '@/components/DatePicker.vue'
export default {
  data() {
        return {
            baseURL:"",
            imageUrl: "",
            formTitle:'save',
            product: {
                name: '',
                filename: '',
                categoryid1: '',
                categoryid2: '',
                price: '',
                tprice: 0,
                content: '',
                audit: "审核中",
                issj: "已上架",
            },
            rules: {
                filename: [{required: true, message: '请上传图片', trigger: 'blur'}],
                name: [{required: true, message: '请填写名称', trigger: 'blur'}],
                categoryid1: [{required: true, message: '请选择分类', trigger: 'blur'}],
                categoryid2: [{required: true, message: '请选择分类', trigger: 'blur'}],
                price: [{required: true, message: '价格输入错误', pattern:/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, trigger: 'blur'}],
                tprice: [{required: true, message: '特价输入错误', pattern:/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, trigger: 'blur'},
                    { validator: (rule, value, callback) => {
                        if (parseFloat(this.product.price)<=parseFloat(this.product.tprice)) {
                        callback(new Error('特价不能大于价格'));
                        } else {
                        callback();
                        }
                    }, trigger: 'blur' }
                ],
                content: [{required: true, message: '请填写描述', trigger: 'blur'}],
            },
            categoryList: [],
            childCategoryList: [],
            admin: null,
        }
    },

    components: {
        MyEditor,
        DatePicker
    },
    created(){
        this.admin = JSON.parse(sessionStorage.getItem("sessionadmin"))
        this.baseURL = this.$http.defaults.baseURL
        this.load()
    },
    methods: {
        async getchildcategory(value){
            this.product.categoryid2 = ""
            let {data : res} = await this.$http.post('/category/selectAll',this.qs.stringify({
                fatherid: value
            }))
            this.childCategoryList = res.data
        },
         getContent(value){
            this.product.content = value
        },
        async load(){
            this.$http.post('/category/selectAll',this.qs.stringify({
                fatherid: 0,
            })).then( async res =>{
                this.categoryList = res.data.data
                if(this.$route.query.id!==undefined){
                    this.formTitle = 'update'
                    let {data : res} = await this.$http.post('/product/get',this.qs.stringify({
                        id: this.$route.query.id,
                    }))
                    this.product = res.data
                    this.imageUrl = this.baseURL + '/upload/' + this.product.filename

                    let {data : resulst} = await this.$http.post('/category/selectAll',this.qs.stringify({
                        fatherid: this.product.categoryid1
                    }))
                    this.childCategoryList = resulst.data 
                }
            })
        },
        onSubmit() {
            this.$refs.product.validate(async (valid) => {
                if (valid) {
                    let url = '/product/insert'
                    if(this.formTitle ==='update'){
                        url = '/product/update'
                    }
                    await this.$http.post(url, this.qs.stringify({
                        name: this.product.name,
                        filename: this.product.filename,
                        categoryid1: this.product.categoryid1,
                        categoryid2: this.product.categoryid2,
                        price: this.product.price,
                        tprice: this.product.tprice,
                        content: this.product.content,
                        issj: this.product.issj,
                        audit: this.product.audit,
                        sellerid: this.admin.id,
                        id: this.product.id,
                    }))
                    await this.$router.push('productList')
                }
            })
        },

        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            this.product.filename = res.data.src
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

.el-upload.el-upload--text img{
    width: 175px;
}

.el-select {
    width: 100%;
}

:deep(.el-date-editor.el-input, .el-date-editor.el-input__inner){
    width: 100% !important;

}
</style>
