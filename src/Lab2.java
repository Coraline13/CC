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
}

public class Lab2 {
    public static void main(String[] args) {
        String currentString;
        String num1, num2;
        int number1, number2, sum;

        num1 = JOptionPane.showInputDialog("num1");
        number1 = Integer.parseInt(num1);

        num2 = JOptionPane.showInputDialog("num2");
        number2 = Integer.parseInt(num2);

        sum = number1 + number2;

        JOptionPane.showMessageDialog(null, "the sum is : " + sum, "Results", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }
}
