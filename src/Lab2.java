import java.util.*;
import java.lang.*;

import javafx.util.Pair;

import javax.swing.JOptionPane;

// Example 1.11 from the textbook

enum Symbol {
    A, B
}

enum TransitionState {
    STATE0, STATE1, STATE2, STATE3, STATE4;

    public boolean isTransitionComplete() {
        switch (this) {
            case STATE0:
                return false;
            case STATE1:
                return true;
            case STATE2:
                return false;
            case STATE3:
                return true;
            case STATE4:
                return false;
            default:
                return true;
        }
    }
}

class Aba {
    private TransitionState currentState;
    private String entireString;
    private ArrayList<TransitionState> way;
    private static HashMap<Pair<TransitionState, Symbol>, TransitionState> transitions = new HashMap<>();

    // transition diagram (contains all legal moves and all reachable states)
    static {
        transitions.put(new Pair<>(TransitionState.STATE0, Symbol.A), TransitionState.STATE1);
        transitions.put(new Pair<>(TransitionState.STATE0, Symbol.B), TransitionState.STATE3);

        transitions.put(new Pair<>(TransitionState.STATE1, Symbol.A), TransitionState.STATE1);
        transitions.put(new Pair<>(TransitionState.STATE1, Symbol.B), TransitionState.STATE2);

        transitions.put(new Pair<>(TransitionState.STATE2, Symbol.A), TransitionState.STATE1);
        transitions.put(new Pair<>(TransitionState.STATE2, Symbol.B), TransitionState.STATE2);

        transitions.put(new Pair<>(TransitionState.STATE3, Symbol.A), TransitionState.STATE4);
        transitions.put(new Pair<>(TransitionState.STATE3, Symbol.B), TransitionState.STATE3);

        transitions.put(new Pair<>(TransitionState.STATE4, Symbol.A), TransitionState.STATE4);
        transitions.put(new Pair<>(TransitionState.STATE4, Symbol.B), TransitionState.STATE3);
    }

    /**
     * constructor
     */
    public Aba() {
        currentState = TransitionState.STATE0;
        entireString = "";
        way = new ArrayList<>();
    }

    public TransitionState transition(Symbol sym) {
        return transitions.get(new Pair<>(currentState, sym));
    }

    public void showTransitionDialog() {
        String currentString;
        boolean continuing = true;
        int option;

        while (continuing) {
            JOptionPane.showMessageDialog(null,
                    "This finite state automaton has two accept states and operates over the alphabet \u03A3 = {a, b}.\n" +
                            "It accepts strings that start and end with the same symbol.\n\n" +
                            "You can enter strings containing the two symbols 'a' and 'b' (the automaton will ignore any other symbol)\nand see the transitions between the states.",
                    "Welcome!",
                    JOptionPane.PLAIN_MESSAGE);

            currentString = JOptionPane.showInputDialog("Input");

//        JOptionPane.showMessageDialog(null, "the sum is : " + sum, "Results", JOptionPane.PLAIN_MESSAGE);

            if (currentState.isTransitionComplete()) {
                option = JOptionPane.showConfirmDialog(
                        null,
                        "You arrived in one of the two accept states.\n" +
                                "Do you want to continue?",
                        "Continue to input strings",
                        JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    continuing = false;
                }
            }
        }
    }
}

public class Lab2 {
    public static void main(String[] args) {
        // initial state is STATE0
        Aba attempt = new Aba();
        attempt.showTransitionDialog();
    }
}
