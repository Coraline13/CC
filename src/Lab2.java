import java.util.*;
import java.lang.*;

import javafx.util.Pair;

import javax.swing.*;

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

    public ImageIcon getImage() {
        switch (this) {
            case STATE0:
                return new ImageIcon(Aba.class.getResource("/state0.png"));
            case STATE1:
                return new ImageIcon(Aba.class.getResource("/state1.png"));
            case STATE2:
                return new ImageIcon(Aba.class.getResource("/state2.png"));
            case STATE3:
                return new ImageIcon(Aba.class.getResource("/state3.png"));
            case STATE4:
                return new ImageIcon(Aba.class.getResource("/state4.png"));
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case STATE0:
                return "0";
            case STATE1:
                return "1";
            case STATE2:
                return "2";
            case STATE3:
                return "3";
            case STATE4:
                return "4";
            default:
                return null;
        }
    }
}

class Aba {
    private TransitionState currentState;
    private String currentString;
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
        way.add(currentState);
    }

    public TransitionState transition(Symbol sym) {
        return transitions.get(new Pair<>(currentState, sym));
    }

    public void showTransitionDialog() {
        boolean continuing = true;
        int option;

        JOptionPane.showMessageDialog(null,
                "This finite state automaton has two accept states and operates over the alphabet \u03A3 = {a, b}.\n" +
                        "It accepts strings that start and end with the same symbol.\n\n" +
                        "You can enter strings containing the two symbols 'a' and 'b' (the automaton will ignore any other symbol)\n" +
                        "and see the transitions between the states.");

        while (continuing) {
            currentString = JOptionPane.showInputDialog("Input");

            // if Cancel
            if (currentString == null) {
                return;
            }

            // for each symbol in input string
            for (int i = 0; i < currentString.length(); i++) {
                char c = currentString.charAt(i);
                Symbol sym;

                if (c == 'a') {
                    sym = Symbol.A;
                } else if (c == 'b') {
                    sym = Symbol.B;
                } else {
                    break;
                }

                currentState = transition(sym);
                way.add(currentState);
            }

            entireString += currentString;

            String unreachableStates = "";
            if (currentState.equals(TransitionState.STATE1) || currentState.equals(TransitionState.STATE2)) {
                unreachableStates += "Unreachable states: 3, 4\n";
            } else if (currentState.equals(TransitionState.STATE3) || currentState.equals(TransitionState.STATE4)) {
                unreachableStates += "Unreachable states: 1, 2\n";
            }

            ImageIcon image = currentState.getImage();
            JOptionPane.showMessageDialog(null,
                    "Last input: " + currentString +
                            "\nEntire input: " + entireString +
                            "\nCurrent state: " + currentState +
                            "\n" + unreachableStates +
                            "Transitions: " + way.toString(),
                    "Results",
                    JOptionPane.PLAIN_MESSAGE,
                    image);

            if (currentState.isTransitionComplete()) {
                option = JOptionPane.showConfirmDialog(
                        null,
                        "You arrived in one of the two accept states.\n" +
                                "Do you want to continue?",
                        "Continue to input strings",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    continuing = false;
                }
            }
        }
    }
}

public class Lab2 {
    public static void main(String[] args) {
        // initial state is state 0
        Aba attempt = new Aba();
        attempt.showTransitionDialog();
    }
}
