package Users;

import SchoolDetails.Major;
import SchoolDetails.Notes;
import SchoolDetails.Role;
import SchoolDetails.TeachersExpressions;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private Major major;
    private int grade;
    private Notes notes;
    private TeachersExpressions expressions;

    public Student(String firstName, String surName, int age, String email, String password, String phoneNumber,
                   Major major, int grade) {
        super(firstName, surName, age, email, password, phoneNumber, Role.STUDENT);
        this.setAge(age);
        this.setMajor(major);
        this.setGrade(grade);
        this.notes = new Notes();
        this.expressions = new TeachersExpressions();
    }

    public Student() {
        super();
        this.major = null;
        this.grade = 0;
        this.notes = new Notes();
        this.expressions = new TeachersExpressions();
        this.role = Role.STUDENT;
    }

    public Major getMajor() {
        return this.major;
    }

    public int getGrade() {
        return this.grade;
    }


    public List<Integer> getNotes() {
        return this.notes.getNotes();
    }

    public List<String> getExpressions() {
        return this.expressions.getExpressions();
    }

    public void setMajor(Major major) {
        if (major == null)
            throw new IllegalArgumentException("--- Major does not exist ---\n");

        this.major = major;
    }

    public void setGrade(int grade) {
        if (grade < 1 || grade > 12)
            throw new IllegalArgumentException("--- Invalid grade ---\n");

        this.grade = grade;
    }

    public void setAge(int age) {
        if (age < 6 || age > 19)
            throw new IllegalArgumentException("--- Invalid age ---\n");

        this.age = age;
    }

    public void addNoteToStudent(int note) {
        notes.addNote(note);
        System.out.println("> Note: " + note + " successfully added\n\n");
    }

    public void editLastNoteToStudent(int note) {
        notes.editLastNote(note);
        System.out.println("> Last note successfully edited\n\n");
    }

    public void addExpressionToStudent(String exp) {
        expressions.addExpression(exp);
        System.out.println("> Expression successfully added\n\n");
    }

    public void removeLastExpressionToStudent() {
        expressions.removeLastExpression();
        System.out.println("> Last expression successfully removed\n\n");
    }

    public void editLastExpressionToStudent(String exp) {
        expressions.editLastExpression(exp);
        System.out.println("> Last expression successfully edited\n\n");
    }

    @Override
    public void viewProfile() {
        System.out.printf("Names: %s %s\n", this.firstName, this.surname);
        System.out.printf("Age: %d\nID: %d\nPhoneNumber: %s\n", this.age, id, this.getPhoneNumber());
        System.out.printf("Email: %s --- and --- Password: N/A\n\n", this.email);
        System.out.println("Role: Student\n");
        System.out.printf("Major: %s\n", this.major);
        System.out.printf("Grade: %d\n", this.grade);

        System.out.println("[ Do you need to see further information? ] - [ type \"yes\" or \"no\" ]\n>>> ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer == null || answer.isEmpty())
            throw new IllegalArgumentException("--- Invalid answer ---\n");

        if (answer.equals("yes")) {
            System.out.println("[ Then you need to enter the password ]\n>>> ");
            String pass = scanner.nextLine();

            if (pass.equals(this.password)) {
                System.out.println("[ Pass ]\n");
                this.viewGrades();
//                this.viewFeedback();
            } else throw new IllegalArgumentException("--- Invalid password ---\n");
        }
        System.out.println();
    }

    public void viewGrades() {
        System.out.println("Your Grades:");

        for (int grade : this.getNotes())
            System.out.println("Grade: " + grade);
    }

//    public void viewFeedback() {
//        System.out.println("Your Feedback:");
//
//        for (String feedback : this.getExpressions())
//            System.out.println("Feedback: " + feedback);
//    }
}
