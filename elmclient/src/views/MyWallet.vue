<template>
	<!-- 总容器 -->
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<div class="go-back"><i class="fa fa-reply" @click="goback"></i></div>
			<p class="left">我的钱包</p>
		</header>
		<div class="wallet-box">
			<!-- 钱包内容 -->
			<div class="wallet">
				<!-- 显示余额 -->
				<div class="balance-box">
					<h3>可用余额(元)</h3>
					<p><strong>{{this.balance.toFixed(2)}}</strong></p>
				</div>
				<div class="rechargeAndWithdraw">
					<button class="recharge" @click="toRecharge">
						充值
					</button>
					<button class="withdraw" @click="toWalletDetail">
						清单
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name: 'MyWallet',
		data() {
			return {
				user: {},
				balance: 1895,
				userId: this.$route.query.userId
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
		},
		methods: {
			toWalletDetail(){
				this.$router.push({
					path: '/walletDetail'
				});
			},
			toRecharge(){
				this.$router.push({
					path: '/recharge',
					query:{walletId:this.walletId}
				});
			},
			toWithdraw(){
				this.$router.push({
					path: '/withdraw',
					query:{walletId:this.walletId}
				});
			},
			goback() {
				this.$router.go(-1);
			}
		}
	}
</script>

<style scoped>
	/* 总容器 */
	.wrapper {
		width: 100%;
		height: 100%;
		background-color: #f5f5f5;
	}

	/* 头部 */
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;
	
		position: fixed;
		left: 0;
		top: 0;
	
		display: flex;
		/*justify-content: center;*/
		align-items: center;
	}
	
	.wrapper header .go-back{
		padding:0 32vw 0 2vw;
	}
	
	/* 钱包内容 */
	.wrapper .wallet-box {
		width: 90%;
		height: 50vw;
		margin: 12vw 4vw 0 4vw;
		background-color: #f5f5f5;
		padding-top: 6vw;
	}

	.wrapper .wallet-box .wallet {
		width: 100%;
		height: 100%;
		background-color: #fff;
		border-radius: 15px;
		box-sizing: border-box;
		padding: 2vw 4vw;
		display: flex;
		flex-direction: column;
	}

	.wrapper .wallet-box .wallet .balance-box h3 {
		margin: 3vw 0 5vw 0;
		display: flex;
		justify-content: center;

		font-size: 4.5vw;
		font-weight: 400;
	}

	.wrapper .wallet-box .wallet .balance-box p {
		margin-bottom: 5vw;
		display: flex;
		justify-content: center;
		font-size: 8vw;
		font-weight: 600;
	}

	.wrapper .wallet-box .wallet .rechargeAndWithdraw {
		margin: 4vw 2vw;
		display: flex;
		align-content: center;
		justify-content: center;
		box-sizing: border-box;
	}

	.wrapper .wallet-box .wallet .rechargeAndWithdraw .recharge {
		width: 45%;
		height: 10vw;
		border: none;
		outline: none;
		border-radius: 6px;
		font-size: 4.5vw;
		font-weight: 500;
		background-color: #00abf5;
		color: #fff;
		margin-right: 4vw;
	}

	.wrapper .wallet-box .wallet .rechargeAndWithdraw .withdraw {
		width: 45%;
		height: 10vw;
		border: none;
		outline: none;
		border-radius: 6px;
		font-size: 4.5vw;
		font-weight: 500;
		background-color: #00abf5;
		color: #fff;
		margin-left: 4vw;
	}
</style>