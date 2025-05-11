
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
                <li class="breadcrumb-item"><a href="menusList">菜单管理</a></li>
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
                      <el-button size="small" type="primary" @click="handleAdd">新增一级菜单</el-button>
                  </el-col>
              </el-row>
              <template>
                <div style="margin-top: 20px;">
                    <el-tree :data="menusList"  node-key="id" :default-expand-all="false" :expand-on-click-node="false">

                      <div class="custom-tree-node" slot-scope="{ node, data }"> <span class="menus-one">{{ node.label }}</span>
                        <div class="btn-list">
                          <el-button type="text" v-if="node.level ==2 || node.level ==1" size="mini" @click="append(node.data.id,node)"> <i class="el-icon-plus"></i> </el-button>
                          <el-button type="text"  size="mini" @click="edit(node.data.id,node)"> <i class="el-icon-edit"></i> </el-button>
                          <el-button type="text" size="mini" @click="remove(node.data.id)"> <i class="el-icon-delete"></i> </el-button>
                        </div>
                     </div>
                    </el-tree>
                </div>
              </template>
            </div>
        </div>
      </div>


          <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" >
        <el-form ref="menus" :model="menus" :rules="rules">

            <el-form-item prop="serial" label="序号" :label-width="formLabelWidth">
                <el-input v-model="menus.serial" placeholder="名称"></el-input>
            </el-form-item>

            <el-form-item prop="name" label="名称" :label-width="formLabelWidth">
                <el-input v-model="menus.name" placeholder="名称"></el-input>
            </el-form-item>

            <el-form-item prop="url" label="URL" :label-width="formLabelWidth">
                <el-input v-model="menus.url" placeholder="URL"></el-input>
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
export default {
  data() {
    return {
      defaultProps: {
          children: 'children',
          label: 'label'
      },

      dialogTitle:"新增二级菜单",
      dialogFormVisible:false,
      formLabelWidth: "100px",
      menus: {
          serial: '',
          name: '',
          url: '',
          upid:"",
          id:"",
      },
      rules: {
          serial: [{required: true, message: '序号输入错误', pattern: /^[1-9]\d*$/,trigger: 'blur'}],
          name: [{required: true, message: '请输入名称', trigger: 'blur'}],
          url: [{required: true, message: '请输入URL', trigger: 'blur'}],
      },
      menusList:[],

    };
  },
  created() {
    this.getMenusList();
  },
  methods: {

    async remove(id){
        await this.$http.post("/menus/delete",this.qs.stringify({
          id:id,
        }))
        this.getMenusList()
    },

    edit(id,node) {
        if(node.level == 1){
            this.$router.push({
              path: "menusAdd",
              query: {
                id: id,
              }
            })
        }else if(node.level == 2){
            this.dialogTitle = "编辑二级菜单"
            this.menus.id = id
            this.show()

        }else{
            this.menus.id = id
            this.dialogTitle = "编辑三级菜单"
            this.show()
        }
    },

    async show(){
      let { data : res} = await this.$http.post("/menus/get",this.qs.stringify({
          id: this.menus.id,
      }))
      this.menus = res.data
      this.dialogFormVisible = true
    },
 
    append(id,node) {
        if(node.level == 1){
            this.dialogTitle = "新增二级菜单"
        }else{
            this.dialogTitle = "新增三级菜单"
        }
        this.menus.upid = id
        this.menus.serial = ""
        this.menus.name = ""
        this.menus.url = ""
        this.dialogFormVisible = true

    },

    onSubmit(){
      this.$refs.menus.validate( async (valid) => {
          if (valid) {
              if(this.dialogTitle == "新增二级菜单" || this.dialogTitle == "新增三级菜单"){
                await this.$http.post('/menus/insert',this.qs.stringify({
                      serial: this.menus.serial,
                      name: this.menus.name,
                      url: this.menus.url,
                      upid:this.menus.upid,
                      leaf:0,
                }))
                 await this.getMenusList()
                 this.dialogFormVisible = false
              }else{

                await this.$http.post('/menus/update',this.qs.stringify({
                      serial: this.menus.serial,
                      name: this.menus.name,
                      url: this.menus.url,
                      id:this.menus.id,
                }))
                 await this.getMenusList()
                 this.dialogFormVisible = false
              }
          }

      })
    },
    handleAdd(){
      this.$router.push('menusAdd')
    },
    async getMenusList() {
      let { data: res } = await this.$http.post(
        "/menus/selectAll",
        this.qs.stringify({
          sortBy:"serial"
        })
      );
      this.menusList = res.data;

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

:deep(.el-tree-node__content){
 padding: 15px 0px !important;
}

.custom-tree-node{
  display: flex;
  align-items: center;
}

.el-tree-node.is-focusable .el-tree-node__content .custom-tree-node .menus-one{
  font-weight: bold;
}

.el-tree-node__children .menus-one{
  font-weight: 400 !important;
}

:deep(.el-tree-node__children){
  padding-left: 20px !important;
}

.btn-list{
  margin-left: 15px;
}

</style>
