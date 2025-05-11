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
                            <li class="active">商品详情</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    

    <div class="single-product-page-content mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 mb-md-50 mb-sm-50">
                    <!-- single product tabstyle one image gallery -->
                    <div class="product-image-slider pts1-product-image-slider pts-product-image-slider pts1-product-image-slider flex-row-reverse">
                        <!--product large image start -->
                        <div class="tab-content product-large-image-list pts-product-large-image-list pts1-product-large-image-list" id="myTabContent">
                            <div class="tab-pane fade1 show active" id="single-slide-1" role="tabpanel" aria-labelledby="single-slide-tab-1">
                                <div class="single-product-img img-full">
                                    <img :src="baseURL + '/upload/' + product.filename" alt="" class="img-fluid">
                                    <a :href="baseURL + '/upload/' + product.filename" class="big-image-popup"><i class="fa fa-search-plus"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    
                    <div class="single-product-details-container">
                        
                        <p class="product-title mb-15">{{product.name}}</p>
                        <!-- <div class="rating d-inline-block mb-15">
                            <i class="lnr lnr-star active"></i>
                            <i class="lnr lnr-star active"></i>
                            <i class="lnr lnr-star active"></i>
                            <i class="lnr lnr-star active"></i>
                            <i class="lnr lnr-star"></i>
                        </div> -->
                        <p class="review-links d-inline-block">
                            <a href="#"><i class="fa fa-comment-o"></i> 评价 ({{commentList.length}}) </a>
                        </p>



                        <p class="product-price mb-30">

                            <span v-if="product.tprice > 0" class="main-price discounted">￥{{product.price}}</span> 
                            <span v-if="product.tprice > 0" class="discounted-price">￥{{product.tprice}}</span>
                            <span v-if="product.tprice == 0" class="discounted-price">￥{{product.price}}</span>
                        </p>   
                        <!-- <p class="product-description mb-15">
                            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ipsum nemo at a amet eaque adipisci, repellat aspernatur tempora corrupti cupiditate?
                        </p>        -->

                        <p class="reference-text mb-15">库存: {{product.stockNum}}</p>

                        <p class="reference-text mb-15">销量: {{product.saleNum}}</p>

                        <div class="cart-buttons mb-30">
                            <p class="mb-15">数量</p>
                            <div class="pro-qty mr-10">
                                <input type="text" v-model="num" min="1" max="99">
                            </div>
                            <a href="javascript:void(0)" @click="handleCartAdd" class="pataku-btn"><i class="fa fa-shopping-cart"></i> 加入购物车</a>
                        </div>      
                        <p class="wishlist-link mb-30"><a href="javascript:void(0)" @click="handleCollectAdd"   > <i class="fa fa-heart"></i> 收藏</a></p>   
                    </div>
                    
                    <!--=======  End of single product details  =======-->
                </div>
            </div>
        </div>
    </div>



<div class="single-product-tab-section mb-80">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="tab-slider-wrapper">
                        <nav>
                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                                    aria-selected="true">描述</a>
                                <!-- <a class="nav-item nav-link" id="features-tab" data-toggle="tab" href="#features" role="tab"
                                    aria-selected="false">Features</a> -->
                                <a class="nav-item nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab"
                                    aria-selected="false">评价 ({{commentList.length}})</a>
                            </div>
                        </nav>
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
                                <p class="product-desc" v-html="product.content"></p>
                            </div>
                            <!-- <div class="tab-pane fade" id="features" role="tabpanel" aria-labelledby="features-tab">
                                <table class="table-data-sheet">
                                    <tbody>
                                        <tr class="odd">

                                            <td>Name</td>
                                            <td>Kaoreet lobortis sagittis laoreet</td>
                                        </tr>
                                        <tr class="even">

                                            <td>Color</td>
                                            <td>Yellow</td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div> -->
                            <div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
                                <div class="product-ratting-wrap">
                                    <div class="rattings-wrapper">

                                        <div v-for="(item,index) in commentList" :key="index" class="sin-rattings">
                                            <div class="ratting-author">
                                                <img :src="baseURL + ['/upload/'] + item.filename" alt="">
                                                <h3>{{item.memberName}}</h3>
                                                <div class="rate">
                                                      <el-rate
                                                        v-model="item.score"
                                                        disabled
                                                        show-score
                                                        text-color="#ff9900"
                                                        score-template="{value}">
                                                        </el-rate>
                                                </div>

                                                <el-link type="success" @click="handleReplyAdd(item.id)" :underline="false" class="reply-link">回复</el-link>

                                            </div>
                                            <p v-html="item.content"></p>

                                            <p v-if="item.hfcontent!=null && item.hfcontent!=''">
                                            <el-tag type="success">{{item.hfcontent}}</el-tag>
                                            </p>



                                            <div v-for="(reply,index) in item.replyList" :key="index" class="sin-rattings reply">
                                                <div class="ratting-author">
                                                    <img :src="baseURL + ['/upload/'] + reply.filename" alt="">
                                                    <h3>{{reply.memberName}}</h3>
                                                </div>
                                                <p v-html="reply.content"></p>
                                            </div>


                                        </div>


                                    </div>
                                    <div class="ratting-form-wrapper fix">
                                            <div class="ratting-form row">
                                                <div class="col-12 mb-15">
                                                    <el-rate v-model="comment.score"></el-rate>
                                                </div>
                                                <div class="col-12 mb-15">
                                                    <textarea name="review" v-model="comment.content"  id="your-review"
                                                        placeholder="评价内容"></textarea>
                                                </div>
                                                <div class="col-12">
                                                    <input value="提交" @click="handelCommentAdd" type="submit">
                                                </div>
                                            </div>
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

    
    <el-dialog  :title="titleDialog"  :visible.sync="dialogFormVisible" ref="userEditor">
      <el-form ref="reply" :model="reply" :rules="rules">

      <el-form-item label="评价内容" prop="content" :label-width="formLabelWidth">
      <el-input v-model="reply.content" type="textarea" autocomplete="off"></el-input>
      <!--<textarea id="editor_id"></textarea>-->
      </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
	
	

  </div>
</template>
<script>
import $ from 'jquery'
import Header from "./header";
import Footer from "./footer";
export default {
  data() {
    return {
      product: {},
	  sessionmember: null,
	  num:1,

	  comment:{
		score: 5,
		content: "",
	  },
	  commentList: [],

	  titleDialog: "评价回复",
      dialogFormVisible: false,
      formLabelWidth: "100px",
      reply: {
        commentid: "",
        content: "",
      },
      rules: {
        content: [{required: true, message: '请填写内容', trigger: 'blur'}],
      },
    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
    sessionStorage.setItem("banAcitive", "product");
	this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
    this.load()
  },
  mounted(){
     $('.nav-item.nav-link').on('click', function(e) {
        $('.nav-item.nav-link').removeClass("active")
        $(".tab-pane.fade").removeClass("show active")
        $(this).addClass("active")
        $($(this).attr("href")).addClass("show active")
     });
  },
  components: {
    Footer,
    Header,
  },

  methods: {
    handleReplyAdd(commentid){
      if(this.sessionmember == null){
         this.$router.push("/member/login")
      }else{
        this.reply.content = ""
        this.reply.commentid = commentid
        this.dialogFormVisible = true
      }
    },
    async handelCommentAdd(){
      if(this.sessionmember!=null){
        if(this.comment.content == ""){
            this.$message.warning("请填写评价内容")
            return;
        }
        let { data :res } = await this.$http.post("/comment/insert",this.qs.stringify({
          content: this.comment.content,
          score: this.comment.score,
          memberid: this.sessionmember.id,
          productid: this.product.id,
          sellerid: this.product.sellerid,
        }))
        if(res.status != 120){
          this.$message.warning("无购买历史，无法评价")
        }else{
          this.comment.content = ""
          this.comment.score = 5
          this.$message.success("评价成功")
          this.getCommentList()
          this.load()
        }

      }else{
        this.$router.push("/member/login")
      }
    },

	async onSubmit(){
        this.$refs.reply.validate(async (valid) => {
            if (valid) {
              await this.$http.post("/reply/insert",this.qs.stringify({
                commentid: this.reply.commentid,
                memberid: this.sessionmember.id,
                content: this.reply.content,
              }))
            }
        })
        this.dialogFormVisible = false
        this.reply.content = ""
        this.$message.success("评价成功")
        await this.getCommentList()
    },

	async hanleCommentAdd(){
		if(this.sessionmember){
			if(this.comment.content == ""){
			this.$message.warning("请填写评价内容")
			return;
		}
		let { data : res } =  await this.$http.post("/comment/insert",this.qs.stringify({
			memberid: this.sessionmember.id,
			productid: this.product.id,
			sellerid: this.product.userid,
			content: this.comment.content,
			score: this.comment.score
		}))
		if(res.status == 120){
			this.comment.content = ""
			this.$message.success("评价成功")
			this.getCommentList()
		}else{
			this.$message.warning("无购买记录，评价失败")
		}
		}else{
			this.$router.push("/member/login")
		}

	},

	async handleCartAdd(){
		if(this.sessionmember){
			if(!/^[1-9]\d*$/.test(this.num)){
				this.$message.warning("数量输入错误")
				return;
			}
			if(this.num > this.product.stockNum){
				this.$message.warning("库存不足")
				return;
			}
			let { res : data } = await this.$http.post("/cart/insert",this.qs.stringify({
				memberid: this.sessionmember.id,
				productid: this.product.id,
                sellerid: this.product.sellerid,
				num: this.num
			}))
			this.$message.success("加入购物车成功")
			this.num = 1
			this.$refs.childCart.getCartList()

		}else{
			this.$router.push({path: "/member/login"})
		}
	},
	async handleCollectAdd(){
		if(this.sessionmember){
			let { data : res } = await this.$http.post("/collect/insert",this.qs.stringify({
				memberid: this.sessionmember.id,
				productid: this.product.id,
			}))
			if(res.status == 120){
				this.$message.success("已加入收藏列表")
			}else{
				this.$message.warning("收藏列表中已存在")
			}
		}else{
			this.$router.push({path: "/member/login"})
		}
	},
    async load () {
      let { data : res } = await this.$http.post("/product/get1", this.qs.stringify({
          id: this.$route.query.id,
      }))
      this.product = res.data
	  this.getCommentList()
    },
	async getCommentList(){
		let { data : res } = await this.$http.post("/comment/selectAll",this.qs.stringify({
			productid: this.product.id,
		}))
		this.commentList = res.data
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


.nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active {
    box-shadow: unset !important;
}

.tab-content {
    box-shadow: unset !important;
}

.ratting-author{
    display: flex;
}

.ratting-author img{
    width: 40px;
    align-items: flex-start;
}

.ratting-author h3{
    margin-left : 10px !important;
}

:deep(.el-rate__icon) {
    margin-right: 0px;
}

.rate{
    padding-top: 2px;
}


.reply-link.el-link.el-link--success {
    display: inline-block;
    padding: 3px 10px;
}

.sin-rattings.reply{
    padding-left: 100px;
}

.el-tag {
    height: auto;
    line-height: auto;
    white-space: wrap;
}
</style>