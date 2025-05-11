
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
              <li class="breadcrumb-item">
                <a href="statisticsList"><i class="feather icon-home"></i></a>
              </li>
              <li class="breadcrumb-item"><a href="statisticsList">统计</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-xl-12 col-md-12">
        <div class="row">
          <!-- <div class="col-md-4">
              <div class="card bg-c-blue order-card">
                <div class="card-body">
                  <h6 class="m-b-20">今日采购额</h6>
                  <h2 class="text-start"><span>{{sale.total}}</span><i
                      class="feather icon-shopping-cart float-end"></i></h2>
                  <p class="m-b-0 text-end">Completed Orders<span
                      class="float-start">351</span></p>
                </div>
              </div>
            </div> -->
          <!-- <div class="col-md-4">
              <div class="card bg-c-green order-card">
                <div class="card-body">
                  <h6 class="m-b-20">Total Sales</h6>
                  <h2 class="text-start"><span>1641</span><i
                      class="feather icon-shopping-cart float-end"></i></h2>
                  <p class="m-b-0 text-end">This Month<span class="float-start">213</span>
                  </p>
                </div>
              </div>
            </div>
              <div class="col-md-4">
              <div class="card bg-c-green order-card">
                <div class="card-body">
                  <h6 class="m-b-20">Total Sales</h6>
                  <h2 class="text-start"><span>1641</span><i
                      class="feather icon-shopping-cart float-end"></i></h2>
                  <p class="m-b-0 text-end">This Month<span class="float-start">213</span>
                  </p>
                </div>
              </div>
            </div> -->
        </div>
      </div>
      <!-- sessions-section start -->
    </div>

    <div class="row">
      <div class="col-xl-12 col-md-12">
        <div class="grid-content bg-purple">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="grid-content bg-purple">
                <el-date-picker
                  @change="changeValue1"
                  v-model="value1"
                  type="daterange"
                  value-format="yyyy-MM-dd"
                  format="yyyy-MM-dd"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                >
                </el-date-picker>
              </div>
            </el-col>
            <el-col :span="4">
              <div class="grid-content bg-purple">
                <el-button @click="gettAll" type="primary" size="small"
                  ><i class="el-icon-search"></i
                ></el-button>
              </div>
            </el-col>
          </el-row>

          <div id="chartsContainer1" style="width: 100%; height: 450px"></div>
           <div id="chartsContainer2" style="width: 100%; height: 450px"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import echarts from "echarts";
export default {
  data() {
    return {
      stime: "",
      etime: "",
      value1: "",
      sellerid: "",
    };
  },
  created() {
    this.admin = JSON.parse(sessionStorage.getItem("sessionadmin"))
    this.sellerid = this.admin.id
    this.gettj1();
    this.gettj2();

  },
  methods: {

    gettAll(){
      this.gettj1()
      this.gettj2()
    },
    changeValue1(value) {
      this.stime = value[0];
      this.etime = value[1];
    },
    async gettj1() {
      let { data: res } = await this.$http.post(
        "/statistics/tj1",
        this.qs.stringify({
          stime: this.stime,
          etime: this.etime,
          sellerid: this.sellerid,
          status: "交易完成"
        })
      );
      this.$nextTick(function () {
        let that = this;
        const chartsContainer1 = document.getElementById("chartsContainer1");
        this.mycharts = echarts.init(chartsContainer1);
        var option = {
          title: {
            show: true,
            text: "销售额统计",
            left: "center",
          },
          xAxis: {
            nameGap: 25,
            name: "日期",
            nameLocation: "center",
            type: "category",
            data: res.nlist,
          },
          yAxis: {
            nameGap: 25,
            name: "销售额",
            nameLocation: "center",
            type: "value",
            // axisLabel: {
            // // 使用 formatter 格式化标签文本
            // formatter: '{value} kg' // 在这里添加单位
            // }
          },
          series: [
            {
              data: res.vlist,
              type: "line",
              label: {
                show: true, // 显示数值
              },
            },
          ],
        };

        this.mycharts.setOption(option);
      });
    },


async gettj2() {
      let { data: res } = await this.$http.post(
        "/statistics/tj2",
        this.qs.stringify({
          sellerid: this.sellerid,
          stime: this.stime,
          etime: this.etime,
        })
      );
      this.$nextTick(function () {
        let that = this;
        const chartsContainer2 = document.getElementById("chartsContainer2");
        this.mycharts = echarts.init(chartsContainer2);

        var option = {
            title : {
                text: '商品统计',
                // subtext: '纯属虚构',
                x:'center'
            },
            xAxis: {
              type: 'category',
              data: res.nlist,
              axisLabel: {
                  rotate: 45 // X轴标签旋转 45 度
              }
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: res.vlist,
                type: 'bar',
                label: {
                    show: true, // 显示标签
                    position: 'top', // 标签位置
                    // 可以通过 formatter 自定义显示的文本
                    formatter: '{c}' // {c} 表示数据值
                }
              }
            ]
          };
        this.mycharts.setOption(option);
      });
    },
  
  },
};
</script>
<style scoped>
.breadcrumb-item a {
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
  background: white !important;
}

:deep(.el-input__inner) {
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
.page-header
  .page-header-title
  + .breadcrumb
  > .breadcrumb-item
  a
  :first-child {
  color: #746ca7;
}

:deep(#chartsContainer1) {
  background: white !important;
}

:deep(.el-range-input) {
  font-size: 12px !important;
}
</style>
