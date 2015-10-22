package org.disruptiontables.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.disruptiontables.dao.Station;
import org.disruptiontables.service.StationService;
import org.disruptiontables.util.ParseData;

@Path("stations")
public class StationResource {
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> getAllStations(){
		StationService ss = new StationService();
		//return ss.getAllStations();
		//return ss.getStationPaTakeKurce();
		return ss.getSortedStations(2, 5, 21);
	}
	*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Station> getSortedStation(@Context UriInfo uriInfo){
			
			//check params form uri and parse empty params 
			ParseData parse = new ParseData();
		
			String line = parse.parseParams(uriInfo.getQueryParameters().getFirst("line"));
			String from = parse.parseParams(uriInfo.getQueryParameters().getFirst("from"));
			String to = parse.parseParams(uriInfo.getQueryParameters().getFirst("to"));
			
			System.out.println("line "+line);
			System.out.println("from "+from);
			System.out.println("to "+to);
			
			StationService ss = new StationService();
			
			if(from!=null&&to!=null&&line!=null){
				System.out.print("not null ");
				return ss.getSortedStations(from, to, line);
			}
			if((from==null||to==null)&&line!=null){
				System.out.print("line select ");
				return ss.getAllStations(Integer.parseInt(line));
			}
			else
				return ss.getAllStations();
	}
	
}
