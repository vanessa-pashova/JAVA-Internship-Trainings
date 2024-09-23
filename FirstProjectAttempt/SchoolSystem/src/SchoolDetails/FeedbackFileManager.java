package SchoolDetails;

import Users.Student;
import Users.Teacher;

import java.io.*;
import java.util.*;

public class FeedbackFileManager {

    private static final String FILE_NAME = "feedback.txt";

    public static void saveFeedbackToFile(Student student, Teacher teacher, String feedback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("[ " + student.getFirstName() + " " + student.getSurname() + ", " + student.getGrade() + ", " + student.getMajor() + " ]");
            writer.newLine();
            writer.write("> Feedback by [" + teacher.getFirstName() + " " + teacher.getSurname() + "]: " + feedback);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.out.println("--- Error while saving the feedback to file ---");
            e.printStackTrace();
        }
    }

    public static List<String> loadFeedbackFromFile(Student student) {
        List<String> feedbacks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("[" + student.getFirstName() + " " + student.getSurname())) {
                    feedbacks.add(line);
                    line = reader.readLine();

                    while (line != null && !line.isEmpty()) {
                        feedbacks.add(line);
                        line = reader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
}
