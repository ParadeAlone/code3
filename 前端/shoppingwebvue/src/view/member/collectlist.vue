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
                            <li class="active">我的收藏</li>
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
                    
                    <div class="shop-product-wrap grid row">
                        <div v-for="(item,index) in collectList" :key="index" class="col-lg-4 col-md-6 col-sm-6 col-12">
                            
                            <div class="ptk-product shop-grid-view-product">
                                <div class="image">
                                    <a href="javascript:void(0)" @click="skipDetail(item.productid)">
                                        <img :src="baseURL+'/upload/'+item.filename" class="img-fluid" alt="">
                                    </a>
                                
                                    <a class="hover-icon" href="#" data-toggle = "modal" data-target="#quick-view-modal-container"><i class="lnr lnr-eye"></i></a>
                                    <a class="hover-icon" href="javascript:void(0)" @click="haldeDelete(item.id)"><i class="el-icon-delete"></i></a>
                                    <a class="hover-icon" href="javascript:void(0)" @click="handleCartAdd(item.productid,item.stockNum)"><i class="lnr lnr-cart"></i></a>
                                    
                                    
                                </div>
                                <div class="content">
                                    <p class="product-title"><a href="javascript:void(0)" @click="skipDetail(item.productid)">{{item.productName}}</a></p>
                                    <p class="product-price">
                                        <span v-if="item.tprice > 0" class="main-price discounted">￥{{item.price}}</span> 
                                        <span v-if="item.tprice > 0" class="discounted-price">￥{{item.tprice}}</span>
                                        <span v-if="item.tprice == 0" class="discounted-price">￥{{item.price}}</span>
                                    </p>
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
      sessionmember: null,
      collectList: [],
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
    this.getCollectList()
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    async handleCartAdd(productid,stockNum){
      if(this.sessionmember){
        if(1 > stockNum){
          this.$message.warning("库存不足")
          return;
        }
        await this.$http.post("/cart/insert",this.qs.stringify({
          memberid: this.sessionmember.id,
          productid: productid,
          num: 1,
        }))
        this.$message.success("加入购物车成功")
      }else{
        this.$router.push("/member/login")
      }
    },


    async haldeDelete(id) {
      await this.$http.post(
        "/collect/delete",
        this.qs.stringify({
          id: id,
        })
      )
      this.getCollectList()
    },
    async getCollectList() {
      let { data: res } = await this.$http.post(
        "/collect/selectAll",
        this.qs.stringify({
          memberid: this.sessionmember.id,
        })
      )
      this.collectList = res.data
    },

    skipDetail(id){
      this.$router.push({
        path: "/member/productDetail",
        query: {
          id: id,
        }
      })
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

.el-icon-delete{
  position: absolute;
  top: 10px;
  right: 10px;
}
</style>