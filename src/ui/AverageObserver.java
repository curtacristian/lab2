package ui;

import java.util.Observable;
import java.util.Observer;

import controller.controller;
import domain.StackException;
import domain.myException;
import domain.student;

public class AverageObserver implements Observer {
		
		private student [] students;
		private int type;
		private controller c;
		
		public AverageObserver(controller c,int a){
			this.type=a;
			this.c=c;
		}
		
		public int getType(){
			return this.type;
		}
	
		@Override
		public void update(Observable controller, Object arg1){
			String msg="";
			try{
				this.students=this.c.getAllStudents();
			}
			catch(StackException|myException e){
				e.printStackTrace();
			}
			if(this.type==0){
				msg+="Students with grade<5\n";
				for(student s:this.students){
					if(s.average()<5){
						msg+=s.toString()+"\n";
						}
				}
			}
			else{
				msg+="Students with grade>5\n";
				for(student s:this.students){
					if(s.average()>5){
						msg+=s.toString()+"\n";
						}
				}
			}
			System.out.printf("%s",msg);
		}


		
}
