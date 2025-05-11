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
                            <li class="active">系统公告</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

     <div class="blog-page-container mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <!--=======  blog post container  =======-->

                    <div class="blog-post-container mb-15">

                        <div class="row">
                            <div v-for="(news,index) in tableData" :key="index" class="col-lg-4 col-md-6">
                                <!--=======  single blog post  =======-->

                                <div class="single-blog-post mb-35">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="single-blog-post-media mb-20">
                                                <div class="image">
                                                    <a href="javascript:void(0)" @click="skipDetail(news.id)"><img :src="baseURL+'/upload/'+news.filename" class="img-fluid" alt=""></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="single-blog-post-content">
                                                <h3 class="post-title"> <a href="javascript:void(0)" @click="skipDetail(news.id)"> {{news.title}}</a></h3>
                                                <div class="post-meta">
                                                    <p><span><i class="fa fa-user-circle"></i> </span> <a href="#">管理员</a> <span class="separator">/</span> <span><i class="fa fa-calendar"></i> <a href="#">{{news.savetime}}</a></span></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!--=======  End of blog post container  =======-->
                    <!--=======  Pagination container  =======-->

                    <div class="pagination-container pb-20 mb-md-80 mb-sm-80">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">

                                    <div style="text-align: center">
                                      <el-pagination
                                        background
                                        v-if="tableData.length > 0"
                                        @size-change="handleSizeChange"
                                        @current-change="handleCurrentChange"
                                        :current-page="pageNum"
                                        :page-sizes="[10, 20, 30, 40]"
                                        :page-size="pageSize"
                                        layout="total, sizes, prev, pager, next, jumper"
                                        :total="total"
                                      >
                                      </el-pagination>
                                    </div>

                                </div>
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
      baseURL:"",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    sessionStorage.setItem("banAcitive", "news");
    this.getNewsList()
  },
  components: {
    Footer,
    Header,
  },
  methods: {

    skipDetail(id){
      this.$router.push({
        path: "/member/newsDetail",
        query: {
          id: id,
        }
      })
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getNewsList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getNewsList();
    },

    async getNewsList() {
      let { data: res } = await this.$http.post(
        "/news/selectPage",
        this.qs.stringify({
          start: this.pageNum,
          limit: this.pageSize,
        })
      );
      this.total = res.data.totalSize;
      this.tableData = res.data.content;
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
</style>