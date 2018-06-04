//The aim of this programming assignment is to write a program that simulates a pushdown automaton
//which accepts the language L = {wcw^R}.

package Lab7;

import javax.swing.*;
import java.util.*;
import java.lang.*;

class PushdownAutomata {
    Stack<Character> currentStack, tmpStack;
    String input;
    boolean validInput;

    /**
     * constructor
     */
    public PushdownAutomata() {
        currentStack = new Stack<>();
        tmpStack = new Stack<>();
    }

    // returns true if the given word is accepted by the automaton
    public boolean isAccepted() {
        // for each symbol in input string
        while (!input.equals("")) {
            Character c = input.charAt(0);
            // if the symbol is already in the current stack, it verifies if it is the beginning of the symmetry
            if (currentStack.search(c) != -1) {
                // it uses a temporary stack to remember the popped symbols
                while (currentStack.peek() != c) {
                    tmpStack.push(currentStack.pop());
                }
                tmpStack.push(currentStack.pop());

                // for each left symbol in input string
                int index;
                for (index = 1; index < input.length() && !currentStack.empty(); index++) {
                    if (!currentStack.peek().equals(input.charAt(index))) {
                        break;
                    }
                    tmpStack.push(currentStack.pop());
                }

                if (index == input.length()) {
                    if (currentStack.empty()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    while (!tmpStack.empty()) {
                        currentStack.push(tmpStack.pop());
                    }
                }
            } else {
                currentStack.push(c);
            }

            // next symbol in input string
            input = input.substring(1);
        }

        return false;
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
