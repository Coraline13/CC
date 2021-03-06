//Simulate in a language chosen freely the functioning of a finite state automaton described by a regular language.
//The finite state machine may be an example from the textbook. The simulation will specify all the transitions
//starting from the initial state and arriving to the accept state.
//        Suggestions: GUI
//        • Buttons for inputs
//        • State chart with the current state highlighted

package Lab2;

import java.util.*;
import java.lang.*;
import javax.swing.*;

import javafx.util.Pair;

// Example 1.11 from the textbook

enum Alphabet {
    A, B
}

enum States {
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
                return new ImageIcon(Aba.class.getResource("/Lab2/state0.png"));
            case STATE1:
                return new ImageIcon(Aba.class.getResource("/Lab2/state1.png"));
            case STATE2:
                return new ImageIcon(Aba.class.getResource("/Lab2/state2.png"));
            case STATE3:
                return new ImageIcon(Aba.class.getResource("/Lab2/state3.png"));
            case STATE4:
                return new ImageIcon(Aba.class.getResource("/Lab2/state4.png"));
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
    private States currentState;
    private String currentString;
    private String entireString;
    private ArrayList<States> way;
    private static HashMap<Pair<States, Alphabet>, States> transitions = new HashMap<>();

    // transition diagram (contains all legal moves and all reachable states)
    static {
        transitions.put(new Pair<>(States.STATE0, Alphabet.A), States.STATE1);
        transitions.put(new Pair<>(States.STATE0, Alphabet.B), States.STATE3);

        transitions.put(new Pair<>(States.STATE1, Alphabet.A), States.STATE1);
        transitions.put(new Pair<>(States.STATE1, Alphabet.B), States.STATE2);

        transitions.put(new Pair<>(States.STATE2, Alphabet.A), States.STATE1);
        transitions.put(new Pair<>(States.STATE2, Alphabet.B), States.STATE2);

        transitions.put(new Pair<>(States.STATE3, Alphabet.A), States.STATE4);
        transitions.put(new Pair<>(States.STATE3, Alphabet.B), States.STATE3);

        transitions.put(new Pair<>(States.STATE4, Alphabet.A), States.STATE4);
        transitions.put(new Pair<>(States.STATE4, Alphabet.B), States.STATE3);
    }

    /**
     * constructor
     */
    public Aba() {
        currentState = States.STATE0;
        entireString = "";
        way = new ArrayList<>();
        way.add(currentState);
    }

    // transits to the next state
    public States transition(Alphabet sym) {
        return transitions.get(new Pair<>(currentState, sym));
    }

    // GUI
    public void showTransitionDialog() {
        boolean continuing = true;
        int option;

        // show info
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
                Alphabet sym;

                if (c == 'a') {
                    sym = Alphabet.A;
                } else if (c == 'b') {
                    sym = Alphabet.B;
                } else {
                    continue;
                }

                currentState = transition(sym);
                way.add(currentState);
            }

            entireString += currentString;

            String unreachableStates = "";
            if (currentState.equals(States.STATE1) || currentState.equals(States.STATE2)) {
                unreachableStates += "Unreachable states: 3, 4\n";
            } else if (currentState.equals(States.STATE3) || currentState.equals(States.STATE4)) {
                unreachableStates += "Unreachable states: 1, 2\n";
            }

            // show results
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

            // show question if the current state is an accept state
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
