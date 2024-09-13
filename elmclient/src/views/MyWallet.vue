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
				<!-- <div class="rechargeAndWithdraw">
					<button class="recharge" @click="toRecharge">
						充值
					</button>
					<button class="withdraw" @click="toWalletDetail">
						清单
					</button>
				</div> -->
				<div class="balance-box">
					<h3>可用余额(元)</h3>
					<p><strong>{{this.balance.toFixed(2)}}</strong></p>
				</div>
				<div class="vx">
					<img src="../assets/vx.png"><strong>请选择充值金额</strong>
				</div>
				<div class="payment">
					<div class="paymentinfo" v-show="this.number!=1" @click="topayment(1)">1元</div>
					<div class="paymentinfo1" v-show="this.number==1" @click="topayment(1)">1元</div>
					<div class="paymentinfo" v-show="this.number!=5" @click="topayment(5)">5元</div>
					<div class="paymentinfo1" v-show="this.number==5" @click="topayment(5)">5元</div>
					<div class="paymentinfo" v-show="this.number!=10" @click="topayment(10)">10元</div>
					<div class="paymentinfo1" v-show="this.number==10" @click="topayment(10)">10元</div>
					<div class="paymentinfo" v-show="this.number!=20" @click="topayment(20)">20元</div>
					<div class="paymentinfo1" v-show="this.number==20" @click="topayment(20)">20元</div>
					<div class="paymentinfo" v-show="this.number!=50" @click="topayment(50)">50元</div>
					<div class="paymentinfo1" v-show="this.number==50" @click="topayment(50)">50元</div>
					<div class="paymentinfo" v-show="this.number!=100" @click="topayment(100)">100元</div>
					<div class="paymentinfo1" v-show="this.number==100" @click="topayment(100)">100元</div>
					<div class="paymentinfo" v-show="this.number!=200" @click="topayment(200)">200元</div>
					<div class="paymentinfo1" v-show="this.number==200" @click="topayment(200)">200元</div>
					<div class="paymentinfo" v-show="this.number!=500" @click="topayment(500)">500元</div>
					<div class="paymentinfo1" v-show="this.number==500" @click="topayment(500)">500元</div>
				</div>
				<div class="recharge">
					<button class="r" @click="toRecharge">
						钱包充值
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'MyWallet',
		data() {
			return {
				userWallet: {},
				balance: 0,
				number: 0
			}
		},
		created() {
			this.userWallet = this.$getSessionStorage('userWallet');
		},
		methods: {
			topayment(num) {
				this.number = num;
				this.$router.push('/myWallet');
			},
			toRecharge() {
				if (this.number == 0) {
					alert('请选择充值金额');
				}
				this.balance += this.number;
				this.$axios.post('UserWalletController/updateCredit', this.$qs.stringify({
					userId: this.userWallet.userId,
					credit: this.number
				})).then(response => {
					this.balance = response.data;
				}).catch(error => {
					console.error(error);
				});
				this.number = 0;
				this.$router.push('/myWallet');
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

	.wrapper header .go-back {
		padding: 0 32vw 0 2vw;
	}

	/* 钱包内容 */
	.wrapper .wallet-box {
		width: 90%;
		height: 100vw;
		margin: 12vw 4vw 0 4vw;
		background-color: #f5f5f5;
		padding-top: 6vw;
	}

	.wrapper .wallet-box .wallet {
		width: 100%;
		height: 95%;
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

	.wrapper .wallet-box .wallet .payment {
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .wallet-box .wallet .payment .paymentinfo1 {
		width: 20%;
		height: 10vw;
		border: 1px solid black;
		border-radius: 10px;
		background-color: #00abf5;
		display: flex;
		justify-content: space-around;
		align-items: center;
		margin-top: 3vw;
	}


	.wrapper .wallet-box .wallet .payment .paymentinfo {
		width: 20%;
		height: 10vw;
		border: 1px solid black;
		border-radius: 10px;
		display: flex;
		justify-content: space-around;
		align-items: center;
		margin-top: 3vw;
	}

	.wrapper .wallet-box .wallet img {
		width: 4vw;
		height: 4vw;
		margin-left: 1vw;
		margin-right: 1vw;
	}

	.wrapper .wallet-box .wallet .recharge {
		margin: 7vw 2vw;
		display: flex;
		align-content: center;
		justify-content: center;
		box-sizing: border-box;
	}

	.wrapper .wallet-box .wallet .recharge .r {
		width: 86%;
		height: 10vw;
		border: none;
		outline: none;
		border-radius: 6px;
		font-size: 4.5vw;
		font-weight: 500;
		background-color: #00abf5;
		color: #fff;
		margin-right: 7vw;
	}
</style>
