import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> results = new ArrayList<>();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            List<Integer> numbers = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            results.add(getValue(numbers, numbers.get(0)));
        }


        int result = results.stream().reduce(0, Integer::sum);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static int getValue(List<Integer> numbers, int firstNum) {
//        System.out.println(firstNum);
        List<Integer> pattern = new ArrayList<>(numbers.size() - 1);

        for (int i = 0; i < numbers.size() - 1; i++) {
            pattern.add(numbers.get(i + 1) - numbers.get(i));
        }

        if (pattern.stream().allMatch(p -> p == 0)) {
            return firstNum;
        } else {
            return firstNum - getValue(pattern, pattern.get(0));
        }
    }
}