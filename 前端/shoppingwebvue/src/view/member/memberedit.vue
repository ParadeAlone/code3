<template>
  <div>
  <Header></Header>


    <div class="breadcrumb-area breadcrumb-bg pt-85 pb-85 mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-container">
                        <ul>
                            <li><a href="/member/index">首页</a> <span class="separator">/</span></li>
                            <li class="active">个人信息</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="page-section mb-80">
		<div class="container">
			<div class="row login">
				<div class="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb-30">
					<!-- Login Form s-->

						<div class="login-form">
							<h4 class="login-title">个人信息</h4>

							<div class="row">

                <div class="col-md-12 col-12 mb-20">
                  <el-upload
                      class="avatar-uploader"
                      :action="baseURL+['/util/uploadImg']"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess"
                      :before-upload="beforeAvatarUpload">
                      <img v-if="imageUrl" :src="imageUrl" class="avatar">
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
								</div>

								<div class="col-md-12 col-12 mb-20">
									<label>登录名*</label>
									<input class="mb-0" type="text" readonly v-model="member.uname" placeholder="登录名">
								</div>


                <div class="col-md-12 col-12 mb-20">
									<label>姓名*</label>
									<input class="mb-0" type="text" v-model="member.tname" placeholder="姓名">
								</div>

                <div class="col-md-12 col-12 mb-20">
									<label>电话*</label>
									<input class="mb-0" type="text" v-model="member.tel" placeholder="电话">
								</div>

								<!-- <div class="col-md-8">
									
									<div class="check-box d-inline-block ml-0 ml-md-2 mt-10">
										<input type="checkbox" id="remember_me">
										<label for="remember_me">Remember me</label>
									</div>
									
								</div>

								<div class="col-md-4 mt-10 mb-20 text-left text-md-right">
									<a href="#"> Forgotten pasward?</a>
								</div> -->

								<div class="col-md-12">
									<button class="register-button mt-0" @click="register" >提交</button>
								</div>

							</div>
						</div>

				</div>
			</div>
		</div>
	</div>

	
		
	<div class="footer-container pt-60 pb-60">
		
		<Footer></Footer>
		
	</div>
	
	

  </div>
</template>
<script>
import Header from "./header";
import Footer from "./footer";
export default {
  data() {
    return {
      member: {
        uname: '',
        rupass: '',
        tname: '',
        filename: '',
        tel: '',
      },
      sessionmember: null,
      imageUrl: "",
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"));
    this.member = this.sessionmember
    this.imageUrl = this.baseURL + '/upload/' + this.sessionmember.filename
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    async register () {
      if(!this.sessionmember){
        this.$router.push("/member/login")
      }else{
        if(this.member.filename == ""){
          this.$message.warning("请上传头像")
          return;
        }
        if(this.member.tname == ""){
          this.$message.warning("请填写昵称")
          return;
        }
        if(this.member.tel == "" || !/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(this.member.tel)){
          this.$message.warning("联系电话填写错误")
          return;
        }
        await this.$http.post("/member/update", this.qs.stringify({
            filename: this.member.filename,
            tname: this.member.tname,
            tel: this.member.tel,
            id: this.member.id,
        }))

        await this.$alert('修改成功，请重新登录', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            sessionStorage.removeItem("sessionmember")
            this.$router.push("/member/login")
          }
        });
      }
      
    },
    
    handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        this.member.filename = res.data.src
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
  },
};
</script>
<style scoped>
@import url('./assets/css/bootstrap.min.css');
@import url('./assets/css/font-awesome.min.css');
@import url('./assets/css/linear-icon.css');
@import url('./assets/css/plugins.css');
@import url('./assets/css/helper.css');
@import url('./assets/css/main.css');

:deep(.breadcrumb-area) {
	padding-top:200px !important;
}

.breadcrumb-bg {
    background-image: unset !important;
}


:deep(.el-pagination.is-background .el-pager li:not(.disabled).active){
        background-color: #cea45f;
      color: #FFF;
}

:deep(.el-pager li:hover){
      color: #FFF;
}

.row.login{
  justify-content: center;
}

:deep(.el-upload.el-upload--text img){
  width: 175px;
}
</style>