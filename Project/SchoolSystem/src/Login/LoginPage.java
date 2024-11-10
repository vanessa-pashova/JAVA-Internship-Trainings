package Login;

import SchoolDetails.Departments;
import SchoolDetails.Major;
import Users.Admin;
import Users.Student;
import Users.Teacher;
import Users.User;
import Messenger.Messenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginPage {
    private HashMap<String, User> userDatabase;
    private User currentUser;
    private Messenger messenger;

    public LoginPage() {
        this.userDatabase = new HashMap<>();
        this.currentUser = null;
        this.messenger = new Messenger();
    }

    public void addUserToDatabase(User user) {
        if (user != null && user.getEmail() != null)
            userDatabase.put(user.getEmail(), user);
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[ Email ]: ");
        String inputEmail = scanner.nextLine().trim();
        System.out.print("[ Password ]: ");
        String inputPassword = scanner.nextLine().trim();

        Map<String, User> users = UserFileManager.loadUsersFromFile();

        User user = users.get(inputEmail);
        if (user != null && user.getPassword().equals(inputPassword)) {
            System.out.println("> Login successful! Welcome, " + user.getFirstName() + " " + user.getSurname());
            currentUser = user;
            return true;
        }

        else {
            System.out.println("--- Invalid email or password ---");
            return false;
        }
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("> Goodbye, " + currentUser.getFirstName() + "!");
            currentUser = null;
        }

        else
            System.out.println("--- No user is logged in ---");
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void showCurrentUserProfile() {
        if (currentUser != null)
            currentUser.viewProfile();

        else
            throw new IllegalStateException("--- No user is logged in ---");
    }
}