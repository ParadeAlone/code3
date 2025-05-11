<template>
  <div>
    <section>
      <div class="colour"></div>
      <div class="colour"></div>
      <div class="colour"></div>

      <div class="box">
        <div class="square" style="--i:0;"></div>
        <div class="square" style="--i:1;"></div>
        <div class="square" style="--i:2;"></div>
        <div class="square" style="--i:3;"></div>
        <div class="square" style="--i:4;"></div>

        <div class="container">
          <div class="form">
            <div class="logOrgist">
              <h2><a href="javascript:void(0)" @click="skip('/admin/login')">登录</a></h2>
              <h2 class="reg"><a href="javascript:void(0)" class="active">销售人员注册</a></h2>
            </div>

			  <div class="input__box">
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

              <div class="input__box">
                <input type="text" v-model="user.uname" placeholder="登录名">
              </div>
              <div class="input__box">
                <input type="password" v-model="user.upass" placeholder="密码">
              </div>
			  <div class="input__box">
                <input type="text" v-model="user.tname" placeholder="姓名">
              </div>
			  <div class="input__box">
                <input type="text" v-model="user.tel" placeholder="电话">
              </div>
              <div class="input__box">
                <input type="submit" @click="onSubmit" value="注册">
              </div>
              <!-- <p class="forget">Forgot Password? <a href="#">Click Here</a></p>

              <p class="forget">Don't have an account? <a href="#">Sign Up</a></p> -->
          </div>
        </div>

      </div>
    </section>
  </div>
</template>
<script>
export default {
  data() {
        return {
            baseURL:"",
            imageUrl: "",
            user: {
                uname: '',
                upass: '',
                filename: '',
                tname: '',
                tel: '',
                roleid: '2',
            },
        }
    },

    created(){
        this.baseURL = this.$http.defaults.baseURL
    },
    methods: {

		skip(url){
			this.$router.push(url)
		},
        async onSubmit() {
			if (this.user.uname === "") {
				this.$message.warning("请输入登录名");
				return;
			}
			if (this.user.upass == "") {
				this.$message.warning("请输入密码");
				return;
			}
			if (this.user.tname == "") {
				this.$message.warning("请输入姓名");
				return;
			}
			if (this.user.tel == "" || !/^1[3456789]\d{9}$/.test(this.user.tel)) {
				this.$message.warning("电话输入错误");
				return;
			}
			if (this.user.roleid == "") {
				this.$message.warning("请选择角色");
				return;
			}
            let {data : res} = await this.$http.post('/user/selectAll', this.qs.stringify({
				uname: this.user.uname,
			}))
			if(res.status === '110'){
				this.$message.error('登录名重复，请更换');
				this.user.uname = ''
				return false;
			}
			await this.$http.post("/user/insert", this.qs.stringify({
				uname: this.user.uname,
				filename: this.user.filename,
				upass: this.user.upass,
				tname: this.user.tname,
				tel: this.user.tel,
				roleid: this.user.roleid,
				status: "正常",
				audit: "审核中"
			}))
			this.imageUrl = ""
			this.user.uname = ""
			this.user.filename = ""
			this.user.upass = ""
			this.user.tname = ""
			this.user.tel = ""
			this.user.roleid = ""

			this.$message.success("注册成功，待管理员审核")
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
}
</script>


<style scoped>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Poppins", sans-serif;
}

body {
	overflow: hidden;
}

section {
	display: flex;
	min-height: 100vh;
	align-items: center;
	justify-content: center;
	background: linear-gradient(to bottom, #f7f7fe, #dff1ff);
}

section .colour {
	position: absolute;
	filter: blur(150px);
}

section .colour:nth-child(1) {
	top: -350px;
	width: 600px;
	height: 600px;
	background: #bf4ad4;
}

section .colour:nth-child(2) {
	left: 100px;
	width: 500px;
	height: 500px;
	bottom: -150px;
	background: #ffa500;
}

section .colour:nth-child(3) {
	right: 100px;
	bottom: 50px;
	width: 300px;
	height: 300px;
	background: #2b67f3;
}

.box {
	position: relative;
}

.box .square {
	position: absolute;
	border-radius: 10px;
	backdrop-filter: blur(5px);
	background: rgba(255, 255, 255, 0.1);
	animation-delay: calc(-1s * var(--i));
	animation: animate 10s linear infinite;
	box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
	border: 1px solid rgba(255, 255, 255, 0.5);
	border-right: 1px solid rgba(255, 255, 255, 0.2);
	border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

@keyframes animate {
	0%,
	100% {
		transform: translateY(-40px);
	}

	50% {
		transform: translateY(40px);
	}
}

.box .square:nth-child(1) {
	top: -50px;
	left: -60px;
	width: 100px;
	height: 100px;
}

.box .square:nth-child(2) {
	z-index: 2;
	top: 150px;
	left: -100px;
	width: 120px;
	height: 120px;
}

.box .square:nth-child(3) {
	z-index: 2;
	width: 80px;
	height: 80px;
	right: -50px;
	bottom: -60px;
}

.box .square:nth-child(4) {
	left: 100px;
	width: 50px;
	height: 50px;
	bottom: -80px;
}

.box .square:nth-child(5) {
	top: -80px;
	left: 140px;
	width: 60px;
	height: 60px;
}

.container {
	width: 400px;
	display: flex;
	min-height: 400px;
	position: relative;
	border-radius: 10px;
	align-items: center;
	justify-content: center;
	backdrop-filter: blur(5px);
	background: rgba(255, 255, 255, 0.1);
	box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
	border: 1px solid rgba(255, 255, 255, 0.5);
	border-right: 1px solid rgba(255, 255, 255, 0.2);
	border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.form {
	width: 100%;
	height: 100%;
	padding: 40px;
	position: relative;
}

.form h2 {
	color: #fff;
	font-size: 24px;
	font-weight: 600;
	position: relative;
	letter-spacing: 1px;
	margin-bottom: 40px;
}

.active{
  border-bottom: 4px solid white;
}

.form .input__box {
	width: 100%;
	margin-top: 20px;
}

.form .input__box input {
	width: 100%;
	color: #fff;
	border: none;
	outline: none;
	font-size: 16px;
	padding: 10px 20px;
	letter-spacing: 1px;
	border-radius: 35px;
	background: rgba(255, 255, 255, 0.2);
	border: 1px solid rgba(255, 255, 255, 0.5);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
	border-right: 1px solid rgba(255, 255, 255, 0.2);
	border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.form::placeholder {
	color: #fff;
}

.form .input__box input[type="submit"] {
	color: #666;
	cursor: pointer;
	background: #fff;
	max-width: 100px;
	font-weight: 600;
	margin-bottom: 20px;
}

.forget {
	color: #fff;
	margin-top: 5px;
}

.forget a {
	color: #fff;
	font-weight: 600;
	text-decoration: none;
}

.logOrgist{
  display: flex;
}
.reg{
  margin-left: 20px;
}

.logOrgist h2 a {
  color: white;
  text-decoration: none;
}


.form select {
    width: 100%;
    color: gray;
    border: none;
    outline: none;
    font-size: 16px;
    padding: 10px 20px;
    letter-spacing: 1px;
    border-radius: 35px;
    background: rgba(255, 255, 255, 0.2);
    border: 1px solid rgba(255, 255, 255, 0.5);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
    border-right: 1px solid rgba(255, 255, 255, 0.2);
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.form .input__box input[data-v-256cf944] {
    color: gray;
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
	height: 175px;
}
</style>