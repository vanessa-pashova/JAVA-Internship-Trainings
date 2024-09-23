package Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SchoolDetails.*;

public class Teacher extends User {
    private LocalDate hiringDate;
    private Departments department;
    private ArrayList<Courses> coursesTaught = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    private Map<Student, Notes> studentNotesMap;

    public Teacher(String firstName, String surname, int age, String email, String password, String phoneNumber, String department) {
        super(firstName, surname, age, email, password, phoneNumber, Role.TEACHER);
        this.studentNotesMap = new HashMap<>();
    }

    public Notes getStudentNotes(Student student) {
        return  studentNotesMap.computeIfAbsent(student, k -> new Notes());
    }

    public Teacher(String fn, String ln, int age, String email, String password, String pn,
                   LocalDate hiringDate, Departments department) {
        super(fn, ln, age, email, password, pn, Role.TEACHER);
        this.setAge(age);
        this.setHiringDate(hiringDate);
        this.setDepartment(department);
    }

    public Teacher() {
        super();
        this.hiringDate = null;
        this.department = null;
        this.coursesTaught = null;
        this.role = Role.TEACHER;
    }

    public LocalDate getHiringDate() {
        return this.hiringDate;
    }

    public Departments getDepartment() {
        return this.department;
    }

    public ArrayList<Courses> getCoursesTaught() {
        return this.coursesTaught;
    }

    public int getNumberOfCourses() {
        return this.coursesTaught.size();
    }

    @Override
    public void setAge(int age) {
        if(age < 22 || 65 < age)
            throw new IllegalArgumentException("--- Invalid teacher age ---\n");

        else this.age = age;
    }

    public void setDepartment(Departments dep) {
        if(dep == null)
            throw new IllegalArgumentException("--- Department not inserted ---\n");

        else this.department = dep;
    }

    public void setHiringDate(LocalDate date) {
        int year = date.getYear();

        if(year > 2024 || year < 1960)
            throw new IllegalArgumentException("--- Hiring year illegal ---\n");

        else this.hiringDate = date;
    }
    public void addNewStudent(Student student) {
        if(student == null)
            throw new IllegalArgumentException("--- Invalid student information ---\n");

        this.students.add(student);
    }
    public void addCourse(Courses course) {
        if(course == null)
            throw new IllegalArgumentException("--- Invalid course ---\n");

        this.coursesTaught.add(course);
    }
    public void removeCourse(int index) {
        if(index < 0 || index >= getNumberOfCourses())
            throw new IllegalArgumentException("--- Invalid index given for number of the course ---\n");

        else if(this.getNumberOfCourses() == 0)
            throw new IllegalArgumentException("--- Cannot remove, list of courses is empty ---\n");

        this.coursesTaught.remove(index);
    }
    public void viewCourses() {
        int counter = 1;
        for(Courses course : this.coursesTaught) {
            System.out.printf("%d. %s\n", counter, course);
            counter++;
        }

        System.out.println();
    }

    public void viewTeachersStudents() {
        for(Student s : this.students)
            s.viewProfile();
    }

    public void addNoteToStudent(String name, String surname, int note) {
        if(name == null || surname == null || name.isEmpty() || surname.isEmpty())
            throw new IllegalArgumentException("--- Name does not exist ---\n");

        Student student = getStudentByNames(name, surname);
        student.addNoteToStudent(note);
    }

    public void editLastNoteToStudent(String name, String surname, int note) {
        if(name == null || surname == null || name.isEmpty() || surname.isEmpty())
            throw new IllegalArgumentException("--- Name does not exist ---\n");

        Student student = getStudentByNames(name, surname);
        student.editLastNoteToStudent(note);
    }

    public void addExpressionToStudent(String name, String surname, String exp) {
        if(name == null || surname == null || name.isEmpty() || surname.isEmpty())
            throw new IllegalArgumentException("--- Name does not exist ---\n");

        if(exp == null || exp.isEmpty())
            throw new IllegalArgumentException("--- Expression is empty, cannot be added ---\n");

        Student student = getStudentByNames(name, surname);
        student.addExpressionToStudent(exp);
    }

    public void removeLastExpressionToStudent(String name, String surname) {
        if(name == null || surname == null || name.isEmpty() || surname.isEmpty())
            throw new IllegalArgumentException("--- Name does not exist ---\n");

        Student student = getStudentByNames(name, surname);
        student.removeLastExpressionToStudent();
    }

    public void editLastExpressionToStudent(String name, String surname, String exp) {
        if(name == null || surname == null || name.isEmpty() || surname.isEmpty())
            throw new IllegalArgumentException("--- Name does not exist ---\n");

        Student student = getStudentByNames(name, surname);
        student.editLastExpressionToStudent(exp);
    }

    @Override
    public void viewProfile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String date = this.hiringDate.format(formatter);

        System.out.printf("Names: %s %s\n", this.firstName, this.surname );
        System.out.printf("Age: %d\nID: %d\n", this.age, id);
        System.out.printf("Email: %s\nPassword: N/A\n", this.email);

        System.out.println();
        System.out.println("Role: Teacher\n");
        System.out.printf("Department: %s\nHiring Date: %s\n\n", this.department, date);

        this.viewCourses();
        System.out.println();
    }

    public Student getStudentByNames(String firstName, String lastName) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName) && student.getSurname().equalsIgnoreCase(lastName))
                return student;
        }

        throw new IllegalArgumentException("--- No student found ---");
    }
}
