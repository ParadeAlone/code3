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
                            <li class="active">修改密码</li>
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

							<div class="row">
								<div class="col-12 mb-20">
									<label>旧密码</label>
									<input class="mb-0" type="password" v-model="pwd.upass" placeholder="旧密码">
								</div>

                <div class="col-12 mb-20">
									<label>新密码</label>
									<input class="mb-0" type="password" v-model="pwd.newsupass" placeholder="新密码">
								</div>

                <div class="col-12 mb-20">
									<label>确认密码</label>
									<input class="mb-0" type="password" v-model="pwd.rnewsupass" placeholder="确认密码">
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
									<button class="register-button mt-0" @click="add" >提交</button>
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
      pwd: {
        upass: "",
        newsupass: "",
        rnewsupass: "",
      },
      sessionmember: null,
    };
  },
  created() {
      this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"));
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    async add() {
        if (this.pwd.upass == "") {
          this.$message.warning("请填写旧密码");
          return;
        }
        if (this.pwd.newsupass == "") {
          this.$message.warning("请填写新密码");
          return;
        }
        if (this.pwd.rnewsupass == "") {
          this.$message.warning("请填写确认密码");
          return;
        }

        if (this.pwd.rnewsupass != this.pwd.newsupass) {
          this.$message.warning("确认密码与新密码不一致");
          return;
        }
        let { data : res } = await this.$http.post("/member/updateUpass",
          this.qs.stringify({
            upass: this.pwd.upass,
            newsupass: this.pwd.newsupass,
            id: this.sessionmember.id,
          })
        );
        if(res.data == 200){
          this.$message.success("修改成功")
          this.pwd.upass = ""
          this.pwd.newsupass = ""
          this.pwd.rnewsupass = ""
        }else{
          this.$message.warning("旧密码输入错误")
        }
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