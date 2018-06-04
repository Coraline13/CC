//The aim of this programming assignment is to write a program that given a regular language described by
//a regular expression determines the corresponding deterministic finite automaton that recognizes it.

package Lab6;

import java.lang.*;
import java.util.*;
import javax.swing.*;


import javafx.util.Pair;

enum States {
    STATE0, STATE1, STATE2;

    public boolean isTransitionComplete(String input) {
        switch (this) {
            case STATE0:
                return false;
            case STATE1:
                return input.equals("(a|b)");
            case STATE2:
                return input.equals("(ab)");
            default:
                return true;
        }
    }
}

class RegularExpressionToDFA {
    private String input;

    public HashMap<Pair<States, Character>, EnumSet<States>> conversion() {
        HashMap<Pair<States, Character>, EnumSet<States>> tmp = new HashMap<>();

        if (input.equals("(ab)")) {
            tmp.put(new Pair<>(States.STATE0, 'a'), EnumSet.of(States.STATE1));
            tmp.put(new Pair<>(States.STATE1, 'b'), EnumSet.of(States.STATE2));
        } else {
            tmp.put(new Pair<>(States.STATE0, 'a'), EnumSet.of(States.STATE1));
            tmp.put(new Pair<>(States.STATE0, 'b'), EnumSet.of(States.STATE1));
        }

        return tmp;
    }

    // GUI
    public void showMessage() {
        // show info
        JOptionPane.showMessageDialog(null,
                "This is a simulation of a conversion from a regular language described by a regular expression\n" +
                        "to a deterministic finite automaton that recognizes it.");

        String[] possibilities = {"(ab)", "(a|b)"};
        input = (String) JOptionPane.showInputDialog(
                null,
                "Choose one of the following regular expressions:",
                "Choose option",
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibilities,
                "(ab)");

        // if Cancel
        if (input == null) {
            return;
        }

        String result = "";
        HashMap<Pair<States, Character>, EnumSet<States>> results = conversion();
        Iterator it = results.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Pair<States, Character> key = (Pair<States, Character>) pair.getKey();
            result = result + key.getKey() + " + input: " + key.getValue() + " => " + pair.getValue() + "\n";
            it.remove();
        }

        // show result
        JOptionPane.showMessageDialog(null,
                result,
                "Result",
                JOptionPane.PLAIN_MESSAGE);
    }
}

public class Lab6 {
    public static void main(String[] args) {
        RegularExpressionToDFA attempt = new RegularExpressionToDFA();
        attempt.showMessage();
    }
}
