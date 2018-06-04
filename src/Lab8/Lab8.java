//The aim of this programming assignment is to write a program that simulates a Turing Machine
//which accepts the language L = {a^n b^n c^n | n ≥ 1}.

package Lab8;

import javax.swing.*;
import java.lang.*;

enum Move {
    LEFT, RIGHT
}

enum States {
    STATE0, STATE1, STATE2, STATE3, STATE4, STATE5, STATE6, STATE7, STATE8, STATE9, ACCEPT, REJECT;

    public ReturnTuple transition(Character currentSymbol) {
        switch (this) {
            case STATE0:
                if (currentSymbol.equals('a')) {
                    return new ReturnTuple('x', Move.RIGHT, STATE1);
                }
                break;
            case STATE1:
                if (currentSymbol.equals('a')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE1);
                } else if (currentSymbol.equals('b')) {
                    return new ReturnTuple('y', Move.RIGHT, STATE2);
                }
                break;
            case STATE2:
                if (currentSymbol.equals('b')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE2);
                } else if (currentSymbol.equals('c')) {
                    return new ReturnTuple('z', Move.RIGHT, STATE3);
                }
                break;
            case STATE3:
                if (currentSymbol.equals('x')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE4);
                } else {
                    return new ReturnTuple(currentSymbol, Move.LEFT, STATE3);
                }
            case STATE4:
                if (currentSymbol.equals('a')) {
                    return new ReturnTuple('x', Move.RIGHT, STATE6);
                } else if (currentSymbol.equals('y')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE5);
                }
                break;
            case STATE5:
                if (currentSymbol.equals(' ')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, ACCEPT);
                } else if (!currentSymbol.equals('b') && !currentSymbol.equals('c')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE5);
                }
                break;
            case STATE6:
                if (currentSymbol.equals('y')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE7);
                } else if (currentSymbol.equals(' ')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, REJECT);
                } else {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE6);
                }
            case STATE7:
                if (currentSymbol.equals('b')) {
                    return new ReturnTuple('y', Move.RIGHT, STATE8);
                } else if (currentSymbol.equals('y')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE7);
                }
                break;
            case STATE8:
                if (currentSymbol.equals('z')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE9);
                } else if (currentSymbol.equals(' ')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, REJECT);
                } else {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE8);
                }
            case STATE9:
                if (currentSymbol.equals('c')) {
                    return new ReturnTuple('z', Move.RIGHT, STATE3);
                } else if (currentSymbol.equals('z')) {
                    return new ReturnTuple(currentSymbol, Move.RIGHT, STATE9);
                }
                break;
            default:
                throw new IllegalStateException();
        }
        return new ReturnTuple(currentSymbol, Move.RIGHT, REJECT);
    }
}

class ReturnTuple {
    private Character sign;
    private Move direction;
    private States state;

    public ReturnTuple(Character sign, Move direction, States state) {
        this.sign = sign;
        this.direction = direction;
        this.state = state;
    }

    public Character getSign() {
        return sign;
    }

    public Move getDirection() {
        return direction;
    }

    public States getState() {
        return state;
    }
}

class TuringMachine {
    String input;
    States currentState;
    int index;
    boolean validInput;

    /**
     * constructor
     */
    public TuringMachine() {
        currentState = States.STATE0;
        index = 1;
    }

    // returns true if the given word is accepted by the automaton
    public boolean isAccepted() {
        ReturnTuple nextState = currentState.transition(input.charAt(index));

        if (nextState.getState().equals(States.ACCEPT)) {
            return true;
        } else if (nextState.getState().equals(States.REJECT)) {
            return false;
        }

        input = input.substring(0, index) + nextState.getSign() + input.substring(index + 1);
        if (nextState.getDirection().equals(Move.LEFT)) {
            index--;
        } else if (nextState.getDirection().equals(Move.RIGHT)) {
            index++;
        }

        currentState = nextState.getState();

        return isAccepted();
    }

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
        input = " " + input + " ";

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
