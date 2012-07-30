package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.pojo.ResourceArea.ResourceType;

public class Seaport extends BasePOJO {
	public static enum SeaportType{specialResource,allResource};
	private SeaportType seaportType;
	private ResourceType specialResourceType;
	
	public SeaportType getSeaportType() {
		return seaportType;
	}

	public void setSeaportType(SeaportType seaportType) {
		this.seaportType = seaportType;
	}

	public ResourceType getSpecialResourceType() {
		return specialResourceType;
	}

	public void setSpecialResourceType(ResourceType specialResourceType) {
		this.specialResourceType = specialResourceType;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("seaportType", this.seaportType);
		jsonObject.put("specialResourceType", this.specialResourceType);
		return jsonObject;
	}

}
