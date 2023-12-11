import java.io.*;

import static java.lang.Character.isDigit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        int res = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            line = replaceWrittenNumbersWithNumbers(line);
            char[] nums = line.toCharArray();
            Integer firstNumber = null;
            Integer lastNumber = null;
            for (char num : nums) {
                if (isDigit(num)) {
                    int number = Integer.parseInt(String.valueOf(num));
                    if (firstNumber == null) {
                        firstNumber = number;
                    }
                    lastNumber = number;
                }
            }
            res += Integer.parseInt("" + firstNumber + lastNumber);
        }

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
    }

    private static String replaceWrittenNumbersWithNumbers(String line) {
        line = line.replace("eight", "eeightt").replace("eight", "8");
        line = line.replace("five", "ffivee").replace("five", "5");
        line = line.replace("four", "ffourr").replace("four", "4");
        line = line.replace("nine", "nninee").replace("nine", "9");
        line = line.replace("one", "oonee").replace("one", "1");
        line = line.replace("seven", "ssevenn").replace("seven", "7");
        line = line.replace("six", "ssixx").replace("six", "6");
        line = line.replace("three", "tthreee").replace("three", "3");
        line = line.replace("two", "ttwoo").replace("two", "2");
        return line;
    }
}