package org.disruptiontables.dao;

public class Station {
	
	private int stationId;
	private String name;
	private int stationOrder;
	private Line line;
	
	public Station(){}
	
	public Station(int stationId, String name, int stationOrder, Line line){
		this.stationId=stationId;
		this.name=name;
		this.stationOrder=stationOrder;
		this.line=line;
	}
	
	
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStationOrder() {
		return stationOrder;
	}
	public void setStationOrder(int stationOrder) {
		this.stationOrder = stationOrder;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	
	
	

}
