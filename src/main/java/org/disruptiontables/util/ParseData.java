package org.disruptiontables.util;

import java.util.ArrayList;
import java.util.List;

import org.disruptiontables.dao.Station;

public class ParseData {
	
	public String parseParams(String input){
		
		if(input.equals(""))
			return null;
		
		else
			return input;
	}
	
	public boolean parseStringToInt(String input){
		
		try{
			Integer.parseInt(input);
			return true;
		}
		catch(Exception e){
			return false;
		}
		//return (Integer.getInteger(input)==null) ? false : true;
		
	}
	
	
	public List<Station> sortStations(int from, int to, List<Station> listUnsorted){
		
		int fromIndex=-1, toIndex=-1;
		
		for(int i=0;i<listUnsorted.size();i++){
			
			//search for 'from' station from the front of the list
			if(from==listUnsorted.get(i).getStationId())
				fromIndex=i;
			
			//looking for 'to' station from the back of the list
			if (to==listUnsorted.get(listUnsorted.size()-i-1).getStationId())
				toIndex=listUnsorted.size()-i; //leave out -1 as subList method is exlusive of to index
			
			if(fromIndex!=-1&&toIndex!=-1)
				break;
			
		}
		
		return new ArrayList<Station>(listUnsorted.subList(fromIndex, toIndex));
		
	}
	

}
