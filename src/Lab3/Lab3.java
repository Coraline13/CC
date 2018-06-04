//The aim of this programming assignment is to write a program that given a non-deterministic finite automaton N and a word w,
//determines if w ∈ L(N). The non-deterministic finite state machine may be an example from the textbook.
//The simulation will also specify all the transitions starting from the initial state and arriving to the accept state.

package Lab3;

import javax.swing.*;
import java.util.*;
import java.lang.*;

import javafx.util.Pair;

// Example 1.30 from the textbook

enum Alphabet {
    ZERO, ONE
}

enum States {
    STATE1, STATE2, STATE3, STATE4
}

class NFA {
    private States currentState;
    private String input;
    private boolean reachedEnd;
    private static HashMap<Pair<States, Alphabet>, EnumSet<States>> transitions = new HashMap<>();

    // transition diagram (reachedEnd all legal moves and all reachable states)
    static {
        transitions.put(new Pair<>(States.STATE1, Alphabet.ZERO),
                EnumSet.of(States.STATE1));
        transitions.put(new Pair<>(States.STATE1, Alphabet.ONE),
                EnumSet.of(States.STATE1, States.STATE2));

        transitions.put(new Pair<>(States.STATE2, Alphabet.ZERO),
                EnumSet.of(States.STATE3));
        transitions.put(new Pair<>(States.STATE2, Alphabet.ONE),
                EnumSet.of(States.STATE3));

        transitions.put(new Pair<>(States.STATE3, Alphabet.ZERO),
                EnumSet.of(States.STATE4));
        transitions.put(new Pair<>(States.STATE3, Alphabet.ONE),
                EnumSet.of(States.STATE4));
    }

    /**
     * constructor
     */
    public NFA() {
        currentState = States.STATE1;
        reachedEnd = false;
    }

    // transits to the next state
    public EnumSet<States> transition(Alphabet sym) {
        return transitions.get(new Pair<>(currentState, sym));
    }

    // verifies if the given word is accepted by the automaton
    public void validWord(String word) {
        if (word.equals("")) {
            if (currentState == States.STATE4) {
                reachedEnd = true;
            }

            return;
        }

        Alphabet sym;
        if (word.charAt(0) == '0') {
            sym = Alphabet.ZERO;
        } else if (word.charAt(0) == '1') {
            sym = Alphabet.ONE;
        } else {
            validWord(word.substring(1));
            return;
        }

        EnumSet<States> possibleTransitions = transition(sym);

        for (States transition : possibleTransitions) {
            States oldState = currentState;
            currentState = transition;

            validWord(word.substring(1));
            currentState = oldState;
        }
    }

    // GUI
    public void showMessage() {
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

        validWord(input);

        // show result
        ImageIcon image = new ImageIcon(NFA.class.getResource("/Lab3/automaton.png"));
        if (reachedEnd) {
            JOptionPane.showMessageDialog(null,
                    "The automaton accepts the given word.",
                    "Result",
                    JOptionPane.PLAIN_MESSAGE,
                    image);
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
