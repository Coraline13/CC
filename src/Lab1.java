import java.util.*;
import java.lang.*;

class Attempt {
    private String currentState;
    private ArrayList<String[]> way;

    static {
        String[][][] map =
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
        Map<String[], String[]> transitions = new HashMap<>();
        for (String[][] m : map) {
            transitions.put(m[0], m[1]);
        }
    }

    public Attempt(String initialState) {
        currentState = initialState;
        way = new ArrayList<>();
    }

//    public ArrayList shortestWay() {
//
//    }

    public void printWay() {
        int counter = 1;
        //this.shortestWay();

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
