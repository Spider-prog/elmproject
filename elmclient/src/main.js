import {
	createApp
} from 'vue';
//import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import qs from 'qs'

import {
	getCurDate,
	setSessionStorage,
	getSessionStorage,
	removeSessionStorage,
	setLocalStorage,
	getLocalStorage,
	removeLocalStorage
} from './common.js'

const app = createApp(App)
const mountedApp = app.use(router);
mountedApp.mount('#app');
app.config.productionTip = false

//设置axios的基础url部分
axios.defaults.baseURL = 'http://localhost:8080/elm/';
//axios.defaults.timeout = 15000;
//axios.defaults.headers.common['Authorization']=getLocalStorage('accessToken');
// const httpService = axios.create({
//   baseURL: 'http://localhost:8080/elm/',
//   timeout: 600000,
//   responseType: `json`,
//   transformRequest: [data => qs.stringify(data)],
//   headers: {
//     "Content-Type": 'application/x-www-form-urlencoded'
//   }
// });

// 在配置里参数里设置Authorization 将 token 赋值给它
// httpService.interceptors.request.use(
// 	(config) => {
// 		// 设置token start
// 		let accessToken = getLocalStorage('accessToken');
// 		if (accessToken && accessToken !== '') {
// 			config.headers.Authorization = accessToken;
// 		}
		
// 		// 设置token end
// 		return config;
// 	},
// 	(error) => {
// 		return Promise.reject(error);
// 	}
// );

// // 在请求响应的地方回设 token 
// httpService.interceptors.response.use(
// 	(response) => {
// 		// 回设置token start
// 		let accessToken = response.headers['Authorization'];
// 		if (accessToken && accessToken !== '') {
// 			setLocalStorage('accessToken', accessToken);
// 		}
// 		const status = response.data.code || response.status;
// 		    //如果是401则跳转到登录页面
// 		if (status === 404) this.$router.push({ path: "/" });
// 		// 回设置token end
// 		return response;
// 	},

// 	// 请求异常信息提示处理
// 	(error) => {
// 		if (error && error.response) {
// 			switch (error.response.status) {
// 				case 400:
// 					error.message = `错误请求`;
// 					break;
// 				case 401:
// 					error.message = `未授权，请重新登录`;
// 					break;
// 				case 403:
// 					error.message = `拒绝访问`;
// 					break;
// 				case 404:
// 					error.message = `请求错误,未找到该资源`;
// 					break;
// 				case 405:
// 					error.message = `请求方法未允许`;
// 					break;
// 				case 408:
// 					error.message = `请求超时`;
// 					break;
// 				case 500:
// 					error.message = `服务器端出错`;
// 					break;
// 				case 501:
// 					error.message = `网络未实现`;
// 					break;
// 				case 502:
// 					error.message = `网络错误`;
// 					break;
// 				case 503:
// 					error.message = `服务不可用`;
// 					break;
// 				case 504:
// 					error.message = `网络超时`;
// 					break;
// 				case 505:
// 					error.message = `http版本不支持该请求`;
// 					break;
// 				default:
// 					error.message = `未知错误${error.response.status}`;
// 			}
// 		} else {
// 			error.message = "连接到服务器失败";
// 		}
// 		return Promise.reject(error);
// 	}
// );
//export default httpService;
// new Vue({
//   router,
//   render: h => h(App)
// }).$mount('#app')

//将axios挂载到vue实例上，使用时就可以 this.$axios 这样使用了
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$qs = qs;
app.config.globalProperties.$getCurDate = getCurDate;
app.config.globalProperties.$setSessionStorage = setSessionStorage;
app.config.globalProperties.$getSessionStorage = getSessionStorage;
app.config.globalProperties.$removeSessionStorage = removeSessionStorage;
app.config.globalProperties.$setLocalStorage = setLocalStorage;
app.config.globalProperties.$getLocalStorage = getLocalStorage;
app.config.globalProperties.$removeLocalStorage = removeLocalStorage;

router.beforeEach(function(to, from, next) {
	let user = sessionStorage.getItem('user');
	//除了登录、注册、首页、商家列表、商家信息之外，都需要判断是否登录
	if (!(to.path == '/' || to.path == '/index' || to.path == '/businessList' || to.path == '/businessInfo' || to.path ==
			'/login' || to.path == '/register')) {
		if (user == null) {
			router.push('/login');
			location.reload();
		}
	}
	next();
});