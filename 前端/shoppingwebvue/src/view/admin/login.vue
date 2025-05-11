<template>
  <div class="container">
  <form class="login form">
    <h2 class="login__title">登录</h2>
    <div class="login__row">
      <label class="login__label" for="lg-em">登录名</label>
      <input class="login__input" id="lg-em" v-model="user.uname" type="text" placeholder="登录名"/>
    </div>
    <div class="login__row">
      <label class="login__label" for="lg-ps">密码</label>
      <input class="login__input" id="lg-ps" v-model="user.upass"  type="password" placeholder="密码"/>
    </div>
    <div class="login__row">
      <button class="login__button" @click="login" type="button">登录</button>
      <button class="login__button" @click="register" type="button">注册</button>

    </div>
    <!-- <div class="login__row"><a class="login__link">forgot your password? Восстановить пароль?</a></div> -->
  </form>


  <el-dialog :title="titleDialog" :visible.sync="dialogFormVisible">
                    <el-form ref="userinfo" :model="userinfo" :rules="rules">

                        <el-form-item label="图片" prop="filename" :label-width="formLabelWidth">
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

                        <el-form-item label="登录名" prop="uname" :label-width="formLabelWidth">
                            <el-input v-model="userinfo.uname" autocomplete="off"></el-input>
                        </el-form-item>

                        <el-form-item label="密码" prop="upass" :label-width="formLabelWidth">
                            <el-input type="password" v-model="userinfo.upass"></el-input>
                        </el-form-item>

                        <el-form-item label="姓名" prop="tname" :label-width="formLabelWidth">
                            <el-input v-model="userinfo.tname" autocomplete="off"></el-input>
                        </el-form-item>

                        <el-form-item label="联系电话" prop="tel" :label-width="formLabelWidth">
                            <el-input v-model="userinfo.tel" autocomplete="off"></el-input>
                        </el-form-item>


                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="onSubmit">注 册</el-button>
                    </div>
        </el-dialog>
</div>
</template>
<script>


export default {
  data() {
    return {
      baseURL: "",
      user: {
        uname: "",
        upass: "",
      },

	  imageUrl: "",
      titleDialog:"销售人员注册",
      dialogFormVisible: false,
      formLabelWidth: "100px",
      userinfo: {
          filename: "",
          uname: "",
          upass: "",
          tname: "",
          tel: "",
      },
      rules: {
          filename: [{required: true, message: '请上传图片', trigger: 'blur'}],
          uname: [{required: true, message: '请填写登录名', trigger: 'blur'}],
          upass: [{required: true, message: '请填写密码', trigger: 'blur'}],
          tname: [{required: true, message: '请填写姓名', trigger: 'blur'}],
          tel: [{required: true, message: '联系电话输入错误', pattern:/^1[3456789]\d{9}$/, trigger: 'blur'}],
      },

    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
  },
  methods: {

	register(){
            this.userinfo.filename = ""
            this.userinfo.uname = ""
            this.userinfo.upass = ""
            this.userinfo.tname = ""
            this.userinfo.tel = ""
            this.dialogFormVisible = true
        },

      onSubmit(){
          this.$refs.userinfo.validate(async (valid) => {
              if (valid) {
                  let { data : res } = await this.$http.post("/user/checkUser", this.qs.stringify({
                      uname: this.userinfo.uname,
                  }))
                  if(res.status == 110 ){
                      this.$message.warning("登录名已被注册，请更换！")
                  }else{
                      let { data : res } = await this.$http.post("/user/insert", this.qs.stringify({
                          uname: this.userinfo.uname,
                          upass: this.userinfo.upass,
                          tname: this.userinfo.tname,
                          tel: this.userinfo.tel,
                          filename: this.userinfo.filename,
                          status: "正常",
                          roleid: 2,
                          audit: "审核中",

                      }))
                      this.dialogFormVisible = false
                      this.$message.success("注册成功,待管理员审核")
                      for(let key in this.userinfo){
                          this.userinfo[key] = ""     
                      }
                  }


              }
          })
      },



    async login() {
      if (this.user.uname === "") {
        this.$message.warning("请输入登录名");
        return;
      }
      if (this.user.upass === "") {
        this.$message.warning("请输入密码");
        return;
      }
      let { data: res } = await this.$http.post(
        "/user/login",
        this.qs.stringify({
          uname: this.user.uname,
          upass: this.user.upass,
        })
      );
      if (res.data === 200) {
        sessionStorage.setItem("sessionadmin",JSON.stringify(res.sessionadmin))
        this.$store.commit('pushpermissionMenusList',res.permissionMenusList);

        sessionStorage.setItem("permissionMenusList",JSON.stringify(res.permissionMenusList))
        sessionStorage.setItem("permissionList",JSON.stringify(res.permissionList))
        // 设置 Cookie
        // this.Cookie.set("adminToken", res.adminToken, { expires: 2 / 24 }); // 2小时过期
        window.location.href = "/admin/mainList";
      } else if (res.data === 400) {
        this.$message.warning("登录名或密码错误");
      } else if (res.data === 600) {
        this.$message.warning("未通过审核");
      } else {
        this.$message.warning("帐号冻结");
      }
    },

	handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        this.userinfo.filename = res.data.src
    },
    beforeAvatarUpload(file) {

        let isExist = true;
        const imgFormat = ['image/jpeg', 'image/png'];
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!imgFormat.includes(file.type)) {
            isExist = false;
        }

        if (!isExist) {
            this.$message.error('上传头像图片格式不符合!');
        }
        if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return file.type && isLt2M;
    },

  },
};
</script>


<style scoped>

.container{
	padding-top: 160px;
}
/***********************/
/***********************/
*,
*::after,
*::before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/***********************/
/***********************/
body {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 17px;
  height: 100vh;
  width: 100%;
  background-color: #ebeff5;
}

.container {
  position: relative;
  width: 325px;
}

.svg-icon {
  width: 35px;
  height: 35px;
  cursor: pointer;
}
.svg-icon:hover {
  transform: scale(1.15);
}

.svg-icon path,
.svg-icon polygon,
.svg-icon rect {
  fill: #ffffff;
}

.login,
.signup {
  width: 100%;
  display: grid;
  grid-gap: 15px;
  padding: 35px;
  border-radius: 12px;
  box-shadow: 0 0 25px rgba(0, 0, 0, 0.07);
}
.login__icon,
.signup__icon {
  font-size: 1.225rem;
  font-weight: bold;
  letter-spacing: -0.025rem;
  line-height: 1;
}
.login__title,
.signup__title {
  margin-bottom: 17.5px;
  font-size: 1.424rem;
  font-weight: bold;
  letter-spacing: -0.075rem;
  line-height: 1;
}
.login__title::first-letter,
.signup__title::first-letter {
  text-transform: capitalize;
}
.login__row,
.signup__row {
  display: grid;
  grid-gap: 10px;
}
.login__label,
.signup__label {
  font-family: "Montserrat", sans-serif;
  font-size: 0.79rem;
  font-weight: bold;
  text-transform: capitalize;
}
.login__input, .login__button,
.signup__input,
.signup__button {
  height: 40px;
  font-size: 0.79rem;
  border-radius: 8px;
  cursor: pointer;
}
.login__input,
.signup__input {
  padding: 0 17.5px;
  background-color: transparent;
}
.login__button,
.signup__button {
  height: 45px;
  font-size: 0.702rem;
  letter-spacing: 0.075rem;
  font-weight: bold;
  text-transform: uppercase;
}
.login__link,
.signup__link {
  font-size: 0.702rem;
  text-align: center;
  cursor: pointer;
}
.login__link::first-letter,
.signup__link::first-letter {
  text-transform: capitalize;
}

.login {
  background-color: #ffffff;
}
.login__icon {
  color: #3f66ff;
}
.login__title {
  color: #201568;
}
.login__label {
  color: #201568;
}
.login__input, .login__button {
  border: 1px solid #201568;
  outline-color: #3f66ff;
}
.login__input {
  color: #13006b;
  caret-color: #3f66ff;
}
.login__input::placeholder {
  color: #00009a;
}
.login__button {
  background-color: #201568;
  color: #ffffff;
}
.login__link {
  color: #b4afc9;
}

.signup {
  position: absolute;
  top: 70%;
  right: 0;
  transform: translateY(-50%);
  width: 100%;
  height: 550px;
  transform-origin: top right;
  transition: 250ms;
  background-color: #3f66ff;
}
.signup__wrapper {
  width: 100%;
  display: grid;
  grid-gap: 15px;
  visibility: visibility;
  opacity: 1;
  transition: 350ms cubic-bezier(0.17, 0.67, 0.68, 1.39) 100ms;
}
.signup__icon {
  color: #ffffff;
}
.signup__title {
  color: #ffffff;
  text-transform: capitalize;
}
.signup__row--flex {
  display: flex;
  justify-content: space-between;
}
.signup__trigger--fixed {
  position: absolute;
  transition: 100ms cubic-bezier(0.17, 0.67, 0.68, 1.39) 600ms;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
}
.signup__label {
  color: #ffffff;
}
.signup__input, .signup__button {
  border: 3px solid #ffffff;
  outline-color: #ffffff;
}
.signup__input {
  color: rgba(255, 255, 255, 0.85);
  caret-color: #ffffff;
}
.signup__input::placeholder {
  color: rgba(255, 255, 255, 0.5);
}
.signup__button {
  background-color: #ffffff;
  color: #3f66ff;
}
.signup__link {
  color: rgba(255, 255, 255, 0.85);
}

.is-form-open {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: -15px;
  top: -15px;
  width: 70px;
  height: 70px;
  transform: translateY(0);
  overflow: hidden;
  border-radius: 24px;
  transform-origin: top right;
  transition: 250ms cubic-bezier(0.17, 0.67, 0.68, 1.39) 100ms;
}

.is-wrapper-open {
  visibility: hidden;
  opacity: 0;
  transform: scale(0.85);
  transition: 250ms;
}

.is-trigger-hidden {
  width: 0;
  height: 0;
  opacity: 0;
  right: -100%;
  transition: 100ms cubic-bezier(0.15, 0.65, 0.65, 1.25);
}


:deep(.avatar-uploader .el-upload) {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

:deep(.avatar-uploader .el-upload:hover) {
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

:deep(.el-upload__input) {
    display: none;
}

:deep(.avatar) {
    width: 178px;
    height: 178px;
    display: block;
}


</style>