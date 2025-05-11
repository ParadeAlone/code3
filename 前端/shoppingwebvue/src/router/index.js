import Vue from 'vue'
import VueRouter from 'vue-router'
import Cookie from 'js-cookie'
import AdminLogin from '../view/admin/login.vue'
import AdminIndex from '../view/admin/index.vue'
import AdminMain from '../view/admin/main.vue'
import UserList from '../view/admin/userlist.vue'
import UserAdd from '../view/admin/useradd.vue'
import AdminEdit from '../view/admin/adminedit.vue'
import UpassEdit from '../view/admin/upassedit.vue'
import CategoryList from '../view/admin/categorylist.vue'
import CategoryAdd from '../view/admin/categoryadd.vue'
import RoleList from '../view/admin/rolelist.vue'
import RoleAdd from '../view/admin/roleadd.vue'
import MenusList from '../view/admin/menuslist.vue'
import MenusAdd from '../view/admin/menusadd.vue'
import ProductList from '../view/admin/productlist.vue'
import ProductAdd from '../view/admin/productadd.vue'
import InventoryAdd from '../view/admin/inventoryadd.vue'
import InventoryInList from '../view/admin/inventoryinlist.vue'
import InventoryOutList from '../view/admin/inventoryoutlist.vue'
import TorderList from '../view/admin/torderlist.vue'
import NewsList from '../view/admin/newslist.vue'
import NewsAdd from '../view/admin/newsadd.vue'
import ImgadvList from '../view/admin/imgadvlist.vue'
import ImgadvAdd from '../view/admin/imgadvadd.vue'
import CommentList from '../view/admin/commentlist.vue'
import CommentEdit from '../view/admin/commentedit.vue'
import StatisticsList from '../view/admin/statisticslist.vue'
import MemberList from '../view/admin/memberlist.vue'
import MessageList from '../view/admin/messagelist.vue'
import MessageEdit from '../view/admin/messageedit.vue'
import LoginipList from '../view/admin/loginiplist.vue'




import MemberIndex from '../view/member/index.vue'
import MemberLogin from '../view/member/login.vue'
import MemberRegister from '../view/member/register.vue'
import MembereEdit from '../view/member/memberedit.vue'
import MemberUpassEdit from '../view/member/upassedit.vue'
import MemberProductList from '../view/member/productlist.vue'
import MemberProductDetail from '../view/member/productdetail.vue'
import CartList from '../view/member/cartlist.vue'
import MemberTorderList from '../view/member/torderlist.vue'
import CollectList from '../view/member/collectlist.vue'
import MemberNewsList from '../view/member/newslist.vue'
import NewsDetail from '../view/member/newsdetail.vue'
import AddressList from '../view/member/addresslist.vue'
import AddressAdd from '../view/member/addressadd.vue'
import MemberMessageList from '../view/member/messagelist.vue'

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}


const adminRoutes = [
    { path: '/admin/login', name: 'admin/login', component: AdminLogin },

    {
        path: '/admin/index',
        component: AdminIndex
    },
    {
        path: '/admin',
        name: 'admin',
        component: AdminIndex,
        children: [
            {path: 'mainList', name: 'admin/mainList', component: AdminMain},
            {path: 'adminEdit', name: 'admin/adminEdit', component: AdminEdit},
            {path: 'upassEdit', name: 'admin/upassEdit', component: UpassEdit},
            {path: 'categoryList', name: 'admin/categoryList', component: CategoryList},
            {path: 'categoryAdd', name: 'admin/categoryAdd', component: CategoryAdd},
            { path: 'roleList', name: 'admin/roleList', component: RoleList },
            { path: 'roleAdd', name: 'admin/roleAdd', component: RoleAdd },
            { path: 'menusList', name: 'admin/menusList', component: MenusList },
            { path: 'menusAdd', name: 'admin/menusAdd', component: MenusAdd },
            { path: 'userList', name: 'admin/userList', component: UserList },
            { path: 'userAdd', name: 'admin/userAdd', component: UserAdd },
            { path: 'productList', name: 'admin/productList', component: ProductList },
            { path: 'productAdd', name: 'admin/productAdd', component: ProductAdd },
            { path: 'inventoryAdd', name: 'admin/inventoryAdd', component: InventoryAdd },
            { path: 'inventoryInList', name: 'admin/inventoryInList', component: InventoryInList },
            { path: 'inventoryOutList', name: 'admin/inventoryOutList', component: InventoryOutList },
            { path: 'tOrderList', name: 'admin/tOrderList', component: TorderList },
            { path: 'newsList', name: 'admin/newsList', component: NewsList },
            { path: 'newsAdd', name: 'admin/newsAdd', component: NewsAdd },
            { path: 'imgadvList', name: 'admin/imgadvList', component: ImgadvList },
            { path: 'imgadvAdd', name: 'admin/imgadvAdd', component: ImgadvAdd },
            { path: 'commentList', name: 'admin/commentList', component: CommentList },
            { path: 'commentEdit', name: 'admin/commentEdit', component: CommentEdit },
            { path: 'statisticsList', name: 'admin/statisticsList', component: StatisticsList },
            { path: 'memberList', name: 'admin/memberList', component: MemberList },
            { path: 'messageList', name: 'admin/messageList', component: MessageList },
            { path: 'messageEdit', name: 'admin/messageEdit', component: MessageEdit },
            { path: 'loginipList', name: 'admin/loginipList', component: LoginipList },

            
        ]
    },

]

const memberRoutes = [
    { path: '/member/index', name: 'member/index', component: MemberIndex },
    { path: '/member/login', name: 'member/login', component: MemberLogin },
    { path: '/member/register', name: 'member/register', component: MemberRegister },
    { path: '/member/membereEdit', name: 'member/membereEdit', component: MembereEdit },
    { path: '/member/upassEdit', name: 'member/upassEdit', component: MemberUpassEdit },
    { path: '/member/productList', name: 'member/productList', component: MemberProductList },
    { path: '/member/productDetail', name: 'member/productDetail', component: MemberProductDetail },
    { path: '/member/cartList', name: 'member/cartList', component: CartList },
    { path: '/member/tOrderList', name: 'member/tOrderList', component: MemberTorderList },
    { path: '/member/collectList', name: 'member/collectList', component: CollectList },
    { path: '/member/newsList', name: 'member/newsList', component: MemberNewsList },
    { path: '/member/newsDetail', name: 'member/newsDetail', component: NewsDetail },
    { path: '/member/addressList', name: 'member/addressList', component: AddressList },
    { path: '/member/addressAdd', name: 'member/addressAdd', component: AddressAdd },
    { path: '/member/messageList', name: 'member/messageList', component: MemberMessageList },








]

const router = new VueRouter({
    // routes,// (缩写) 相当于 routes: routes
    mode: 'history'
})

router.addRoutes(adminRoutes)
router.addRoutes(memberRoutes)



router.beforeEach((to, from, next) => {
    console.log(4444,to.name)
    if(to.name != null){
        if (to.path === from.path) {
            next(false); // 防止重复导航
        } else {
            next(); // 继续导航
        }
        if (to.name.includes('admin')) {
            const adminToken = sessionStorage.getItem("sessionadmin")
            if (!adminToken && to.name !== 'admin/login') {
                next({ name: 'admin/login' })
            }else if (adminToken && to.name === 'admin/login') {
                next({ name: 'admin/mainList' })
            } else {
                next()
            }
        } else if (to.name.includes('member')) {
            const memberToken = sessionStorage.getItem("sessionmember")
            if (!memberToken) {
                if (
                    to.name === 'member/cartList'
                    || to.name === 'member/addressList'
                    || to.name === 'member/tOrderList'
                    || to.name === 'member/membereEdit'
                    || to.name === 'member/collectList'
                    || to.name === 'member/upassEdit'
                ) {
                    next({ name: 'member/login' })
                }
            } else { 
              next()
            }
        }
        
    }else{
        next({ name: 'member/index' })
    }

})



export default router
