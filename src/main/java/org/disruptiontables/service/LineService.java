package org.disruptiontables.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.disruptiontables.dao.Branch;
import org.disruptiontables.dao.Line;
import org.disruptiontables.dao.WriterDao;

public class LineService {
	
	/**
	 * Gets all lines
	 * */
	public List<Line> getAllLines(){
		
		WriterDao dao = new WriterDao();
		List<Line> linesLst = new ArrayList<Line>();
		
		try{
			dao.setDBConnection();
			linesLst = dao.getAllLines();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return linesLst;
	}
	
	/**
	 * Get ordered line based on selected branches
	 * */
	public List<Branch> getAllLineBranches(int lineId){
			
			WriterDao dao = new WriterDao();
			List<Branch> branchList = new ArrayList<Branch>();
			
			try{
				dao.setDBConnection();
				branchList = dao.getAllLineBranches(lineId);
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		
			return branchList;
	}
	
	/**
	 * The method returns line based on ID
	 * */
	public Line getLine(int id) {
		
			WriterDao dao = new WriterDao();
			Line line = new Line();
			
			try{
				dao.setDBConnection();
				line = dao.getLine(id);
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
			return line;
	}
	
	/**
	 * Method returns sorted list of branches
	 * */
	public List<Branch> getSortedLineBranches(int lineId, int from, int to){
		
		WriterDao dao = new WriterDao();
		List<Branch> branchList = new ArrayList<Branch>();
		
		try{
			dao.setDBConnection();
			branchList = dao.getAllLineBranches(lineId);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		//branchList = sortBranches(branchList, from, to);
		branchList = sortBranches(branchList, from, to);
		return branchList;
	}
	
	
	//TO-DO: this will be changed with tree search algorithm or NN search algorithm
	//TO-DO: create both left & right movement in the same go: from 2 to 4 - Picadilly
	//TO-DO: move this method to separate class in org.distuptiontables.util package
	/**
	 * Method returns a sorted list of branches
	 * */
	public List<Branch> sortBranches(List<Branch> listUnsorted, int from, int to) throws NullPointerException{
			
			//from=2;
			//to=5;
			Branch branchFrom=null;
			Branch branchTo=null;
			Branch temp;
			List<Branch> listSortedFrom = new ArrayList<Branch>();
			List<Branch> listSortedTo = new ArrayList<Branch>();
			
			//find initial 'from' branch and 'to' branches
			for(int i=0;i<listUnsorted.size();i++){
				
				//search for 'from' branch from the front of the list
				if(from==listUnsorted.get(i).getId()){
					branchFrom = listUnsorted.get(i);
					listSortedFrom.add(branchFrom);
				}
				
				//looking for 'to' branch from the back of the list
				if (to==listUnsorted.get(listUnsorted.size()-i-1).getId()){
					branchTo=listUnsorted.get(listUnsorted.size()-i-1);
					listSortedTo.add(branchTo);
				}
				
				//end loop when we have both from and to branches
				if(branchTo!=null&&branchFrom!=null)
					break;
				
			}
			
			//if from and to destinations are on the same branch, we don't to search for connection
			//TO DO: is it necessary, we can optimize this
			if(branchFrom.getId()==branchTo.getId()){
				List<Branch> list = new ArrayList<Branch>();
				list.add(branchFrom);
				return list;
			}
			
			//construct right branch list order
			for(int i=0; i<=listUnsorted.size()/2; i++){
				
				//we already have initial element, we're getting nextId for the following branch
				int next = listSortedFrom.get((listSortedFrom.size()-1)).getNextId();
				int previous = listUnsorted.size()-i-1;
				temp= new Branch();
				
				//we get next branch item
				if(next!=branchTo.getId()){
					temp = getNextBranch(listUnsorted, next, i);
						
						//if thre's multiple branching from left (from) to right (to) we return special Branch obj (-1) and we stop adding to the list
						if((temp.getNextId()!=-1)){
							branchFrom=temp;
							listSortedFrom.add(branchFrom);	
						}
				}
				else
					break;
				
				//we get previous branch starting from end destination
				for(int j=previous; j>=0; j--){
					if(listUnsorted.get(previous).getNextId()==branchTo.getId()){
						branchTo=listUnsorted.get(previous);
						listSortedTo.add(branchTo);
						break;
					}
				}
				
			}
			
			Collections.reverse(listSortedTo);
			
			List<Branch> listSorted = new ArrayList<>(listSortedFrom);
			listSorted.addAll(listSortedTo);
			
			return listSorted;
		}
		
	//gets next branch based on our collection
	private Branch getNextBranch(List<Branch> listUnsorted, int next, int current){
		for(int i=current; i<listUnsorted.size()-1;i++){
			//check if there's multiple branching from start to finish
			if(listUnsorted.get(i).getId()!=listUnsorted.get(i+1).getId()){
				if(listUnsorted.get(i).getId()==next)
					return listUnsorted.get(i);}
			//if there's multiple branching we return a special branch
			else{
				return new Branch(-1,"",-1); }
		}
		return null;
	}
	
	
	

}
