package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.exception.CatanLogicException;

public class Land extends BasePOJO {
	private ResourceArea[] resourceAreas;
	private House[] baseApartments;
	private Seaport[] seaports;	
	private SoldierInLand soldierInLand;
	
	public SoldierInLand getSoldierInLand() {
		return soldierInLand;
	}
	public void setSoldierInLand(SoldierInLand soldierInLand) {
		this.soldierInLand = soldierInLand;
	}
	public Seaport[] getSeaports() {
		return seaports;
	}
	public void setSeaports(Seaport[] seaports) {
		this.seaports = seaports;
	}
	public House[] getBaseApartments() {
		return baseApartments;
	}

	public void setBaseApartments(House[] baseApartments) {
		this.baseApartments = baseApartments;
	}

	public ResourceArea[] getResourceAreas() {
		return resourceAreas;
	}

	public void setResourceAreas(ResourceArea[] resourceAreas) {
		this.resourceAreas = resourceAreas;
	}
	public int getSoldierInResourceAreaIndex() throws CatanLogicException{
		ResourceArea captureResourceArea = this.soldierInLand.getCaptureResourceArea();
		for(int i=0;i<this.resourceAreas.length;i++){
			if(captureResourceArea.equals(resourceAreas[i])){
				return i;
			}
		}
		throw new CatanLogicException("can not find a resourceArea in land to equals soldier capture ResourceArea.");
	}
	@Override
	public JSONObject toJSON() throws JSONException, CatanLogicException {
		JSONObject resJsonObject = new JSONObject();
		resJsonObject.put("resourceAreas", getJSONArray(resourceAreas));
		resJsonObject.put("baseApartments", getJSONArray(baseApartments));
		resJsonObject.put("seaports", getJSONArray(seaports));
		resJsonObject.put("SoldierInResourceAreaIndex", this.getSoldierInResourceAreaIndex());
		return resJsonObject;
	}

}
