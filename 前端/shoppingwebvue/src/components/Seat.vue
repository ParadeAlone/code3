<template>
<div class="btn-item">
      <h6 class="h6">{{area}}</h6>
      <el-checkbox-group v-model="checkboxStr" @change="changeValue">
        <div class="search-categorylist">
            <el-checkbox-button v-for="(ticket,index) in allSeats" :key="index"  :disabled="ticket.status=='已占座'?true:false" :label="ticket.id+':'+ticket.seatNum">{{ticket.seatNum}}
            </el-checkbox-button>
        </div>
    </el-checkbox-group>
</div>
</template>
<script>
export default {
    props: ['allSeats','area'],//子组件向父组件传值
    watch: {
        checkboxValue(newValue,oldValue){
            this.$emit('getCheckboxValue',newValue)
        },
        checkboxSeatNum(newValue,oldValue){
            this.$emit('getCheckboxSeatNum',newValue)
        },
    },
    data(){
        return{
            checkboxStr: [],
            checkboxValue: [],//选中的座位ID
            checkboxSeatNum: [],//选中座位的座位号
        }
    },
    methods: {
        changeValue(value){
            this.checkboxValue = [];
            this.checkboxSeatNum= [];
            value.forEach(item=>{
            const value = item.split(':')[0];
            const label= item.split(':')[1];
            this.checkboxValue.push(value);
            this.checkboxSeatNum.push(label);
            });
        }
    }

}
</script>
<style scoped>

.btn-item{
    background: #1f1e24;
    padding: 30px 0px;
}
.search-categorylist{
    display: flex;
    flex-wrap: wrap;
    justify-content: center !important;
}
.el-checkbox-button{
    margin-right: 10px;
}

:deep(.el-checkbox-button__inner){
    border-radius: 5px 5px;
}

:deep(.el-checkbox-button:first-child .el-checkbox-button__inner){
    border-radius: 5px 5px;
}

:deep(.el-checkbox-button:last-child .el-checkbox-button__inner){
    border-radius: 5px 5px;
}

:deep(.el-checkbox-button__inner){
   width: 100px;
}
:deep(.el-checkbox-button){
    margin-bottom: 40px;
}
.h6{
    width: 100%;
    text-align: center;
    padding: 0px 0px 30px 0px;
}

/** 
 选中状态
*/
:deep(.el-checkbox-button.is-checked .el-checkbox-button__inner){
    background-color: #e4d804;
    border-color: #e4d804;
    box-shadow: -1px 0 0 0 #c5bf4e;
}
:deep(.el-checkbox-button__inner:hover){
    color: #e4d804;
}
/** 已选状态 */
:deep(.el-checkbox-button.is-disabled .el-checkbox-button__inner){
    background-color: #c43939;
    border-color: #c43939;
    box-shadow: -1px 0 0 0 #c43939;
}

</style>