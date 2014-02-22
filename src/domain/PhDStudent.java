package domain;

public class PhDStudent extends student implements compareStudent<student>,java.io.Serializable{
    public String supervisor;
    public String thesis;
    public int grade2;

    public PhDStudent(int id, String name, int grade, int grade2, String supervisor, String thesis) {
        super(id, name, grade);
        this.supervisor = supervisor;
        this.thesis = thesis;
        this.grade2 = grade2;
    }

    @Override
    public float average() {
        return (float)(this.getGrade() + this.grade2) / 2;
    }

    @Override
    public boolean isGreaterThan(student student) {
        return (this.average() > student.average());
    }
    
    @Override
    public String toString(){
    	String s="";
		s="PHD | "+this.getID()+"  | "+this.getName()+"    | "+this.getGrade()+" |"+grade2+" | "+thesis+" | "+supervisor;
		return s; 
    }
    @Override
    public String fileString(){
    	String s="";
		s=this.getID()+" "+this.getName()+" "+this.getGrade()+" "+grade2+" "+this.thesis+" "+this.supervisor;
		return s;
    	
    }
}
