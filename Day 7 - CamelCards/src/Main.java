import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String hand;

        List<Hand> hands = new ArrayList<>();

        while ((hand = bufferedReader.readLine()) != null) {
            if(hand.isEmpty()){
                break;
            }
            String[] line = hand.replaceAll("\\s+$", "").split(" ");
            hands.add(new Hand(line[0], Integer.parseInt(line[1])));
        }

        Collections.sort(hands);

        int result = 0;
        for (int i = 0; i < hands.size(); i++) {
            result += (i + 1) * hands.get(i).getValue();
        }
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}