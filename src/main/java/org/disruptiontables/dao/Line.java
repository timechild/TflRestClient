package org.disruptiontables.dao;

import java.util.Date;
import java.util.List;

public class Line {
	private int id;
	private String lineName;
	private String lineDirection;
	private String lineType;
	private List<Branch> branchList;
	
	public Line(){}
	
	public Line(int id, String lineName, String lineDirection, String lineType){
		
		this.id=id;
		this.lineName=lineName;
		this.lineDirection = lineDirection;
		this.lineType= lineType;
	}

	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineDirection() {
		return lineDirection;
	}
	public void setLineDirection(String lineDirection) {
		this.lineDirection = lineDirection;
	}
	public String getLineType() {
		return lineType;
	}
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

}
