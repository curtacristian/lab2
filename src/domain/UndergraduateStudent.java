package domain;

public class UndergraduateStudent extends student implements compareStudent<student>,java.io.Serializable {
    public int grade2;

    public UndergraduateStudent(int id, String name, int grade, int grade2) {
        super(id, name, grade);
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
		s="Under | "+this.getID()+"  | "+this.getName()+"    | "+this.getGrade()+" | "+grade2;
		return s;
	}
    @Override
    public String fileString(){
    	String s="";
		s=this.getID()+" "+this.getName()+" "+this.getGrade()+" "+grade2;
		return s;
    	
    }
}