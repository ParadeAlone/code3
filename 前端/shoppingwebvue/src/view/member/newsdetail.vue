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
                            <li class="active">公告详情</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

           <div class="blog-page-container mb-80">
            <div class="container">
                <div class="row">
                <div class="col-lg-12 order-1 order-lg-2">
                    <!--=======  blog post container  =======-->
                    
                    <div class="blog-single-post-container mb-80">

                        <!--=======  post title  =======-->
                        
                        
                        <h3 class="post-title">{{news.title}}</h3>
                        
                        <!--=======  End of post title  =======-->


                        <!--=======  Post meta  =======-->
                        <div class="post-meta">
                            <p><span><i class="fa fa-user-circle"></i> 发布者: </span> <a href="#">管理员</a> <span class="separator">/</span> <span><i class="fa fa-calendar"></i> 时间: <a href="#">{{news.savetime}}</a></span></p>
                        </div>
                        
                        <!--=======  End of Post meta  =======-->

                        <!--=======  Post media  =======-->
                        
                        <div class="single-blog-post-media mb-xs-20">
                            <div class="blog-image-gallery">
                                <div class="single-image">
                                    <img :src="baseURL+'/upload/'+news.filename" class="img-fluid" alt="">
                                </div>
                            </div>
                        </div>
                        
                        <div class="post-content mb-40">
                            <p v-html="news.content"></p>
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
      baseURL:"",
      news: null,
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    sessionStorage.setItem("banAcitive", "news");
    this.load();
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    async load(){
      let { data : res } = await this.$http.post("/news/get",this.qs.stringify({
        id: this.$route.query.id,
      }))
      this.news = res.data
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

.single-image{
  text-align: center;
}

</style>