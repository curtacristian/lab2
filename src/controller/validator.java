package controller;

import repository.repository;
import domain.StackException;
import domain.myException;
import domain.student;

public class validator {
	
	private repository<student> repo;
	student [] students;
	
	public validator(repository<student> r){
		this.repo=r;
		this.students=new student[this.repo.totalNumber()];
	}
	
	public String validate(student s)throws StackException{
		//validates if the input data is correct
		//in case the information is wrong,it returns an error message 
		//that will be printed on the standard output
		//pre condition:s is a student
		//post condition: returns an empty string if no errors are found
		//in case of errors it will return a string containing the error mesages
		String msg="";
		if(s.getID()<0){
			msg+="Invalid id!\n";
		}
		if(s.getGrade()<1||s.getGrade()>10){
			msg+="Invalid grade!\n";
		}
		if(s.getName()==""){
			msg+="Invalid name!\n";
		}
		return msg;
	}
	
}
