package com.games.catan.service;

import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.Land;
import com.games.catan.pojo.ResourceArea;
import com.games.catan.pojo.ResourceArea.ResourceType;
import com.games.catan.pojo.SoldierInLand;

public class LandService extends BaseService {
	public Land getLandByGameStart() throws CatanLogicException{
		Land land = new Land();
		land.setResourceAreas(this.resourceAreaService.getInitResourceAreas());
		land.setSeaports(this.seaportService.getSeaportWhenGameStart());
		
		SoldierInLand soldierInLand = new SoldierInLand();
		ResourceArea[] resourceAreas = land.getResourceAreas();
		for(int i=0;i<resourceAreas.length;i++){
			ResourceArea area = resourceAreas[i];
			if(area.getResourceType().equals(ResourceType.desert)){
				soldierInLand.setCaptureResourceArea(area);
				break;
			}
			if(i==resourceAreas.length){
				throw new CatanLogicException("can not find a desert resourceArea in land resource areas.");
			}
		}
		land.setSoldierInLand(soldierInLand);
		
		return land;
	}
	
	private ResourceAreaService resourceAreaService;
	private SeaportService seaportService;
	
	public void setSeaportService(SeaportService seaportService) {
		this.seaportService = seaportService;
	}

	public void setResourceAreaService(ResourceAreaService resourceAreaService) {
		this.resourceAreaService = resourceAreaService;
	}
	
}
