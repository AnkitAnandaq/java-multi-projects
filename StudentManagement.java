import java.io.*;
import java.util.*;

class Student {
    private String rollNo;
    private String name;

    public Student(String rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return rollNo + "," + name;
    }

    public void display() {
        System.out.println("Roll No: " + rollNo + ", Name: " + name);
    }
}

public class StudentManagement {
    private static final String FILE_NAME = "students.txt";
    private static String lastRollNo = "0103CA231000"; 

    public static void main(String[] args) {
        ArrayList<Student> students = loadStudents();  
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n📌 Student Management System");
            System.out.println("1️⃣ Add Student");
            System.out.println("2️⃣ Delete Student");
            System.out.println("3️⃣ Update Student Name");
            System.out.println("4️⃣ Display Students");
            System.out.println("5️⃣ Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    String newRollNo = generateNextRollNo();
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    students.add(new Student(newRollNo, name));
                    lastRollNo = newRollNo;
                    saveStudents(students);
                    System.out.println("✅ Student Added with Roll No: " + newRollNo);
                    break;

                case 2:
                    System.out.print("Enter Roll No to Delete: ");
                    String delRoll = sc.nextLine();
                    if (students.removeIf(s -> s.getRollNo().equals(delRoll))) {
                        saveStudents(students);
                        System.out.println("❌ Student Deleted!");
                    } else {
                        System.out.println("⚠ Student Not Found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll No to Update Name: ");
                    String updateRoll = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).getRollNo().equals(updateRoll)) {
                            System.out.print("Enter New Name: ");
                            String newName = sc.nextLine();
                            students.set(i, new Student(updateRoll, newName));
                            saveStudents(students);
                            System.out.println("🔄 Name Updated Successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("⚠ Student Not Found!");
                    break;

                case 4:
                    System.out.println("📋 Student List:");
                    for (Student s : students) {
                        s.display();
                    }
                    break;

                case 5:
                    System.out.println("🚀 Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid Option! Try Again.");
            }
        }
    }

    private static String generateNextRollNo() {
        int num = Integer.parseInt(lastRollNo.substring(10)) + 1;
        return "0103CA231" + String.format("%03d", num);
    }

    private static void saveStudents(ArrayList<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error Saving Data!");
        }
    }

    private static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return students; 

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("|");
                if (parts.length == 2) {
                    students.add(new Student(parts[0], parts[1]));
                    lastRollNo = parts[0]; 
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error Loading Data!");
        }

        return students;
    }
}
