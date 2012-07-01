catanClient.controller.resourceController = {
	
	// Resource model
	resourceModel: null,
	
	// Resource view
	resourceView: null,
	
	init: function() {
		this.resourceModel = catanClient.model.resourceModel;
		this.resourceView = catanClient.view.resourceView;
		
		this.resourceView.setModel(this.resourceModel);
	},
		
	paint: function(resourceX, resourceY, resourceSrc, resourceNumber, brush) {
		this.resourceModel.setResourceProperties(resourceX, resourceY, resourceSrc, resourceNumber, brush);
	}
	
};