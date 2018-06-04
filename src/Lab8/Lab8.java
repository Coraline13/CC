//The aim of this programming assignment is to write a program that simulates a Turing Machine
//which accepts the language L = {a^n b^n c^n | n ≥ 1}.

package Lab8;

import javax.swing.*;
import java.util.*;
import java.lang.*;

import javafx.util.Pair;

enum Alphabet {

}

enum Move {
    LEFT, RIGHT
}

enum States {
    STATE0, STATE1, STATE2, STATE3, STATE4, STATE5, STATE6, STATE7, STATE8, STATE9, ACCEPT, REJECT;

    public Pair<Character, Move> transition(Character currentSymbol) {
        switch (this) {
            case STATE0:
                if (currentSymbol.equals('a')){
                    return new Pair<>(, Move.RIGHT);
                }
            case STATE1:
                return true;
            case STATE2:
                return false;
            case STATE3:
                return true;
            case STATE4:
                return false;
            case STATE5:
                return false;
            case STATE6:
                return true;
            case STATE7:
                return false;
            case STATE8:
                return true;
            case STATE9:
                return false;
            case ACCEPT:
                return falsa;
            case REJECT:
                ceva;
        }
    }
}

class TuringMachine {
    String input;
    Character currentSymbol;
    boolean validInput;

    /**
     * constructor
     */
    public TuringMachine() {

    }

    // returns true if the given word is accepted by the automaton
//    public boolean isAccepted() {
//        Character c = input.charAt(0);
//
//    }

    // GUI
    public void showMessage() {
        // show info
        JOptionPane.showMessageDialog(null,
                "This is a simulation of a Turing Machine which accepts the language L = {a^n b^n c^n | n ≥ 1}.\n\n" +
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

public class Lab8 {
    public static void main(String[] args) {
        TuringMachine attempt = new TuringMachine();
        attempt.showMessage();
    }
}
