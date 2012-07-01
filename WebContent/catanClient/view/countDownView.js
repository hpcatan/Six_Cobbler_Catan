var countDownView = {
		
	showCountDownTimeForDice: function() {
		this.countDown(this.countDownModel.countDown.countDownDice);
	},
	
	showCountDownTimeForBuildingAndBuyingCard: function() {
		this.clearCountDownImage();
		this.showCountDownImagePrivate(catanUtil.getCountDownLongAsImage());
		
		this.countDown(this.countDownModel.countDown.countDownBuildAndBuyingCard);
	},
	
	showCountDownTimeForMarket: function() {
		this.clearCountDownImage();
		this.showCountDownImagePrivate(catanUtil.getCountDownLongAsImage());
		
		this.countDown(this.countDownModel.countDown.countDownMarket);
	},
	
	countDown: function(countDownType) {
		var time = countDownType.time;

		var self = this;
		var tempBrush = catanUtil.getTempLandCanvas();
		tempBrush.font = "18pt Times New Roman bold";
		tempBrush.fillStyle = "white"; // stroke color
		countDownType.interval = setInterval(function() {
			if (time == countDownType.emergencyTime) {
				self.clearCountDownImage();
				self.showCountDownImagePrivate(catanUtil.getCountDownShortAsImage());
			}
			
			var time1 = Math.floor(time / 10);
			var time2 = time % 10;
			
			tempBrush.clearRect(self.LEFT_X, self.LEFT_Y, self.WIDTH, self.HEIGHT);
			self.showCountDownTimePrivate(time1, time2);
			
			if (time == 0) {
				self.clear(countDownType.interval, countDownType.type);
			}
			
			--time;
		}, 1000);
	},
	
	showCountDownTimePrivate: function(time1, time2) {
		var tempBrush = catanUtil.getTempLandCanvas();
		tempBrush.fillText(time1, this.NUMBER1_X, this.NUMBER1_Y);
		tempBrush.fillText(time2, this.NUMBER2_X, this.NUMBER2_Y);
	},
	
	showCountDownImagePrivate: function(countDownImage) {
		this.clearCountDownImage();
		
		var self = this;
		countDownImage.onload = function() {
			var tempBrush = catanUtil.getTempLandCanvas();
			tempBrush.drawImage(countDownImage, self.IMG_X, self.IMG_Y, self.IMG_WIDTH, self.IMG_HEIGHT);
		};
	},
	
	clear: function(interval, type) {
		clearInterval(interval);
		if (this.countDownModel.isBuildingOrBuyingCard(type)) {
			this.clearCountDownImage();	
		}
	},
	
	clearCountDownImage: function() {
		var tempBrush = catanUtil.getTempLandCanvas();
		tempBrush.clearRect(this.IMG_X, this.IMG_Y, this.IMG_WIDTH, this.IMG_HEIGHT);
	}
};
