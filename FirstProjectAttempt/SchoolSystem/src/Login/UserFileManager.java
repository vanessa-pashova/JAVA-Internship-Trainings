package Login;

import Messenger.Messege;
import SchoolDetails.Departments;
import SchoolDetails.Major;
import SchoolDetails.Role;
import Users.Admin;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserFileManager {
    private static final String FILE_NAME = "users.txt";

    public static void saveUsersToFile(Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User user : users.values()) {
                String userInfo;

                if (user instanceof Student) {
                    Student student = (Student) user;
                    userInfo = String.format("Names: %s %s, Age: %d, Email: %s, Password: %s, Phone number: %s, Role: %s, Grade: %d, Major: %s",
                            student.getFirstName(), student.getSurname(), student.getAge(), student.getEmail(),
                            student.getPassword(), student.getPhoneNumber(), student.getRole(), student.getGrade(), student.getMajor());
                }

                else if (user instanceof Teacher) {
                    Teacher teacher = (Teacher) user;
                    userInfo = String.format("Names: %s %s, Age: %d, Email: %s, Password: %s, Phone number: %s, Role: %s, Department: %s",
                            teacher.getFirstName(), teacher.getSurname(), teacher.getAge(), teacher.getEmail(),
                            teacher.getPassword(), teacher.getPhoneNumber(), teacher.getRole(), teacher.getDepartment());
                }

                else if (user instanceof Admin) {
                    Admin admin = (Admin) user;
                    userInfo = String.format("Names: %s %s, Age: %d, Email: %s, Password: %s, Phone number: %s, Role: %s",
                            admin.getFirstName(), admin.getSurname(), admin.getAge(), admin.getEmail(),
                            admin.getPassword(), admin.getPhoneNumber(), admin.getRole());
                }

                else
                    continue;

                writer.write(userInfo);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> loadUsersFromFile() {
        Map<String, User> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Names: ")) {
                    String[] parts = line.split(", ");
                    String[] names = parts[0].split(": ")[1].split(" ");
                    String firstName = names[0];
                    String lastName = names[1];
                    int age = Integer.parseInt(parts[1].split(": ")[1]);
                    String email = parts[2].split(": ")[1];
                    String password = parts[3].split(": ")[1];
                    String phoneNumber = parts[4].split(": ")[1];
                    String role = parts[5].split(": ")[1];

                    if (role.equalsIgnoreCase("STUDENT")) {
                        int grade = Integer.parseInt(parts[6].split(": ")[1]);
                        Major major = Major.valueOf(parts[7].split(": ")[1]);
                        users.put(email, new Student(firstName, lastName, age, email, password, phoneNumber, major, grade));
                    }

                    else if (role.equalsIgnoreCase("TEACHER")) {
                        Departments department = Departments.valueOf(parts[6].split(": ")[1]);
                        users.put(email, new Teacher(firstName, lastName, age, email, password, phoneNumber, LocalDate.now(), department));
                    }

                    else if (role.equalsIgnoreCase("ADMIN"))
                        users.put(email, new Admin(firstName, lastName, age, email, password, phoneNumber));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void addUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String userRecord;

            if (user instanceof Student) {
                Student student = (Student) user;
                userRecord = String.format("Names: %s %s, Age: %d, Email: %s, Password: %s, Phone number: %s, Role: %s, Grade: %d, Major: %s",
                        student.getFirstName(), student.getSurname(), student.getAge(), student.getEmail(),
                        student.getPassword(), student.getPhoneNumber(), student.getRole(), student.getGrade(), student.getMajor());
            }

            else if (user instanceof Teacher) {
                Teacher teacher = (Teacher) user;
                userRecord = String.format("Names: %s %s, Age: %d, Email: %s, Password: %s, Phone number: %s, Role: %s, Department: %s",
                        teacher.getFirstName(), teacher.getSurname(), teacher.getAge(), teacher.getEmail(),
                        teacher.getPassword(), teacher.getPhoneNumber(), teacher.getRole(), teacher.getDepartment());
            }

            else if (user instanceof Admin) {
                Admin admin = (Admin) user;
                userRecord = String.format("Names: %s %s, Age: %d, Email: %s, Password: %s, Phone number: %s, Role: %s",
                        admin.getFirstName(), admin.getSurname(), admin.getAge(), admin.getEmail(),
                        admin.getPassword(), admin.getPhoneNumber(), admin.getRole());
            }

            else
                return;

            writer.write(userRecord);
            writer.newLine();

            System.out.println("User added to file successfully.");
        } catch (IOException e) {
            System.out.println("Error while adding the user to file.");
            e.printStackTrace();
        }
    }

    public static Student findStudentInUsersFile(String firstName, String lastName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Names: ")) {
                    String namesPart = line.substring(7, line.indexOf(", Age:"));
                    String[] names = namesPart.split(" ");
                    String fileFirstName = names[0].trim();
                    String fileLastName = names[1].trim();

                    if (line.contains("Role: STUDENT")) {
                        if (fileFirstName.equals(firstName) && fileLastName.equals(lastName)) {
                            int age = Integer.parseInt(line.substring(line.indexOf("Age: ") + 5, line.indexOf(", Email:")).trim());
                            String email = line.substring(line.indexOf("Email: ") + 7, line.indexOf(", Password:")).trim();
                            String password = line.substring(line.indexOf("Password: ") + 10, line.indexOf(", Phone number:")).trim();
                            String phoneNumber = line.substring(line.indexOf("Phone number: ") + 14, line.indexOf(", Role:")).trim();
                            int grade = Integer.parseInt(line.substring(line.indexOf("Grade: ") + 7, line.indexOf(", Major:")).trim());
                            Major major = Major.valueOf(line.substring(line.indexOf("Major: ") + 7).trim());

                            return new Student(fileFirstName, fileLastName, age, email, password, phoneNumber, major, grade);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
