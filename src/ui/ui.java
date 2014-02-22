package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.controller;
import domain.StackException;
import domain.myException;
import domain.student;

public class ui extends JFrame {
	
	private controller cont;
	private String filename;
	private JPanel leftPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel rightPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JButton print=new JButton("See students");
	private JLabel printList=new JLabel();
	private String[] averages={"All students","Average is less than 5","Average is more than 5","Average is 10"};
	private String[] students={"Normal","Graduate","Undergraduate","PhD"};
	private JComboBox ShowAverage,StudentType;
	private JButton addStudent=new JButton("Add student");
	private JButton tryAdd=new JButton("Add the data");
	private TextField id=new TextField();
	private TextField grade1=new TextField();
	private TextField grade2=new TextField();
	private TextField grade3=new TextField();
	private TextField name =new TextField();
	private TextField supervisor=new TextField();
	private TextField thesis =new TextField();
	private TextField added=new TextField();
	private JFrame mainAdd=new JFrame();
	
	public ui(){
		this.cont=null;
	}
	
	public void setCont(controller c,String filename){
		this.cont=c;
		this.filename=filename;
		this.createUi();
	}
	
	public void printMenu(){
		System.out.println("\nApplication Menu");
		System.out.println("1.Add Student");
		System.out.println("2.Delete until grade 10 appears");
		System.out.println("3.Total number of students");
		System.out.println("4.Print students");
		System.out.println("5.Number of students greater than");
		System.out.println("6.Copy elements from stack to stack");
		System.out.println("7.Read from file");
		System.out.println("8.Write to file");	
		System.out.println("9.Serialize repository");
		System.out.println("10.Deserialize repository");
		System.out.println("13.Quit Application");
	}
	
	public class ComboBox implements ActionListener,ItemListener{
		
		public ComboBox(){
			ShowAverage = new JComboBox(averages);
	        ShowAverage.setSelectedIndex(-1);
	        ShowAverage.setPreferredSize(new Dimension(140, 22));
	        ShowAverage.setMaximumSize(new Dimension(140, 22));
	        ShowAverage.setLocation(30, 15);
	        ShowAverage.addItemListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
	        
	    }
		public void itemStateChanged(ItemEvent e){
    		if(e.getStateChange()==ItemEvent.SELECTED){
    			JComboBox combo = (JComboBox) e.getSource();
                int index = combo.getSelectedIndex();
                if(index==0) printStudents();
                if(index==1) printLessThan5();
                if(index==2) printMoreThan5();
                if(index==3) print10();
                
    		}
    		
    	}
	}
public class StudentBox implements ActionListener,ItemListener{
		
		public StudentBox(){
			StudentType = new JComboBox(students);
			StudentType.setSelectedIndex(-1);
			StudentType.setPreferredSize(new Dimension(100, 40));
			StudentType.setMaximumSize(new Dimension(100, 40));
			StudentType.setLocation(30, 15);
			StudentType.addItemListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
	        
	    }
		public void itemStateChanged(ItemEvent e){
    		if(e.getStateChange()==ItemEvent.SELECTED){
    			JComboBox combo = (JComboBox) e.getSource();
                int index = combo.getSelectedIndex();
                if(index==0) {
                	grade1.setVisible(true);
                	name.setVisible(true);
                	grade2.setVisible(false);
                	grade3.setVisible(false);
                	thesis.setVisible(false);
                	supervisor.setVisible(false);
                }
                if(index==1) {
                	grade1.setVisible(true);
                	name.setVisible(true);
                	grade2.setVisible(true);
                	grade3.setVisible(true);
                	thesis.setVisible(false);
                	supervisor.setVisible(true);
                }
                if(index==2) {
                	grade1.setVisible(true);
                	name.setVisible(true);
                	grade2.setVisible(true);
                	grade3.setVisible(false);
                	thesis.setVisible(false);
                	supervisor.setVisible(false);
                }
                if(index==3) {
                	grade1.setVisible(true);
                	name.setVisible(true);
                	grade2.setVisible(true);
                	grade3.setVisible(false);
                	thesis.setVisible(true);
                	supervisor.setVisible(true);
                }
                
                
    		}
    		
    	}
	}
	
	public void createUi(){
		readFromFile(this.filename);
		setTitle("Student Management");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 300));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 315, 15, 15));
	    print.setBounds(100,100,100,100);
	    print.addActionListener(new ActionListener(){
	    	@Override
	    	public void actionPerformed(ActionEvent event){
	    		printStudents();
	   	    	}
	    });
	    
	    this.setLayout(new FlowLayout());
	    this.setSize(new Dimension(450, 450));
	    setBackground(Color.ORANGE);
	    rightPanel.setBackground(Color.LIGHT_GRAY);
	    rightPanel.setPreferredSize(new Dimension(400, 400));
	    leftPanel.setPreferredSize(new Dimension(300,300));
	    print.setLocation(15, 15);
	    printList.setLocation(15,30);
	    
	    leftPanel.add(print);
	    rightPanel.add(printList);
	    ComboBox s=new ui.ComboBox();
	    leftPanel.add(ShowAverage);
	    addStudent.setBounds(200,200,200,200);

	    //Create and populate the panel.
	    JPanel p = new JPanel();
	    p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
	    Container c=mainAdd.getContentPane();
	    c.setLayout(new FlowLayout());
	    p.setVisible(true);
	    p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	    mainAdd.setSize(500,500);
	    JLabel l1=new JLabel("ID:");
	    JLabel l2=new JLabel("NAME:");
	    JLabel l3=new JLabel("Grade1:");
	    JLabel l4=new JLabel("Grade2:");
	    JLabel l5=new JLabel("Grade3:");
	    JLabel l6=new JLabel("Thesis:");
	    JLabel l7=new JLabel("Supervisor:");
	    
	    JPanel button1=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    JPanel button2=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    JPanel button3=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    JPanel button4=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    JPanel button5=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    JPanel button6=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    JPanel button7=new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    
	    mainAdd.setTitle("Add student");
	    mainAdd.setLocationRelativeTo(null);
	    id.setPreferredSize(new Dimension(50,25));
	    name.setPreferredSize(new Dimension(50,25));
	    grade1.setPreferredSize(new Dimension(50,25));
	    grade2.setPreferredSize(new Dimension(50,25));
	    grade3.setPreferredSize(new Dimension(50,25));
	    thesis.setPreferredSize(new Dimension(50,25));
	    supervisor.setPreferredSize(new Dimension(50,25));
	    id.setLocation(new Point(10,25));l1.setLabelFor(id);
	    name.setLocation(new Point(10,50));l2.setLabelFor(name);
	    grade1.setLocation(new Point(10,75));l3.setLabelFor(grade1);
	    grade2.setLocation(new Point(10,100));l4.setLabelFor(grade2);
	    grade3.setLocation(new Point(10,125));l5.setLabelFor(grade3);
	    thesis.setLocation(new Point(10,150));l6.setLabelFor(thesis);
	    supervisor.setLocation(new Point(10,175));l7.setLabelFor(supervisor);
	    tryAdd.setLocation(new Point(10,200));
	    StudentBox s2=new ui.StudentBox();
	    p.add(StudentType);
	    button1.add(l1);button1.add(id);
	    button2.add(l2);button2.add(name);
	    button3.add(l3);button3.add(grade1);
	    button4.add(l4);button4.add(grade2);
	    button5.add(l5);button5.add(grade3);
	    button6.add(l6);button6.add(thesis);
	    button7.add(l7);button7.add(supervisor);
	    
	    tryAdd.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		if(StudentType.getSelectedIndex()==0){
	    			try{
	    				ui.this.cont.push(Integer.parseInt(id.getText()),name.getText(),Integer.parseInt(grade1.getText()));
	    				id.setText("");
	    				name.setText("");
	    				grade1.setText("");
	    			}
	    			catch(myException |StackException ex){
	    				
	    			}
	    		}
	    		if(StudentType.getSelectedIndex()==1){
	    			try{
	    				ui.this.cont.push(Integer.parseInt(id.getText()), name.getText(),Integer.parseInt(grade1.getText()),Integer.parseInt(grade2.getText()));
	    				id.setText("");
	    				name.setText("");
	    				grade1.setText("");
	    				grade2.setText("");
	    			}
	    			catch(myException | StackException ex){}
	   	    	}
	    		if(StudentType.getSelectedIndex()==2)
	    			try{
	    				ui.this.cont.push(Integer.parseInt(id.getText()), name.getText(),Integer.parseInt(grade1.getText()),Integer.parseInt(grade2.getText()),Integer.parseInt(grade3.getText()),supervisor.getText());
	    				id.setText("");
	    				name.setText("");
	    				grade1.setText("");
	    				grade2.setText("");
	    				grade3.setText("");
	    				supervisor.setText("");
	    			}
	    		catch(myException |StackException ex){}
	    		
	    		if(StudentType.getSelectedIndex()==3)
	    			try{
	    				ui.this.cont.push(Integer.parseInt(id.getText()), name.getText(),Integer.parseInt(grade1.getText()),Integer.parseInt(grade2.getText()),thesis.getText(),supervisor.getText());
	    				id.setText("");
	    				name.setText("");
	    				grade1.setText("");
	    				grade2.setText("");
	    				grade3.setText("");
	    				supervisor.setText("");
	    			}
	    		catch(myException |StackException ex){}
	    	
	    	}
	    });
	    
	    p.add(button1);p.add(button2);p.add(button3);p.add(button4);p.add(button5);p.add(button6);p.add(button7);
	    p.add(tryAdd);
	    mainAdd.add(p);
	    
	    
	    
	    
	    
	    addStudent.addActionListener(new ActionListener(){
	    	@Override
	    	public void actionPerformed(ActionEvent event){
	    		mainAdd.setVisible(true);;
	   	    	}
	    });
	    leftPanel.add(addStudent);
	    
	    add(rightPanel);
	    add(leftPanel);
	    pack();
	    
	}

	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public void readFromFile(String filename){
		BufferedReader br=null;;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		String[] tokens;
		int lineNo = 0,id,grade,grade2,grade3;
		String name,supervisor,thesis;
		try {
			while((line = br.readLine()) != null){
					tokens = line.split(" ");
					switch (tokens.length){
						case 3:
							id=Integer.valueOf(tokens[0]).intValue();
							name = String.valueOf(tokens[1]);
							grade= Integer.valueOf(tokens[2]).intValue();
							try{
								this.cont.push(id, name, grade);
							}
							catch(myException | StackException e){
								e.printStackTrace();	
							}
							break;
						case 4:
							id=Integer.valueOf(tokens[0]).intValue();
							name = String.valueOf(tokens[1]);
							grade= Integer.valueOf(tokens[2]).intValue();
							grade2=Integer.valueOf(tokens[3]).intValue();
							try{
								this.cont.push(id, name, grade,grade2);
							}
							catch(myException | StackException e){
								e.printStackTrace();	
							}
							break;
						case 6:
							id=Integer.valueOf(tokens[0]).intValue();
							name = String.valueOf(tokens[1]);
							grade= Integer.valueOf(tokens[2]).intValue();
							grade2=Integer.valueOf(tokens[3]).intValue();
							if(isInteger(tokens[4])){
								grade3=Integer.valueOf(tokens[4]).intValue();
								supervisor = String.valueOf(tokens[5]);
								try{
									this.cont.push(id, name, grade,grade2,grade3,supervisor);
								}
								catch(myException | StackException e){
									e.printStackTrace();	
								}
							}
									
							else{
								thesis=String.valueOf(tokens[4]);
								supervisor = String.valueOf(tokens[5]);
								try{
									this.cont.push(id, name, grade,grade2,thesis,supervisor);
								}
								catch(myException | StackException e){
									e.printStackTrace();	
								}
							}
							break;
						default:
							break;
					}
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToFile(){
		try {
			FileWriter write = new FileWriter(this.filename);
			PrintWriter printStudents= new PrintWriter(write);
			String [] all=new String[this.cont.getSize()];
			try{
				all=this.cont.allToString();
			}
			catch (myException | StackException e){
				System.out.println(e.getMessage());
				return;
			}
			for(int i=0;i<all.length;i++){
				printStudents.printf(all[i]);
			}
			printStudents.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	
	public void readStudent(){
		Scanner input =new Scanner(System.in);
		int id=0,grade=0,grade2=0,grade3=0,choice=0;
		String name="",supervisor="",thesis="",valid="";
		System.out.print("ID:");
		if(input.hasNextInt()){
			id=input.nextInt();
		}
		else {
			System.out.print("ID should be integer!");
			runUI();
		}
		System.out.print("Grade:");
		if(input.hasNextInt()){
			grade=input.nextInt();
		}
		else {
			System.out.print("Grade should be integer!");
			runUI();
		}
		System.out.print("Name:");
		if(input.hasNext()){
			name=input.next();
		}
		else runUI();
		System.out.print("Student Type\n1.Normal student\n2.Undergraduate student\n3.GraduateStudent\n4.PhD Student\nYour Choice:");
		if(input.hasNextInt()){
			choice=input.nextInt();
		}
		else runUI();
		switch(choice){
		case 1:
			try{
				valid=this.cont.push(id, name, grade);
			}
			catch(StackException |myException e){
				System.out.print(e.getMessage());
			}
			if(!valid.isEmpty())
				System.out.printf("Errors at adding:\n%s", valid);
			break;
		case 2:
			System.out.print("Grade2:");
			if(input.hasNextInt()){
				grade2=input.nextInt();
			}
			else runUI();
			try{
				valid=this.cont.push(id, name, grade,grade2);
			}
			catch(StackException |myException e){
				System.out.print(e.getMessage());
			}
			if(!valid.isEmpty())
				System.out.printf("Errors at adding:\n%s", valid);
			break;
		case 3:
			System.out.print("Grade2:");
			if(input.hasNextInt()){
				grade2=input.nextInt();
			}
			else runUI();
			System.out.print("Grade3:");
			if(input.hasNextInt()){
				grade3=input.nextInt();
			}
			else runUI();
			System.out.print("Supervisor:");
			if(input.hasNext()){
				supervisor=input.next();
			}
			else runUI();
			try{
				valid=this.cont.push(id, name, grade,grade2,grade3,supervisor);
			}
			catch(StackException |myException e){
				System.out.print(e.getMessage());
			}
			if(!valid.isEmpty())
				System.out.printf("Errors at adding:\n%s", valid);
			break;
		case 4:
			System.out.print("Grade2:");
			if(input.hasNextInt()){
				grade2=input.nextInt();
			}
			else runUI();
			System.out.print("Supervisor:");
			if(input.hasNext()){
				supervisor=input.next();
			}
			else runUI();
			System.out.print("Thesis:");
			if(input.hasNext()){
				thesis=input.next();
			}
			else runUI();
			
			try{
				valid=this.cont.push(id, name, grade,grade2,supervisor,thesis);
			}
			catch(StackException |myException e){
				System.out.print(e.getMessage());
			}
			if(!valid.isEmpty())
				System.out.printf("Errors at adding:\n%s", valid);
			break;
		}
			
			
			
	}
	
	public void printStudents(){
		student [] all=new student [this.cont.getSize()];
		try{
			all=this.cont.getAllStudents();
		}
		catch (myException | StackException e){
			System.out.println(e.getMessage());
		}
		String msg="<html>";
		msg+="TYPE | ID | NAME |GRADE | GRADE2 | GRADE3 | THESIS | SUPERVISOR\n<br>";
		int i=0;
		while(i<this.cont.getSize()){
			msg+=all[i].toString()+"<br>";
			i++;
		}
		msg +="</html>";
		printList.setText(msg);
		
	}
	
	public void printLessThan5(){
		student [] all=new student [this.cont.getSize()];
		try{
			all=this.cont.getLessThan5();
		}
		catch (myException | StackException e){
			System.out.println(e.getMessage());
		}
		String msg="<html>";
		msg+="TYPE | ID | NAME |GRADE | GRADE2 | GRADE3 | THESIS | SUPERVISOR\n<br>";
		int i=0;
		while(i<this.cont.getSize()){
			if(all[i]==null) break;
			msg+=all[i].toString()+"<br>";
			i++;
		}
		msg +="</html>";
		printList.setText(msg);
		
	}
	
	public void printMoreThan5(){
		student [] all=new student [this.cont.getSize()];
		try{
			all=this.cont.getMoreThan5();
		}
		catch (myException | StackException e){
			System.out.println(e.getMessage());
		}
		String msg="<html>";
		msg+="TYPE | ID | NAME |GRADE | GRADE2 | GRADE3 | THESIS | SUPERVISOR\n<br>";
		int i=0;
		while(i<this.cont.getSize()){
			if(all[i]==null) break;
			msg+=all[i].toString()+"<br>";
			i++;
		}
		msg +="</html>";
		printList.setText(msg);
		
	}
	
	public void print10(){
		student [] all=new student [this.cont.getSize()];
		try{
			all=this.cont.get10();
		}
		catch (myException | StackException e){
			System.out.println(e.getMessage());
		}
		String msg="<html>";
		msg+="TYPE | ID | NAME |GRADE | GRADE2 | GRADE3 | THESIS | SUPERVISOR\n<br>";
		int i=0;
		while(i<this.cont.getSize()){
			if(all[i]==null) break;
			msg+=all[i].toString()+"<br>";
			i++;
		}
		msg +="</html>";
		printList.setText(msg);
		
	}
	
	
	public void runUI(){
		int choice=0;
		String valid="";
		Scanner input =new Scanner(System.in);
		student x=new student();
		boolean b=true;
		AverageObserver less=new AverageObserver(this.cont,0);
		AverageObserver more=new AverageObserver(this.cont,1);
		this.cont.addObserver(less);
		this.cont.addObserver(more);
		while(b){
			printMenu();
			System.out.print("Your Choice:");
			if(input.hasNextInt()){
				choice=input.nextInt();
			}
			else {
				System.out.print("Invalid choice!");
				this.runUI();
			}
			switch(choice){
			case 1:
				readStudent();
				break;
			case 2:
				try{
					this.cont.removeStudentsUntilMaxGrade();
				}
				catch (StackException e){
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.printf("Number of students= %d",this.cont.getSize());
				break;
			case 4:
				printStudents();
				break;
			case 5:
				int id=0;
				System.out.print("ID:");
				if(input.hasNextInt()){
					id=input.nextInt();
				}
				else {
					System.out.print("ID should be integer!");
					runUI();
				}
				try{
					System.out.printf("Number of students greater than %d =%d",id,this.cont.numberOfStudentGreaterThan(id));
				}
				catch(myException e){
					e.printStackTrace();
				}
				break;
			case 6:
				Map<Integer , student> newSource=new HashMap<Integer , student>();
				Map<Integer , student> newStack=new HashMap<Integer , student>();
				this.cont.moveElements(newSource,newStack);
				System.out.print("New stack copy created!");
				break;
			case 7:
				readFromFile(this.filename);
				System.out.print("Succesfully read from file !");
				break;
			case 8:
				writeToFile();
				System.out.print("Succesfully wrote to file !");
				break;
			case 9:
				this.cont.serializeRepository();
				System.out.print("Succesfully serialized repository !");
				break;
			case 10:
				this.cont.deserializeRepository();
				System.out.print("Succesfully deserialized repository !");
				break;
			case 13:	
				b=false;
				return;
			default:
				System.out.println("Wrong choice.Try again!");
				break;
			}
		}
		input.close();
	}
	
}
