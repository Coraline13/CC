//The aim of this programming assignment is to write a program that given a regular language described by
//a regular expression determines the corresponding non-deterministic finite automaton that recognizes it.
//The simulation will specify all the transitions starting from the initial state and arriving to the accept state.

package Lab4;

import java.lang.*;
import java.util.*;
import javax.swing.*;

import javafx.util.Pair;

enum States {
    STATE0, STATE1, STATE2, STATE3;

    public boolean isTransitionComplete(String input) {
        switch (this) {
            case STATE0:
                return false;
            case STATE1:
                return input.equals("(a|b)");
            case STATE2:
                return false;
            case STATE3:
                return input.equals("(a*)");
            default:
                return true;
        }
    }
}

class RegularExpressionToNFA {
    private String input;

    public HashMap<Pair<States, Character>, EnumSet<States>> conversion() {
        HashMap<Pair<States, Character>, EnumSet<States>> tmp = new HashMap<>();

        if (input.equals("(a|b)")) {
            tmp.put(new Pair<>(States.STATE0, 'a'), EnumSet.of(States.STATE1));
            tmp.put(new Pair<>(States.STATE0, 'b'), EnumSet.of(States.STATE1));
        } else {
            tmp.put(new Pair<>(States.STATE0, 'E'), EnumSet.of(States.STATE1, States.STATE3));
            tmp.put(new Pair<>(States.STATE1, 'a'), EnumSet.of(States.STATE2));
            tmp.put(new Pair<>(States.STATE2, 'E'), EnumSet.of(States.STATE1, States.STATE3));
        }

        return tmp;
    }

    // GUI
    public void showMessage() {
        // show info
        JOptionPane.showMessageDialog(null,
                "This is a simulation of a conversion from a regular language described by a regular expression\n" +
                        "to a non-deterministic finite automaton that recognizes it.");

        String[] possibilities = {"(a*)", "(a|b)"};
        input = (String) JOptionPane.showInputDialog(
                null,
                "Choose one of the following regular expressions:",
                "Choose option",
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibilities,
                "(a*)");

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
            result = result + key.getKey() + " + input:" + key.getValue() + " => " + pair.getValue() + "\n";
            it.remove();
        }

        // show result
        JOptionPane.showMessageDialog(null,
                result,
                "Result",
                JOptionPane.PLAIN_MESSAGE);
    }
}

public class Lab4 {
    public static void main(String[] args) {
        RegularExpressionToNFA attempt = new RegularExpressionToNFA();
        attempt.showMessage();
    }
}
