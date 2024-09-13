<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<p>钱包登录</p>
		</header>

		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					手机号码：
				</div>
				<div class="content">
					<!--<input type="text" v-model="userId" placeholder="手机号码"> -->
					<!--表单提交用v-model实现双向数据绑定-->
					<input type="text" v-model="userId" placeholder="手机号码" :disabled="isLoggingIn" pattern="^1[358][0-9]{9}$" maxlength="11">
				</div>
			</li>
			<li>
				<div class="title">
					钱包密码：
				</div>
				<div class="content">
					<!--<input type="password" v-model="password" placeholder="密码">-->
					<input type="password" v-model="walletPassword" placeholder="密码" :disabled="isLoggingIn" pattern="[A-Za-z0-9]{6,}" title="密码需包含且只能包含大、小写字母和数字，长度至少为6位">
				</div>
			</li>
		</ul>

		<div class="button-login">
			<button @click="login">登陆</button>
		</div>
		<div class="button-register">
			<button @click="register">去注册</button>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';
	  
	export default {
		name: 'Login2',
		data() {
			return {
				userId: '',
				walletPassword: '', //传入两个字符串类型
				isLoggingIn: false
			}
		},
		methods: {
			login() {
				//表单验证，手机号码和密码不能为空
				if (this.userId == '') {
					alert('手机号码不能为空！'); //alert()方法是显示一条弹出提示消息和确认按钮的警告框
					return;
				}
				if (this.walletPassword == '') {
					alert('密码不能为空！');
					return;
				}

				this.isLoggingIn = true; // 禁用登录按钮，防止重复提交

				//登录请求
				this.$axios.post('UserWalletController/getUserByIdByPass', this.$qs.stringify({
					userId: this.userId,
					walletPassword: this.walletPassword
				})).then(response => {
					let userWallet = response.data;
					if (userWallet == null) { //查询完看是否返回了user对象
						alert('用户名或密码不正确！');
					} else {
						//this.$setLocalStorage('accessToken',response.data.headers.Authorization);
						//user.userImg = ''; //sessionstorage有容量限制，为了防止数据溢出将Img置空
						this.$setSessionStorage('userWallet', userWallet); //将键值对放进sessionstorage中
						this.$router.go(-1); //从哪来回哪去，回退到上一个页面（这个功能很棒）
					}
				}).catch(error => {
					console.error(error);
				}).finally(() => {
					this.isLoggingIn = false; // 重新启用登录按钮
				});
			},
			register() {
				this.$router.push({
					path: '/register2'
				});
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
		justify-content: center;
		align-items: center;
	}

	/****************** 表单部分 ******************/
	.wrapper .form-box {
		width: 100%;
		margin-top: 12vw;
	}

	.wrapper .form-box li {
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
		display: flex;
		align-items: center;
	}

	.wrapper .form-box li .title {
		flex: 0 0 18vw;
		font-size: 3vw;
		font-weight: 700;
		color: #666;
	}

	.wrapper .form-box li .content {
		flex: 1;
	}

	.wrapper .form-box li .content input {
		border: none;
		outline: none;
		width: 100%;
		height: 4vw;
		font-size: 3vw;
	}

	.wrapper .button-login {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-login button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #fff;
		background-color: #38CA73;
		border-radius: 4px;

		border: none;
		outline: none;
	}

	.wrapper .button-register {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-register button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		/*与上面登陆按钮不同的只有颜色、背景色、边框不同*/
		color: #666;
		background-color: #EEE;
		border: solid 1px #DDD;
		border-radius: 4px;

		border: none;
		outline: none;
	}

	/****************** 底部菜单部分 ******************/
	.wrapper .footer {
		width: 100%;
		height: 14vw;
		border-top: solid 1px #DDD;
		background-color: #fff;

		position: fixed;
		left: 0;
		bottom: 0;

		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.wrapper .footer li {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		color: #999;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .footer li p {
		font-size: 2.8vw;
	}

	.wrapper .footer li i {
		font-size: 5vw;
	}
</style>
