import java.util.*;
import java.lang.*;

enum Thing {
    MAN, WOLF, GOAT, CABBAGE
}

class MWGC {
    private EnumSet<Thing> state;
    private ArrayList<List<EnumSet<Thing>>> way;
    private static HashMap<EnumSet<Thing>, List<EnumSet<Thing>>> transitions = new HashMap<>();

    static {
        transitions.put(EnumSet.allOf(Thing.class), Arrays.asList(EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.WOLF, Thing.CABBAGE), Arrays.asList(EnumSet.of(Thing.MAN)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.WOLF, Thing.CABBAGE), Arrays.asList(
                EnumSet.of(Thing.MAN, Thing.CABBAGE), EnumSet.of(Thing.MAN, Thing.WOLF)));
        transitions.put(EnumSet.of(Thing.WOLF), Arrays.asList(EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.CABBAGE), Arrays.asList(EnumSet.of(Thing.MAN, Thing.GOAT)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.WOLF, Thing.GOAT), Arrays.asList(EnumSet.of(Thing.MAN, Thing.WOLF)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.GOAT, Thing.CABBAGE), Arrays.asList(EnumSet.of(Thing.MAN, Thing.CABBAGE)));
        transitions.put(EnumSet.of(Thing.GOAT), Arrays.asList(EnumSet.of(Thing.MAN)));
        transitions.put(EnumSet.of(Thing.MAN, Thing.GOAT), Arrays.asList(EnumSet.of(Thing.MAN, Thing.GOAT)));
    }

    public MWGC(EnumSet<Thing> initialState) {
        state = initialState;
        way = new ArrayList<>();
    }

    public ArrayList<List<EnumSet<Thing>>> shortestWay() {
        List<EnumSet<Thing>> possibleTransition = transitions.get(state);
        for (EnumSet<Thing> boat : possibleTransition) {
            state = EnumSet.copyOf(state);
            if (state.contains(Thing.MAN)) {
                state.removeAll(boat);
            } else {
                state.addAll(boat);
            }

            EnumSet<Thing> leftSide = state;
            EnumSet<Thing> rightSide = EnumSet.complementOf(state);
            way.add(Arrays.asList(boat, leftSide, rightSide));

            if (leftSide.isEmpty()) {
                return way;
            }

            return shortestWay();
        }
        return null;
    }

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

    public void printWay() {
        int counter = 1;
        shortestWay();

        System.out.print("state 0: MWGC --------- ");

        for (List<EnumSet<Thing>> w : way) {
            if (counter % 2 == 0) {
                System.out.print(" (<= " + conversion(w.get(0)) + " <=)\nstate " + counter + ": " + conversion(w.get(1)) + " --------- " + conversion(w.get(2)));
            } else {
                System.out.print(" (=> " + conversion(w.get(0)) + " =>)\nstate " + counter + ": " + conversion(w.get(1)) + " --------- " + conversion(w.get(2)));
            }
            counter++;
        }
    }
}

public class Lab1 {
    public static void main(String[] args) {
        MWGC attempt = new MWGC(EnumSet.allOf(Thing.class));
        attempt.printWay();
    }
}
