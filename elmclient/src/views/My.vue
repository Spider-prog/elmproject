<template>
	<!-- 总容器 -->
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>我的页面</p>
		</header>
		
		<!-- 用户信息展示 -->
		<div class="user">
			<img src="../assets/userimg.png">
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
		
		


		<!-- 底部菜单部分 很多页面都有的部分，视为共通组件-->
		<Footer></Footer>
	</div>
</template>

<script>
	//导入共通组件
	import Footer from '../components/Footer.vue';

	export default {
		name: 'My',
		data() {
			return {
				user: {},

			}
		},
		created() {
			this.user = this.$getSessionStorage('user');

		},
		components: {
			Footer
		},
		methods: {

		}
	}
</script>

<style scoped>
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}
	
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
	
	.wrapper .user {
		width: 100%;
		height: 24vw;
		margin-top: 12vw;
		padding : 2.5vw 2vw;
		box-sizing: border-box;
		display: flex;
	}
	
	.wrapper .user .user-info{
		padding : 1.5vw 0 0 3vw;
	}
	
	.wrapper .user .user-info .user-info-name {
		display: flex;
		align-items: center;
		padding : 0 0 1vw 0;
	}
	
	.wrapper .user .user-info .user-info-state {
		display: flex;
		align-items: center;
		padding : 1vw 0;
	}
</style>
