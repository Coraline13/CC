//Write a program, in a language of your choice, to provide the solution(s) of the problem „The man, wolf, goat, cabbage”.
//The problem will be described in terms of a finite automaton.

package Lab1;

import java.util.*;
import java.lang.*;

enum Thing {
    MAN, WOLF, GOAT, CABBAGE
}

class MWGC {
    private EnumSet<Thing> state;
    private ArrayList<List<EnumSet<Thing>>> way;
    private ArrayList<Integer> visited;
    private static HashMap<EnumSet<Thing>, List<EnumSet<Thing>>> transitions = new HashMap<>();

    // transition diagram (contains all legal moves and all reachable states)
    static {
        transitions.put(EnumSet.allOf(Thing.class), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.WOLF, Thing.CABBAGE), Arrays.asList(
                EnumSet.of(Thing.MAN), EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.WOLF, Thing.CABBAGE), Arrays.asList(
                EnumSet.of(Thing.MAN), EnumSet.of(Thing.MAN, Thing.CABBAGE), EnumSet.of(Thing.MAN, Thing.WOLF)));
        transitions.put(EnumSet.of(Thing.WOLF), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.GOAT), EnumSet.of(Thing.MAN, Thing.CABBAGE)));
        transitions.put(EnumSet.of(Thing.CABBAGE), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.GOAT), EnumSet.of(Thing.MAN, Thing.WOLF)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.WOLF, Thing.GOAT), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.WOLF), EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.GOAT, Thing.CABBAGE), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.CABBAGE), EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.GOAT), Arrays.asList(
                EnumSet.of(Thing.MAN), EnumSet.of(Thing.MAN, Thing.CABBAGE), EnumSet.of(Thing.MAN, Thing.WOLF)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.GOAT), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.GOAT), EnumSet.of(Thing.MAN)));
    }

    /**
     * constructor
     *
     * @param initialState is the state given as argument and contains the start state
     */
    public MWGC(EnumSet<Thing> initialState) {
        state = initialState;
        way = new ArrayList<>();
        visited = new ArrayList<>();
    }

    /**
     * finds and outputs all the ways that do not pass multiple times through the same state
     */
    public void shortestWay() {
        if (state.isEmpty()) {
            printWay();
            return;
        }

        List<EnumSet<Thing>> possibleTransitions = transitions.get(state);
        for (EnumSet<Thing> boat : possibleTransitions) {
            EnumSet<Thing> oldState = state;
            EnumSet<Thing> nextState = EnumSet.copyOf(state);

            if (nextState.contains(Thing.MAN)) {
                nextState.removeAll(boat);
            } else {
                nextState.addAll(boat);
            }

            if (visited.contains(nextState.hashCode())) {
                continue;
            }

            state = nextState;
            EnumSet<Thing> rightSide = EnumSet.complementOf(nextState);
            way.add(Arrays.asList(boat, nextState, rightSide));
            visited.add(nextState.hashCode());

            shortestWay();

            way.remove(way.size() - 1);
            visited.remove(Integer.valueOf(nextState.hashCode()));
            state = oldState;
        }
    }

    /**
     * converts from EnumSet to String (for print purposes)
     *
     * @param input is the input EnumSet with the content of the boat
     * @return String containing letters for each Lab1.Thing in the boat
     */
    private String conversion(EnumSet<Thing> input) {
        String str = "";
        if (input.contains(Thing.MAN)) {
            str += "M";
        }
        if (input.contains(Thing.WOLF)) {
            str += "W";
        }
        if (input.contains(Thing.GOAT)) {
            str += "G";
        }
        if (input.contains(Thing.CABBAGE)) {
            str += "C";
        }
        return str;
    }

    /**
     * prints the shortest way found
     */
    public void printWay() {
        int counter = 1;

        System.out.print("state 0: Lab1.MWGC --------- ");

        for (List<EnumSet<Thing>> w : way) {
            if (counter % 2 == 0) {
                System.out.print(" (<= " + conversion(w.get(0)) + " <=)\nstate " + counter + ": " + conversion(w.get(1)) + " --------- " + conversion(w.get(2)));
            } else {
                System.out.print(" (=> " + conversion(w.get(0)) + " =>)\nstate " + counter + ": " + conversion(w.get(1)) + " --------- " + conversion(w.get(2)));
            }
            counter++;
        }
        System.out.println("\n\n");
    }
}

public class Lab1 {
    public static void main(String[] args) {
        // initial state has all Characters on the left side of the river
        MWGC attempt = new MWGC(EnumSet.allOf(Thing.class));
        attempt.shortestWay();
    }
}
