<!--
  功能： Vue2初始化模板 | 修订时间：2020.10.01 | 组件生成时间：2023年05月16日 15:43:30
-->
<template>
  <span :endTime="endTime" :callback="callback" :endText="endText">
    <slot>
      {{ content }}
    </slot>
  </span>
</template>
<script>
export default {
  data() {
    return {
      content: "",
    };
  },
  props: {
    buyStatus: {
      type: String,
      default: "",
    },
    endTime: {
      type: String,
      default: "",
    },
    endText: {
      type: String,
      default: "已结束",
    },
    callback: {
      type: Function,
      default: "",
    },
  },
  mounted() {
    
  },
  watch:{
    endTime(newValue,oldValue){
      this.countdowm(newValue);
    }
  },
  methods: {
    countdowm(timestamp) {
      let self = this;
      let timer = setInterval(function () {
        let nowTime = new Date();
        let endTime = new Date(timestamp);
        let t = endTime.getTime() - nowTime.getTime();
        if (t > 0) {
          let day = Math.floor(t / 86400000);
          let hour = Math.floor((t / 3600000) % 24);
          let min = Math.floor((t / 60000) % 60);
          let sec = Math.floor((t / 1000) % 60);
          hour = hour < 10 ? "0" + hour : hour;
          min = min < 10 ? "0" + min : min;
          sec = sec < 10 ? "0" + sec : sec;
          let format = "";
          if (day > 0) {
            format = `${day}天${hour}小时${min}分${sec}秒`;
          }
          if (day <= 0 && hour > 0) {
            format = `${hour}小时${min}分${sec}秒`;
          }
          if (day <= 0 && hour <= 0) {
            format = `${min}分${sec}秒`;
          }
          self.content = format;
        } else {
          clearInterval(timer);
          self.content = self.endText;
          self.$parent.load()
          self._callback();
        }
      }, 1000);
    },
    _callback() {
      if (this.callback && this.callback instanceof Function) {
        this.callback(...this);
      }
    },
  },
};
</script>
<style scoped>
/* @import url(); 引入css类 */
</style>