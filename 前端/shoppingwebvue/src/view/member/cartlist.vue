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
                            <li class="active">购物车</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="page-section mb-80">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form action="#">				
                        <!--=======  cart table  =======-->
                        
                        <div class="cart-table table-responsive mb-40">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="pro-thumbnail">图片</th>
                                        <th class="pro-title">商品</th>
                                        <th class="pro-price">价格</th>
                                        <th class="pro-quantity">数量</th>
                                        <th class="pro-subtotal">小计</th>
                                        <th class="pro-remove">移出</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="(item,index) in cartList" :key="index">
                                        <td class="pro-thumbnail"><a href="javascript:void(0)" @click="$router.push({path: 'productDetail', query:{id: item.productid}})"><img :src="baseURL + '/upload/' +item.filename" class="img-fluid" alt="Product"></a></td>
                                        <td class="pro-title"><a href="javascript:void(0)" @click="$router.push({path: 'productDetail', query:{id: item.productid}})">{{item.productName}}</a></td>
                                        <td class="pro-price">
                                          <del v-if="item.tprice > 0">￥{{item.price}}</del>
                                          <span v-if="item.tprice > 0">￥{{item.tprice}}</span>
                                          <span v-if="item.tprice == 0">￥{{item.price}}</span>

                                        </td>
                                        <td class="pro-quantity">
                                          <el-input-number :value="item.num" :id="'num' + item.productid"  @change="(e) => handleChange(e,item)"  :min="1"  label="描述文字"></el-input-number>
                                        </td> 
                                        <td class="pro-subtotal"><span>￥{{ item.subTotal }}</span></td>
                                        <td class="pro-remove"><a href="javascript:void(0)" @click="handleDel(item.id)"><i class="fa fa-trash-o"></i></a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <!--=======  End of cart table  =======-->
                        
                        
                    </form>	
                        
                    <div class="row">

                        <div class="col-lg-6 col-12">

                          <!-- <label for="">收货地址</label> -->

                           <el-select v-model="addressid"  placeholder="请选择收货地址">
                              <el-option
                                v-for="(address, index) in addressList"
                                :key="index"
                                :label="
                                  address.addr +
                                  ' ' +
                                  address.name +
                                  ' ' +
                                  address.tel
                                "
                                :value="address.id"
                              >
                              </el-option>
                            </el-select>
                            
                            
                        </div>

                        
                        <div class="col-lg-6 col-12 d-flex">
                            <!--=======  Cart summery  =======-->
                        
                            <div class="cart-summary">
                                <div class="cart-summary-wrap">
                                    <h4>购物车总数</h4>
                                    <p>数量： <span>{{cartList.length}}件</span></p>
                                    <p>总计： <span>{{total}}件</span></p>


                                </div>
                                <div class="cart-summary-button">
                                    <button v-if="cartList.length > 0"  @click="jiezhang()" class="checkout-btn">创建订单</button>
                                    <button v-if="cartList.length == 0" class="update-btn">空空如也</button>
                                </div>
                            </div>
                        
                            <!--=======  End of Cart summery  =======-->
                            
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
import $ from "jquery";
export default {
  name: "HelloWorld",
  data() {
    return {
      cartList: [],
      baseURL: "",
      sessionmember: null,
      total: 0,

      addressList: [],
      addressid: "",
    };
  },
  components: {
    Footer,
    Header,
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL;
    this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"));
    this.getAddressList()
    this.load()
  },
  methods: {

    async handleChange(value,item){
      let { data: res } = await this.$http.post(
        "/cart/update",
        this.qs.stringify({
          id: item.id,
          num: value,
        })
      )
      await this.load();
    },
    skipProductList() {
      this.$router.push("/member/productList");
    },
    async getAddressList() {
      let { data: res } = await this.$http.post(
        "/address/selectAll",
        this.qs.stringify({
          memberid: this.sessionmember.id,
        })
      );
      this.addressList = res.data;
      this.addressList.forEach((item, index, Array) => {
        if (item.ismr == "是") {
          this.addressid = item.id;
        }
      });
    },
    async handleDelAll() {
      await this.$http.post(
        "/cart/deleteAll",
        this.qs.stringify({
          memberid: this.sessionmember.id,
        })
      );
      await this.load();
    },
    async handleDel(id) {
      await this.$http.post(
        "/cart/delete",
        this.qs.stringify({
          id: id,
        })
      );
      await this.load();
    },
    async load() {
      let { data: res } = await this.$http.post(
        "/cart/selectAll",
        this.qs.stringify({
          memberid: this.sessionmember.id,
        })
      );
      this.cartList = res.data;
      this.total = res.total.toFixed(2);

    },
    async jiezhang() {
      if (this.sessionmember != null) {
        if(this.addressid == ""){
          this.$message.warning("请选择收货地址")
          return;
        }
        let Randnum = "";
        for (var i = 0; i < 10; i++) {
          Randnum += Math.floor(Math.random() * 10);
        }
        await this.$http.post(
          "/tOrder/insert",
          this.qs.stringify({
            ddno: Randnum,
            memberid: this.sessionmember.id,
            total: this.total,
            status: "待支付",
            addressid: this.addressid,
          })
        );
        await this.load();
        this.addressid = "";
        await this.$router.push("/member/tOrderList")
      } else {
        this.$router.push("/member/login");
      }
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

.row.login{
  justify-content: center;
}

.bg-purple {
    background: transparent !important;
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

.el-select {
  width: 100%;
}

</style>