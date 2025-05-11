import Vue from 'vue'
import Vuex from 'vuex'

//用于响应组件中的动作
const actions = {}

//用于操作数据
const mutations = {
    pushpermissionMenusList(state, value) { 
        state.permissionMenusList = value 
    },
}

//用于储存数据
const state = {
    count: 1,
    permissionMenusList: [],

}

Vue.use(Vuex);
// 创建一个新的 store 实例
const store = new Vuex.Store({
    actions,
    mutations,
    state
})

export default store