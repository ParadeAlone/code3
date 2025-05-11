<template>
  <div>
    <div style="padding: 0px 30px; width: 100%; margin-top: 30px">
      <div
        class="layui-container"
        style="border: 1px solid #b8b8b8; width: auto; padding: 0px"
      >
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12">
            <div class="fly-panel" style="margin-bottom: 0">
              <div
                class="fly-panel-title fly-filter"
                style="font-weight: bold; text-align: left"
              >
                <a href="javascript:void(0)" @click="searchResult('')" >全部</a>
                <span v-for="(section, index) in sectionList" :key="index">
                  <a href="javascript:void(0)" @click="searchResult(section.id)" :style="categoryid == section.id ? 'border-bottom: 2px solid #0BAC4F' : ''" class="layui-this">{{ section.name }}</a
                  >
                </span>
              </div>
              <ul class="fly-list">
                <li
                  v-for="(article, index) in tableData"
                  :key="index"
                  style="height: auto"
                >
                  <a href="" class="fly-avatar">
                    <img
                      :src="[baseURL+'/upload/' + article.filename]"
                      alt="article.memberName"
                    />
                  </a>
                  <h2 style="margin-bottom: 0px; text-align: left">
                    <a class="layui-badge" style="top: 0px; height: 18px">{{
                      article.sectionName
                    }}</a>
                    <a href="javascript:void(0)"  @click="handleToDetails(article.id)" style="color: grey">{{ article.title }}</a>
                  </h2>
                  <div class="fly-list-info" style="text-align: left">
                    <a href="#" link>
                      <cite>{{ article.memberName }}</cite>
                      <i class="iconfont icon-renzheng" title="实名认证"></i>
                      <!--
                                                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                                <i class="layui-badge fly-badge-vip">VIP3</i>
                                                -->
                    </a>
                    <span>{{ article.savetime }}</span>

                    <!--<span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
                                            <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
                    <span class="fly-list-nums">
                      <i class="iconfont icon-pinglun1" title="回帖"></i
                      >{{ article.replyNum }}
                    </span>
                  </div>
                  <div class="fly-list-badge">

                  <el-tag v-if="article.istop == '是'" class="tag-group__title" size="mini" effect="success"> 置顶</el-tag>
                  <el-tag v-if="article.isjh == '是'" class="tag-group__title" size="mini" effect="danger"> 精帖</el-tag>
                  </div>
                </li>
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
            </div>


          </div>
        </div>
      </div>

      <div
        class="layui-container"
        style="
          border: 1px solid #b8b8b8;
          margin-top: 20px;
          width: auto;
          padding: 0px;
        "
      >
        <div
          style="
            background: #e3e3e3;
            color: #0bac4f;
            font-weight: bold;
            padding: 10px 10px;
            border-bottom: 1px solid #b8b8b8;
          "
        >
          快速发帖
        </div>
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12" style="padding: 20px 50px">
            <el-form
              ref="article"
              :rules="rules"
              :model="article"
              label-width="80px"
            >
              <el-form-item prop="title" label="标题">
                <el-input v-model="article.title" placeholder="标题"></el-input>
              </el-form-item>

              <el-form-item prop="sectionid" label="版块">
                <el-select v-model="article.sectionid" placeholder="请选择版块">
                  <el-option
                    v-for="(section, index) in sectionList"
                    :key="index"
                    :label="section.name"
                    :value="section.id"
                  >
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item prop="content" label="内容">
                <my-editor
                  :content="article.content"
                  @getContent="getContent"
                ></my-editor>
              </el-form-item>

              <el-form-item>
                <el-button type="info" @click="onSubmit"> 提交</el-button>
              </el-form-item>
            </el-form>
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
      sectionid: "",
      article: {
        title: "",
        sectionid: "",
        content: "",
      },
      rules: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        sectionid: [
          { required: true, message: "请输入选择版块", trigger: "blur" },
        ],
        content: [{ required: true, message: "请输入内容", trigger: "blur" }],
      },
      sessionmember: null,
    };
  },
  props: ["tableData", "pageNum", "pageSize", "total", "pages", "sectionList","handleCurrentChange","handleSizeChange","categoryid"],
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
    sectionid(newValue,oldValue){
      this.$emit("getSectionid",newValue)
    }
  },
  methods: {

    handleToDetails(id){
      this.$router.push({
        path: "/member/articleDetails",
        query: {
          id: id,
        }
      })
    },
    searchResult(sectionid){
      if(sectionid ==""){
        this.$parent.categoryid = ""
        this.$parent.getArticleList()
      }else{
        this.sectionid = sectionid
      }
    },
    getContent(value) {
      this.article.content = value;
    },
    onSubmit() {
      if (this.sessionmember) {
        this.$refs.article.validate(async (valid) => {
          if (valid) {
            await this.$http.post(
              "/article/insert",
              this.qs.stringify({
                title: this.article.title,
                sectionid: this.article.sectionid,
                content: this.article.content,
                cs: 0,
                istop: "否",
                isjh: "否",
                memberid: this.sessionmember.id,
              })
            );
            this.article.title = "";
            this.article.sectionid = "";
            this.article.content = "";
            this.$parent.getArticleList()
            this.$message.success("发表成功");
          }
        });
      } else {
        this.$router.push("/member/login");
      }
    },
  },
};
</script>
<style scoped>
.el-select {
  width: 100%;
}
</style>