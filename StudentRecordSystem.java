import java.io.*;
import java.util.*;

class Student implements Serializable {
    private Integer rollNo;
    private String name;
    private String email;
    private String course;
    private Double marks;

    public Student() {}
    public Student(Integer rollNo, String name, String email, String course, Double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public Integer getRollNo() { return rollNo; }
    public String getName() { return name; }
    public Double getMarks() { return marks; }

    public void displayDetails() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }

    @Override
    public String toString() {
        return rollNo + "," + name + "," + email + "," + course + "," + marks;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(Integer.valueOf(parts[0]), parts[1], parts[2], parts[3], Double.valueOf(parts[4]));
    }
}

class FileUtil {
    public static void writeToFile(String filename, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Student> readFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with empty list.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return students;
    }
}

class StudentManager {
    private List<Student> students;

    public StudentManager(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void viewAllStudents() {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            it.next().displayDetails();
            System.out.println();
        }
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.displayDetails();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Student not found!");
    }

    public void deleteByName(String name) {
        Iterator<Student> it = students.iterator();
        boolean found = false;
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name)) {
                it.remove();
                System.out.println("Student deleted!");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Student not found!");
    }

    public void sortByMarks() {
        students.sort(Comparator.comparing(Student::getMarks).reversed());
        System.out.println("Sorted Student List by Marks:");
        viewAllStudents();
    }

    public List<Student> getStudents() {
        return students;
    }
}

public class StudentRecordSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename = "students.txt";

        List<Student> students = FileUtil.readFromFile(filename);
        if (!students.isEmpty()) {
            System.out.println("Loaded students from file:");
            for (Student s : students) s.displayDetails();
            System.out.println();
        }

        StudentManager manager = new StudentManager(students);

        Student s1 = new Student(101, "Ankit", "ankit@mail.com", "B.Tech", 85.5);
        Student s2 = new Student(102, "Riya", "riya@mail.com", "M.Tech", 91.0);
        Student s3 = new Student(103, "Karan", "karan@mail.com", "BCA", 76.2);

        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(s3);

        int choice;
        do {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = Integer.valueOf(sc.nextLine());
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = Double.valueOf(sc.nextLine());
                    manager.addStudent(new Student(roll, name, email, course, marks));
                    break;
                case 2:
                    manager.viewAllStudents();
                    break;
                case 3:
                    System.out.print("Enter Name to search: ");
                    manager.searchByName(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter Name to delete: ");
                    manager.deleteByName(sc.nextLine());
                    break;
                case 5:
                    manager.sortByMarks();
                    break;
                case 6:
                    FileUtil.writeToFile(filename, manager.getStudents());
                    System.out.println("Saved and exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
