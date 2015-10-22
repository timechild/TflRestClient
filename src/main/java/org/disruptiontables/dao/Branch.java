package org.disruptiontables.dao;

public class Branch {
	
	private int id;
	private String name;
	private int nextId;
	
	public Branch(){}
	
	public Branch(int id, String name, int nextId){
		this.id = id;
		this.name = name;
		this.nextId = nextId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNextId() {
		return nextId;
	}
	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

}
