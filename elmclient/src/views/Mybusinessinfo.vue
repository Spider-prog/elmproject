<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<div class="go-back"><i class="fa fa-reply" @click="goback"></i></div>
			<p>商家信息</p>
		</header>

		<!-- 商家logo部分 -->
		<div class="business-logo">
			<img :src="business.businessImg">
			<!--动态绑定图片-->
		</div>

		<!-- 商家信息部分 -->
		<div class="business-info">
			<h1>{{business.businessName}}</h1>
			<p>&#165;{{business.starPrice}}起送 &#165;{{business.deliveryPrice}}配送</p>
			<p>{{business.businessExplain}}</p>
		</div>

		<!-- 食品列表部分 -->
		<ul class="food">
			<li v-for="(item,index) in foodArr">
				<!--遍历附带索引便于之后选定食品-->
				<div class="foodup">
					<div class="food-left">
						<img :src="item.foodImg" @click="detailetShow(item)">
						<div class="food-left-info">
							<h3>{{item.foodName}}</h3>
							<p>{{item.foodExplain}}</p>
							<p>&#165;{{item.foodPrice}}</p>
						</div>
					</div>
					<div class="food-right-info">
						<p>目前单价：{{item.foodPrice}}</p>
						<p>目前数量：{{item.quantity}}</p>
					</div>
				</div>
				<div class="reset" v-show="item.isShowDetailet">
					<div class="reset-left">
						<input type="text" v-model="foodPrice" placeholder="单价">
						<button class="q" @click="tocommitfoodPrice(index)">
							确定
						</button>
					</div>
					<div class="rest-right">
						<input type="password" v-model="quantity" placeholder="数量">
						<button class="q" @click="tocommitquantity(index)">
							确定
						</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: 'Mybusinessinfo',
		data() {
			return {
         		businessId: 10001,
				//businessId: this.$route.query.businessId, //从BusinessList组件传入id值
				business: {}, //返回一个business对象
				foodArr: [], //食品数组
				user: {}, //添加用户对象
				cartArr: [],
				foodPrice: null,
				quantity: null
			}
		},
		created() {
			this.user = this.$getSessionStorage('user'); //从sessionStorage中获取user对象
			//根据businessId查询商家信息
			this.$axios.post('BusinessController/getBusinessById', this.$qs.stringify({
				businessId: this.businessId
			})).then(response => {
				this.business = response.data;
			}).catch(error => {
				console.error(error);
			});
			//根据businessId查询所属食品信息
			this.$axios.post('FoodController/listFoodByBusinessId', this.$qs.stringify({
				businessId: this.businessId
			})).then(response => {
				this.foodArr = response.data;
				for (let orders of foodArr) {
					orders.isShowDetailet = false;
				}
			}).catch(error => {
				console.error(error);
			});
		},
		methods: {
			detailetShow(orders) {
				orders.isShowDetailet = !orders.isShowDetailet;
			},
			tocommitfoodPrice(num) {
				this.$axios.post('FoodController/setFoodPriceByFoodId', this.$qs.stringify({
					foodId:this.foodArr[index].foodId,
					foodPrice:this.foodPrice
				})).then(response => {
					if(response.data>0){
						alert('修改成功！');
					}
				}).catch(error => {
					console.error(error);
				});
			},
			tocommitquantity(num) {
				this.$axios.post('FoodController/setFoodQuantityByFoodId', this.$qs.stringify({
					foodId:this.foodArr[index].foodId,
					quantity:this.quantity
				})).then(response => {
					if(response.data>0){
						alert('修改成功！');
					}
				}).catch(error => {
					console.error(error);
				});
			}
		},
		computed: {
			// //食品总价格
			// totalPrice() {
			// 	let total = 0;
			// 	for (let item of this.foodArr) {
			// 		total += item.foodPrice * item.quantity;
			// 	}
			// 	return total;
			// },
			// //食品总数量
			// totalQuantity() {
			// 	let quantity = 0;
			// 	for (let item of this.foodArr) {
			// 		quantity += item.quantity;
			// 	}
			// 	return quantity;
			// },
			// //结算总价格
			// totalSettle() {
			// 	return this.totalPrice + this.business.deliveryPrice;
			// },
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

	.wrapper .whiteblock {
		width: 100%;
		height: 2vw;
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

	/****************** 商家logo部分 ******************/
	.wrapper .business-logo {
		width: 100%;
		height: 35vw;
		/*使用上外边距避开header部分*/
		margin-top: 12vw;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business-logo img {
		width: 40vw;
		height: 30vw;
		border-radius: 5px;
	}

	/****************** 商家信息部分 ******************/
	.wrapper .business-info {
		width: 100%;
		height: 20vw;

		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business-info h1 {
		font-size: 5vw;
	}

	.wrapper .business-info p {
		font-size: 3vw;
		color: #666;
		margin-top: 1vw;
	}

	/****************** 食品列表部分 ******************/
	.wrapper .food {
		width: 100%;
		/*使用下外边距避开footer部分*/
		margin-bottom: 14vw;
	}

	.wrapper .food li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		user-select: none;

		
	}
	
	.wrapper .food li .foodup {
		display: flex;
		
	}

	.wrapper .food li .foodup .food-left {
		display: flex;
		align-items: center;
	}

	.wrapper .food li .foodup .food-left img {
		width: 20vw;
		height: 20vw;
	}

	.wrapper .food li .foodup .food-left .food-left-info {
		margin-left: 3vw;
	}

	.wrapper .food li .foodup .food-left .food-left-info h3 {
		font-size: 3.8vw;
		color: #555;
	}

	.wrapper .food li .foodup .food-left .food-left-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
	}

	.wrapper .food li .foodup .food-left .food-right-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
	}

	/****************** 购物车部分 ******************/
	.wrapper .cart {
		width: 100%;

		position: fixed;
		left: 0;
		bottom: 0;

		display: flex;
		flex-direction: column;
	}

	.wrapper .cart .cart-detailed {
		width: 100%;

		left: 0;
		bottom: 0;

		display: flex;
		flex-direction: column;
		justify-content: space-between;
		font-size: 4vw;

		padding: 0 0 5vw 0;
		/*background-color: #3190E8;*/
		background-color: #477DAE;
		color: #fff;
	}

	.wrapper .cart .cart-detailed li {
		left: 0;
		bottom: 0;

		display: flex;
		justify-content: space-between;
		padding: 0 2.5vw 0 2.5vw;
		margin: 2vw 0;
	}

	.wrapper .cart .cart-bottom {
		width: 100%;
		height: 14vw;

		left: 0;
		bottom: 0;

		display: flex;
	}

	.wrapper .cart .cart-bottom .cart-left {
		flex: 2;
		background-color: #505051;
		display: flex;
	}

	.wrapper .cart .cart-bottom .cart-left .cart-left-icon {
		width: 16vw;
		height: 16vw;
		box-sizing: border-box;
		border: solid 1.6vw #444;
		border-radius: 8vw;
		background-color: #3190E8;
		font-size: 7vw;
		color: #fff;

		display: flex;
		justify-content: center;
		align-items: center;

		margin-top: -4vw;
		margin-left: 3vw;

		position: relative;
	}

	.wrapper .cart .cart-bottom .cart-left .cart-left-icon-quantity {
		width: 5vw;
		height: 5vw;
		border-radius: 2.5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;

		display: flex;
		justify-content: center;
		align-items: center;

		position: absolute;
		right: -1.5vw;
		top: -1.5vw;
	}

	.wrapper .cart .cart-bottom .cart-left .cart-left-info p:first-child {
		font-size: 4.5vw;
		color: #fff;
		margin-top: 1vw;
	}

	.wrapper .cart .cart-bottom .cart-left .cart-left-info p:last-child {
		font-size: 2.8vw;
		color: #AAA;
	}

	.wrapper .cart .cart-bottom .cart-right {
		flex: 1;
	}

	/*达到起送费时的样式*/
	.wrapper .cart .cart-bottom .cart-right .cart-right-item {
		width: 100%;
		height: 100%;
		background-color: #38CA73;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;
		cursor: pointer;

		display: flex;
		justify-content: center;
		align-items: center;
	}


	/*不够起送费时的样式（只有背景色和鼠标样式的区别）*/
	/*
	.wrapper .cart .cart-right .cart-right-item{
		width: 100%;
		height: 100%;
		background-color: #535356;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;
		
		display: flex;
		justify-content: center;
		align-items: center;
	}
	*/
</style>
