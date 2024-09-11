<template>
	<div class="wrapper">
		<div class='background'>
			<header>
				<p>我的页面</p>
			</header>
		</div>

		<div class='userinfo'>
			<!-- header部分 -->
			<div class="user">
				<img v-bind:src="userimg">
				<div class="user-info">
					<div class="user-info-name">
						<h2>{{this.user.userName}}</h2>
						<i class="fa fa-address-card"> </i>
					</div>
					<div class="user-info-state">
						<p>关注 0 | 评价 0 | 动态 0</p>
					</div>
				</div>
				<!--<i class="fa fa-gear"> </i>-->
			</div>

			<!-- 个人信息部分 -->
			<ul class="userinfoa">
				<li @click="changeImg(user.userId)">
					<img src="../assets/user.png">
					<p>我的头像</p>

				</li>
				<li @click="toMyWallet">
					<img src="../assets/wallet.png">
					<p>我的钱包</p>

				</li>
				<li @click="toMyCredit">
					<img src="../assets/collect.png">
					<p>我的收藏</p>

				</li>
			</ul>
			<ul class="userinfob">
				<li @click="toChangePwd">
					<p><i class="fa fa-expeditedssl"></i>修改密码</p>
					<i class="fa fa-angle-right"></i>
				</li>
				<li @click="">
					<p><i class="fa fa-pie-chart"></i>浏览记录</p>
					<i class="fa fa-angle-right"></i>
				</li>
				<li @click="toUserAddress">
					<p><i class="fa fa-address-book"></i>地址管理</p>
					<i class="fa fa-angle-right"></i>
				</li>
				<li @click="toBusinessList2(1)">
					<p><i class="fa fa-shopping-cart"></i>购物车</p>
					<i class="fa fa-angle-right"></i>
				</li>
				<li @click="toOrderList2">
					<p><i class="fa fa-reorder"></i>历史订单</p>
					<i class="fa fa-angle-right"></i>
				</li>
				<li @click="">
					<p><i class="fa fa-credit-card"></i>退款售后</p>
					<i class="fa fa-angle-right"></i>
				</li>
				<li @click="logout">
					<p><i class="fa fa-sign-out"></i>退出登录</p>
					<i class="fa fa-angle-right"></i>
				</li>

			</ul>
			<Footer></Footer>
		</div>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue'
	export default {
		name: 'My',
		data: function() {
			return {
				user: {},
				userimg: ''
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			this.userimg = this.$getSessionStorage('userimg');
		},
		methods: {
			toBusinessList2(orderTypeId) {
				this.$router.push({
					path: '/businessList2',
					query: {
						orderTypeId: orderTypeId
					}
				});
			},
			toOrderList2(){
				this.$router.push({path:'/orderList2'});
			},
			toUserAddress() {
				this.$router.push({
					path: '/userAddress',
					query: {
						businessId: this.businessId
					}
				});
			},
			// 跳转到修改密码页面
			toChangePwd() {
				this.$router.push({
					path: '/changePwd',
				});
			},
			toMyWallet() {
				this.$router.push({
					path: '/myWallet',
				});
			},
			// 跳转到头像修改页面
			changeImg: function(userId) {
				this.$router.push({
					path: '/myImg',
					query: {
						userId: userId
					}
				});
			},
			toMyCredit: function() {
				this.$router.push('/credit');
			},
			// 获取用户信息
			getUserById: function(userId) {
				let that = this;
				let url = 'UserController/getUserById';
				this.$axios.post(url, this.$qs.stringify({
						userId
					}))
					.then(resp => {
						that.user = resp.data;
					})
					.catch(err => {
						console.log(err);
					})
			},
			// 退出登录 (实际退出操作,依赖响应拦截器)
			logout(user) {
				this.$removeSessionStorage('user');
				let result = this.$getSessionStorage('user');
				if (result == null) {
					alert('退出成功');
					this.$router.push('/index');
				} else {
					alert('退出失败')
				}
			}
		},
		components: {
			Footer: Footer
		}
	}
</script>

<style scoped="scoped">
	/*************** 总容器 ***************/
	.wrapper {
		width: 100%;
		height: 100%;
		background-color: #F5F5F5;
	}

	.wrapper .background {
		width: 100%;
		height: 35%;
		background-color: #0097FF;
		border-radius: 15px;
	}

	.wrapper header {
		width: 100%;
		height: 12vw;
		color: #fff;
		font-size: 4.8vw;
		position: fixed;
		left: 0;
		top: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		/*z-index: 999;*/
	}

	.wrapper .userinfo {
		width: 90%;
		height: 100%;
		position: absolute;
		top: 15vw;
		left: 5vw;
		background-color: #F5F5F5;
		border-radius: 15px;
	}

	.wrapper .userinfo .user {
		width: 100%;
		height: 25vw;
		background-color: #fff;

		display: flex;
		align-items: center;
		border-radius: 15px;
	}
    
	.wrapper .userinfo .user img {
		width: 16vw;
		height: 16vw;
		border-radius: 8vw;
		margin-left: 4vw;
		margin-right: 3vw;
	}
	
	.wrapper .userinfo .user .user-info .user-info-name {
		display: flex;
		align-items: center;
		font-size : 4vw;
		padding-bottom: 1vw;
	}
	
	.wrapper .userinfo .user .user-info .user-info-name h2{
		padding-right: 1vw;
	}
	
	.wrapper .userinfo .user .user-info .user-info-state {
		display: flex;
		align-items: center;
		font-size : 3vw;
	}
/* 
	.wrapper .userinfo .user p {
		margin-left: 5vw;
		font-size: 5.6vw;
		font-weight: 700;
	}
 */ 
	/*************** userinfo ***************/

	.wrapper .userinfo .userinfob {
		width: 100%;
		padding-bottom: 14vw;
	}

	.wrapper .userinfo .userinfob li {
		width: 100%;
		height: 12vw;
		box-sizing: border-box;
		padding: 0 4vw;
		margin-top: 3.6vw;
		background-color: #fff;

		display: flex;
		justify-content: space-between;
		align-items: center;

		user-select: none;
		cursor: pointer;
	}

	.wrapper .userinfo .userinfob li p {
		font-size: 4.2vw;
		color: #666;
	}

	.wrapper .userinfo .userinfob li i {
		/*左右两边的图标样式一样*/
		font-size: 5vw;
		color: #0097FF;
		margin-right: 3vw;
	}

	.wrapper .userinfo .userinfoa {
		width: 100%;
		height: 26vw;
		border: 1px solid black;
		border-radius: 20px;
		background-color: #fff;
		left: 0;
		bottom: 0;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.wrapper .userinfo .userinfoa li {
		/*li本身的尺寸完全由内容撑起*/
		box-sizing: content-box;
		width: 33.3%;
		
		padding: 9px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		color: #999;
		user-select: none;
		cursor: pointer;
	}
	
	.wrapper .userinfo .userinfoa li img {
		width: 10vw;
		height: 10vw;
	}

	.wrapper .userinfo .userinfoa li p {
		font-size: 3.5vw;
	}
</style>
