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
                            <li class="active">登录</li>
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
							<h4 class="login-title">登录</h4>

							<div class="row">
								<div class="col-md-12 col-12 mb-20">
									<label>登录名*</label>
									<input class="mb-0" type="text" v-model="member.uname" placeholder="登录名">
								</div>
								<div class="col-12 mb-20">
									<label>密码</label>
									<input class="mb-0" type="password" v-model="member.upass" placeholder="密码">
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
									<button class="register-button mt-0" @click="login" >登录</button>
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
        upass: '',
      },
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    async login () {
      if(this.member.uname == ""){
        this.$message.warning("请填写登录名")
        return;
      }
      if(this.member.upass == ""){
        this.$message.warning("请填写密码")
        return;
      }
      let { data : res } = await this.$http.post("/member/login", this.qs.stringify({
          uname: this.member.uname,
          upass: this.member.upass,
      }))
      if(res.data == 400){
        this.$message.warning("登录名或密码错误！")
      }else if(res.data == 300){
        this.$message.warning("帐号冻结！")
      }else{
        sessionStorage.setItem('sessionmember',JSON.stringify(res.sessionmember))
        await this.$http.post("/product/createDataFile")
        this.$router.push('/member/index')
      }
      for (let key in this.member) {
        this.member[key] = ''
      }
      this.imageUrl = ""
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
</style>