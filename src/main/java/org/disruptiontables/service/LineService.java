package org.disruptiontables.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.disruptiontables.dao.Branch;
import org.disruptiontables.dao.Line;
import org.disruptiontables.dao.WriterDao;

public class LineService {
	
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
	
	//get ordered line based on selected branches
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
	/*
	private List<Branch> sortBranches(List<Branch> listUnsorted, int from, int to){
		
		Branch branchFrom;
		Branch temp;
		List<Branch> listSorted = new ArrayList<Branch>();
		
		//find initial 'from' branch
		for(int i=0;i<listUnsorted.size();i++){
			if(from==listUnsorted.get(i).getId()){
				branchFrom = listUnsorted.get(i);
				listSorted.add(branchFrom);
				break;}
		}
		
		
		//construct right branch list order
		for(int i=0; i<listUnsorted.size(); i++){
			//we already have initial element, we're getting nextId for the following branch
			int next = listSorted.get(i).getNextId();
			temp= new Branch();
			
			//we get next branch item
			if(next!=0){
				temp=getNextBranch(listUnsorted, next, i);
				//temp=listUnsorted.get(next-1);
				listSorted.add(temp);
				//continue;
			}
			else
				break;
			
		}
		return listSorted;
	}*/
	
	private Branch getNextBranch(List<Branch> listUnsorted, int next, int current){
		for(int i=current; i<listUnsorted.size()-1;i++){
			//we check if there's multiple branching from start to finish
			if(listUnsorted.get(i).getId()!=listUnsorted.get(i+1).getId()){
				if(listUnsorted.get(i).getId()==next)
					return listUnsorted.get(i);}
			//if there's multiple branching we return a special branch
			else{
				return new Branch(-1,"",-1); }
		}
		return null;
	}
	
	
	public List<Branch> sortBranches(List<Branch> listUnsorted, int from, int to){
			
			//from=2;
			//to=5;
			Branch branchFrom=null;
			Branch branchTo=null;
			Branch temp;
			List<Branch> listSortedFrom = new ArrayList<Branch>();
			List<Branch> listSortedTo = new ArrayList<Branch>();
			
			//find initial 'from' branch
			//TO-DO size: it desn't always apply: piccadilly 2 to 3  unsorted is list of all junctions not just mine
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
				
				if(branchTo!=null&&branchFrom!=null)
					break;
				
			}
			
			//get branch id by 
			//TO DO: je to potrebno? ali lahko damo p2p 
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
						
						//if thre's multiple branching from left (from) to right (to) we return special Branch obj and we stop adding to the list
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
	

}
