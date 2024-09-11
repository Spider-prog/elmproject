<template>
	<div class="wrapper">
		<div class='picture'>
			<img src='../assets/qidong.png'>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'Home',
		data() {
			return {
				count: 0
			}
		},
		created() {
			// 跳转选人加分页面
			this.gojump()
		},
		methods: {
			gojump() {
				const TIME_COUNT = 2;
				if (!this.timer) {
					this.count = TIME_COUNT;
					this.show = false;
					this.timer = setInterval(() => {
						if (this.count > 0 && this.count <= TIME_COUNT) {
							this.count--;
						} else {
							this.show = true;
							clearInterval(this.timer);
							this.timer = null;
							this.$router.push('/index');
						}
					}, 1000);
				}
			}
		}
	}
</script>

<style scoped>
	.wrapper {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: #FFFDF1;
	}

	.wrapper .picture {
		width: 100%;
		height: 27vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .picture img {
		width: 150vw;
		height: 81vw;
	}
</style>
