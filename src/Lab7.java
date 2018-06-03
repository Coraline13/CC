//The aim of this programming assignment is to write a program that simulates a pushdown automaton
//which accepts the language L = {wcw^R}.

import javax.swing.*;
import java.util.*;
import java.lang.*;

class PushdownAutomata {
    Stack<Character> tmpStack;
    String input;
    boolean validInput;

    /**
     * constructor
     */
    public PushdownAutomata() {
        tmpStack = new Stack<>();
    }

    public boolean isAccepted() {
        while (!input.equals("")) {
                        
            input = input.substring(1);
        }

        return true;
    }

    // GUI
    public void showMessage() {
        // show info
        JOptionPane.showMessageDialog(null,
                "This is a simulation of a pushdown automaton which accepts the language L = {wcw^R}.\n\n" +
                        "Enter a word to test if the automaton accepts it.");

        input = JOptionPane.showInputDialog("Input");

        // if Cancel
        if (input == null) {
            return;
        }

        validInput = isAccepted();

        // show result
        //ImageIcon image = new ImageIcon(NFA.class.getResource("/automaton.png"));
        if (validInput) {
            JOptionPane.showMessageDialog(null,
                    "The automaton accepts the given word.",
                    "Result",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "The automaton does not accept the given word.",
                    "Result",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}

public class Lab7 {
    public static void main(String[] args) {
        PushdownAutomata attempt = new PushdownAutomata();
        attempt.showMessage();
    }
}
