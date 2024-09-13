<template>
	<div class="wrapper">
		<div class='picture'>
			<img src='../assets/success.png'>
		</div>
		<div class='word'>
			<p><strong>交易日期：{{this.$getCurDate()}}</strong></p>
		</div>
		<div class="queding">
			<button class="q" @click="toRecharge">
				确定
			</button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'Success',
		data() {
			return {

			}
		},
		create() {

		},
		methods: {
			toRecharge() {
				this.$router.push({path:'/orderList'});
			}
		}
	}
</script>

<style scoped>
	.wrapper {
		width: 100%;
		height: 100%;

	}

	.wrapper .picture {
		width: 100%;
		height: 27vw;
		display: flex;
		justify-content: center;
		padding-top: 20vw;
	}

	.wrapper .picture img {
		width: 62vw;
		height: 62vw;
	}

	.wrapper .word {
		font-size: 5vw;
		display: flex;
		margin-left:20vw;
		margin-top: 42vw;
	}
	
	
	.wrapper .queding {
		margin: 35vw 2vw;
		display: flex;
		align-content: center;
		justify-content: center;
		box-sizing: border-box;
	}
	
	.wrapper .queding .q {
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
