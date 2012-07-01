package com.games.catan.service;

import java.util.HashSet;

import com.games.catan.pojo.City;
import com.games.catan.pojo.DevelopCard;
import com.games.catan.pojo.Player;
import com.games.catan.pojo.ResourceCard;
import com.games.catan.pojo.Road;
import com.games.catan.pojo.Village;

public class PlayerService extends BaseService {
	public void initPlayerWhenGameStart(Player player){
		player.setRoads(new HashSet<Road>());
		player.setVillages(new HashSet<Village>());
		player.setCities(new HashSet<City>());
		player.setResourceCards(new HashSet<ResourceCard>());
		player.setUnusedDevelopCards(new HashSet<DevelopCard>());
		player.setUsedDevelopCards(new HashSet<DevelopCard>());
	}
}
