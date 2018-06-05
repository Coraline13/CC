//The aim of this programming assignment is to write a program that given a nondeterministic finite automaton
//determines the corresponding deterministic finite automaton.

package Lab5;

import java.lang.*;
import java.util.*;

class NFAtoDFA {
    private int NFAstates;
    private String[][] NFA;
    private int DFAstates;
    private String[][] DFA;
    private int nSymbols;

    public NFAtoDFA() {
        NFAstates = 5;
        nSymbols = 2;

        NFA = new String[NFAstates][nSymbols];
        NFA[0][0] = "12";
        NFA[0][1] = "13";
        NFA[1][0] = "12";
        NFA[1][1] = "13";
        NFA[2][0] = "4";
        NFA[2][1] = "";
        NFA[3][0] = "";
        NFA[3][1] = "4";
        NFA[4][0] = "4";
        NFA[4][1] = "4";

        DFAstates = 0;
    }

    // prints state-transition table where state names are 'A', 'B', 'C', ...
    void printDFA(int[][] DFA) {
        System.out.println("STATE TRANSITION TABLE");

        // input symbols: '0', '1', ...
        System.out.print("     | ");
        for (int i = 0; i < nSymbols; i++) {
            System.out.print("  ");
            System.out.print((char) ('0' + i));
            System.out.print("  ");
        }

        System.out.print("\n-----+--");
        for (int i = 0; i < nSymbols; i++) {
            System.out.print("-----");
        }
        System.out.print("\n");

        for (int i = 0; i < DFA.length; i++) {
            System.out.print("  ");
            System.out.print((char) ('A' + i));
            System.out.print("  | ");
            for (int j = 0; j < nSymbols; j++) {
                System.out.print("  ");
                System.out.print((char) ('A' + DFA[i][j]));
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

    String merge(String s1, String s2) {
        Set<Integer> resultSet = new HashSet<>();
        resultSet.addAll(Arrays.asList(s1.chars().boxed().toArray(Integer[]::new)));
        resultSet.addAll(Arrays.asList(s2.chars().boxed().toArray(Integer[]::new)));

        List<Integer> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result.stream().collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String getNextState(String curStates, String[][] nfa, int symbol) {
        String result = "";

        for (char state : curStates.toCharArray())
            result = merge(result, nfa[state - '0'][symbol]);
        return result;
    }

    private int stateIndex(String stateName, ArrayList<String> stateNames) {
        if (stateName == null || stateName.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < stateNames.size(); ++i) {
            if (stateNames.get(i).equals(stateName)) {
                return i;
            }
        }

        stateNames.add(stateName);
        return stateNames.size() - 1;
    }

    private int[][] conversion(String[][] nfa) {
        ArrayList<String> stateNames = new ArrayList<>();
        ArrayList<int[]> dfa = new ArrayList<>();

        stateNames.add("0");

        for (int stateIdx = 0; stateIdx < stateNames.size(); stateIdx++) {
            for (int symbolId = 0; symbolId < nfa[0].length; symbolId++) {
                String nextState = getNextState(stateNames.get(stateIdx), nfa, symbolId);
                int[] newStateSymbols = new int[nfa[0].length];
                dfa.add(newStateSymbols);
                newStateSymbols[symbolId] = stateIndex(nextState, stateNames);
            }
        }

        return dfa.toArray(new int[dfa.size()][]);
    }

    public void showMessage() {
        printDFA(conversion(NFA));
    }
}

public class Lab5 {
    public static void main(String[] args) {
        NFAtoDFA attempt = new NFAtoDFA();
        attempt.showMessage();
    }
}
