package com.games.catan.pojo;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.exception.CatanLogicException;

public abstract class BasePOJO {
	public abstract JSONObject toJSON() throws JSONException, CatanLogicException;
	JSONArray getJSONArray(BasePOJO[] basePOJOs) throws JSONException, CatanLogicException{
		if(basePOJOs==null)
			return null;
		JSONArray jsonArray = new JSONArray();
		for(BasePOJO basePOJO:basePOJOs){
			jsonArray.put(basePOJO==null?basePOJO:basePOJO.toJSON());
		}
		return jsonArray;
	}
	JSONArray getJSONArray(Collection<? extends BasePOJO> basePOJOs) throws JSONException, CatanLogicException{
		if(basePOJOs==null)
			return null;
		JSONArray jsonArray = new JSONArray();
		for(BasePOJO basePOJO:basePOJOs){
			jsonArray.put(basePOJO==null?basePOJO:basePOJO.toJSON());
		}
		return jsonArray;
	}
}
