
<template>
  <div>
      <div class="page-header">
        <div class="page-block">
          <div class="row align-items-center">
            <div class="col-md-12">
              <div class="page-header-title">
                <!-- <h5>首页</h5> -->
              </div>
              <ul class="breadcrumb">
                <li class="breadcrumb-item"><a href="mainList"><i
                      class="feather icon-home"></i></a></li>
                <li class="breadcrumb-item"><a href="loginipList">日志信息</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    
      <div class="row custom-color">
        <div class="col-sm-12">
            <div class="card">
              <el-row :gutter="20">
               
                    <el-col :span="4">
                      <div class="grid-content bg-purple">
                        <el-button @click="deleteAll" type="primary" size="small"
                          ><i class="el-icon-delete"></i
                        ></el-button>
                      </div>
                    </el-col>
                  </el-row>

                  <template>

                    <template>
                <el-table size="mini" :data="tableData"  border style="width: 100%">

                  <el-table-column show-overflow-tooltip prop="name" label="登录用户">
                  </el-table-column>

                  <el-table-column show-overflow-tooltip prop="addr" label="IP">
                  </el-table-column>


                  <el-table-column show-overflow-tooltip prop="content" label="信息">
                  </el-table-column>

                  <el-table-column show-overflow-tooltip prop="savetime" label="时间">
                  </el-table-column>

           

                
                </el-table>

              </template>
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
      </div>


  </div>
</template>
<script>
export default {
  data() {
    return {
      key: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页
      sellerid: "",
    };
  },
  created() {
    this.admin = JSON.parse(sessionStorage.getItem("sessionadmin"))
    this.sellerid = this.admin.id
    this.getLoginipList();
  },
  methods: {
   
    handleSizeChange(val) {
      this.pageSize = val;
      this.getLoginipList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getLoginipList();
    },

    async getLoginipList() {
      let { data: res } = await this.$http.post(
        "/loginip/selectPage",
        this.qs.stringify({
          key: this.key,
          start: this.pageNum,
          limit: this.pageSize,
        })
      );
      this.total = res.data.totalSize;
      this.tableData = res.data.content;
    },
    deleteAll() {
      let { data: res } = this.$http.post("/loginip/delete")
      this.getLoginipList()
    },
  },
};
</script>
<style scoped>
.breadcrumb-item a{
  text-decoration: none;
}

.card {
    padding: 20px;
    border-radius: 2px;
    box-shadow: 0 0 0 1px #e2e5e8;
    border: none;
    margin-bottom: 30px;
    transition: all 0.5s ease-in-out;
    --bs-body-color: #686c71;
}

.bg-purple {
  background: white  !important;
}


:deep(.el-input__inner){
    height: 32px !important;
    line-height: 32px !important;
    font-size: 12px !important;
}

.el-row {
    margin: 10px;
}



:deep(.el-pagination.is-background .el-pager li:not(.disabled).active){
    background-color: #746ca7;
    color: #FFF;
}

.el-pager li:hover {
    color: #746ca7 !important;
}

.page-header .page-header-title + .breadcrumb > .breadcrumb-item a :first-child{
    color: #746ca7;
}

.el-button--primary {
    background-color: #746ca7;
    border-color: #746ca7;
}

.el-button--primary:hover {
    background-color: #746ca7;
    border-color: #746ca7;
}

.el-button--primary:focus, .el-button--primary:hover {
    background: #746ca7;
    border-color: #746ca7;
    color: #FFF;
}

:deep(.el-link--inner){
  font-size: 12px;
}

:deep(.operation.el-link.el-link--primary .el-link--inner){
margin-right: 8px;
}

:deep(.el-link--inner){
  font-size: 12px !important;
  padding-right: 5px;
}
:deep(.cell.el-tooltip p){
  font-size: 12px !important;
}

</style>
