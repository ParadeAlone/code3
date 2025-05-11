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
                            <li class="active">在线留言</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>



           <div class="blog-page-container mb-80">
            <div class="container">
                <div class="row message">
                <div class="col-lg-9 order-1 order-lg-2">
                    
                    <div class="comment-section mb-md-80 mb-sm-80">
                        <h3 class="comment-counter">{{total}} 留言</h3>

                        <!--=======  comment container  =======-->
                        
                        <div class="comment-container mb-40">
                            <!--=======  single comment  =======-->
                            
                            <div v-for="(item,index) in tableData" :key="index" class="single-comment">
                                <div class="image">
                                    <img :src="baseURL + ['/upload/'] + item.filename" alt="">
                                </div>
                                <div class="content">
                                    <h3 class="user">{{item.memberName}} <span class="comment-time">{{item.savetime}}</span></h3>
                                    <p class="comment-text" v-html="item.content">
                                    </p>
                                      <p v-if="item.hfcontent!=null && item.hfcontent!=''">
                                      <el-tag type="success">{{item.hfcontent}}</el-tag>
                                    </p>
                                </div>
                            </div>


                            

                        </div>
                        
                        
                        <div class="comment-form-container">
                            <!-- <h3 class="comment-form-title">LEAVE A REPLY</h3>
                            <p>Your email address will not be published. Required fields are marked *</p> -->

                            
                            <div class="comment-form">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>留言内容</label>
                                                <textarea name="commentMessage" v-model="message.content" id="commentMessage"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <button type="submit" class="pataku-btn" @click="onSubmit" name="submit">提交</button>
                                        </div>
                                    </div>
                            </div>
                            
                            <!--=======  End of comment form  =======-->
                        </div>
                        
                        <!--=======  End of comment form container  =======-->

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
  name: 'BlogClassic',
  data() {
    return {
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页

      message: {
        content: ""
      },
      sessionmember: null,
      
      baseURL: "",
    };
  },
  components: {
    Footer,
    Header,
  },
  created(){
    sessionStorage.setItem("banAcitive","message")
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
    if(this.sessionmember){
       this.getMessageList()
    }
    this.baseURL = this.$http.defaults.baseURL
  },
  methods: {

    async onSubmit(){
      if(this.sessionmember !=null){
          if(this.message.content == ""){
              this.$message.warning("请填写留言内容")
              return;
          }
          await this.$http.post("/message/insert",this.qs.stringify({
              memberid: this.sessionmember.id,
              content: this.message.content,
          }))
          this.message.content = ""
          this.$message.success("留言成功")
          await this.getMessageList()
      }else{
        this.$router.push("/member/login")
      }
      

    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getMessageList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getMessageList();
    },

    async getMessageList() {
      let { data: res } = await this.$http.post(
        "/message/selectPage",
        this.qs.stringify({
          memberid: this.sessionmember.id,
          start: this.pageNum,
          limit: this.pageSize,
        })
      );
      this.total = res.data.totalSize;
      this.tableData = res.data.content;
    },
  }
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

.single-image{
  text-align: center;
}

.row.message{
  justify-content: center;
}
.el-tag {
    height: auto;
    line-height: auto;
    white-space: wrap;
}

.image img{
  width: 100px;
  border-radius: 50%;
}
</style>