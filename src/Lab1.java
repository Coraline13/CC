import java.util.*;
import java.lang.*;

class State
{
    private String value;
    private static String[][][] map =
            {
                    {{"MWGC", "MG"}, {"WC", "MG"}},
                    {{"WC", "M"}, {"MWC", "G"}},
                    {{"MWC", "MC"}, {"W", "MGC"}},
                    {{"MWC", "MW"}, {"C", "MWG"}},
                    {{"W", "MG"}, {"MWG", "C"}},
                    {{"C", "MG"}, {"MGC", "W"}},
                    {{"MWG", "MW"}, {"G", "MWC"}},
                    {{"MGC", "MC"}, {"G", "MWC"}},
                    {{"G", "M"}, {"MG", "WC"}},
                    {{"MG", "MG"}, {".", "MWGC"}}
            };
    private static Map<String[], String[]> createMap() {
        Map<String[], String[]> transitions = new HashMap<>();
        for (String[][] m : map) {
            transitions.put(m[0], m[1]);
        }
        return transitions;
    }

    public State(String initialState) {
        value = initialState;
        Map<String[], String[]> transitions = createMap();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

class MWGC
{
    private State currentState;
    private ArrayList<String[]> way;
    private static String[] boat = {"M", "MW", "MG", "MC"};

    public MWGC(String initialState) {
        currentState = new State(initialState);
        way = new ArrayList<>();
    }

    private boolean validTransition(String[] current_key) {
        return transitions.containsKey(current_key);
    }

    private void shortestWay() {
        for (String b : boat) {
            String[] key = {currentState.getValue(), b};

            if (validTransition(key)) {
                String[] value = transitions.get(key);
                String[] tmp = {key[1], value[0], value[1]};
                way.add(tmp);
                currentState.setValue(value[0]);

                if (currentState.getValue().equals(".")) {
                    return;
                }

                this.shortestWay();
            }
        }
    }

    public void printWay() {
        int counter = 1;
        this.shortestWay();

        System.out.print("state 0: MWGC --------- .");

        for (String[] w : this.way) {
            if (counter % 2 == 0) {
                System.out.print(" (<=" + w[0] + "<=)\nstate " + counter + ": " + w[1] + " --------- " + w[2]);
            } else {
                System.out.print(" (=>" + w[0] + "=>)\nstate " + counter + ": " + w[1] + " --------- " + w[2]);
            }
            counter++;
        }
    }
}

class Lab1
{
    public static void main(String[] args) {
        MWGC attempt = new MWGC("MWGC");
        attempt.printWay();
    }
}
