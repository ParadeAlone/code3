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
                            <li class="active">商品列表</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="shop-page-content mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    
                    <div class="shop-header mb-20">
                      <div class="row">
                        <el-row :gutter="20">
                    <el-col :span="4">
                      <div class="grid-content bg-purple">
                        <el-input
                           clearable
                          v-model="key"
                          placeholder="名称"
                        ></el-input>
                      </div>
                    </el-col>

                    <el-col :span="4">
                        <div class="grid-content bg-purple">
                            <el-select v-model="categoryid1" clearable placeholder="请选择一级类别" @change="getchildcategory">
                                <el-option
                                        v-for="(category1,index) in categoryList"
                                        :key= "index"
                                        :label="category1.name"
                                        :value="category1.id">
                                </el-option>
                            </el-select>
                        </div>
                    </el-col>

                    <el-col :span="4">
                        <div class="grid-content bg-purple">
                            <el-select v-model="categoryid2" clearable placeholder="请选择二级类别">
                                <el-option v-for="(category2,index) in childCategoryList"
                                            :key="index"
                                            :label="category2.name"
                                            :value="category2.id">
                                </el-option>
                            </el-select>
                        </div>
                    </el-col>


                    <el-col :span="3">
                      <div class="grid-content bg-purple">
                        <el-input
                        clearable
                          v-model="minPrice"
                          placeholder="最低价格"
                        ></el-input>
                      </div>
                    </el-col>


                    <el-col :span="3">
                      <div class="grid-content bg-purple">
                        <el-input
                        clearable
                          v-model="maxPrice"
                          placeholder="最高价格"
                        ></el-input>
                      </div>
                    </el-col>



                    <el-col :span="3">
                      <div class="grid-content bg-purple">
                        <el-button @click="getProductList" type="primary" size="medium"
                          ><i class="el-icon-search"></i
                        ></el-button>
                      </div>
                    </el-col>
              </el-row>
                      </div>
					          </div>
                    
                    
                    <div class="shop-product-wrap grid row">
                        <div v-for="(product,index) in tableData" :key="index" class="col-lg-3 col-md-6 col-sm-6 col-12">
                            
                            <div class="ptk-product shop-grid-view-product">
                                <div class="image">
                                    <a href="javascript:void(0)" @click="skipDetail(product.id)">
                                        <img :src="baseURL+'/upload/'+product.filename" class="img-fluid" alt="">
                                    </a>
                                    <!--=======  hover icons  =======-->
                                
                                    <a class="hover-icon" href="#" data-toggle = "modal" data-target="#quick-view-modal-container"><i class="lnr lnr-eye"></i></a>
                                    <a class="hover-icon" href="javascript:void(0)" @click="handleCollectAdd(product.id)"><i class="lnr lnr-heart"></i></a>
                                    <a class="hover-icon" href="javascript:void(0)" @click="handleCartAdd(product.id,product.stockNum,product.sellerid)"><i class="lnr lnr-cart"></i></a>
                                    
                                    <!--=======  End of hover icons  =======-->

                                    <!--=======  badge  =======-->
<!--                                     
                                    <div class="product-badge">
                                        <span class="new-badge">NEW</span>
                                        <span class="discount-badge">-8%</span>
                                    </div> -->
                                    
                                    <!--=======  End of badge  =======-->
                                    
                                </div>
                                <div class="content">
                                    <p class="product-title"><a href="javascript:void(0)" @click="skipDetail(product.id)">{{product.name}}</a></p>
                                    <p class="product-price">
                                        <span v-if="product.tprice > 0" class="main-price discounted">￥{{product.price}}</span> 
                                        <span v-if="product.tprice > 0" class="discounted-price">￥{{product.tprice}}</span>
                                        <span v-if="product.tprice == 0" class="discounted-price">￥{{product.price}}</span>

                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                              
                              
                    <div class="pagination-container mt-50 pb-20 mb-md-80 mb-sm-80">
                        <div class="row">

                            <div class="col-lg-12 col-md-8 col-sm-12">
                                
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
                                
                                <!--=======  End of pagination-content  =======-->
                            </div>
                        </div>
                    </div>
                    
                    <!--=======  End of pagination  =======-->
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
      key: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页
      categoryid1: "",
      categoryid2: "",
      minPrice: "",
      maxPrice: "",

      categoryList: [],
      childCategoryList: [],

      sessionmember: null,

    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    sessionStorage.setItem("banAcitive", "product");
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
    this.getProductList()
    this.getCategoryList()

  },
  components: {
    Footer,
    Header,
  },
  methods: {

    async handleCollectAdd(productid){
      if(this.sessionmember){
        let { data : res } = await this.$http.post("/collect/insert",this.qs.stringify({
          memberid: this.sessionmember.id,
          productid: productid,
        }))
        if(res.status == 121){
          this.$message.warning("已有收藏记录")
        }else{
          this.$message.success("收藏成功")
        }
      }else{
        this.$router.push("/member/login")
      }
    },

    async handleCartAdd(productid,stockNum,sellerid){
      if(this.sessionmember){
        if(1 > stockNum){
          this.$message.warning("库存不足")
          return;
        }
        await this.$http.post("/cart/insert",this.qs.stringify({
          memberid: this.sessionmember.id,
          productid: productid,
          sellerid: sellerid,
          num: 1,
        }))
        this.$message.success("加入购物车成功")
      }else{
        this.$router.push("/member/login")
      }
    },



    skipDetail(id){
      this.$router.push({
        path: "/member/productDetail",
        query: {
          id: id,
        }
      })
    },

    async getCategoryList(){
      let {data : resulst} = await this.$http.post('/category/selectAll',this.qs.stringify({
          fatherid: 0
      }))
      this.categoryList = resulst.data 
    },

    async getchildcategory(value){
      this.categoryid2 = ""
      if(value ==""){
        this.childCategoryList = []
        return;
      }
      let {data : res} = await this.$http.post('/category/selectAll',this.qs.stringify({
          fatherid: value
      }))
      this.childCategoryList = res.data
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getProductList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getProductList();
    },

    async getProductList() {
      let { data: res } = await this.$http.post(
        "/product/selectPage",
        this.qs.stringify({
          key: this.key,
          categoryid1: this.categoryid1,
          categoryid2: this.categoryid2,
          userid: this.$route.query.userid,
          minPrice: this.minPrice,
          maxPrice: this.maxPrice,
          issj: "已上架",
          audit: "已通过",
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

.bg-purple {
    background: transparent!important;
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
</style>