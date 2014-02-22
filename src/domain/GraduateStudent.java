package domain;


public class GraduateStudent extends student implements compareStudent<student>,java.io.Serializable{
    public String supervisor;
    public int grade2;
    public int grade3;

    public GraduateStudent(int id, String name, int grade, int grade2, int grade3, String supervisor) {
        super(id, name, grade);
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.supervisor=supervisor;
    }
    

    @Override
    public float average() {
        return (float)(this.getGrade() + this.grade2 + this.grade3) / 3;
    }

    @Override
    public boolean isGreaterThan(student student) {
        return (this.average() > student.average());
    }
    public String toString(){
		String s="";
		s="Grad | "+this.getID()+"  | "+this.getName()+"    | "+this.getGrade()+" | "+grade2+" | "+grade3+" | "+supervisor;
		return s;
	}
    
    @Override
    public String fileString(){
    	String s="";
		s=this.getID()+" "+this.getName()+" "+this.getGrade()+" "+grade2+" "+grade3+" "+supervisor;
		return s;
    	
    }
}