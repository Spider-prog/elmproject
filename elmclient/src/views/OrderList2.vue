<template>
	<div class="wrapper">

		<!--header部分-->
		<header>
			<div class="go-back"><i class="fa fa-reply" @click="goback"></i></div>
			<p>历史订单</p>
		</header>
		<br></br>
		<br></br>
		<br></br>
		<!-- 订单列表部分 -->
		<ul class="order">
			<li v-for="item in orderArr">
				<template v-if="item.orderState==1">
					<div class="order-info">
						<img :src="item.business.businessImg">
						<p>
							{{item.business.businessName}}
							<i class="fa fa-caret-down" @click="detailetShow(item)"></i>
						</p>
						<div class="order-info-right">
							<p>&#165;{{item.orderTotal}}</p>
						</div>
					</div>
					<ul class="order-detailet" v-show="item.isShowDetailet">
						<li v-for="odItem in item.list">
							<img :src="odItem.food.foodImg">
							<p>{{odItem.food.foodName}} x {{odItem.quantity}}</p>
							<div class="order-detailet-right">
							<p>&#165;{{odItem.food.foodPrice*odItem.quantity}}</p>
							</div>
						</li>
						<li>
							<p class = "peisong">配送费</p>
							<p>&#165;{{item.business.deliveryPrice}}</p>
						</li>
					</ul>
				</template>
			</li>
		</ul>

		<!-- 底部菜单部分 -->
		<Footer></Footer>

	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';

	export default {
		name: 'OrderList2',
		data() {
			return {
				orderArr: [],
				user: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');

			this.$axios.post('OrdersController/listOrdersByUserId', this.$qs.stringify({
				userId: this.user.userId
			})).then(response => {
				let result = response.data;
				for (let orders of result) {
					orders.isShowDetailet = false;
				}
				this.orderArr = result;
			}).catch(error => {
				console.error(error);
			});
		},
		methods: {
			detailetShow(orders) {
				orders.isShowDetailet = !orders.isShowDetailet;
			},
			toPayment(orders) {
				this.$router.push({
					path: '/payment',
					query: {
						orderId: orders.orderId
					}
				})
			},
			goback(){
				this.$router.go(-1);
			}
		},
		components: {
			Footer
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
	
	.wrapper header .go-back{
		padding:0 32vw 0 2vw;
	}

	/****************** 历史订单列表部分 ******************/
	.wrapper h3 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 4vw;
		font-size: 4vw;
		font-weight: 300;
		color: #999;
	}

	.wrapper .order {
		width: 100%;
	}

	.wrapper .order li {
		width: 100%;
	}

	.wrapper .order li .order-info {
		box-sizing: border-box;
		padding: 2vw 4vw;
		font-size: 4vw;
		color: #666;

		display: flex;
		/*justify-content: space-between;*/
		align-items: center;
	}
	
	.wrapper .order li .order-info img{
		width: 10vw;
		height: 10vw;
		padding-right:2vw;
	}

	.wrapper .order li .order-info .order-info-right {
		display: flex;
		padding-left: 20vw;
	}

	.wrapper .order li .order-info .order-info-right .order-info-right-icon {
		background-color: #f90;
		color: #fff;
		border-radius: 3px;
		margin-left: 2vw;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .order li .order-detailet {
		width: 100%;
	}
	
	.wrapper .order li .order-detailet img{
		width: 6vw;
		height: 6vw;
		padding-right:1vw;
	}

	.wrapper .order li .order-detailet li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 4vw;
		color: #666;
		font-size: 3vw;

		display: flex;
		/*justify-content: space-between;*/
		align-items: center;
	}
	
	.wrapper .order li .order-detailet li .order-detailet-right {
		display: flex;
		padding-left: 48vw;
	}
	
	.wrapper .order li .order-detailet .peisong {
		display: flex;
		padding-right: 76vw;
	}
</style>
