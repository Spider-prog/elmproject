<template>
	<ul class="footer">
		<li @click="toIndex">
			<i class="fa fa-home"></i>
			<p>首页</p>
		</li>
		<li>
			<i class="fa fa-compass"></i>
			<p>发现</p>
		</li>
		<li @click="toOrderList">
			<i class="fa fa-file-text-o"></i>
			<p>订单</p>
		</li>
		<li @click="toMy">
			<i class="fa fa-user-o"></i>
			<p>我的</p>
		</li>
	</ul>
</template>

<script>
	export default{
		name:'Footer',
		data() {
			return {
				user: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
		},
		methods:{
			toIndex(){
				this.$router.push({path:'/index'});
			},
			toOrderList(){
				if (this.user == null) { //根据user对象是否为空判断是否登录
					this.$router.push({
						path: '/login'
					}); //如果未登录强制路由到登录组件
					return; //接着之后直接退出
				}
				this.$router.push({path:'/orderList'});
			},
			toMy(){
				if (this.user == null) { //根据user对象是否为空判断是否登录
					this.$router.push({
						path: '/login'
					}); //如果未登录强制路由到登录组件
					return; //接着之后直接退出
				}
				this.$router.push({path:'/my'});
			}
		}
	}
</script>

<style>
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
		/*li本身的尺寸完全由内容撑起*/
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
