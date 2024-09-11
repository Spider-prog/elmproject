<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<div class="go-back"><i class="fa fa-reply" @click="goback"></i></div>
			<p>头像管理</p>
		</header>
		<div class="userinfo">
			<img v-bind:src="imgdata" class="userinfo-img">
			<ul id="userAlternateImgArrBox">
				<li @click="changeImglocal(1)"><img src="../assets/user1.png"></li>
				<li @click="changeImglocal(2)"><img src="../assets/user2.png"></li>
				<li @click="changeImglocal(3)"><img src="../assets/user3.png"></li>
				<li @click="changeImglocal(4)"><img src="../assets/user4.png"></li>
				<li @click="changeImglocal(5)"><img src="../assets/user5.png"></li>
				<li @click="changeImglocal(6)"><img src="../assets/user6.png"></li>
				<li @click="changeImglocal(7)"><img src="../assets/user7.png"></li>
				<li @click="changeImglocal(8)"><img src="../assets/user8.png"></li>
			</ul>
			<div class = 'choosefile'>
			<label for="avatar">Choose a profile picture:</label>
			<input type="file" @change="getBase64">
			</div>
			<button v-on:click="saveImg(this.imgdata)">保存修改</button>
		</div>
		<Footer></Footer>
	</div>
</template>
//js代码
<script>
	import Footer from '../components/Footer.vue'
	export default {
		name: 'MyImg',
		data: function() {
			return {
				user: {},
				userId: this.$route.query.userId,
				base64: '',
				imgdata: ''
			}
		},
		created() {
			this.imgdata = "http://localhost:8081/img/user1.d1dcd88e.png";
		},
		methods: {
			getBase64(e) {
				// 选择的文件
				let file = e.target.files[0];
				console.log(file.name) // 文件名称，有需求可处理
				console.log(file.type) // 文件类型，有需求可处理
				// 判断文件是否读取完毕，读取完毕后执行
				if (window.FileReader) {
					let reader = new FileReader();
					reader.readAsDataURL(file);
					reader.onload = e => {
						let base64String = e.target.result;
						console.log("bese64编码：", base64String);
						this.imgSrc = base64String;
						this.saveImg(base64String); //调用上传接口
					}
				}
			},
			saveImg(base64String) {
				// this.$axios.post('UserController/changeUserPortrait', this.$qs.stringify({
				// 		userId: this.$route.query.userId,
				// 		base64: base64String

				// 	}))
				// 	.then(resp => {

				// 		let result = resp.data;
				// 		if (result == 0) {
				// 			alert("头像更改失败");
				// 		} else {
				// 			alert('更改成功');
				// 		}
				// 	})
				// 	.catch(err => {
				// 		console.log(err);
				// 	})
				this.$setSessionStorage('userimg', base64String);
				this.$router.go(-1);
			},
			changeImglocal: function(index) {
				if (index == 1) 
				    this.imgdata = "http://localhost:8081/img/user1.d1dcd88e.png";
				else if (index == 2)
					this.imgdata = "http://localhost:8081/img/user2.e0104f7b.png";
				else if (index == 3)
					this.imgdata = "http://localhost:8081/img/user3.27879587.png";
				else if (index == 4)
					this.imgdata = "http://localhost:8081/img/user4.237a7461.png";
				else if (index == 5)
					this.imgdata = "http://localhost:8081/img/user5.e1716084.png";
				else if (index == 6)
					this.imgdata = "http://localhost:8081/img/user6.43273f97.png";
				else if (index == 7)
					this.imgdata = "http://localhost:8081/img/user7.6bcc06b1.png";
				else if (index == 8)
					this.imgdata = "http://localhost:8081/img/user8.b329ca5d.png";
			},
			getUserById() {
				this.$axios.post('UserController/getUserById', this.$qs.stringify({
						userId: this.$route.query.userId
					}))
					.then(resp => {
						this.user = resp.data;
						return this.imgSrc = user.userImg;
					})
					.catch(err => {
						console.log(err);
					})
			},
			goback(){
				this.$router.go(-1);
			}
		},
		components: {
			Footer: Footer
		},
	}
</script>

<style scoped="scoped">
	/*************** 总容器 ***************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/*************** header ***************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		display: flex;
		/*justify-content: space-around;*/
		align-items: center;
		color: #fff;
		font-size: 4.8vw;
		position: fixed;
		left: 0;
		top: 0;
		/*保证在最上层*/
		z-index: 1000;
	}
	
	.wrapper header .go-back{
		padding:0 32vw 0 2vw;
	}

	/*************** userimg ***************/
	.wrapper .userinfo {
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		box-sizing: border-box;
		padding: 4vw;

		padding-top: 14vw;
		padding-bottom: 14vw;
	}

	.wrapper .userinfo .userinfo-img {
		display: block;
		width: 30vw;
		height: 30vw;
		border-radius: 15vw;
		margin-bottom: 8vw;
	}

	.wrapper .userinfo ul {
		width: 100%;
		display: flex;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .userinfo ul li {
		width: 16vw;
		height: 16vw;
		margin: 0 0 4vw 6vw;
	}

	.wrapper .userinfo ul li img {
		display: block;
		width: 16vw;
		height: 16vw;
		border-radius: 8vw;
		cursor: pointer;
	}

	.wrapper .userinfo button {
		margin-top: 4vw;
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;

		border: none;
		outline: none;
		border-radius: 4px;

		color: #fff;
		background-color: #38CA73;
	}
	
	.wrapper .userinfo .chooesfile {
		display: flex;
	}
</style>
