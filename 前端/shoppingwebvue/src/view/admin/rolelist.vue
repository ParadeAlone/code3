
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
                <li class="breadcrumb-item"><a href="roleList">角色管理</a></li>
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
                        <el-button @click="getRoleList" type="primary" size="small"
                          ><i class="el-icon-search"></i
                        ></el-button>
                          <el-button  @click="handleAdd" type="primary" size="small"
                          ><i class="el-icon-plus"></i
                        ></el-button>
                      </div>
                    </el-col>
                  </el-row>

                  <template>

                    <el-table size="mini" :data="tableData"  border style="width: 100%">
                      
                      <el-table-column show-overflow-tooltip prop="name" label="角色"></el-table-column>

                      <el-table-column width="100px" label="操作">
                        <template slot-scope="scope">
                          <el-link :underline="false" @click="permissionAdd(scope.$index, scope.row)"><i class="el-icon-medal"></i></el-link>
                          <el-link  :underline="false" @click="handleEdit(scope.row.id)"><i class="el-icon-edit"></i></el-link>
                          <el-link  :underline="false" @click="handleDelete(scope.$index, scope.row, tableData)"><i class="el-icon-delete"></i></el-link>
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

      <el-dialog
            title="角色授权"
            :visible.sync="dialogVisible"
            width="50%"
            :before-close="handleClose">
        <!--内容-->
        <el-tree
                :data="treeData"
                show-checkbox
                node-key="id"
                ref="tree"
                :default-checked-keys="checkedData"
                :props="defaultProps">
        </el-tree>
        <!--内容-->

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="getCheckedKeys">确 定</el-button>
        </span>
    </el-dialog>

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


      dialogVisible: false,
      treeData: [],
      defaultProps: {
          children: 'children',
          label: 'label'
      },
      currentclickid: 0,//当前点击ID
      checkedData: [],

    };
  },
  created() {
    this.getRoleList();
    this.getmenuslist()
  },
  methods: {


    //展开授权页面
    permissionAdd(index, row) {
        this.checkedData = []
        this.dialogVisible = true
        this.currentclickid = row.id
        this.getCheckedData(row.id)
    },

    //获取授权菜单
    async getmenuslist() {
      let { data : res } = await this.$http.post('/menus/selectTreeAll',this.qs.stringify({
      }))
      this.treeData = res.data
    },

    handleClose() {
        this.dialogVisible = false
    },
    //通过key来获取选中节点
    async getCheckedKeys() {
        this.dialogVisible = false
        let arr = new Array()
        let arr1 = new Array()

        this.$refs.tree.getCheckedNodes().forEach(item => {
            if (item.treenum == 3) {
                arr.push(item.id)
            } else if (item.treenum == 2) {
                arr1.push(item.id)
            }
        })

        //将获取的节点的ID提交
        let { data : res } = await this.$http.post('/permission/insert',this.qs.stringify({
            arr: arr.toString(),
            arr1: arr1.toString(),
            roleid: this.currentclickid,
        }))
         this.$message.success("授权成功")
         await this.getCheckedData(this.currentclickid)
    },
    //获取已授权项
    async getCheckedData(roleid) {

        let { data : res } = await this.$http.post('/permission/selectAll',this.qs.stringify({
            roleid: roleid,
        }))
        this.checkedData = res.data

        this.checkedData.forEach(item => {
            this.checkedData.push(item.menusid)
        })
        this.$refs.tree.setCheckedKeys([]);//el-tree清队节点缓存
    },
    handleEdit(id) {
      this.$router.push({
        path: "roleAdd",
        query: {
          id: id,
        },
      });
    },
    handleAdd() {
       this.$router.push("roleAdd")
    },

    handleSizeChange(val) {
      this.pageSize = val;
      this.getRoleList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getRoleList();
    },

    async getRoleList() {
      let { data: res } = await this.$http.post(
        "/role/selectPage",
        this.qs.stringify({
          key: this.key,
          // notRoleId: 1,
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
        "/role/delete",
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
