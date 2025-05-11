
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
                <li class="breadcrumb-item"><a href="productList">商品管理</a></li>
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
                                <el-select v-model="categoryid1" placeholder="请选择一级类别" @change="getchildcategory">
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
                                <el-select v-model="categoryid2" placeholder="请选择二级类别">
                                    <el-option v-for="(category2,index) in childCategoryList"
                                                :key="index"
                                               :label="category2.name"
                                               :value="category2.id">
                                    </el-option>
                                </el-select>
                            </div>
                        </el-col>


                    <el-col :span="4">
                      <div class="grid-content bg-purple">
                        <el-button @click="getProductList" type="primary" size="small"
                          ><i class="el-icon-search"></i
                        ></el-button>
                          <el-button v-if="currentPermission.includes('productAdd')" @click="handleAdd" type="primary" size="small"
                          ><i class="el-icon-plus"></i
                        ></el-button>
                      </div>
                    </el-col>
              </el-row>

                  <template>

                    <el-table size="mini" :data="tableData"  border style="width: 100%">
                      <el-table-column show-overflow-tooltip label="图片">
                        <template slot-scope="scope">
                            <el-popover placement="top-start" title="" trigger="hover">
                                <img :src="baseURL+['/upload/']+scope.row.filename" alt="" style="width: 120px;height: 100px">
                                <img slot="reference" :src="baseURL+['/upload/']+scope.row.filename" style="width: 80px;height: 60px">
                            </el-popover>
                        </template>
                      </el-table-column>
                      <el-table-column show-overflow-tooltip prop="name" label="名称"></el-table-column>
                      <el-table-column show-overflow-tooltip prop="name" label="分类">
                        <template slot-scope="scope">
                          {{scope.row.categoryName1 + " / " + scope.row.categoryName2}}
                        </template>
                      </el-table-column>
                      <el-table-column show-overflow-tooltip prop="price" label="价格"></el-table-column>
                      <el-table-column show-overflow-tooltip prop="tprice" label="特价"></el-table-column>
                      <el-table-column show-overflow-tooltip prop="stockNum" label="库存"></el-table-column>
                      <el-table-column show-overflow-tooltip  label="上下架">
                          <template slot-scope="scope">
                            <el-link v-if="currentPermission.includes('updateIssj') && scope.row.issj=='已上架'" @click="updateIssj(scope.row.id,'已下架')" :underline="false" type="success" >已上架</el-link>
                            <el-link v-if="currentPermission.includes('updateIssj') && scope.row.issj=='已下架'" @click="updateIssj(scope.row.id,'已上架')" :underline="false" type="danger" >已下架</el-link>
                            <el-link v-if="currentPermission.includes('noUpdateIssj') && scope.row.issj=='已上架'"  :underline="false" type="success" >已上架</el-link>
                            <el-link v-if="currentPermission.includes('noUpdateIssj') && scope.row.issj=='已下架'"  :underline="false" type="danger" >已下架</el-link>
                          </template>
                      </el-table-column>

                      <el-table-column
                          label="审核状态">
                          <template slot-scope="scope">
                          <el-popconfirm
                          v-if=" currentPermission.includes('updateProductAudit') && scope.row.audit=='审核中' "
                          confirm-button-text="通过"
                          cancel-button-text="驳回"
                          @confirm="editAudit(scope.row.id,'已通过')"
                          @cancel="editAudit(scope.row.id,'未通过')"
                          title="是否通过审核？"
                          >
                          <el-link  slot="reference" :underline="false"  type="primary" style="font-size:12px;">审核中</el-link>
                          </el-popconfirm>
                          <el-link v-if="currentPermission.includes('noupdateProductAudit') && scope.row.audit=='审核中'" :underline="false" type="primary" style="font-size:12px;">审核中</el-link>
                          <el-link v-if="scope.row.audit=='已通过'" :underline="false" type="success" style="font-size:12px;">通过</el-link>
                          <el-link v-if="scope.row.audit=='未通过'" :underline="false" type="danger" style="font-size:12px;">未通过</el-link>
                          </template>
                      </el-table-column>


                      <el-table-column width="100px" label="操作">
                        <template slot-scope="scope">
                          <el-link v-if="currentPermission.includes('inventoryAdd')" @click="handleInventoryAdd(scope.row.id)"><i class="el-icon-plus"></i></el-link>
                          <el-link v-if="currentPermission.includes('productEdit')" :underline="false" @click="handleEdit(scope.row.id)"><i class="el-icon-edit"></i></el-link>
                          <el-link v-if="currentPermission.includes('productDelete')" :underline="false" @click="handleDelete(scope.$index, scope.row, tableData)"><i class="el-icon-delete"></i></el-link>
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
      baseURL: "",
      key: "",
      categoryid1: "",
      categoryid2: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页
      sellerid: "",
      categoryList: [],
      childCategoryList: [],


    };
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
    this.admin = JSON.parse(sessionStorage.getItem("sessionadmin"))
    if(this.admin.roleid == '2'){
      this.sellerid = this.admin.id
    }
    this.getProductList();
  },
  computed: {
    currentPermission(){
      return JSON.parse(sessionStorage.getItem("permissionList"))
    }
  },
  methods: {

    async editAudit(id,audit){
      this.$http.post("/product/update",this.qs.stringify({
        id: id,
        audit: audit,
      }))
      this.getProductList()
    },



    async updateIssj(id,issj){
      this.$http.post("/product/update",this.qs.stringify({
        id: id,
        issj: issj,
      }))
      this.getProductList()
    },

    handleInventoryAdd(id){
      this.$router.push({
        path: 'inventoryAdd',
        query: {
          productid:id
        }
      })
    },

    async getchildcategory(value){
      this.categoryid2 = ""
      let {data : res} = await this.$http.post('/category/selectAll',this.qs.stringify({
          fatherid: value
      }))
      this.childCategoryList = res.data
    },


    handleEdit(id) {
      this.$router.push({
        path: "productAdd",
        query: {
          id: id,
        },
      });
    },


    handleAdd() {
       this.$router.push("productAdd")
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
          sellerid: this.sellerid,
          categoryid1: this.categoryid1,
          categoryid2: this.categoryid2,
          start: this.pageNum,
          limit: this.pageSize,
        })
      );

      let {data : resulst} = await this.$http.post('/category/selectAll',this.qs.stringify({
          fatherid: 0
      }))
      this.categoryList = resulst.data 

      this.total = res.data.totalSize;
      this.tableData = res.data.content;
    },
    handleDelete(index, rows, tableData) {
      tableData.splice(index, 1);
      let { data: res } = this.$http.post(
        "/product/delete",
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
:deep(.el-link--inner){
  font-size: 12px;
}

</style>
