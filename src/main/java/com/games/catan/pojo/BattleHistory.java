package com.games.catan.pojo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.exception.CatanLogicException;

public class BattleHistory extends BasePOJO {
	public BattleHistory(){
		this.setBattleHistoryDetails(new ArrayList<BattleHistoryDetail>());
	}
	private List<BattleHistoryDetail> battleHistoryDetails;
	
	public List<BattleHistoryDetail> getBattleHistoryDetails() {
		return battleHistoryDetails;
	}

	public void setBattleHistoryDetails(
			List<BattleHistoryDetail> battleHistoryDetails) {
		this.battleHistoryDetails = battleHistoryDetails;
	}

	@Override
	public JSONObject toJSON() throws JSONException, CatanLogicException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("battleHistoryDetails", getJSONArray(battleHistoryDetails));
		return jsonObject;
	}

}
