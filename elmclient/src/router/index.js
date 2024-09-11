import { createApp } from 'vue';
import App from '../App.vue';
import { createRouter, createWebHistory } from 'vue-router';
//import Vue from 'vue'
//import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessList2 from '../views/BusinessList2.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import Orders from '../views/Orders.vue'
import UserAddress from '../views/UserAddress.vue'
import Payment from '../views/Payment.vue'
import OrderList from '../views/OrderList.vue'
import OrderList2 from '../views/OrderList2.vue'
import AddUserAddress from '../views/AddUserAddress.vue'
import EditUserAddress from '../views/EditUserAddress.vue'
import Register from '../views/Register.vue'
import My from '../views/My.vue'
import BusinessSearch from '../views/BusinessSearch.vue'
import MyWallet from '../views/MyWallet.vue'
import ChangePwd from '../views/ChangePwd.vue'
import MyImg from '../views/MyImg.vue'
import OpenPage from '../views/OpenPage.vue'
//Vue.use(VueRouter)

const routes = [{
		path: '/index',
		name: 'Index',
		component: Index
	}, {
		path: '/',
		name: 'Home',
		component: OpenPage
	}, {
		path: '/index',
		name: 'Index',
		component: Index
	}, {
		path: '/businessList',
		name: 'BusinessList',
		component: BusinessList
	}, {
		path: '/businessList2',
		name: 'BusinessList2',
		component: BusinessList2
	},{
		path: '/businessInfo',
		name: 'BusinessInfo',
		component: BusinessInfo
	}, {
		path: '/login',
		name: 'Login',
		component: Login
	}, {
		path: '/orders',
		name: 'Orders',
		component: Orders
	}, {
		path: '/orderList2',
		name: 'OrderList2',
		component: OrderList2
	}, {
		path: '/userAddress',
		name: 'UserAddress',
		component: UserAddress
	}, {
		path: '/payment',
		name: 'Payment',
		component: Payment
	}, {
		path: '/orderList',
		name: 'OrderList',
		component: OrderList
	}, {
		path: '/addUserAddress',
		name: 'AddUserAddress',
		component: AddUserAddress
	}, {
		path: '/editUserAddress',
		name: 'EditUserAddress',
		component: EditUserAddress
	}, {
		path: '/register',
		name: 'Register',
		component: Register
	}, {
		path: '/my',
		name: 'My',
		component: My
	}, {
		path: '/businessSearch',
		name: 'BusinessSearch',
		component: BusinessSearch
	},{
		path: '/myWallet',
		name: 'MyWallet',
		component: MyWallet
	},{
		path: '/changePwd',
		name: 'ChangePwd',
		component: ChangePwd
	},{
		path: '/myImg',
		name: 'MyImg',
		component: MyImg
	}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

//createApp(App).use(router).mount('#app');

const originalPush = router.push;
router.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

export default router;

/*
//解决重复路由报异常问题
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

export default router
*/