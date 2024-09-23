package SchoolDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Notes {
    private List<Integer> studentNotes;

    public Notes() {
        this.studentNotes = new ArrayList<>();
    }

    public List<Integer> getNotes() {
        return Collections.unmodifiableList(this.studentNotes);
    }

    private boolean isValidNote(int note) {
        return (1 < note && note < 7);
    }

    public void addNote(int note) {
        if(isValidNote(note))
            this.studentNotes.add(note);

        else throw new IllegalArgumentException("--- Illegal note value ---\n");
    }

    public void editLastNote(int note) {
        if(isValidNote(note))
            this.studentNotes.set(this.studentNotes.size() - 1, note);

        else throw new IllegalArgumentException("--- Illegal note value ---\n");
    }

    public void printNotes() {
        for(int note : studentNotes)
            System.out.print(note + " ");

        System.out.println();
    }
}