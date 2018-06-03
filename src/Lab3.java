//The aim of this programming assignment is to write a program that given a non-deterministic finite automaton N and a word w,
//determines if w ∈ L(N). The non-deterministic finite state machine may be an example from the textbook.
//The simulation will also specify all the transitions starting from the initial state and arriving to the accept state.

import javax.swing.*;
import java.util.*;
import java.lang.*;

import javafx.util.Pair;

// Example 1.30 from the textbook

enum Symbol {
    ZERO, ONE
}

enum States {
    STATE1, STATE2, STATE3, STATE4;

    public boolean isTransitionComplete() {
        switch (this) {
            case STATE1:
                return false;
            case STATE2:
                return false;
            case STATE3:
                return false;
            case STATE4:
                return true;
            default:
                return true;
        }
    }

    @Override
    public String toString() {
        switch (this) {
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

class NFA {
    private States currentState;
    private String input;
    private static HashMap<Pair<States, Symbol>, EnumSet<States>> transitions = new HashMap<>();

    // transition diagram (contains all legal moves and all reachable states)
    static {
        transitions.put(new Pair<>(States.STATE1, Symbol.ZERO),
                EnumSet.of(States.STATE1));
        transitions.put(new Pair<>(States.STATE1, Symbol.ONE),
                EnumSet.of(States.STATE1, States.STATE2));

        transitions.put(new Pair<>(States.STATE2, Symbol.ZERO),
                EnumSet.of(States.STATE3));
        transitions.put(new Pair<>(States.STATE2, Symbol.ONE),
                EnumSet.of(States.STATE3));

        transitions.put(new Pair<>(States.STATE3, Symbol.ZERO),
                EnumSet.of(States.STATE4));
        transitions.put(new Pair<>(States.STATE3, Symbol.ONE),
                EnumSet.of(States.STATE4));
    }

    /**
     * constructor
     */
    public NFA() {
        currentState = States.STATE1;
    }

    // transits to the next state
    public EnumSet<States> transition(Symbol sym) {
        return transitions.get(new Pair<>(currentState, sym));
    }

    //
    public boolean validWord(String word) {
        if (word == null) {
            return true;
        }

        Symbol sym;
        if (word.charAt(0) == '0') {
            sym = Symbol.ZERO;
        } else if (word.charAt(0) == '1') {
            sym = Symbol.ONE;
        } else {
            return validWord(word.substring(1));
        }

        EnumSet<States> possibleTransitions = transition(sym);

        for (States transition : possibleTransitions) {
            States oldState = currentState;
            currentState = transition;

            return validWord(word.substring(1));
            currentState = oldState;
        }
    }

    // GUI
    public void showMessage() {
        int option;
        boolean contains = true;
        EnumSet<States> possibleTransitions = null;

        // show info
        JOptionPane.showMessageDialog(null,
                "This finite state automaton recognizes the language consisting of all strings over {0, 1} " +
                        "containing a 1\nin the third position from the end (e.g., 000100 is okay, but 0011 is not).\n\n" +
                        "Enter a word to test if the automaton accepts it.\n" +
                        "You can enter strings containing the two symbols '0' and '1' " +
                        "(the automaton will ignore any other symbol).");

        input = JOptionPane.showInputDialog("Input");

        // if Cancel
        if (input == null) {
            return;
        }

        contains = validWord(input);

        // show result
        if (contains) {
            JOptionPane.showMessageDialog(null,
                    "The automaton accepts the given word.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "The automaton does not accept the given word.",
                    "Result",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}

public class Lab3 {
    public static void main(String[] args) {
        // initial state is state 1
        NFA attempt = new NFA();
        attempt.showMessage();
    }
}
