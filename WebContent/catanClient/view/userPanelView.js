catanClient.view.userPanelView = {
	USER_INFO_COORDINATE : {
		RED : {
			NAME : {
				X : 675,
				Y : 64,
				CLEAR_RECTANGLE : {
					X : 668,
					Y : 50,
					WIDTH : 122,
					HEIGHT : 21
				}
			},
			SCORE : {
				X : 670,
				Y : 99,
				CLEAR_RECTANGLE : {
					X : 656,
					Y : 86,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			ROAD_COUNT : {
				X : 708,
				Y : 99,
				CLEAR_RECTANGLE : {
					X : 695,
					Y : 86,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			RESOURCE_COUNT : {
				X : 746,
				Y : 99,
				CLEAR_RECTANGLE : {
					X : 733,
					Y : 86,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			SOLDIER_COUNT : {
				X : 783,
				Y : 99,
				CLEAR_RECTANGLE : {
					X : 770,
					Y : 86,
					WIDTH : 33,
					HEIGHT : 17
				}
			}
		},
		YELLOW : {
			NAME : {
				X : 700,
				Y : 137,
				CLEAR_RECTANGLE : {
					X : 694,
					Y : 124,
					WIDTH : 122,
					HEIGHT : 21
				}
			},
			SCORE : {
				X : 695,
				Y : 172,
				CLEAR_RECTANGLE : {
					X : 682,
					Y : 159,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			ROAD_COUNT : {
				X : 733,
				Y : 172,
				CLEAR_RECTANGLE : {
					X : 720,
					Y : 159,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			RESOURCE_COUNT : {
				X : 770,
				Y : 172,
				CLEAR_RECTANGLE : {
					X : 758,
					Y : 159,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			SOLDIER_COUNT : {
				X : 808,
				Y : 172,
				CLEAR_RECTANGLE : {
					X : 795,
					Y : 159,
					WIDTH : 33,
					HEIGHT : 17
				}
			}
		},
		GREEN : {
			NAME : {
				X : 725,
				Y : 211,
				CLEAR_RECTANGLE : {
					X : 718,
					Y : 197,
					WIDTH : 122,
					HEIGHT : 21
				}
			},
			SCORE : {
				X : 720,
				Y : 245,
				CLEAR_RECTANGLE : {
					X : 707,
					Y : 233,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			ROAD_COUNT : {
				X : 758,
				Y : 245,
				CLEAR_RECTANGLE : {
					X : 745,
					Y : 233,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			RESOURCE_COUNT : {
				X : 795,
				Y : 245,
				CLEAR_RECTANGLE : {
					X : 782,
					Y : 233,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			SOLDIER_COUNT : {
				X : 833,
				Y : 245,
				CLEAR_RECTANGLE : {
					X : 820,
					Y : 233,
					WIDTH : 33,
					HEIGHT : 17
				}
			}
		},

		BLUE : {
			NAME : {
				X : 750,
				Y : 284,
				CLEAR_RECTANGLE : {
					X : 744,
					Y : 270,
					WIDTH : 122,
					HEIGHT : 21
				}
			},
			SCORE : {
				X : 745,
				Y : 319,
				CLEAR_RECTANGLE : {
					X : 733,
					Y : 306,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			ROAD_COUNT : {
				X : 782,
				Y : 319,
				CLEAR_RECTANGLE : {
					X : 769,
					Y : 306,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			RESOURCE_COUNT : {
				X : 820,
				Y : 319,
				CLEAR_RECTANGLE : {
					X : 807,
					Y : 306,
					WIDTH : 33,
					HEIGHT : 17
				}
			},
			SOLDIER_COUNT : {
				X : 857,
				Y : 319,
				CLEAR_RECTANGLE : {
					X : 845,
					Y : 306,
					WIDTH : 33,
					HEIGHT : 17
				}
			}
		}
	},

	USER_INFO_FONT : "9pt Courier New bold"
};

catanClient.view.userPanelView.init = function(usersArray) {

};
catanClient.view.userPanelView.update = function() {
	var players = catanClient.model.userPanel.players;
	var userPanelView = catanClient.view.userPanelView;
	for ( var i = 0; i < players.length; i++) {
		var player = players[i];
		userPanelView.showUserName(player.name, player.index);
		userPanelView.showUserScore(player.score, player.index);
		userPanelView.showUserRoadCount(player.roadCount, player.index);
		userPanelView.showUserResourceCount(player.resourceCount, player.index);
		userPanelView.showUserSoldierCount(player.soliderCount, player.index);
	}
};

// catanClient.view.userPanelView.showPlayersInfo = function() {
// this.showUserName(UserPanelModel.player.name, index);
// };

catanClient.view.userPanelView.showUserName = function(name, index) {
	index = index.toUpperCase();

	if (this.isNameValid(name) && this.isIndexValid(index)) {
		var userPanelBrush = catanClient.util.catanUtil.getUserPanelBrush();

		userPanelBrush.clearRect(
				this.USER_INFO_COORDINATE[index].NAME.CLEAR_RECTANGLE.X,
				this.USER_INFO_COORDINATE[index].NAME.CLEAR_RECTANGLE.Y,
				this.USER_INFO_COORDINATE[index].NAME.CLEAR_RECTANGLE.WIDTH,
				this.USER_INFO_COORDINATE[index].NAME.CLEAR_RECTANGLE.HEIGHT);

		userPanelBrush.font = this.USER_INFO_FONT;
		userPanelBrush.fillStyle = "black";
		userPanelBrush.fillText(name, this.USER_INFO_COORDINATE[index].NAME.X,
				this.USER_INFO_COORDINATE[index].NAME.Y);
	} else {
		// TODO throw exception
	}
};

catanClient.view.userPanelView.showUserScore = function(score, index) {
	index = index.toUpperCase();

	if (this.isScoreValid(score) && this.isIndexValid(index)) {
		// name to score
		var userPanelBrush = catanClient.util.catanUtil.getUserPanelBrush();

		userPanelBrush.clearRect(
				this.USER_INFO_COORDINATE[index].SCORE.CLEAR_RECTANGLE.X,
				this.USER_INFO_COORDINATE[index].SCORE.CLEAR_RECTANGLE.Y,
				this.USER_INFO_COORDINATE[index].SCORE.CLEAR_RECTANGLE.WIDTH,
				this.USER_INFO_COORDINATE[index].SCORE.CLEAR_RECTANGLE.HEIGHT);
		userPanelBrush.font = this.USER_INFO_FONT;
		userPanelBrush.fillStyle = "white";
		if (score >= 10) {
			userPanelBrush.fillText(score,
					this.USER_INFO_COORDINATE[index].SCORE.X - 3,
					this.USER_INFO_COORDINATE[index].SCORE.Y);
		} else {
			userPanelBrush.fillText(score,
					this.USER_INFO_COORDINATE[index].SCORE.X,
					this.USER_INFO_COORDINATE[index].SCORE.Y);
		}
	} else {
		// TODO throw exception
	}
};

catanClient.view.userPanelView.showUserRoadCount = function(roadCount, index) {
	index = index.toUpperCase();

	if (this.isRoadCountValid(roadCount) && this.isIndexValid(index)) {
		var userPanelBrush = catanClient.util.catanUtil.getUserPanelBrush();

		userPanelBrush
				.clearRect(
						this.USER_INFO_COORDINATE[index].ROAD_COUNT.CLEAR_RECTANGLE.X,
						this.USER_INFO_COORDINATE[index].ROAD_COUNT.CLEAR_RECTANGLE.Y,
						this.USER_INFO_COORDINATE[index].ROAD_COUNT.CLEAR_RECTANGLE.WIDTH,
						this.USER_INFO_COORDINATE[index].ROAD_COUNT.CLEAR_RECTANGLE.HEIGHT);
		userPanelBrush.font = this.USER_INFO_FONT;
		userPanelBrush.fillStyle = "white";
		if (roadCount >= 10) {
			userPanelBrush.fillText(roadCount,
					this.USER_INFO_COORDINATE[index].ROAD_COUNT.X - 3,
					this.USER_INFO_COORDINATE[index].ROAD_COUNT.Y);
		} else {
			userPanelBrush.fillText(roadCount,
					this.USER_INFO_COORDINATE[index].ROAD_COUNT.X,
					this.USER_INFO_COORDINATE[index].ROAD_COUNT.Y);
		}
	} else {
		// TODO throw exception
	}
};

catanClient.view.userPanelView.showUserResourceCount = function(resourceCount,
		index) {
	index = index.toUpperCase();

	if (this.isResourceCountValid(resourceCount) && this.isIndexValid(index)) {
		var userPanelBrush = catanClient.util.catanUtil.getUserPanelBrush();

		userPanelBrush
				.clearRect(
						this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.CLEAR_RECTANGLE.X,
						this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.CLEAR_RECTANGLE.Y,
						this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.CLEAR_RECTANGLE.WIDTH,
						this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.CLEAR_RECTANGLE.HEIGHT);
		userPanelBrush.font = this.USER_INFO_FONT;
		userPanelBrush.fillStyle = "white";
		if (resourceCount >= 10) {
			userPanelBrush.fillText(resourceCount,
					this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.X - 3,
					this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.Y);
		} else {
			userPanelBrush.fillText(resourceCount,
					this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.X,
					this.USER_INFO_COORDINATE[index].RESOURCE_COUNT.Y);
		}
	} else {
		// TODO throw exception
	}
};

catanClient.view.userPanelView.showUserSoldierCount = function(soldierCount,
		index) {
	index = index.toUpperCase();

	if (this.isSoldierCountValid(soldierCount) && this.isIndexValid(index)) {
		var userPanelBrush = catanClient.util.catanUtil.getUserPanelBrush();

		userPanelBrush
				.clearRect(
						this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.CLEAR_RECTANGLE.X,
						this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.CLEAR_RECTANGLE.Y,
						this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.CLEAR_RECTANGLE.WIDTH,
						this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.CLEAR_RECTANGLE.HEIGHT);
		userPanelBrush.font = this.USER_INFO_FONT;
		userPanelBrush.fillStyle = "white";
		if (soldierCount >= 10) {
			userPanelBrush.fillText(soldierCount,
					this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.X - 3,
					this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.Y);
		} else {
			userPanelBrush.fillText(soldierCount,
					this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.X,
					this.USER_INFO_COORDINATE[index].SOLDIER_COUNT.Y);
		}
	} else {
		// TODO throw exception
	}
};

catanClient.view.userPanelView.isNameValid = function(name) {
	var isNameValid = false;
	if (name || name == "") {
		isNameValid = true;
	} else {
		isNameValid = false;
	}
	return isNameValid;
};

catanClient.view.userPanelView.isScoreValid = function(score) {
	var isScoreValid = false;

	if (catanClient.util.catanUtil.checkNotNegativeInteger(score)) {
		isScoreValid = true;
	} else {
		isScoreValid = false;
	}

	return isScoreValid;
};

catanClient.view.userPanelView.isRoadCountValid = function(roadCount) {
	var isRoadCountValid = false;

	if (catanClient.util.catanUtil.checkNotNegativeInteger(roadCount)) {
		isRoadCountValid = true;
	} else {
		isRoadCountValid = false;
	}

	return isRoadCountValid;
};

catanClient.view.userPanelView.isResourceCountValid = function(resourceCount) {
	var isResourceCountValid = false;

	if (catanClient.util.catanUtil.checkNotNegativeInteger(resourceCount)) {
		isResourceCountValid = true;
	} else {
		isResourceCountValid = false;
	}

	return isResourceCountValid;
};

catanClient.view.userPanelView.isSoldierCountValid = function(soldierCount) {
	var isSoldierCountValid = false;

	if (catanClient.util.catanUtil.checkNotNegativeInteger(soldierCount)) {
		isSoldierCountValid = true;
	} else {
		isSoldierCountValid = false;
	}

	return isSoldierCountValid;
};

catanClient.view.userPanelView.isIndexValid = function(index) {
	var isIndexValid = false;

	var userIndexArray = catanClient.controller.userPanelController
			.getUserIndexArray();
	if (index == userIndexArray[0] || index == userIndexArray[1]
			|| index == userIndexArray[2] || index == userIndexArray[3]) {
		isIndexValid = true;
	} else {
		isIndexValid = false;
	}

	return isIndexValid;
};