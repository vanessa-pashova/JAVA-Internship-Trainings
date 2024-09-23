package Users;

import java.io.*;

public class TeacherFileManager {

    private static final String FILE_NAME = "teacher_students.txt";

    public static void addStudentToTeacherFile(Teacher teacher, Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String teacherInfo = String.format("[ TEACHER: %s %s - %s ]", teacher.getFirstName(), teacher.getSurname(), teacher.getDepartment());
            String studentInfo = String.format("- %s %s, %d, %s", student.getFirstName(), student.getSurname(), student.getGrade(), student.getMajor());


            if (!isTeacherInFile(teacher)) {
                if (new File(FILE_NAME).length() > 0)
                    writer.newLine();

                writer.write(teacherInfo);
                writer.newLine();
            }

            if (!isStudentInTeacherFile(teacher, student)) {
                writer.write(studentInfo);
                writer.newLine();

                writer.newLine();
            } else {
                System.out.println("This student is already added under this teacher.");
                return;
            }

        } catch (IOException e) {
            System.out.println("Error while adding student to the teacher's file.");
            e.printStackTrace();
        }
    }

    public static boolean isTeacherInFile(Teacher teacher) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            String teacherIdentifier = String.format("[ TEACHER: %s %s - %s ]", teacher.getFirstName(), teacher.getSurname(), teacher.getDepartment());

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(teacherIdentifier))
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isStudentInTeacherFile(Teacher teacher, Student student) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            String teacherIdentifier = String.format("[ TEACHER: %s %s - %s ]", teacher.getFirstName(), teacher.getSurname(), teacher.getDepartment());
            String studentIdentifier = String.format("- %s %s, %d, %s", student.getFirstName(), student.getSurname(), student.getGrade(), student.getMajor());

            boolean teacherFound = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equals(teacherIdentifier))
                    teacherFound = true;

                else if (teacherFound && line.equals(studentIdentifier))
                    return true;

                else if (line.startsWith("[ TEACHER: "))
                    teacherFound = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isStudentUnderTeacher(Teacher teacher, String studentFirstName, String studentLastName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isUnderCurrentTeacher = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("[ TEACHER: ")) {
                    String teacherIdentifier = String.format("[ TEACHER: %s %s - %s ]", teacher.getFirstName(), teacher.getSurname(), teacher.getDepartment());
                    if (line.equals(teacherIdentifier))
                        isUnderCurrentTeacher = true;

                    else
                        isUnderCurrentTeacher = false;
                }

                if (isUnderCurrentTeacher && line.startsWith("- ")) {
                    line = line.substring(2);

                    int firstSpaceIndex = line.indexOf(' ');
                    String firstName = line.substring(0, firstSpaceIndex).trim();

                    int lastNameStartIndex = firstSpaceIndex + 1;
                    int commaIndex = line.indexOf(',', lastNameStartIndex);
                    String lastName = line.substring(lastNameStartIndex, commaIndex).trim();

                    if (firstName.equalsIgnoreCase(studentFirstName) && lastName.equalsIgnoreCase(studentLastName)) {
                        System.out.println("Student found: " + firstName + " " + lastName);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Student not found under this teacher.");
        return false;
    }
}
