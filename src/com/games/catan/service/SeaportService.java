package com.games.catan.service;

import com.games.catan.pojo.ResourceArea.ResourceType;
import com.games.catan.pojo.Seaport;
import com.games.catan.pojo.Seaport.SeaportType;
import com.games.catan.util.ArrayUtil;

public class SeaportService extends BaseService {
	static final int seaportCount = 9;
	public Seaport[] getSeaportWhenGameStart(){
		Seaport[] seaports = new Seaport[seaportCount];
		
		Seaport forestSeaport = new Seaport();
		forestSeaport.setSeaportType(SeaportType.specialResource);
		forestSeaport.setSpecialResourceType(ResourceType.forest);
		
		Seaport brickSeaport = new Seaport();
		brickSeaport.setSeaportType(SeaportType.specialResource);
		brickSeaport.setSpecialResourceType(ResourceType.brick);
		
		Seaport grainSeaport = new Seaport();
		grainSeaport.setSeaportType(SeaportType.specialResource);
		grainSeaport.setSpecialResourceType(ResourceType.grain);
		
		Seaport sheepSeaport = new Seaport();
		sheepSeaport.setSeaportType(SeaportType.specialResource);
		sheepSeaport.setSpecialResourceType(ResourceType.sheep);
		
		Seaport oreSeaport = new Seaport();
		oreSeaport.setSeaportType(SeaportType.specialResource);
		oreSeaport.setSpecialResourceType(ResourceType.ore);

		Seaport seaport1 = new Seaport();
		seaport1.setSeaportType(SeaportType.allResource);
		Seaport seaport2 = new Seaport();
		seaport2.setSeaportType(SeaportType.allResource);
		Seaport seaport3 = new Seaport();
		seaport3.setSeaportType(SeaportType.allResource);
		Seaport seaport4 = new Seaport();
		seaport4.setSeaportType(SeaportType.allResource);
		
		seaports[0] = forestSeaport;
		seaports[1] = brickSeaport;
		seaports[2] = grainSeaport;
		seaports[3] = sheepSeaport;
		seaports[4] = oreSeaport;
		seaports[5] = seaport1;
		seaports[6] = seaport2;
		seaports[7] = seaport3;
		seaports[8] = seaport4;
		
		ArrayUtil.makeArrayRandom(seaports);
		return seaports;
	}
}
