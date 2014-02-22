package start;

import repository.repository;
import ui.ui;
import controller.controller;
import controller.validator;
import domain.student;

public class start {
	public static void main(String []args){
		repository<student> StudentRepository= new repository<student>();
		validator Validator=new validator(StudentRepository);
		controller Controller=new controller(StudentRepository,Validator);
		ui UI= new ui();
		UI.setCont(Controller,"students.txt");
		UI.setVisible(true);
		UI.runUI();
		
	}
}
