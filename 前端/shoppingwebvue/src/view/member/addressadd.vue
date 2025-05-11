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
                            <li class="active">新增收货地址</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="page-section mb-80">
		<div class="container">

      <div class="row address">
				<div class="col-lg-9 col-md-8 pl-100 pl-sm-15">
					<!--=======  contact form content  =======-->
					
					<div class="contact-form-content">
						<h3 class="contact-page-title">新增收货地址</h3>

						<div class="contact-form">
								<div class="form-group">
									<label>姓名 <span class="required">*</span></label>
									<input type="text" v-model="address.name" name="customerName" id="customername" required>
								</div>
								<div class="form-group">
									<label>电话 <span class="required">*</span></label>
									<input type="text"  v-model="address.tel" name="customerEmail" id="customerEmail" required>
								</div>
								<div class="form-group">
									<label>收货地址</label>
									<input type="text" v-model="address.addr" name="contactSubject" id="contactSubject">
								</div>
								<div class="form-group mb-0">
									<button type="submit" value="submit"  @click="add()" id="submit" class="pataku-btn" name="submit">提交</button>
								</div>
						</div>
						<p class="form-messege pt-10 pb-10 mt-10 mb-10"></p>
					</div>
					
					<!--=======  End of contact form content =======-->
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
  data () {
    return {
      address: {
        name: '',
        addr: '',
        tel: '',
      },
      sessionmember: null,
    }
  },
  created(){
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
    this.address.name = this.sessionmember.tname
    this.address.tel = this.sessionmember.tel
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    async add () {
      if(this.address.name == ""){
        this.$message.warning("请填写姓名")
        return;
      }
      if(this.address.tel == "" || !/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(this.address.tel)){
        this.$message.warning("联系电话填写错误")
        return;
      }
      if(this.address.addr == ""){
        this.$message.warning("请填写收货地址")
        return;
      }
     
      await this.$http.post("/address/insert", this.qs.stringify({
          addr: this.address.addr,
          name: this.address.name,
          tel: this.address.tel,
          memberid: this.sessionmember.id,
          ismr: "否"
      }))
      await this.$router.push("/member/addressList")

    },
    
  },
}
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

.bg-purple {
    background: transparent !important;
}


.el-button--primary {
    background-color: #cea45f;
    border-color: #cea45f;
}

.el-button--primary:hover {
    background-color: #cb9743;
    border-color: #cea45f;
}

.el-button--primary:focus, .el-button--primary:hover {
    background: #cb9743;
    border-color: #cb9743;
    color: #FFF;
}

.row.address{
  justify-content: center;
}
</style>