package org.disruptiontables.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.disruptiontables.dao.Line;
import org.disruptiontables.dao.WriterDao;


@Path("/rest")
public class DisruptionTablesResource {

	
	@GET //katero http metodo naj sprejme 
	@Produces(MediaType.APPLICATION_JSON) //kaj naj Jersey vrne - plain text 
	public List<Line> getAllTables(){
		//return getAllDisruptionTables();
		return createMockList(2);
	}
	
	
	/**
	 * Method creates and returns mock list of items
	 * */
	private List<Line> createMockList(int size){
		
			List<Line> listDisptTable = new ArrayList<>();
			
			for (int i=0;i<size;i++){	
				String direction;
				
				if(i%2==0)
					direction ="southbound";
				else
					direction = "northbound";
				
				listDisptTable.add(new Line(i, "name "+Long.toString(i), direction, "underground"));
			}
			return listDisptTable;	
	 }
		
	
	/**
	 * The Method returns list of all DisruptionTables elements form the databases
	 * */
	public List<Line> getAllDisruptionTables(){
		return fillTheTables();
		//return createMockList(8);	
	}
	
	
	private List<Line> fillTheTables(){
		WriterDao dao = new WriterDao();
		List<Line> tableList = new ArrayList<>();
		try {
	    	dao.setDBConnection();
	    	tableList = dao.getAllLines();
	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableList;
	}
	
}
