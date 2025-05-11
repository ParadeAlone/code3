<template>

	<div class="header-container header-sticky">

		<!--=======  header top  =======-->
		
		<div class="header-top pt-15 pb-15">
			<div class="container">
				<div class="row">
					<div  class="col-12 col-md-6 text-center text-md-left mb-sm-15">
						<span v-if="sessionmember" class="header-top-text">欢迎 {{sessionmember.tname}} 登录系统 !</span>
					</div>
					<div class="col-12 col-md-6">

						<!--=======  header top dropdowns  =======-->
						
						<div v-if="!sessionmember" class="header-top-dropdown d-flex justify-content-center justify-content-md-end">
							<div class="single-dropdown">
								<a href="/member/login" ><span > 登录 </span></a>
							</div>
							<span class="separator pl-15 pr-15">|</span>
                            <div class="single-dropdown">
								<a  href="/member/register" ><span > 注册 </span></a>
							</div>
						</div>


						<div v-if="sessionmember" class="header-top-dropdown d-flex justify-content-center justify-content-md-end">
							
							
							<div class="single-dropdown">



								<el-dropdown  @command="handleCommand">
								<span class="el-dropdown-link">
									<a class="member-avt" href="#">
									<img :src="baseURL+'/upload/'+sessionmember.filename">
									</a>
								</span>
								<el-dropdown-menu slot="dropdown">
								<el-dropdown-item command='/member/cartList'>购物车</el-dropdown-item>
								<el-dropdown-item command='/member/addressList'>收货地址</el-dropdown-item>
								<el-dropdown-item command='/member/tOrderList'>我的订单</el-dropdown-item>
								<el-dropdown-item command='/member/collectList'>我的收藏</el-dropdown-item>
								<el-dropdown-item command='/member/membereEdit'>个人信息</el-dropdown-item>
								<el-dropdown-item command='/member/upassEdit'>修改密码</el-dropdown-item>
								<el-dropdown-item command='logout' >退出登录</el-dropdown-item>
								</el-dropdown-menu>
								</el-dropdown>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		

		
		<!--=======  End of Menu Top  =======-->

		<!--=======  navigation menu  =======-->
		
		<div class="navigation-menu">
			<div class="container">
				<div class="row">
					<div class="col-12 col-lg-3">
						<!--=======  category menu  =======-->
					
						<div class="hero-side-category">
							<!-- Category Toggle Wrap -->
							<div class="category-toggle-wrap">
								网络应用开发课程设计
							</div>
						</div>
						
					</div>
					<div class="col-12 col-lg-9">
                        <div class="main-menu">
							<nav>
								<ul>
									<!-- <li class="active menu-item-has-children"><a href="#">HOME</a>
										<ul class="sub-menu">
											<li><a href="/member/index">Home Shop 1</a></li>
											<li><a href="index-2.html">Home Shop 2</a></li>
											<li><a href="index-3.html">Home Shop 3</a></li>
											<li><a href="index-4.html">Home Shop 4</a></li>
										</ul>
									</li> -->
									<li :class="banAcitive=='index'?'active':''"><a   href="/member/index">首页</a></li>
									<li :class="banAcitive=='news'?'active':''"><a   href="/member/newsList">系统公告</a></li>
									<li :class="banAcitive=='product'?'active':''"><a   href="/member/productList">线上商城</a></li>
									<li :class="banAcitive=='message'?'active':''"><a   href="/member/messageList">在线留言</a></li>

								</ul>
							</nav>
						</div>
						<!-- end of navigation section -->
					</div>
					<div class="col-12 d-block d-lg-none">
						<!-- Mobile Menu -->
						<div class="mobile-menu"></div>
					</div>
				</div>
			</div>
		</div>
		
		<!--=======  End of navigation menu  =======-->
	</div>
    
</template>
<script>
export default {
  data() {
    return {
      sessionmember: null,
      banAcitive: "index",
      noticeList: [],
      notifyPromise: Promise.resolve(),
      cartList: [],
      total:"",
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"));
    if (this.sessionmember) {
      this.getCartList()
    }
    this.banAcitive = sessionStorage.getItem("banAcitive");
  },
  mounted() {},
  watch: {
    cartList(newValue,oldValue){
      this.$emit("getNewValue",newValue)
    },
    total(newValue,oldValue){
      this.$emit("getTotal",newValue)
    },
  },
  methods: {


    shipCollect(){
      this.$router.push("/member/collectList")
    },
    shipCart(){
      this.$router.push("/member/cartList")
    },
    async getCartList() {
      let { data: res } = await this.$http.post(
        "/cart/selectAll",
        this.qs.stringify({
          memberid: this.sessionmember.id,
        })
      );
      this.cartList = res.data
      let a = 0
      this.cartList.map(item =>{
        a += item.subTotal
      })
      this.total = a.toFixed(2)
    },

    async handleCommand(command) {
      if (command == "logout") {
        await this.$http.post("/member/logout");
        sessionStorage.removeItem("sessionmember");
        window.location.replace("/member/index");
      } else {
        this.$router.push(command);
      }
    },
  },
};
</script>
<style scoped>

.category-toggle-wrap{
	color:white;
	line-height: 60px;
	text-align: center;
	font-size: 16px;
}

.navigation-menu {
    border-bottom: 0;
	background: white;
}

.active{
	color: #664d03;
}

.member-avt img{
	width: 25px;
	border-radius: 50%;
}

.el-link.el-link--default.el-popover__reference{
	padding: 0px 20px;
	margin-top: 5px;
}


.bg-purple {
    background: transparent !important;
}
</style>