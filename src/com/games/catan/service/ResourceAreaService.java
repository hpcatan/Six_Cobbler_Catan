package com.games.catan.service;

import com.games.catan.pojo.ResourceArea;
import com.games.catan.pojo.ResourceArea.ResourceType;
import com.games.catan.util.ArrayUtil;

public class ResourceAreaService extends BaseService {
	public ResourceArea[] getInitResourceAreas(){
		Integer[] OutputValues = getRandomOutputValues();
		ResourceType[] resourceTypes = this.getRandomResourceTypes();
		ResourceArea[] resourceAreas = new ResourceArea[19];
		boolean isDesertSetted = false;
		for(int i=0;i<resourceAreas.length;i++){
			ResourceArea resourceArea = new ResourceArea();
			resourceAreas[i] = resourceArea;
			resourceArea.setResourceType(resourceTypes[i]);
			if(resourceTypes[i]==ResourceType.desert){
				isDesertSetted = true;
				continue;
			}
			resourceArea.setOutputValue(OutputValues[isDesertSetted?i-1:i]);
		}
		return resourceAreas;
	}
	private ResourceType[] getRandomResourceTypes(){
		ResourceType[] resourceTypes = new ResourceType[19];
		ResourceType[] baseResourceTypes = {ResourceType.forest,ResourceType.brick,ResourceType.grain,ResourceType.sheep,ResourceType.ore};
		ArrayUtil.makeArrayRandom(baseResourceTypes);
		int i=0;
		resourceTypes[i++] = baseResourceTypes[0];
		resourceTypes[i++] = baseResourceTypes[0];
		resourceTypes[i++] = baseResourceTypes[0];
		resourceTypes[i++] = baseResourceTypes[0];
		resourceTypes[i++] = baseResourceTypes[1];
		resourceTypes[i++] = baseResourceTypes[1];
		resourceTypes[i++] = baseResourceTypes[1];
		resourceTypes[i++] = baseResourceTypes[1];
		resourceTypes[i++] = baseResourceTypes[2];
		resourceTypes[i++] = baseResourceTypes[2];
		resourceTypes[i++] = baseResourceTypes[2];
		resourceTypes[i++] = baseResourceTypes[2];
		resourceTypes[i++] = baseResourceTypes[3];
		resourceTypes[i++] = baseResourceTypes[3];
		resourceTypes[i++] = baseResourceTypes[3];
		resourceTypes[i++] = baseResourceTypes[4];
		resourceTypes[i++] = baseResourceTypes[4];
		resourceTypes[i++] = baseResourceTypes[4];
		resourceTypes[i++] = ResourceType.desert;
		
		ArrayUtil.makeArrayRandom(resourceTypes);
		return resourceTypes;
	}
	private Integer[] getRandomOutputValues(){
		//initialize res
		int resLength = 18;
		Integer[] res = new Integer[resLength];
		res[0]=2;
		res[res.length-1] = 12;
		int value = 3;
		int repeat = 2;
		int currentRepeat = 0;
		int ignoreValue = 7; 
		for(int i=1;i<res.length-1;i++){
			if(currentRepeat>=repeat){
				currentRepeat = 0;
				value++;
				value = value==ignoreValue?++value:value;
			}
			res[i] = value;
			currentRepeat++;
		}
		
		//make res random
		ArrayUtil.makeArrayRandom(res);
		return res;
	}
	
	
	public static void main(String[] args) {
		//test getRondomOutputValues()
		/*ResourceAreaService areaService = new ResourceAreaService();
		Integer[] aaa= areaService.getRandomOutputValues();
		for(int i:aaa){
			System.out.println("="+i);
		}*/
		
		//test getInitResourceAreas()
		ResourceAreaService areaService = new ResourceAreaService();
		ResourceArea[] resourceAreas = areaService.getInitResourceAreas();
		for(ResourceArea resourceArea:resourceAreas){
			System.out.println(resourceArea.getResourceType()+" "+resourceArea.getOutputValue());
		}
	}
}
