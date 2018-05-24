import java.util.*;
import java.lang.*;

class Attempt {
    private String currentState;
    private ArrayList way;

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
                        {{"MG", "MG"}, {"", "MWGC"}}
                };
        Map<String[], String[]> transitions = new HashMap<>();
        for (String[][] m : map) {
            transitions.put(m[0], m[1]);
        }
    }

    public Attempt(String initialState) {
        currentState = initialState;
        way = new ArrayList();
    }

//    public ArrayList shortestWay() {
//
//    }

    public void printWay() {
        this.shortestWay();

        for (ArrayList w : this.way) {
            System.out.println(w[0] + "===>" + w[1] + "===>"w[2]);
        }
    }
}

class Lab1 {
    public static void main(String[] args) throws java.lang.Exception {
        attempt = new Attempt("MWGC");
        attempt.printWay();
    }
}
