<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<div class="go-back"><i class="fa fa-reply" @click="goback"></i></div>
			<p>搜索列表</p>
		</header>

		<!-- 商家列表部分 -->
		<ul class="business">
			<li v-for="item in businessArr" @click="toBusinessInfo(item.businessId)">
				<div class="business-img">
					<img :src="item.businessImg">
					<div class="business-img-quantity" v-show="item.quantity>0">{{item.quantity}}</div>
				</div>
				<div class="business-info">
					<h3>{{item.businessName}}</h3>
					<p>&#165;{{item.starPrice}}起送 | &#165;{{item.deliveryPrice}}配送</p>
					<p>{{item.businessExplain}}</p>
				</div>
			</li>
		</ul>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>
<script>
	import Footer from '../components/Footer.vue';

	export default {
		name: 'BusinessSearch',
		data() {
			return {
				businessOrFoodName: this.$route.query.orderTypeId,
				businessArr: [],
				user: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			this.$axios.post('QueryController/listBusinessByBusinessName', this.$qs.stringify({
				businessOrFoodName: this.businessOrFoodName
			})).then(response => {
				this.businessArr = response.data;
				if (this.user != null) {
					this.listCart();
				}
			}).catch(error => {
				console.error(error);
			});
			//test下面仅用于测试
			// this.$axios.post('BusinessController/listBusinessByOrderTypeId',this.$qs.stringify({
			// 	orderTypeId:this.businessOrFoodName
			// })).then(response=>{//响应成功
			// 	this.businessArr = response.data;
			// 	if(this.user!=null){//判断是否登录
			// 		this.listCart();
			// 	}
			// }).catch(error=>{//响应失败
			// 	console.error(error);
			// });
		},
		components: {
			Footer
		},
		methods: {
			listCart() {
				this.$axios.post('CartController/listCart', this.$qs.stringify({
					userId: this.user.userId
				})).then(response => {
					let cartArr = response.data;
					//遍历所有食品列表
					for (let businessItem of this.businessArr) {
						businessItem.quantity = 0;
						for (let cartItem of cartArr) {
							if (cartItem.businessId == businessItem.businessId) {
								businessItem.quantity = cartItem.quantity;
							}
						}
					}
					this.businessArr.sort();
				}).catch(error => {
					console.error(error);
				});
			},
			toBusinessInfo(businessId) {
				this.$router.push({
					path: '/businessInfo',
					query: {
						businessId: businessId
					}
				});
			},
			goback() {
				this.$router.go(-1);
			}
		}
	}
</script>
<style scoped>
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****************** header部分 ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;
		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;
		display: flex;
		/*justify-content: center;*/
		align-items: center;
	}

	.wrapper header .go-back {
		padding: 0 32vw 0 2vw;
	}

	/****************** 商家列表部分 ******************/
	.wrapper .business {
		width: 100%;
		margin-top: 12vw;
		padding-bottom: 14vw;
	}

	.wrapper .business li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		border-bottom: solid 1px #DDD;
		user-select: none;
		cursor: pointer;
		display: flex;
		align-items: center;
	}

	.wrapper .business li .business-img {
		/*这里设置为相当定位，成为business-img-quantity元素的父元素*/
		position: relative;
	}

	.wrapper .business li .business-img img {
		width: 20vw;
		height: 20vw;
	}

	.wrapper .business li .business-img .business-img-quantity {
		width: 5vw;
		height: 5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;
		border-radius: 2.5vw;
		display: flex;
		justify-content: center;
		align-items: center;
		/*设置成绝对定位，不占文档流空间*/
		position: absolute;
		right: -1.5vw;
		top: -1.5vw;
	}

	.wrapper .business li .business-info {
		margin-left: 3vw;
	}

	.wrapper .business li .business-info h3 {
		font-size: 3.8vw;
		color: #555;
	}

	.wrapper .business li .business-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
	}
</style>
