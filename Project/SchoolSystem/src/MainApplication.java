import Login.LoginPage;
import Login.UserFileManager;
import Messenger.*;
import SchoolDetails.*;
import Users.*;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class MainApplication {
    private LoginPage loginPage;
    private Messenger messenger;

    public MainApplication() {
        this.loginPage = new LoginPage();
        this.messenger = new Messenger();
    }

    public static void main(String[] args) {
        MainApplication app = new MainApplication();
        app.run();
    }

    public void run() {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            if (!loginPage.isUserLoggedIn()) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Login");
                System.out.println("0. Exit");
            }

            else {
                System.out.println("\n--- Main Menu ---");
                System.out.println("2. Logout");
                System.out.println("3. Send Message");
                System.out.println("4. View Received Messages");
                System.out.println("5. View Sent Messages");
                System.out.println("6. View Profile");
                System.out.println("7. View Contact List");

                if (loginPage.getCurrentUser().getRole() == Role.ADMIN) {
                    System.out.println("8. Add User to Community");
                    System.out.println("9. Remove User from Community");
                }

                if (loginPage.getCurrentUser().getRole() == Role.TEACHER) {
                    System.out.println("8. Manage Student Notes");
                    System.out.println("9. Manage Student Expressions");
                    System.out.println("10. Add Student to File");
                    System.out.println("11. View Grades of Students");
                }

                if (loginPage.getCurrentUser().getRole() == Role.STUDENT)
                    System.out.println("8. View My Grades");

                System.out.println("0. Exit");
            }

            System.out.print("\nSelect an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (!loginPage.isUserLoggedIn())
                        loginPage.login();

                    break;

                case 2:
                    if (loginPage.isUserLoggedIn())
                        loginPage.logout();

                    break;

                case 3:
                    if (loginPage.isUserLoggedIn())
                        sendMessage();

                    break;

                case 4:
                    if (loginPage.isUserLoggedIn())
                        viewReceivedMessages();

                    break;

                case 5:
                    if (loginPage.isUserLoggedIn())
                        viewSentMessages();

                    break;

                case 6:
                    if (loginPage.isUserLoggedIn())
                        loginPage.showCurrentUserProfile();

                    break;

                case 7:
                    if (loginPage.isUserLoggedIn()) {
                        viewContacts();
                    }
                    break;

                case 8:
                    if (loginPage.getCurrentUser().getRole() == Role.ADMIN)
                        addUserToCommunity();

                    else if (loginPage.getCurrentUser().getRole() == Role.TEACHER)
                        manageStudentNotes();

                    else if (loginPage.getCurrentUser().getRole() == Role.STUDENT)
                        viewMyGrades();

                    break;
                case 9:
                    if (loginPage.getCurrentUser().getRole() == Role.ADMIN)
                        removeUserFromCommunity();

                    else if (loginPage.getCurrentUser().getRole() == Role.TEACHER)
                        manageStudentExpressions();

                    break;

                case 10:
                    if (loginPage.getCurrentUser().getRole() == Role.TEACHER)
                        addStudentToTeacherFile();

                    break;

                case 11:
                    if (loginPage.getCurrentUser().getRole() == Role.TEACHER)
                        viewGradesOfStudents();

                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }



    private void initializeUsers() {
        Map<String, User> loadedUsers = UserFileManager.loadUsersFromFile();
        if (loadedUsers != null && !loadedUsers.isEmpty()) {
            for (User user : loadedUsers.values()) {
                System.out.println("Loaded user: " + user.getEmail() + " / " + user.getPassword());
                messenger.getContacts().addToCommunity(user.getRole(), user);
            }
            System.out.println("> Users loaded successfully from file.");
        }

        else
            System.out.println("> No users found in file, initializing default users.");
    }

    private void viewContacts() {
        if (loginPage.isUserLoggedIn()) {
            Map<String, User> loadedUsers = UserFileManager.loadUsersFromFile();

            if (loadedUsers != null && !loadedUsers.isEmpty()) {
                System.out.println("--- Contact List ---");

                for (User user : loadedUsers.values())
                    System.out.println(user.getFirstName() + " " + user.getSurname() + " (" + user.getEmail() + ")");
            }

            else
                System.out.println("No contacts found in file.");
        }

        else
            System.out.println("You must be logged in to view the contact list.");
    }

    private void manageStudentExpressions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter student's last name: ");
        String lastName = scanner.nextLine();

        User currentUser = loginPage.getCurrentUser();
        if (currentUser instanceof Teacher) {
            Teacher teacher = (Teacher) currentUser;
            Student student = teacher.getStudentByNames(firstName, lastName);

            if (student != null) {
                System.out.println("[1] Add Expression");
                System.out.println("[2] Edit Last Expression");
                System.out.println("[3] Remove Last Expression");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter expression to add: ");
                        String expressionToAdd = scanner.nextLine();
                        teacher.addExpressionToStudent(firstName, lastName, expressionToAdd);
                        break;

                    case 2:
                        System.out.print("Enter new expression: ");
                        String newExpression = scanner.nextLine();
                        teacher.editLastExpressionToStudent(firstName, lastName, newExpression);
                        break;

                    case 3:
                        teacher.removeLastExpressionToStudent(firstName, lastName);
                        break;

                    default:
                        System.out.println("Invalid option");
                }
            }

            else
                System.out.println("Student not found");
        }
    }

    private void sendMessage() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipient's email: ");
        String receiverEmail = scanner.nextLine();

        System.out.print("Enter recipient's role (Student/Teacher/Admin): ");
        String receiverRoleString = scanner.nextLine();

        Role receiverRole;
        try {
            receiverRole = Role.valueOf(receiverRoleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role entered.");
            return;
        }

        User sender = loginPage.getCurrentUser();

        System.out.print("Enter recipient's first name: ");
        String receiverFirstName = scanner.nextLine();

        System.out.print("Enter recipient's last name: ");
        String receiverLastName = scanner.nextLine();

        User receiver = messenger.getContacts().findUserByRoleAndName(receiverRole, receiverFirstName, receiverLastName, receiverEmail);

        if (receiver == null) {
            System.out.println("Receiver not found.");
            return;
        }

        System.out.print("Enter message subject: ");
        String subject = scanner.nextLine();

        System.out.print("Enter message content: ");
        String messageContent = scanner.nextLine();

        Messege message = new Messege(subject, messageContent,
                sender.getRole(), sender.getFirstName(), sender.getSurname(),
                receiverRole, receiverFirstName, receiverLastName);

        MessageFileManager.saveMessageToFile(sender, receiver, message);

        messenger.addToSent(sender.getRole(), sender, message);
        messenger.addToReceived(receiver.getRole(), receiver, message);

        System.out.println("> Message was sent to " + receiverRole + ": " + receiverFirstName + " " + receiverLastName + '\n');
    }

    public void addUserToCommunity() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("[ Enter role (Student/Teacher/Admin)] : ");
        String roleString = scanner.nextLine();

        Role role;
        try {
            role = Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("--- Invalid role ---");
            return;
        }

        System.out.print("[ Enter first name ]: ");
        String firstName = scanner.nextLine();

        System.out.print("[ Enter last name ]: ");
        String lastName = scanner.nextLine();

        System.out.print("[ Enter age ]: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("[ Enter email ]: ");
        String email = scanner.nextLine();

        System.out.print("[ Enter password ]: ");
        String password = scanner.nextLine();

        System.out.print("[ Enter phone number ]: ");
        String phoneNumber = scanner.nextLine();

        User newUser;
        switch (role) {
            case STUDENT:
                System.out.print("[ Enter major (COMPUTER_SCIENCE/DATA_SCIENCE/FRONT_END_DEVELOPMENT/CYBER_SECURITY) ]: ");
                String majorString = scanner.nextLine();
                Major major;
                try {
                    major = Major.valueOf(majorString.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("--- Invalid major ---");
                    return;
                }

                System.out.print("[ Enter grade ]: ");
                int grade = scanner.nextInt();
                newUser = new Student(firstName, lastName, age, email, password, phoneNumber, major, grade);
                break;

            case TEACHER:
                System.out.print("[ Enter department ]: ");
                String department = scanner.nextLine();
                newUser = new Teacher(firstName, lastName, age, email, password, phoneNumber, LocalDate.now(), Departments.valueOf(department.toUpperCase()));
                break;

            case ADMIN:
                newUser = new Admin(firstName, lastName, age, email, password, phoneNumber);
                break;

            default:
                System.out.println("--- Invalid role ---");
                return;
        }

        messenger.getContacts().addToCommunity(role, newUser);

        UserFileManager.addUserToFile(newUser);

        System.out.println("> User added successfully.");
    }

    private void removeUserFromCommunity() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("[ Enter role of the user to remove (Student/Teacher/Admin) ]: ");
        String roleString = scanner.nextLine();

        Role role;
        try {
            role = Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("--- Invalid role ---");
            return;
        }

        System.out.print("[ Enter first name of the user ]: ");
        String firstName = scanner.nextLine();

        System.out.print("[ Enter last name of the user ]: ");
        String lastName = scanner.nextLine();

        System.out.print("[ Enter email of the user ]: ");
        String email = scanner.nextLine();

        User userToRemove = messenger.getContacts().findUserByRoleAndName(role, firstName, lastName, email);

        if (userToRemove != null) {
            messenger.getContacts().removeFromCommunity(role, userToRemove);
            saveUsersWithStringKeys();
            System.out.println("> User removed successfully.");
        }

        else
            System.out.println("--- User not found ---");
    }

    private void saveUsersWithStringKeys() {
        Map<String, User> usersWithStringKeys = new HashMap<>();

        for (Map.Entry<Role, List<User>> entry : messenger.getContacts().getCommunity().entrySet()) {
            String roleAsString = entry.getKey().toString();
            List<User> users = entry.getValue();

            for (User user : users)
                usersWithStringKeys.put(user.getEmail(), user);
        }

        UserFileManager.saveUsersToFile(usersWithStringKeys);
    }

    private void addStudentToTeacherFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("[ Enter student's first name ]: ");
        String studentFirstName = scanner.nextLine();

        System.out.print("[ Enter student's last name ]: ");
        String studentLastName = scanner.nextLine();

        Student student = UserFileManager.findStudentInUsersFile(studentFirstName, studentLastName);

        if (student == null) {
            System.out.println("--- Error: The student does not exist in the system. Please add the student first ---");
            return;
        }

        User currentUser = loginPage.getCurrentUser();
        if (currentUser instanceof Teacher) {
            Teacher teacher = (Teacher) currentUser;

            if (TeacherFileManager.isStudentInTeacherFile(teacher, student))
                System.out.println("> This student is already added under this teacher.");

            else {
                TeacherFileManager.addStudentToTeacherFile(teacher, student);
                System.out.println("> Student added successfully to " + teacher.getFirstName() + " " + teacher.getSurname() + "'s file.");
            }
        }

        else
            System.out.println("Only teachers can add students to their file.");
    }

    private void viewSentMessages() {
        User currentUser = loginPage.getCurrentUser();
        List<Messege> sentMessages = MessageFileManager.loadSentMessagesFromFile(currentUser);

        if (sentMessages.isEmpty())
            System.out.println("> No sent messages.");

        else {
            for (Messege message : sentMessages) {
                System.out.println("[ " + message.getSenderRole() + ": " + message.getSenderFirstName() + " " + message.getSenderLastName() + " ] -> " +
                        "[ " + message.getReceiverRole() + ": " + message.getReceiverFirstName() + " " + message.getReceiverLastName() + " ]");
                System.out.println("Subject: " + message.getSubject());
                System.out.println(message.getMessege());
                System.out.println();
            }
        }
    }

    private void viewReceivedMessages() {
        User currentUser = loginPage.getCurrentUser();
        List<Messege> receivedMessages = MessageFileManager.loadMessagesFromFile(currentUser);

        if (receivedMessages.isEmpty())
            System.out.println("[ No received messages ]");

        else {
            for (Messege message : receivedMessages) {
                System.out.println("[ " + message.getSenderRole() + ": " + message.getSenderFirstName() + " " + message.getSenderLastName() + " ]");
                System.out.println(message.getSubject());
                System.out.println(message.getMessege());
                System.out.println();
            }
        }
    }

    private void manageStudentNotes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[ Enter student's first name ]: ");
        String firstName = scanner.nextLine();
        System.out.print("[ Enter student's last name ]: ");
        String lastName = scanner.nextLine();

        User currentUser = loginPage.getCurrentUser();
        if (currentUser instanceof Teacher) {
            Teacher teacher = (Teacher) currentUser;

            if (!TeacherFileManager.isStudentUnderTeacher(teacher, firstName, lastName)) {
                System.out.println("--- Error: This student is not assigned to you ---");
                return;
            }

            Student student = UserFileManager.findStudentInUsersFile(firstName, lastName);

            if (student != null) {
                System.out.println("[1] Add Note");
                System.out.println("[2] Edit Last Note");
                System.out.println("[3] Remove Last Note");
                System.out.print("[ Choose an option ]: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("[ Enter note to add] : ");
                        int noteToAdd = scanner.nextInt();
                        GradeFileManager.saveGrade(student, teacher.getFirstName() + " " + teacher.getSurname(), noteToAdd);
                        System.out.println("> Note added successfully.");
                        break;

                    case 2:
                        System.out.print("[ Enter new note ]: ");
                        int newNote = scanner.nextInt();
                        GradeFileManager.editLastGrade(student, teacher.getFirstName() + " " + teacher.getSurname(), newNote);
                        System.out.println("> Note edited successfully.");
                        break;

                    case 3:
                        GradeFileManager.removeLastGrade(student, teacher.getFirstName() + " " + teacher.getSurname());
                        System.out.println("> Last note removed successfully.");
                        break;

                    default:
                        System.out.println("--- Invalid option ---");
                }
            }

            else
                System.out.println("--- Student not found in the system ---");
        }

        else
            System.out.println("--- Only teachers can manage student notes ---");
    }

    private void viewGradesOfStudents() {
        User currentUser = loginPage.getCurrentUser();

        if (currentUser instanceof Teacher) {
            Teacher teacher = (Teacher) currentUser;
            Map<String, List<Integer>> gradesByStudent = GradeFileManager.loadGradesByTeacher(teacher);

            if (gradesByStudent.isEmpty())
                System.out.println("--- No grades found for your students ---");

            else {
                for (Map.Entry<String, List<Integer>> entry : gradesByStudent.entrySet()) {
                    String studentName = entry.getKey();
                    List<Integer> grades = entry.getValue();

                    System.out.println("[ " + studentName + " ]: " + grades.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", ")));
                    System.out.println();
                }
            }
        }

        else
            System.out.println("--- Only teachers can view grades of students ---");
    }

    private void viewMyGrades() {
        User currentUser = loginPage.getCurrentUser();

        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            Map<String, List<Integer>> gradesByTeacher = GradeFileManager.loadGradesForStudent(student);

            if (gradesByTeacher.isEmpty())
                System.out.println("--- No grades found for you ---");

            else {
                System.out.println("> Your grades:");
                for (Map.Entry<String, List<Integer>> entry : gradesByTeacher.entrySet()) {
                    String teacherName = entry.getKey();
                    List<Integer> grades = entry.getValue();
                    System.out.println("- From" + teacherName + ": " + grades.toString().replace("[", "").replace("]", ""));
                }
            }
        }

        else
            System.out.println("--- This option is only available for students ---");
    }
}
