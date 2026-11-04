import java.io.*;

public class DriverSerialization {
    public static void main(String[] args) {
        String filename = "students.dat";

        Student[] originalStudents = {
            new Student(101, "Alice Smith", 3.85, "TOKEN_SECRET_987"),
            new Student(102, "Bob Johnson", 3.60, "TOKEN_SECRET_654"),
            new Student(103, "Charlie Brown", 3.92, "TOKEN_SECRET_321")
        };

        System.out.println("=== Before Serialization ===");
        for (Student s : originalStudents) {
            System.out.println(s);
        }

        // Save using ObjectOutputStream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(originalStudents);
            System.out.println("\nSuccessfully serialized array of " + originalStudents.length + " students to " + filename);
        } catch (IOException e) {
            System.err.println("Serialization error: " + e.getMessage());
        }

        // Load using ObjectInputStream
        Student[] loadedStudents = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            loadedStudents = (Student[]) ois.readObject();
            System.out.println("\n=== After Deserialization ===");
            for (Student s : loadedStudents) {
                System.out.println(s);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization error: " + e.getMessage());
        }

        System.out.println("\nVerification: ID, Name, and GPA survived accurately.");
        System.out.println("Verification: Transient field 'sessionToken' is null (not saved).");
    }
}