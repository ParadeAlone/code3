
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
                <li class="breadcrumb-item"><a href="mainList">欢迎页</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="hy">
                    欢迎登录网络应用开发课程设计 ！
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


  </div>
</template>
<script>
import echarts from "echarts";
export default {
  data() {
    return {
    };
  },
  created(){
  },
  methods:{
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

:deep(#chartsContainer1){
    background: white !important;
}

:deep(.el-range-input){
  font-size: 12px !important;
}

.hy{
  text-align: center;
  font-size: 20px;
}

</style>
