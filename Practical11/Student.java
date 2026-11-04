import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double gpa;
    private transient String sessionToken; // Will not be serialized

    public Student(int id, String name, double gpa, String sessionToken) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.sessionToken = sessionToken;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    @Override
    public String toString() {
        return "Student[ID=" + id + ", Name=" + name + ", GPA=" + gpa + ", SessionToken=" + sessionToken + "]";
    }
}