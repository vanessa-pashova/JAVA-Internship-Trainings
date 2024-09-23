package Messenger;

import SchoolDetails.Role;

import java.util.Scanner;

public class Messege {
    private String subject;
    private String messegeContent;
    private Role senderRole;
    private String senderFirstName;
    private String senderLastName;
    private Role receiverRole;
    private String receiverFirstName;
    private String receiverLastName;

    public Messege(String subject, String messageContent, Role senderRole, String senderFirstName, String senderLastName, Role receiverRole, String receiverFirstName, String receiverLastName) {
        this.subject = subject;
        this.messegeContent = messageContent;
        this.senderRole = senderRole;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.receiverRole = receiverRole;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
    }

    public Messege() {
        this.messegeContent = "";
    }

    public String getMessege() {
        return messegeContent;
    }

    public Role getReceiverRole() {
        return receiverRole;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public String getReceiverLastName() {
        return receiverLastName;
    }

    public String getSubject() {
        return subject;
    }

    public Role getSenderRole() {
        return senderRole;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void validMessege(String messege) {
        if(messege == null || messege.isEmpty())
            throw new IllegalArgumentException("--- Invalid or empty message ---\n");
    }
}