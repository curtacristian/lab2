package domain;

public class student implements compareStudent<student>,java.io.Serializable,getID{
	private int id;
	private String name;
	private int grade;
	
	public student(){this.id=0;this.name="";this.grade=0;}
	
	public student(int id,String name,int grade){
		this.id=id;
		this.name=name;
		this.grade=grade;
	}
	
	@Override
	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id=id;
	}
	public int getGrade(){
		return this.grade;
	}
	
	public void setGrade(int grade){
		this.grade=grade;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public float average() {
	        return (float)grade;
	}

	@Override
	public boolean isGreaterThan(student student) {
	        return (this.average() > student.average());
	}
	
	public String toString(){
		String s="";
		s="Norm | "+id+"  | "+name+"    | "+grade;
		return s;
	}
	
	public String fileString(){
    	String s="";
		s=this.getID()+" "+this.getName()+" "+this.getGrade();
		return s;
    	
    }
		
}
