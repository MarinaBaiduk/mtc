import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

 class Fsm {
    private ArrayList<Integer> finalStates = new ArrayList<>();
    private HashMap<Integer, HashMap<Character, Integer>> rules = new HashMap<>();
     Fsm(String fileName) throws FsmExcepton, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        while ((line = reader.readLine()) != null && line.startsWith("/")) {
        }
        for (String number : line.replace("\n", "").split(" ")) {
            try {
                int i = Integer.valueOf(number);
                if (i < 1) {
                    throw new FsmExcepton("bad file format");
                }
            } catch (NumberFormatException e) {
                throw new FsmExcepton("bad file format");
            }
            finalStates.add(Integer.valueOf(number));
        }
        if (finalStates.isEmpty()) {
            throw new FsmExcepton("bad file format (empty final states)");
        }

        while ((line = reader.readLine()) != null) {
            String[] tmp = line.replace("\n", "").split(" ");
            if (tmp.length != 3) {
                throw new FsmExcepton("bad file format");
            }
            int startState;
            char symbol;
            int endState;
            try {
                startState = Integer.valueOf(tmp[0]);
                symbol = tmp[1].toCharArray()[0];
                endState = Integer.valueOf(tmp[2]);
                if (startState < 1 || endState < 1) {
                    throw new FsmExcepton("bad file format (incorrect start or end symbol)");
                }
                if (!Character.isLetter(symbol)) {
                    throw new FsmExcepton("bad file format (incorrect symbol)");
                }
            } catch (NumberFormatException e) {
                throw new FsmExcepton("bad file format");
            }

            if (!rules.containsKey(startState)) {
                rules.put(startState, new HashMap<>());
            }
            rules.get(startState).put(symbol, endState);
        }
        if (rules.isEmpty()) {
            throw new FsmExcepton("empty rules");
        }
    }

     boolean checkSequence(String str) throws FsmExcepton {
        int current = 1;
        for (Character c : str.toCharArray()) {
            if (!rules.get(current).containsKey(c)) {
                throw new FsmExcepton("Unexpected transition");
            }
            current = rules.get(current).get(c);
        }
        return finalStates.contains(current);
    }
}