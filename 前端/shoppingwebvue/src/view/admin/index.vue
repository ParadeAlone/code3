<template>
  <div>
	<div class="loader-bg">
	</div>
	<nav class="pcoded-navbar menupos-fixed menu-dark menu-item-icon-style6 ">
		<div class="navbar-wrapper ">
			<div class="navbar-brand header-logo">
				<a href="mainList"  class="b-brand">
				      后台管理系统
				</a>
				<a class="mobile-menu" id="mobile-collapse" href="#!"><span></span></a>
			</div>
			<div class="navbar-content scroll-div   "
				id="layout-sidenav" >
          <div>
            <el-row class="tac">
          <el-col :span="24">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              background-color="#39465C"
              text-color="#fff"
              active-text-color="#19BCBF">

			<el-submenu class="home"   index="1">
				<template slot="title">
				<i class="el-icon-s-home"></i>
				<span>首页</span>
				</template>
			</el-submenu>


              <el-submenu v-for="(permissionMenus,indexPer) in  permissionMenusList" :key="indexPer" :index="`${indexPer+2}`">
                <template slot="title">
                  <i :class="permissionMenus.menus.icon"></i>
                  <span>{{permissionMenus.menus.name}}</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item v-for="(item,indexItem) in permissionMenus.permissions" :key="indexItem" :index="`${indexPer+2}`+'-'+`${indexItem+1}`" @click="skip(item.menus.url)">{{item.menus.name}}</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

<!-- 
              <el-submenu  index="1">
                <template slot="title">
                  <span>系统管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item  index="1-1'" @click="skip('roleList')">角色管理</el-menu-item>
                  <el-menu-item  index="1-2'" @click="skip('menusList')">菜单管理</el-menu-item>
                </el-menu-item-group>
              </el-submenu> -->



            </el-menu>
          </el-col>
        </el-row>
          </div>
				
				
				
			</div>
			
		</div>
	</nav>
	<!-- [ navigation menu ] end -->

	

	<!-- [ Header ] start -->
	<header class="navbar pcoded-header navbar-expand-lg navbar-light headerpos-fixed">
		
			<a class="mobile-menu" id="mobile-header" href="#!">
				<i class="feather icon-more-horizontal"></i>
			</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav ms-auto">
					<li>
						<div class="dropdown drp-user">

							<a href="javascript:void(0)" data-bs-toggle="dropdown" class="dropdown-toggle mob-toggler">
							欢迎 {{admin.tname}} 登录 ！
							</a>
							<div class="dropdown-menu dropdown-menu-end profile-notification">
								<div class="pro-head">
									<img :src="baseURL+['/upload/']+admin.filename" class="img-radius"
										alt="User-Profile-Image">
									<span>
										<span class="text-muted">{{admin.roleName}}</span>
										<span class="h6">{{admin.tname}}</span>
									</span>
								</div>
								<ul class="pro-body">
                  <li><a href="javascript:void(0)" @click="skip('adminEdit')" class="dropdown-item"><i class="feather icon-user"></i> 个人信息</a>
									</li>
									<li><a href="javascript:void(0)" @click="skip('upassEdit')" class="dropdown-item"><i class="feather icon-settings"></i>
											修改密码</a></li>
									<!-- <li><a href="message.html" class="dropdown-item"><i class="feather icon-mail"></i>
											My Messages</a></li>
									<li><a href="auth-signin.html" class="dropdown-item"><i
												class="feather icon-lock"></i> Lock Screen</a></li> -->
									<li><a href="javascript:void(0)" @click="logOut" class="dropdown-item"><i
												class="feather icon-power text-danger"></i> 退出</a></li>
								</ul>
							</div>
						</div>
					</li>
				</ul>
			</div>
			
	</header>
	<!-- [ Header ] end -->
	

	<!-- [ chat user list ] start -->
	<section class="header-user-list">
		<a href="#!" class="h-close-text"><i class="feather icon-x"></i></a>
		<ul class="nav nav-tabs" id="chatTab" role="tablist">
			<li class="nav-item">
				<a class="nav-link active text-uppercase" id="chat-tab" data-bs-toggle="tab" href="#chat" role="tab"
					aria-controls="chat" aria-selected="true"><i class="feather icon-message-circle me-2"></i>Chat</a>
			</li>
			<li class="nav-item">
				<a class="nav-link text-uppercase" id="user-tab" data-bs-toggle="tab" href="#user" role="tab"
					aria-controls="user" aria-selected="false"><i class="feather icon-users me-2"></i>User</a>
			</li>
			<li class="nav-item">
				<a class="nav-link text-uppercase" id="setting-tab" data-bs-toggle="tab" href="#setting" role="tab"
					aria-controls="setting" aria-selected="false"><i class="feather icon-settings me-2"></i>Setting</a>
			</li>
		</ul>
		<div class="tab-content" id="chatTabContent">
			<div class="tab-pane fade show active" id="chat" role="tabpanel" aria-labelledby="chat-tab">
				<div class="h-list-header">
					<div class="input-group">
						<input type="text" id="search-friends" class="form-control" placeholder="Search Friend . . .">
					</div>
				</div>
				<div class="h-list-body">
					<div class="main-friend-cont scroll-div">
						<div class="main-friend-list">
							<div class="d-flex userlist-box" data-id="1" data-status="online"
								data-username="Josephin Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-1.jpg" alt="Profile Image ">
									<div class="live-status">3</div>
								</a>
								<div class="flex-grow-1">
									<h6 class="chat-header">Josephin Doe<small class="d-block text-c-green">Typing . .
										</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="2" data-status="online" data-username="Lary Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-2.jpg" alt="Profile Image">
									<div class="live-status">1</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Lary Doe<small class="d-block text-c-green">online</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="3" data-status="online" data-username="Alice">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-3.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Alice<small class="d-block text-c-green">online</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="4" data-status="offline" data-username="Alia">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-1.jpg" alt="Profile Image">
									<div class="live-status">1</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Alia<small class="d-block text-muted">10 min ago</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="5" data-status="offline" data-username="Suzen">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-4.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Suzen<small class="d-block text-muted">15 min ago</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="1" data-status="online"
								data-username="Josephin Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-1.jpg" alt="Profile Image ">
									<div class="live-status">3</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Josephin Doe<small class="d-block text-c-green">Typing . .
										</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="2" data-status="online" data-username="Lary Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-2.jpg" alt="Profile Image">
									<div class="live-status">1</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Lary Doe<small class="d-block text-c-green">online</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="3" data-status="online" data-username="Alice">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-3.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Alice<small class="d-block text-c-green">online</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="4" data-status="offline" data-username="Alia">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-1.jpg" alt="Profile Image">
									<div class="live-status">1</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Alia<small class="d-block text-muted">10 min ago</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="5" data-status="offline" data-username="Suzen">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-4.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Suzen<small class="d-block text-muted">15 min ago</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="1" data-status="online"
								data-username="Josephin Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-1.jpg" alt="Profile Image ">
									<div class="live-status">3</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Josephin Doe<small class="d-block text-c-green">Typing . .
										</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="2" data-status="online" data-username="Lary Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-2.jpg" alt="Profile Image">
									<div class="live-status">1</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Lary Doe<small class="d-block text-c-green">online</small>
									</h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="3" data-status="online" data-username="Alice">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-3.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Alice<small class="d-block text-c-green">online</small></h6>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="user" role="tabpanel" aria-labelledby="user-tab">
				<div class="h-list-body">
					<div class="main-friend-cont scroll-div">
						<div class="main-friend-list">
							<div class="media px-3 d-flex align-items-center mt-3">
								<a class="media-left m-r-15" href="#!">
									<div
										class="hei-50 wid-50 bg-primary img-radius d-flex text-white f-22 align-items-center justify-content-center">
										<i class="icon feather icon-users"></i>
									</div>
								</a>
								<div class="media-body">
									<p class="chat-header f-w-600 mb-0">New Group</p>
								</div>
							</div>
							<div class="media p-3 d-flex align-items-center">
								<a class="media-left m-r-15" href="#!">
									<div
										class="hei-50 wid-50 bg-primary img-radius d-flex text-white f-22 align-items-center justify-content-center">
										<i class="icon feather icon-user-plus"></i>
									</div>
								</a>
								<div class="media-body">
									<p class="chat-header f-w-600 mb-0">New Contact</p>
								</div>
							</div>
							<div class="media userlist-box" data-id="1" data-status="online"
								data-username="Josephin Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-1.jpg" alt="Profile Image "></a>
								<div class="media-body">
									<p class="chat-header">Josephin Doe<small class="d-block">i am not what happened .
											.</small></p>
								</div>
							</div>
							<div class="media userlist-box" data-id="2" data-status="online" data-username="Lary Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-2.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Lary Doe<small class="d-block">Avalable</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="3" data-status="online" data-username="Alice">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-3.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Alice<small class="d-block">hear using Dasho</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="4" data-status="offline" data-username="Alia">
								<a class="media-left" href="#!">
									<div
										class="hei-50 wid-50 img-radius bg-success d-flex text-white f-22 align-items-center justify-content-center">
										A</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Alia<small class="d-block text-muted">Avalable</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="5" data-status="offline" data-username="Suzen">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-4.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Suzen<small class="d-block text-muted">Avalable</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="1" data-status="online"
								data-username="Josephin Doe">
								<a class="media-left" href="#!">
									<div
										class="hei-50 wid-50 bg-danger img-radius d-flex text-white f-22 align-items-center justify-content-center">
										JD</div>
								</a>
								<div class="media-body">
									<h6 class="chat-header">Josephin Doe<small class="d-block text-muted">Don't send me
											image</small></h6>
								</div>
							</div>
							<div class="media userlist-box" data-id="2" data-status="online" data-username="Lary Doe">
								<a class="media-left" href="#!"><img class="media-object img-radius"
										src="assets/images/user/avatar-2.jpg" alt="Profile Image"></a>
								<div class="media-body">
									<h6 class="chat-header">Lary Doe<small class="d-block text-muted">not send free
											msg</small></h6>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="setting" role="tabpanel" aria-labelledby="setting-tab">
				<div class="p-4 main-friend-cont scroll-div">
					<h6 class="mt-2"><i class="feather icon-monitor me-2"></i>Desktop settings</h6>
					<hr>
					<div class="form-group mb-0">
						<div class="switch switch-primary d-inline m-r-10">
							<input type="checkbox" id="cn-p-1" checked>
							<label for="cn-p-1" class="cr switch-alignment"></label>
						</div>
						<label class="f-w-600">Allow desktop notification</label>
					</div>
					<p class="text-muted ms-5">You get latest content at a time when data will updated</p>
					<div class="form-group mb-0">
						<div class="switch switch-primary d-inline m-r-10">
							<input type="checkbox" id="cn-p-5">
							<label for="cn-p-5" class="cr switch-alignment"></label>
						</div>
						<label class="f-w-600">Store Cookie</label>
					</div>
					<h6 class="mb-0 mt-5"><i class="feather icon-layout me-2"></i>Application settings</h6>
					<hr>
					<div class="form-group mb-0">
						<div class="switch switch-primary d-inline m-r-10">
							<input type="checkbox" id="cn-p-3" checked>
							<label for="cn-p-3" class="cr switch-alignment"></label>
						</div>
						<label class="f-w-600">Backup Storage</label>
					</div>
					<p class="text-muted mb-0 ms-5">Automaticaly take backup as par schedule</p>
					<div class="form-group mb-4">
						<div class="switch switch-primary d-inline m-r-10">
							<input type="checkbox" id="cn-p-4" checked>
							<label for="cn-p-4" class="cr switch-alignment"></label>
						</div>
						<label class="f-w-600">Allow guest to print file</label>
					</div>
					<h6 class="mb-0 mt-5"><i class="feather icon-globe me-2"></i>System settings</h6>
					<hr>
					<div class="form-group mb-0">
						<div class="switch switch-primary d-inline m-r-10">
							<input type="checkbox" id="cn-p-2">
							<label for="cn-p-2" class="cr switch-alignment"></label>
						</div>
						<label class="f-w-600">View other user chat</label>
					</div>
					<p class="text-muted ms-5">Allow to show public user message</p>
				</div>
			</div>
		</div>
	</section>
	<!-- [ chat user list ] end -->

	<!-- [ chat message ] start -->
	<section class="header-chat">
		<div class="h-list-header">
			<h6>Josephin Doe</h6>
			<a href="#!" class="h-back-user-list"><i class="feather icon-chevron-left"></i></a>
		</div>
		<div class="h-list-body">
			<div class="main-chat-cont scroll-div">
				<div class="main-friend-chat">
					<div class="d-flex chat-messages">
						<a class="media-left photo-table" href="#!"><img
								class="media-object img-radius img-radius m-t-5" src="assets/images/user/avatar-2.jpg"
								alt="Profile Image"></a>
						<div class="flex-grow-1 chat-menu-content">
							<div class="">
								<p class="chat-cont">hello tell me something</p>
								<p class="chat-cont">about yourself?</p>
							</div>
							<p class="chat-time">8:20 a.m.</p>
						</div>
					</div>
					<div class="d-flex chat-messages">
						<div class="flex-grow-1 chat-menu-reply">
							<div class="">
								<p class="chat-cont">Ohh! very nice</p>
							</div>
							<p class="chat-time">8:22 a.m.</p>
						</div>
						<a class="media-right photo-table" href="#!"><img
								class="media-object img-radius img-radius m-t-5" src="assets/images/user/avatar-1.jpg"
								alt="Profile Image"></a>
					</div>
					<div class="d-flex chat-messages">
						<a class="media-left photo-table" href="#!"><img
								class="media-object img-radius img-radius m-t-5" src="assets/images/user/avatar-2.jpg"
								alt="Profile Image"></a>
						<div class="flex-grow-1 chat-menu-content">
							<div class="">
								<p class="chat-cont">can you help me?</p>
							</div>
							<p class="chat-time">8:20 a.m.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="h-list-footer">
			<div class="input-group">
				<input type="file" class="chat-attach" style="display:none">
				<a href="#!" class="input-group-prepend btn btn-success btn-attach">
					<i class="feather icon-paperclip"></i>
				</a>
				<input type="text" name="h-chat-text" class="form-control h-send-chat" placeholder="Write hear . . ">
				<button type="submit" class="input-group-append btn-send btn btn-primary">
					<i class="feather icon-message-circle"></i>
				</button>
			</div>
		</div>
	</section>
	<!-- [ chat message ] end -->

<!-- [ Main Content ] start -->
<div class="pcoded-main-container">
	<div class="pcoded-wrapper">
		<div class="pcoded-content">
			<div class="pcoded-inner-content">
				<div class="main-body">
					<div class="page-wrapper">

            <router-view></router-view>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>





  </div>
</template>
<script>
import $ from 'jquery'
import './assets/js/vendor-all.js';
import './assets/plugins/bootstrap/js/popper.min.js';
import './assets/plugins/bootstrap/js/bootstrap.bundle.min.js';

export default {
  data() {
    return {
      admin: null,
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
    this.admin = JSON.parse(sessionStorage.getItem("sessionadmin"));
  },

  mounted(){
	let that = this
	$(".home.el-submenu").click(function(){
		that.$router.push("mainList")
	})

  },
  computed: {
    permissionMenusList(){
      return JSON.parse(sessionStorage.getItem("permissionMenusList"));
    }
  },
  methods: {


    skip(url) {
      this.$router.push(url);
    },
    async logOut() {
        let res = await this.$http.post('user/loginout')
        sessionStorage.removeItem('sessionadmin')
        sessionStorage.removeItem('permissionMenusList')
        sessionStorage.removeItem('permissionList')
        await this.$router.push('/admin/login')
    },
  },
};
</script>

<style scoped>
@import url('./assets/fonts/fontawesome/css/fontawesome-all.min.css');
@import url('./assets/plugins/animation/css/animate.min.css');
@import url('./assets/plugins/notification/css/notification.min.css');
@import url('./assets/css/style.css');


.el-menu {
    border-right: solid 0px #e6e6e6;
}

.b-brand{
  text-decoration: none;
  font-size: 24px;
}


/** 菜单样式 */

:deep(.el-submenu__title i){
    color: gray !important;
}

:deep(.el-menu-item-group__title){
    padding: 0px;
}

:deep(.el-submenu__title){
    border-bottom: 1px solid #746ca7;
    font-weight: bold;
    background-color: white !important;
}
:deep(.el-submenu__title span){
	color:gray;
}
:deep(.el-menu-item){
    padding: 0px 60px  !important;
    border-bottom: 1px solid #746ca7;
	color:gray !important;
	background: white !important;
}

:deep(.el-menu-item){
  text-decoration: none;
  font-size: 12px;
}

:deep(.el-submenu.is-active .el-submenu__title){
    border-bottom-color: #746ca7 !important;
}

:deep(.el-menu-item.is-active){
    background: #746ca7 !important;
	color: white !important;
}

/** 清除菜单首页的箭头 */
:deep(.home.el-submenu .el-submenu__title .el-submenu__icon-arrow.el-icon-arrow-down) {
  display: none !important;
}
/** 菜单样式 */

.navbar-content.scroll-div{
	background: #746ca7;
}
.navbar-brand.header-logo{
	background: #746ca7;
}

.navbar-brand.header-logo a{
    color:white;
}
.pcoded-main-container{
  background: #f8f9fa;
}

.dropdown-toggle{
  text-decoration: none;
}

.mob-toggler{
  margin-left: 30px;
  font-size: 12px;
  text-decoration: none;
}

.pcoded-header .dropdown .dropdown-toggle:after {
    content: "" !important;
}

.navbar.pcoded-header.navbar-expand-lg.navbar-light.headerpos-fixed{
	height: 30px !important;
}

.page-wrapper {
	background: transparent !important;
}

</style>
