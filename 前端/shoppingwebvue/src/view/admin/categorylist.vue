
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
                <li class="breadcrumb-item"><a href="categoryList">分类管理</a></li>
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
                        <el-input
                          v-model="key"
                          placeholder="名称"
                        ></el-input>
                      </div>
                    </el-col>
                    <el-col :span="4">
                      <div class="grid-content bg-purple">
                        <el-button @click="getCategoryList" type="primary" size="small"
                          ><i class="el-icon-search"></i
                        ></el-button>
                          <el-button v-if="currentPermission.includes('categoryAdd')" @click="handleAdd(0)" type="primary" size="small"
                          ><i class="el-icon-plus"></i
                        ></el-button>
                      </div>
                    </el-col>
                  </el-row>

                  <template>
                    <el-table
                            size="mini"
                            :data="tableData"
                            border
                            style="width: 100%">

                        <el-table-column
                                width="200px"
                                label="一级类目">
                            <template slot-scope="scope">
                                {{scope.row.name}}
                                    <el-link v-if="currentPermission.includes('categoryEdit')" @click="handleEdit(scope.row.id)" :underline="false"><i class="el-icon-edit"></i></el-link>
                                    <el-link v-if="currentPermission.includes('categoryDelete')" @click="handleDelete(scope.row.id)" :underline="false"><i class="el-icon-delete"></i></el-link>
                            </template>
                        </el-table-column>

                        <el-table-column
                                label="二级类目"
                        >
                            <template slot-scope="scope">
                                    <el-link v-if="currentPermission.includes('categoryAdd')" @click="handleAdd(scope.row.id)" :underline="false"><i class="el-icon-plus"></i></el-link>
                                    <div v-for="(child,index) in scope.row.categories" :key = "index">
                                       <div class="grid-content bg-purple" style="display:flex;flex-direction:row">
                                        <div>
                                            {{child.name}}
                                            <el-link v-if="currentPermission.includes('categoryEdit')" @click="handleEdit(child.id)" :underline="false"><i class="el-icon-edit"></i></el-link>
                                            <el-link v-if="currentPermission.includes('categoryDelete')" @click="handleDelete(child.id)" :underline="false"><i class="el-icon-delete"></i></el-link>
                                        </div>
                                        
                                      </div>
                                    </div>
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
      key: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页

    };
  },
  created() {
    this.getCategoryList();
  },

  computed: {
    currentPermission(){
      return JSON.parse(sessionStorage.getItem("permissionList"))
    }
  },
  methods: {

    handleEdit(id) {
      this.$router.push({
        path: "categoryAdd",
        query: {
          id: id,
        },
      });
    },


    handleAdd(fatherid) {
       this.$router.push({
          path:'categoryAdd',
          query:{
              fatherid:fatherid
          }
        })
    },

    

    handleSizeChange(val) {
      this.pageSize = val;
      this.getCategoryList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getCategoryList();
    },

    async getCategoryList() {
      let { data: res } = await this.$http.post(
        "/category/selectPage",
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
    async handleDelete(id) {
      await this.$http.post(
        "/category/delete",
        this.qs.stringify({
          id: id,
        })
      );
      await this.getCategoryList()
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
:deep(.el-table td.el-table__cell div){
      display: flex !important;
      padding: 0px 5px;
}
</style>
