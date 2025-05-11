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
                            <li class="active">我的订单</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="page-section mb-80">
		<div class="container">

      <el-row :gutter="20">
        <el-col :span="24">
          <div class="grid-content bg-purple">
              <el-button type="primary" @click="searchTorder('')" size="mini">全部</el-button>
              <el-button type="primary" @click="searchTorder('已取消')" size="mini">已取消</el-button>
              <el-button type="primary" @click="searchTorder('待支付')" size="mini">待支付</el-button>
              <el-button type="primary" @click="searchTorder('待发货')" size="mini">待发货</el-button>
              <el-button type="primary" @click="searchTorder('待收货')" size="mini">待收货</el-button>
              <el-button type="primary" @click="searchTorder('交易完成')" size="mini">交易完成</el-button>
              <!-- <el-button type="primary" @click="searchTorder('已退款')" size="mini">已退款</el-button> -->

          </div>
        </el-col>
      </el-row>

      <template>
        <el-table :data="tableData"  border style="width: 100%">
          <el-table-column show-overflow-tooltip prop="ddno" label="订单编号">
          </el-table-column>
          <el-table-column show-overflow-tooltip  label="商品信息">
              <template slot-scope="scope">
              <div v-html="scope.row.productInfo">
              </div>
              </template>
          </el-table-column>
          <el-table-column show-overflow-tooltip prop="total" label="订单金额">
          </el-table-column>
          <el-table-column show-overflow-tooltip prop="address" label="收货地址">
          </el-table-column>
           <el-table-column show-overflow-tooltip prop="sellerName" label="销售人员">
          </el-table-column>
          <el-table-column show-overflow-tooltip label="状态">
            <template slot-scope="scope">
                    <el-link :underline="false" type="primary">{{scope.row.status}}</el-link>
            </template>
          </el-table-column>
          <el-table-column width="100px" label="操作">
            <template slot-scope="scope">
              <!-- <el-link v-if="scope.row.status == '交易完成'" @click="handleTuikuan(scope.row.id,'退款中')" :underline="false" type="primary">退款</el-link> -->
              <el-link v-if="scope.row.status == '待支付'" @click="handleTuikuan(scope.row.id,'已取消')" :underline="false" type="primary">取消</el-link>
              <el-link v-if="scope.row.status == '待收货'" @click="handleShouHuo(scope.row.id,'交易完成')" :underline="false" type="primary">确认收货</el-link>
              <el-link v-if="scope.row.status == '待支付'" @click="handlePay(scope.row.id,scope.row.total,scope.row.memberid)" :underline="false" type="primary">支付</el-link>
              <el-link  v-if="scope.row.status == '待支付' || scope.row.status == '已取消' || scope.row.status == '已退款' || scope.row.status == '交易完成'"  @click="handleDelete(scope.$index, scope.row, tableData)" :underline="false" type="danger">删除</el-link>
            </template>
          </el-table-column>
        </el-table>

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
      </template>

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
  data () {
    return {
      status: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页
      sessionmember: null,
      baseURL:"",
    }
  },
  components: {
    Footer,
    Header,
  },
  created(){
      this.sessionmember = JSON.parse(sessionStorage.getItem("sessionmember"))
      this.baseURL = this.$http.defaults.baseURL
      this.getTorderList()
  },
  methods: {

    searchTorder(status){
        this.status = status
        this.getTorderList()
    },

    async handleShouHuo(id,status){
      await this.$http.post("/tOrder/update",this.qs.stringify({
        id: id,
        status: status
      }))
      await this.getTorderList()
    },

    async handleTuikuan(id,status){
      await this.$http.post("/tOrder/update",this.qs.stringify({
        id: id,
        status: status
      }))
      await this.getTorderList()
    },


    async handlePay(id,total,memberid){
      let Randnum = "";
      for (var i = 0; i < 10; i++) {
        Randnum += Math.floor(Math.random() * 10);
      }
      let { data : res } = await this.$http.post("/alipay/goAlipay",this.qs.stringify({
        ddno: Randnum,
        price: total,
        id: id,
        flag: "gw"
      }))
      document.write(res.res)
    },

    handleSizeChange(val) {
      this.pageSize = val;
      this.getTorderList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getTorderList();
    },

    async getTorderList() {
      let { data: res } = await this.$http.post(
        "/tOrder/selectPage",
        this.qs.stringify({
          status: this.status,
          memberid: this.sessionmember.id,
          start: this.pageNum,
          limit: this.pageSize,
        })
      );
      this.total = res.data.totalSize;
      this.tableData = res.data.content;
    },
    handleDelete(index, rows, tableData) {
      tableData.splice(index, 1);
      let { data: res } = this.$http.post(
        "/tOrder/delete",
        this.qs.stringify({
          id: rows.id,
        })
      );
    },
  },
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


:deep(.el-row){
  margin-bottom: 30px !important;
}

.el-link{
  font-size: 12px;
}

:deep(.el-link--inner){
  padding: 0px 6px;
}
</style>