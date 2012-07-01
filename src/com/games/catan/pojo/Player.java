package com.games.catan.pojo;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.DevelopCard.DevelopCardType;

public class Player extends BasePOJO {
	public static enum PlayerColor{red,yellow,green,blue};
	private User user;
	private PlayerColor playerColor;
	private Set<Road> roads = new HashSet<Road>();
	private Set<City> cities = new HashSet<City>();
	private Set<Village> villages = new HashSet<Village>();
	private Set<ResourceCard> resourceCards = new HashSet<ResourceCard>();
	private Set<DevelopCard> unusedDevelopCards = new HashSet<DevelopCard>();
	private Set<DevelopCard> usedDevelopCards = new HashSet<DevelopCard>();
	public int getBuildingAndCardPoints(){
		int playerPoints = 0;
		int buildingPoints = villages.size()*2+cities.size();
		int cardPoints = 0;
		for(DevelopCard card:usedDevelopCards){
			if(card.getDevelopCardType().equals(DevelopCardType.point)){
				cardPoints++;
			}
		}
		playerPoints = buildingPoints+cardPoints;
		return playerPoints;
	}
	public Set<DevelopCard> getUnusedDevelopCards() {
		return unusedDevelopCards;
	}
	public void setUnusedDevelopCards(Set<DevelopCard> unusedDevelopCards) {
		this.unusedDevelopCards = unusedDevelopCards;
	}
	public Set<DevelopCard> getUsedDevelopCards() {
		return usedDevelopCards;
	}
	public void setUsedDevelopCards(Set<DevelopCard> usedDevelopCards) {
		this.usedDevelopCards = usedDevelopCards;
	}
	public Set<ResourceCard> getResourceCards() {
		return resourceCards;
	}
	public void setResourceCards(Set<ResourceCard> resourceCards) {
		this.resourceCards = resourceCards;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Village> getVillages() {
		return villages;
	}
	public void setVillages(Set<Village> villages) {
		this.villages = villages;
	}
	public PlayerColor getPlayerColor() {
		return playerColor;
	}
	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}
	public Set<Road> getRoads() {
		return roads;
	}
	public void setRoads(Set<Road> roads) {
		this.roads = roads;
	}
	public Set<City> getCities() {
		return cities;
	}
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	@Override
	public JSONObject toJSON() throws JSONException, CatanLogicException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("playerColor", playerColor);
		jsonObject.put("user", user==null?user:user.toJSON());
		jsonObject.put("roads", getJSONArray(roads));
		jsonObject.put("villages", getJSONArray(villages));
		jsonObject.put("citys", getJSONArray(cities));
		jsonObject.put("resourceCards", getJSONArray(resourceCards));
		jsonObject.put("unusedDevelopCards", getJSONArray(unusedDevelopCards));
		jsonObject.put("usedDevelopCards", getJSONArray(usedDevelopCards));
		jsonObject.put("buildingAndCardPoints", getBuildingAndCardPoints());
		return jsonObject;
	}

}
