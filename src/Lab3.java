////The aim of this programming assignment is to write a program that given a non-deterministic finite automaton N and a word w,
////determines if w ∈ L(N). The non-deterministic finite state machine may be an example from the textbook.
////The simulation will also specify all the transitions starting from the initial state and arriving to the accept state.
//
//import javax.swing.*;
//import java.util.*;
//import java.lang.*;
//
//import javafx.util.Pair;
//
//// Example 1.30 from the textbook
//
//enum Symbol {
//    ZERO, ONE
//}
//
//enum TransitionState {
//    STATE1, STATE2, STATE3, STATE4;
//
//    public boolean isTransitionComplete() {
//        switch (this) {
//            case STATE1:
//                return false;
//            case STATE2:
//                return false;
//            case STATE3:
//                return false;
//            case STATE4:
//                return true;
//            default:
//                return true;
//        }
//    }
//
//    @Override
//    public String toString() {
//        switch (this) {
//            case STATE1:
//                return "1";
//            case STATE2:
//                return "2";
//            case STATE3:
//                return "3";
//            case STATE4:
//                return "4";
//            default:
//                return null;
//        }
//    }
//}
//
//class NFA {
//    private TransitionState currentState;
//    private String currentString;
//    private String entireString;
//    private ArrayList<TransitionState> way;
//    private static HashMap<Pair<TransitionState, Symbol>, TransitionState> transitions = new HashMap<>();
//
//    // transition diagram (contains all legal moves and all reachable states)
//    static {
//        transitions.put(new Pair<>(TransitionState.STATE1, Symbol.ZERO), TransitionState.STATE1);
//        transitions.put(new Pair<>(TransitionState.STATE1, Symbol.ONE), TransitionState.STATE2);
//
//        transitions.put(new Pair<>(TransitionState.STATE2, Symbol.ZERO), TransitionState.STATE1);
//        transitions.put(new Pair<>(TransitionState.STATE2, Symbol.ONE), TransitionState.STATE2);
//
//        transitions.put(new Pair<>(TransitionState.STATE3, Symbol.ZERO), TransitionState.STATE4);
//        transitions.put(new Pair<>(TransitionState.STATE3, Symbol.ONE), TransitionState.STATE3);
//
//        transitions.put(new Pair<>(TransitionState.STATE4, Symbol.ZERO), TransitionState.STATE4);
//        transitions.put(new Pair<>(TransitionState.STATE4, Symbol.ONE), TransitionState.STATE3);
//    }
//
//    /**
//     * constructor
//     */
//    public NFA() {
//        currentState = TransitionState.STATE0;
//        entireString = "";
//        way = new ArrayList<>();
//        way.add(currentState);
//    }
//
//    public class Lab3 {
//
//    }
