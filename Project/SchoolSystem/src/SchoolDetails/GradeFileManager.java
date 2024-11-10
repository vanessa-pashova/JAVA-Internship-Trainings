package SchoolDetails;

import Users.Student;
import Users.Teacher;

import java.io.*;
import java.util.*;

public class GradeFileManager {

    private static final String GRADE_FILE = "grades.txt";
    public static void saveGrade(Student student, String teacherName, int grade) {
        try {
            List<String> lines = new ArrayList<>();
            boolean studentFound = false;
            boolean gradeUpdated = false;
            String studentIdentifier = String.format("[Student: %s %s, Class: %d, Major: %s]", student.getFirstName(), student.getSurname(), student.getGrade(), student.getMajor());
            String newGradeEntry = String.format("- Grade from %s: %d", teacherName, grade);

            BufferedReader reader = new BufferedReader(new FileReader(GRADE_FILE));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.equals(studentIdentifier)) {
                    studentFound = true;
                    lines.add(line);
                    line = reader.readLine();

                    while (line != null && line.startsWith("- Grade from ")) {
                        if (line.startsWith("- Grade from " + teacherName)) {
                            String[] parts = line.split(": ");
                            line = parts[0] + ": " + parts[1] + ", " + grade;
                            gradeUpdated = true;
                        }

                        lines.add(line);
                        line = reader.readLine();
                    }
                }

                else
                    lines.add(line);
            }

            reader.close();

            if (studentFound && !gradeUpdated)
                lines.add(newGradeEntry);

            if (!studentFound) {
                if (!lines.isEmpty())
                    lines.add("");

                lines.add(studentIdentifier);
                lines.add(newGradeEntry);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(GRADE_FILE));
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadGrades(Student student) {
        List<String> grades = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(GRADE_FILE))) {
            String line;
            boolean studentFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("[Student: " + student.getFirstName() + " " + student.getSurname()))
                    studentFound = true;

                else if (studentFound && line.startsWith("- Grade from"))
                    grades.add(line);

                else if (studentFound && line.isEmpty())
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grades;
    }

    public static Map<String, List<Integer>> loadGradesForStudent(Student student) {
        Map<String, List<Integer>> gradesByTeacher = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(GRADE_FILE))) {
            String line;
            boolean studentFound = false;
            String currentTeacher = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains("[Student: " + student.getFirstName() + " " + student.getSurname()))
                    studentFound = true;

                else if (studentFound && line.startsWith("- Grade from ")) {
                    currentTeacher = line.substring(12, line.indexOf(':', 12));
                    String[] grades = line.split(": ")[1].split(", ");

                    for (String grade : grades)
                        gradesByTeacher.computeIfAbsent(currentTeacher, k -> new ArrayList<>()).add(Integer.parseInt(grade.trim()));
                }

                else if (studentFound && line.isEmpty())
                    studentFound = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gradesByTeacher;
    }

    public static Map<String, List<Integer>> loadGradesByTeacher(Teacher teacher) {
        Map<String, List<Integer>> gradesByStudent = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("grades.txt"))) {
            String line;
            String currentStudent = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[Student: "))
                    currentStudent = line.substring(10, line.indexOf(','));

                else if (line.startsWith("- Grade from " + teacher.getFirstName() + " " + teacher.getSurname())) {
                    int grade = Integer.parseInt(line.split(": ")[1]);
                    gradesByStudent.computeIfAbsent(currentStudent, k -> new ArrayList<>()).add(grade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gradesByStudent;
    }

    public static void editLastGrade(Student student, String teacherName, int newGrade) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(GRADE_FILE));
            String line;
            boolean studentFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("[Student: " + student.getFirstName() + " " + student.getSurname()))
                    studentFound = true;

                if (studentFound && line.startsWith("- Grade from " + teacherName)) {
                    String[] parts = line.split(": ");
                    String[] grades = parts[1].split(", ");
                    grades[grades.length - 1] = String.valueOf(newGrade);
                    line = parts[0] + ": " + String.join(", ", grades);
                    studentFound = false;
                }

                lines.add(line);

                if (studentFound && line.isEmpty())
                    studentFound = false;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(GRADE_FILE));
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeLastGrade(Student student, String teacherName) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(GRADE_FILE));
            String line;
            boolean studentFound = false;

            while ((line = reader.readLine()) != null)
                if (line.contains("[Student: " + student.getFirstName() + " " + student.getSurname())) {
                    studentFound = true;

                if (studentFound && line.startsWith("- Grade from " + teacherName)) {
                    String[] parts = line.split(": ");
                    String[] grades = parts[1].split(", ");

                    if (grades.length > 1)
                        line = parts[0] + ": " + String.join(", ", Arrays.copyOf(grades, grades.length - 1));

                    else
                        line = null;

                    studentFound = false;
                }

                if (line != null)
                    lines.add(line);

                if (studentFound && line.isEmpty())
                    studentFound = false;
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(GRADE_FILE));
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
