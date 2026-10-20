import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        double marks;

        Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        String getGrade() {
            if (marks >= 90) {
                return "A+";
            } else if (marks >= 80) {
                return "A";
            } else if (marks >= 70) {
                return "B";
            } else if (marks >= 60) {
                return "C";
            } else if (marks >= 50) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("      STUDENT GRADE TRACKER");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Class Statistics");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    viewStatistics();
                    break;

                case 4:
                    System.out.println("Thank you for using Student Grade Tracker!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1 to 4.");
            }

        } while (choice != 4);

        sc.close();
    }

    static int readInt() {
        while (true) {
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid whole number: ");
            }
        }
    }

    static void addStudent() {

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter marks (0-100): ");

        double marks;

        while (true) {
            String input = sc.nextLine().trim();

            try {
                marks = Double.parseDouble(input);

                if (Double.isFinite(marks) && marks >= 0 && marks <= 100) {
                    break;
                }

                System.out.print("Marks must be between 0 and 100. Try again: ");

            } catch (NumberFormatException e) {
                System.out.print("Enter valid numeric marks: ");
            }
        }

        students.add(new Student(name, marks));

        System.out.println("Student added successfully!");
        System.out.println("Grade: " + students.get(students.size() - 1).getGrade());
    }

    static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n========== STUDENT REPORT ==========");
        System.out.printf("%-20s %-10s %-10s%n", "Name", "Marks", "Grade");
        System.out.println("------------------------------------");

        for (Student student : students) {
            System.out.printf("%-20s %-10.2f %-10s%n",
                    student.name, student.marks, student.getGrade());
        }
    }

    static void viewStatistics() {

        if (students.isEmpty()) {
            System.out.println("No records available to calculate statistics.");
            return;
        }

        double total = 0;
        double highest = students.get(0).marks;
        double lowest = students.get(0).marks;

        for (Student student : students) {
            total += student.marks;

            if (student.marks > highest) {
                highest = student.marks;
            }

            if (student.marks < lowest) {
                lowest = student.marks;
            }
        }

        double average = total / students.size();

        System.out.println("\n========== CLASS STATISTICS ==========");
        System.out.println("Total students : " + students.size());
        System.out.printf("Average marks  : %.2f%n", average);
        System.out.printf("Highest marks  : %.2f%n", highest);
        System.out.printf("Lowest marks   : %.2f%n", lowest);
    }
}