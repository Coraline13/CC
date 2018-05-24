import java.util.*;
import java.lang.*;

class Attempt {
    private String currentState;
    private ArrayList<String[]> way;
    private static Map<String[], String[]> transitions;
    private static String[] boat = {"M", "MW", "MG", "MC"};
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

    public Attempt(String initialState) {
        currentState = initialState;
        way = new ArrayList<>();
        transitions = createMap();
    }

    private boolean validTransition(String[] current_key) {
        return transitions.containsKey(current_key);
    }

    private void shortestWay() {
        for (String b : boat) {
            String[] key = {currentState, b};

            if (validTransition(key)) {
                String[] value = {transitions.get(key)};
                way.add(key[1]);
                way.add(value[0]);
                way.add(value[1]);

                if(currentState.equals(".")){
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

class Lab1 {
    public static void main(String[] args) {
        Attempt attempt = new Attempt("MWGC");
        attempt.printWay();
    }
}
