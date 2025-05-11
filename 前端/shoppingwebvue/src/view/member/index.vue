<template>
  <div>
  <Header></Header>
	 <SlideShow :imgadvList="imgadvList" :baseURL="baseURL"></SlideShow>
	


  <div class="shop-page-content mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 h3">
                  <h3>热销商品</h3>
                </div>
                <div class="col-lg-12">
                    
                    <div class="shop-product-wrap grid row">
                        <div v-for="(product,index) in productList" :key="index" v-if="index < 8" class="col-lg-3 col-md-6 col-sm-6 col-12">
                            
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
                              
                    
                </div>
            </div>
        </div>
    </div>

		
    
  <div v-if="recommendList && recommendList.length>0" class="shop-page-content mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 h3">
                  <h3>推荐商品</h3>
                </div>
                <div class="col-lg-12">
                    
                    <div class="shop-product-wrap grid row">
                        <div v-for="(product,index) in recommendList" :key="index"  class="col-lg-3 col-md-6 col-sm-6 col-12">
                            
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
import SlideShow from "@/components/Slideshow.vue";


export default {
  data() {
    return {
      baseURL: "",
      imgadvList: [],
      productList: [],
      recommendList: [],//协同过滤
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    sessionStorage.setItem("banAcitive", "index");
        this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
     if(this.sessionmember){
      this.getRecommend()
    }
    this.getImgadvList();
    this.getProductList()
  },
  components: {
    Header,
    Footer,
    SlideShow,
  },
  methods: {

    async getRecommend(){
      let { data: res } = await this.$http.post('/product/loveitems',this.qs.stringify({
        memberid: this.sessionmember.id,
      }))
      this.recommendList = res.data
    },


    
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
    async getImgadvList() {
      let { data: res } = await this.$http.post("/imgadv/selectAll");
      this.imgadvList = res.data;
    },
    async getProductList() {
      let { data: res } = await this.$http.post("/product/selectAll",this.qs.stringify({
        sortBy: "saleNum",
        issj: "已上架",
      }));
      this.productList = res.data;
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

:deep(body) {
	padding-top:270px !important;
}

.col-lg-12.h3 h3{
  line-height: 160px;
  text-align: center;
}

</style>