package SchoolDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TeachersExpressions {
    private List<String> teacherExpressions;

    public TeachersExpressions() {
        this.teacherExpressions = new ArrayList<>();
    }

    public List<String> getExpressions() {
        return Collections.unmodifiableList(this.teacherExpressions);
    }

    private boolean isNotValidExpression(String exp) {
        return (exp == null || exp.isEmpty());
    }

    public void addExpression(String exp) {
        if(isNotValidExpression(exp))
            throw new IllegalStateException("--- Expression could not be added ---\n");

        else
            this.teacherExpressions.add(exp);
    }

    public void removeLastExpression() {
        if(this.teacherExpressions == null || this.teacherExpressions.isEmpty())
            throw new IllegalStateException("--- There's no expression to remove ---\n");

        else teacherExpressions.removeLast();
    }

    public void editLastExpression(String exp) {
        if(isNotValidExpression(exp))
            throw new IllegalStateException("--- Expression was not addeed ---\n");

        else
            this.teacherExpressions.set(this.teacherExpressions.size() - 1, exp);
    }

    public void printExpression() {
        int counter = 1;

        for(String exp : this.teacherExpressions) {
            System.out.println(counter + ". " + exp + "\n----------------------------------\n");
            counter++;
        }

        System.out.println();
    }
}