package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class ResourceArea extends BasePOJO{
	public static enum ResourceType{desert,forest,brick,grain,sheep,ore};
	private ResourceType resourceType;
	private int outputValue;
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public int getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(int outputValue) {
		this.outputValue = outputValue;
	}
	
	@Override
	public String toString() {
		return "ResourceArea [resourceType=" + resourceType + ", outputValue="
				+ outputValue + "]";
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resourceType", resourceType);
		jsonObject.put("outputValue", outputValue);
		return jsonObject;
	}
	
}
