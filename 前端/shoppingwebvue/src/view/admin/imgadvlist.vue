
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
                <li class="breadcrumb-item"><a href="roleList">轮播图管理</a></li>
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
                      <el-button v-if="currentPermission.includes('imgadvAdd')" @click="handleAdd" type="primary" size="small"
                      ><i class="el-icon-plus"></i
                    ></el-button>
                  </div>
                </el-col>
              </el-row>

              <template>
                <el-table size="mini" :data="tableData"  border style="width: 100%">
                  <el-table-column  label="轮播图">
                    <template slot-scope="scope">
                        <el-popover placement="top-start" title="" trigger="hover">
                            <img :src="baseURL+['/upload/']+scope.row.filename" alt="" style="width: 650px;height: 350px">
                            <img slot="reference" :src="baseURL+['/upload/']+scope.row.filename" style="width: 260px;height: 160px">
                        </el-popover>
                    </template>
                  </el-table-column>
                  <el-table-column show-overflow-tooltip prop="url" label="URL"></el-table-column>
                  <el-table-column width="100px" label="操作">
                    <template slot-scope="scope">
                      <el-link v-if="currentPermission.includes('imgadvEdit')"  :underline="false" @click="handleEdit(scope.row.id)"><i class="el-icon-edit"></i></el-link>
                      <el-link v-if="currentPermission.includes('imgadvDelete')" :underline="false" @click="handleDelete(scope.$index, scope.row, tableData)"><i class="el-icon-delete"></i></el-link>
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
      </div>

  </div>
</template>
<script>
export default {
  data() {
    return {
      baseURL:"",
      key: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页

    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
    this.getImgadvList();
  },

  computed: {
    currentPermission(){
      return JSON.parse(sessionStorage.getItem("permissionList"))
    }
  },
  methods: {

    handleEdit(id) {
      this.$router.push({
        path: "imgadvAdd",
        query: {
          id: id,
        },
      });
    },


    handleAdd() {
       this.$router.push("imgadvAdd")
    },

    

    handleSizeChange(val) {
      this.pageSize = val;
      this.getImgadvList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getImgadvList();
    },

    async getImgadvList() {
      let { data: res } = await this.$http.post(
        "/imgadv/selectPage",
        this.qs.stringify({
          key: this.key,
          fatherid: 0,
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
        "/imgadv/delete",
        this.qs.stringify({
          id: rows.id,
        })
      );
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

:deep(.el-tree-node__content){
    display: inherit;
}

</style>
