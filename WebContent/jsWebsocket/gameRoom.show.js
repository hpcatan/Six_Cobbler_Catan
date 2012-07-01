catan.gameRoom.show = {
	showMessage:function (message,x,y){
		var chatContext = this.getChatContext();
		
		chatContext.fillStyle  ='black';
		chatContext.fillStyle = "rgba(255,0,0,0.5)";
		
		chatContext.font  = 'italic 20px sans-serif';   
		chatContext.textBaseline = 'top';
		chatContext.fillText (message, x, y);   
	},

	getChatContext:function(){
		var chatCanvas = jws.$("dynamicLandCanvasId");
		return chatCanvas.getContext("2d");
	}
};
