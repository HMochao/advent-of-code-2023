import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        TreeMap<String, GhostNode> nodes = new TreeMap<>();
        char[] instructions = bufferedReader.readLine().toCharArray();

        bufferedReader.readLine();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String label = line.split(" =")[0];
            String left = line.split("\\(")[1].split(", ")[0];
            String right = line.split(", ")[1].split("\\)")[0];

            nodes.put(label, new GhostNode(label, left, right));
        }

        Set<String> currentLabel = nodes.keySet().stream().filter(k -> k.contains("A")).collect(Collectors.toSet());
        long stepNumber = 0;
        int i = 0;
        List<Long> stepsNumber = new ArrayList<>(currentLabel.size());

//        while (stepNumber < 100000) {
//            if(i == instructions.length) {
//                i = 0;
//            }
//            Iterator<String> iterator = currentLabel.iterator();
//            Set<String> newCurrent = new HashSet<>();
//            String newNode = nodes.get(iterator.next()).getDirection(instructions[i]);
//            if (newNode.contains("Z")) {
//                    stepsNumber.add(stepNumber + 1);
//            }
//
//            newCurrent.add(newNode);
//
//            currentLabel = newCurrent;
//            i++;
//            stepNumber++;
//        }
        while (!currentLabel.isEmpty()) {
            if(i == instructions.length) {
                i = 0;
            }
            Iterator<String> iterator = currentLabel.iterator();
            Set<String> newCurrent = new HashSet<>();
            while (iterator.hasNext()) {
                String newNode = nodes.get(iterator.next()).getDirection(instructions[i]);
                if (newNode.contains("Z")) {
                    stepsNumber.add(stepNumber + 1);
                } else {
                    newCurrent.add(newNode);
                }
            }
            currentLabel = newCurrent;
            i++;
            stepNumber++;
        }

        System.out.println(stepsNumber);


        bufferedWriter.write(String.valueOf(stepNumber));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}