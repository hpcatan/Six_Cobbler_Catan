catanClient.controller.backgroundController = {
	
	// Background model
	backgroundModel: null,
	
	// Background view
	backgroundView: null,
		
	// Init
	
	init: function() {
		this.backgroundModel = catanClient.model.backgroundModel;
		this.backgroundView = catanClient.view.backgroundView;
		
		this.backgroundView.setModel(this.backgroundModel);
	},
		
	// Paint
	paint: function(backgroundSrc, brush) {
		this.backgroundModel.setBackgroundProperties(backgroundSrc, brush);
	}
		
};