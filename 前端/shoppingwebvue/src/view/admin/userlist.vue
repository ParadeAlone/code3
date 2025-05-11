
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
                <li class="breadcrumb-item"><a href="userList">销售人员管理</a></li>
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
                          placeholder="登录名或姓名"
                        ></el-input>
                      </div>
                    </el-col>

                    <el-col :span="4">
                      <div class="grid-content bg-purple">
                          <el-select v-model="roleid" placeholder="请选择角色">
                              <el-option
                                      v-for="(role,index) in roleList"
                                      :key= "index"
                                      :label="role.name"
                                      :value="role.id">
                              </el-option>
                          </el-select>
                      </div>
                    </el-col>


                    <el-col :span="4">
                      <div class="grid-content bg-purple">
                           <el-button @click="getUserList" type="primary" size="small"
                          ><i class="el-icon-search"></i
                        ></el-button>
                        <el-button v-if="currentPermission.includes('userAdd')" @click="handleAdd" type="primary" size="small"
                          ><i class="el-icon-plus"></i
                        ></el-button>
                      </div>
                    </el-col>
                  </el-row>

                  <template>
                    <el-table size="mini" :data="tableData"  border style="width: 100%">
                      
                      <el-table-column show-overflow-tooltip prop="uname" label="登录名"></el-table-column>
                      <el-table-column show-overflow-tooltip  label="密码">******</el-table-column>
                      <el-table-column show-overflow-tooltip prop="tname" label="姓名"></el-table-column>
                      <el-table-column show-overflow-tooltip prop="tel" label="电话"></el-table-column>
                      <el-table-column show-overflow-tooltip prop="roleName" label="角色"></el-table-column>

                      <el-table-column show-overflow-tooltip  label="状态">
                          <template slot-scope="scope">
                            <el-link v-if="scope.row.status=='正常'" @click="handleEditStatus(scope.row.id,'冻结')" type="primary" :underline="false" style="font-size:12px">正常</el-link>
                            <el-link v-if="scope.row.status=='冻结'" @click="handleEditStatus(scope.row.id,'正常')" type="danger" :underline="false" style="font-size:12px">冻结</el-link>
                          </template>
                      </el-table-column>


                      <el-table-column
                          label="审核状态">
                          <template slot-scope="scope">
                          <el-popconfirm
                          v-if=" currentPermission.includes('updateAudit') && scope.row.audit=='审核中' "
                          confirm-button-text="通过"
                          cancel-button-text="驳回"
                          @confirm="editAudit(scope.row.id,'已通过')"
                          @cancel="editAudit(scope.row.id,'未通过')"
                          title="是否通过审核？"
                          >
                          <el-link  slot="reference" :underline="false"  type="primary" style="font-size:12px;">审核中</el-link>
                          </el-popconfirm>
                          <el-link v-if="currentPermission.includes('noupdateAudit') && scope.row.audit=='审核中'" :underline="false" type="primary" style="font-size:12px;">审核中</el-link>
                          <el-link v-if="scope.row.audit=='已通过'" :underline="false" type="success" style="font-size:12px;">通过</el-link>
                          <el-link v-if="scope.row.audit=='未通过'" :underline="false" type="danger" style="font-size:12px;">未通过</el-link>
                          </template>
                        </el-table-column>

                      <el-table-column width="100px" label="操作">
                        <template slot-scope="scope">
                          <el-link v-if="currentPermission.includes('userEdit')" @click="handleEdit(scope.row)"><i class="el-icon-edit"></i></el-link>
                          <el-link v-if="currentPermission.includes('userDelete')" @click="handleDelete(scope.$index, scope.row, tableData)"><i class="el-icon-delete"></i></el-link>
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
      roleid: "",
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页

      roleList: [],
    };
  },
  created(){
    this.getUserList();
    this.getRoleList();

  },
  computed: {
    currentPermission(){
      return JSON.parse(sessionStorage.getItem("permissionList"))
    }
  },
  methods: {

    async editAudit(id,audit){
        await this.$http.post('/user/update',this.qs.stringify({
            id : id,
            audit: audit,
        }))
        this.getUserList()
    },

    async getRoleList(){
        let { data : res } = await this.$http.post('/role/selectAll',this.qs.stringify({
            notRoleId : 1
        }))
        this.roleList = res.data
    },
    handleAdd(){
        this.$router.push("userAdd")
    },
    
    handleEdit(user){
        this.$router.push({
            path:'userAdd',
            query:{
                id:user.id
            }
        })
    },
    async handleEditStatus(id,status){
      await this.$http.post('/user/update',this.qs.stringify({
        id: id,
        status: status,
      }))
      await this.getUserList()
    },

    handleSizeChange(val) {
      this.pageSize = val;
      this.getUserList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getUserList();
    },

    async getUserList() {
      let { data: res } = await this.$http.post(
        "/user/selectPage",
        this.qs.stringify({
          key: this.key,
          roleid: this.roleid,
          notroleid: 1,
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
        "/user/delete",
        this.qs.stringify({
          id: rows.id,
        })
      );
    },
  }
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

</style>
