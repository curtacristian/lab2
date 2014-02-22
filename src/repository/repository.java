package repository;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.StackException;
import domain.compareStudent;
import domain.getID;
import domain.student;

public class repository<T extends getID> implements java.io.Serializable{
		
	
	    private Map<Integer, T> elements = new LinkedHashMap<Integer, T>();
		
		
		public void push(T s){
			//pushes the T s in the stack
			//preconditions s=T
			//postcondition the repository stack 
			//in case the size hits the amount of the capacity,the stack will be resized
			//using an auxiliary T vector
			this.elements.put(s.getID(),s);
					

		}
		
		
		
		public void removeT(T s) throws StackException{
			this.elements.remove(s.getID());
		}
		
		
		public T getTopT() throws StackException {
			for (T element : this.elements.values()) {
	            return element;
	        }

	        return null;
	    }
		
		public Map<Integer, T> getAll() {
	        Map<Integer, T> copy = new LinkedHashMap<Integer, T>();
	        copy.putAll(this.elements);
	        return copy;
	    }
		
		public int totalNumber(){
			return this.elements.size();
		}
		
		public int greaterThanT(int id){
			int no=0;
			Map<Integer, T>copy=this.getAll();
			T s=copy.get(id);
			for(T compare:copy.values()){
				
				compareStudent compareS=(compareStudent)compare;
				if(compareS.isGreaterThan(s)){
						no++;
				}	
			}
			return no;
		}
		
		
		
}
	
