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
                            <li class="active">收货地址</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="page-section mb-80">
		<div class="container">
			<el-row :gutter="20">
          <el-col :span="4">
            <div class="grid-content bg-purple">
              <el-button @click="handleAdd"  type="primary" size="small"
                ><i class="el-icon-plus"></i
              ></el-button>
            </div>
          </el-col>
        </el-row>

        <template>
          <el-table :data="tableData"  border style="width: 100%">
            <el-table-column show-overflow-tooltip  label="收货地址">
              <template slot-scope="scope">
                {{ scope.row.addr +" "+ scope.row.name +" "+ scope.row.tel}}
              </template>
            </el-table-column>

            <el-table-column width="80" show-overflow-tooltip  label="默认地址">
                <template slot-scope="scope">
                  <el-link v-if="scope.row.ismr=='是'" @click="handleEdit(scope.row.id,'否')" type="primary" :underline="false" style="font-size:12px">是</el-link>
                  <el-link v-if="scope.row.ismr=='否'" @click="handleEdit(scope.row.id,'是')" type="danger" :underline="false" style="font-size:12px">否</el-link>
                </template>
            </el-table-column>
            <el-table-column width="100px" label="操作">
              <template slot-scope="scope">
                <el-link @click="handleDelete(scope.$index, scope.row, tableData)"><i class="el-icon-delete"></i></el-link>
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
      this.getAddressList()
  },
  methods: {

    async handleEdit(id,ismr){
      await this.$http.post('/address/update',this.qs.stringify({
        memberid: this.sessionmember.id,
        id: id,
        ismr: ismr,
      }))
      await this.getAddressList()
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getAddressList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getAddressList();
    },

    async getAddressList() {
      let { data: res } = await this.$http.post(
        "/address/selectPage",
        this.qs.stringify({
          memberid: this.sessionmember.id,
          start: this.pageNum,
          limit: this.pageSize,
        })
      );
      this.total = res.data.totalSize;
      this.tableData = res.data.content;
    },
    handleAdd () {
      this.$router.push("/member/addressAdd")
    },

    handleDelete(index, rows, tableData) {
      tableData.splice(index, 1);
      let { data: res } = this.$http.post(
        "/address/delete",
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

</style>