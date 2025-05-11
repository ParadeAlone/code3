<template>
  <div>
           <div style="padding:0px 30px;width: 100%;margin-top: 30px;">


                <div class="layui-container" style="border: 1px solid #B8B8B8;width: auto;">
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-md12 content detail">
                            <div class="fly-panel detail-box">
                                <h1>{{article.title}}</h1>
                                <div class="fly-detail-info">
                                    <span v-if="article.isjh=='yes'" class="layui-badge layui-bg-red">精帖</span>
                                    <div class="fly-admin-box" data-id="123">
                                    </div>
                                    <span class="fly-list-nums">
                                    <i class="iconfont" title="跟帖">&#xe60c;</i> {{article.replyNum}}
                                    <i class="iconfont" title="人气">&#xe60b;</i> {{article.cs}}
                                    </span>

                                </div>
                                <div class="detail-about" style="margin-top: 20px;">
                                    <a class="fly-avatar" href="#">
                                        <img :src="[baseURL+'/upload/'+article.filename]"
                                             alt="article.member.tname">
                                    </a>
                                    <div class="fly-detail-user" style="text-align: left">
                                        <a href="#" class="fly-link">
                                            <cite>{{article.memberName}}</cite>
                                            <i class="iconfont icon-renzheng" title="实名认证"></i>
                                        </a>
                                        <span>{{article.savetime}}</span>
                                    </div>
                                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                                        <span type="edit">&nbsp;</span>
                                    </div>
                                </div>
                                <div class="detail-body photos">
                                    <p v-html="article.content"></p>
                                </div>
                            </div>


                            <div class="fly-panel detail-box" id="flyReply"
                                 style="padding-left: 10px;">
                                <fieldset class="layui-elem-field layui-field-title"
                                          style="text-align: center;">
                                    <legend>评论</legend>
                                </fieldset>

                                <ul class="jieda" id="jieda">

                                    <li v-for="(reply,index) in tableData" :key="index" data-id="111" class="jieda-daan">
                                        <a name="item-1111111111"></a>
                                        <div class="detail-about detail-about-reply">
                                            <a class="fly-avatar" href="">
                                                <img :src="[baseURL+'/upload/'+reply.filename]" alt=" ">
                                            </a>
                                            <div class="fly-detail-user" style="text-align: left">
                                                <a href="#}" class="fly-link">
                                                    <cite>{{reply.memberName}}</cite>
                                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                                </a>
                                            </div>

                                            <div class="detail-hits" style="text-align: left">
                                                <span>{{reply.savetime}}&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                            </div>
                                        </div>
                                        <div class="detail-body jieda-body photos" style="text-align: left">
                                            <p v-html="reply.content"></p>
                                        </div>
                                    </li>

                                    <!-- 无数据时 -->
                                    <!-- <li class="fly-none">消灭零回复</li> -->
                                </ul>

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


                                <div class="layui-form layui-form-pane" style="padding-left: 30px;">
                                    <el-form ref="reply" :rules="rules" :model="reply" label-width="80px">


                                        <el-form-item prop="content" label="回复内容">
                                                <my-editor
                                                  :content="reply.content"
                                                  @getContent="getContent"
                                                ></my-editor>
                                        </el-form-item>


                                        <el-form-item>
                                            <el-button type="primary" @click="onSubmit">提交</el-button>
                                            <!--<el-button>取消</el-button>-->
                                        </el-form-item>
                                    </el-form>
                                </div>


                            </div>


                        </div>
                    </div>
                </div>


            </div>

  </div>
</template>
<script>
import MyEditor from "@/components/WangEditor.vue";
export default {
  data() {
    return {
      
      tableData: [],
      pageNum: 1, //设置首页页码
      pageSize: 10, //设置一页显示的条数
      total: 0, //总条数
      pages: 1, //一共多少页

      reply: {
          title: "",
          content: "",
      },
      rules: {
          content: [{required: true, message: '请输入内容', trigger: 'blur'}],
      },
      sessionmember: null,
    };
  },
  props: ["article"],
  components: {
    MyEditor,
  },
  created() {
    this.baseURL = this.$http.defaults.baseURL
    if (this.Cookie.get("sessionmember")) {
      this.sessionmember = JSON.parse(this.Cookie.get("sessionmember"));
    }
  },
  watch: {
  },
  methods: {

     onSubmit(){
      if(this.sessionmember){
          this.$refs.reply.validate( async (valid)=> {
              if (valid) {
                  await this.$http.post("/reply/insert",this.qs.stringify({
                      articleid: this.article.id,
                      content: this.reply.content,
                      memberid: this.sessionmember.id,
                  }))
                      this.reply.content = ""
                      this.$message.success("操作成功")
                      this.getReplyList()
              }
          })
      }else{
        this.$router.push("/member/login")
      }

    },

    getContent(value) {
      this.reply.content = value;
    },

    handleSizeChange(val) {
      this.pageSize = val;
      this.getReplyList();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getReplyList();
    },
    async getReplyList() {
      let { data: res } = await this.$http.post(
        "/reply/selectPage",
        this.qs.stringify({
          articleid: this.article.id,
          start: this.pageNum,
          limit: this.pageSize,
        })
      );
      this.total = res.data.totalSize;
      this.tableData = res.data.content;
      console.log(9999,this.tableData)

    },
  },
};
</script>
<style scoped>
.el-select {
  width: 100%;
}


:deep(.el-pagination.is-background .el-pager li:not(.disabled).active){
  background-color: #e4d804;
  color: #FFF;
}

:deep(.el-pager li:hover){
  color: #e4d804 !important;
}
</style>