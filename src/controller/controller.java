package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Map;
import java.util.Observable;


import repository.repository;

import domain.GraduateStudent;
import domain.PhDStudent;
import domain.StackException;
import domain.UndergraduateStudent;
import domain.getID;
import domain.myException;
import domain.student;

public class controller extends Observable {
	private validator val;
	private repository<student> repo;

	
	public controller(repository<student> r,validator v){
		this.repo=r;
		this.val=v;
	}
	public String push(int id ,String name,int grade)throws StackException,myException{
			//it validates the student s and 
			//Pushes it onto the stack if there are no errors
			//Precondition : s is a student
			//Postcondition : valid,a string containing the error list is returned
			//in case there are errors,it will print in the ui
			//if there are no errors,the student will be pushed into the stack
			student s=new student(id,name,grade);
			String valid=this.val.validate(s);
			student [] students=new student[this.repo.totalNumber()];
			if(valid==""){
				if(this.repo.totalNumber()!=0){
					students=getAllStudents();
					for(int i=0;i<this.repo.totalNumber();i++){
						if(students[i].getID()==s.getID())
							valid+="Duplicate id";
					}
				}
			}
			if(valid.equals("")) {
				this.repo.push(s);
				this.setChanged();
				this.notifyObservers();
			}
			return valid;
			
	}
	
	public String push(int id ,String name,int grade,int grade2)throws StackException,myException{
		//it validates the student s and 
		//Pushes it onto the stack if there are no errors
		//Precondition : s is a student
		//Postcondition : valid,a string containing the error list is returned
		//in case there are errors,it will print in the ui
		//if there are no errors,the student will be pushed into the stack
		student s=new UndergraduateStudent(id,name,grade,grade2);
		student [] students=new student[this.repo.totalNumber()];
		String valid=this.val.validate(s);
		if(valid==""){
			if(this.repo.totalNumber()!=0){
				students=getAllStudents();
				for(int i=0;i<this.repo.totalNumber();i++){
					if(students[i].getID()==s.getID())
						valid+="Duplicate id";
				}
			}
		}
		if(valid.equals("")) {
			this.repo.push(s);
			this.setChanged();
			this.notifyObservers();
		}
		return valid;
		
}
	
	public String push(int id ,String name,int grade,int grade2,int grade3,String supervisor)throws StackException,myException{
		//it validates the student s and 
		//Pushes it onto the stack if there are no errors
		//Precondition : s is a student
		//Postcondition : valid,a string containing the error list is returned
		//in case there are errors,it will print in the ui
		//if there are no errors,the student will be pushed into the stack
		student [] students=new student[this.repo.totalNumber()];
		student s=new GraduateStudent(id,name,grade,grade2,grade3,supervisor);
		String valid=this.val.validate(s);
		if(valid==""){
			if(this.repo.totalNumber()!=0){
				students=getAllStudents();
				for(int i=0;i<this.repo.totalNumber();i++){
					if(students[i].getID()==s.getID())
						valid+="Duplicate id";
				}
			}
		}
		if(valid.equals("")) {
			this.repo.push(s);
			this.setChanged();
			this.notifyObservers();
		}
		return valid;
		
}
	
	public String push(int id ,String name,int grade,int grade2,String supervisor,String thesis)throws StackException,myException{
		//it validates the student s and 
		//Pushes it onto the stack if there are no errors
		//Precondition : s is a student
		//Postcondition : valid,a string containing the error list is returned
		//in case there are errors,it will print in the ui
		//if there are no errors,the student will be pushed into the stack
		student s=new PhDStudent(id,name,grade,grade2,supervisor,thesis);
		String valid=this.val.validate(s);
		student [] students=new student[this.repo.totalNumber()];
		if(valid==""){
			if(this.repo.totalNumber()!=0){
				students=getAllStudents();
				for(int i=0;i<this.repo.totalNumber();i++){
					if(students[i].getID()==s.getID())
						valid+="Duplicate id";
				}
			}
		}
		if(valid.equals("")) {
			this.repo.push(s);
			this.setChanged();
			this.notifyObservers();
		}
		return valid;
		
}
	

	public int getSize(){
			//gets the size of the stack
			//precondition :true
			//postcondition :an int will be returned holding the value of the size of the repository
			return this.repo.totalNumber();
	}
	
	 public void removeStudentsUntilMaxGrade() throws StackException{
	        student current = (student) this.repo.getTopT();
	        while (current.average() != 10){
	            this.repo.removeT(current);
	            current=this.repo.getTopT();
	            if(current==null){
	            	this.setChanged();
	            	this.notifyObservers();
	            	return;
	            }
	        }
	    }
	
	
	public student [] getAllStudents()throws myException,StackException{
			//This will get the students array from the stack
			//precondition true
			//postcondition : the students will be returned in the UI
			//Returns the information from the stack in the shape of an auxiliary copy array
			//pre condition : true
			//post condition : returns the array with the elements from the stack
			
			student [] aux = new student[this.repo.totalNumber()];
			Map<Integer, student> copy=this.repo.getAll();
			int i=0;
			for(student s:copy.values()){
				aux[i]=s;
				i++;
			}
			return aux;
		}
	
	public student [] getLessThan5()throws myException,StackException{
			
		student [] aux = new student[this.repo.totalNumber()];
			Map<Integer, student> copy=this.repo.getAll();
			int i=0;
			for(student s:copy.values()){
				if(s.average()<5){
					aux[i]=s;
					i++;
				}
			}
			return aux;
	}
	
	public student [] getMoreThan5()throws myException,StackException{
		
			student [] aux = new student[this.repo.totalNumber()];
			Map<Integer, student> copy=this.repo.getAll();
			int i=0;
			for(student s:copy.values()){
				if(s.average()>5){
					aux[i]=s;
					i++;
				}
			}
			return aux;
	}
	
	public student [] get10()throws myException,StackException{
		
			student [] aux = new student[this.repo.totalNumber()];
			Map<Integer, student> copy=this.repo.getAll();
			int i=0;
			for(student s:copy.values()){
				if(s.average()==10){
					aux[i]=s;
					i++;
				}
			}
			return aux;
	}

	
	public int numberOfStudentGreaterThan(int id) throws myException{
			
			return this.repo.greaterThanT(id);
	
	}
   
	 public void moveElements(Map<Integer ,? extends getID> source ,Map<Integer ,student> destination) {
	
	 
	 }
	 
	 public String[] allToString() throws myException,StackException{
			//This will get the students array from the stack
			//precondition true
			//postcondition : the students will be returned in the UI
			//Returns the information from the stack in the shape of an auxiliary copy array
			//pre condition : true
			//post condition : returns the array with the elements from the stack
			
			student [] aux = this.getAllStudents();
			//Map<Integer,student> copy=this.repo.getAll();
			String [] values=new String[this.repo.totalNumber()]; 
			for(int i=0;i<aux.length;i++){
				values[i]=aux[i].fileString()+"\n";
			}
			return values;
			
	 
	 }
	 
	 public void serializeRepository()
	   {
	      try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("srepository.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this.repo);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	   }
	 
	 
	 public void deserializeRepository(){

	      try
	      {
	         FileInputStream fileIn = new FileInputStream("srepository.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.repo = (repository<student>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	      
	    }
	 
	 
		
		 
	 

}
	
