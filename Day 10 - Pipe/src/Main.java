import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        List<char[]> map = new ArrayList<>();
        int i = 0;
        int j = 0;
        int currentLine = 0;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String nums = line.replaceAll("\\s+$", "");
            if (nums.contains("S")) {
                i = currentLine + 1;
                j = nums.indexOf("S");
            }
            map.add(nums.toCharArray());
            currentLine++;
        }
        int result = getPartOneResult(map, i, j);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static int getPartOneResult(List<char[]> map, int i, int j) {
        char finish = '.';
        int stepNumber = 0;
        boolean lastPositionHorizontal = false;
        boolean goDown = true;
        boolean goRight = false;
        while (finish != 'S') {
            finish = map.get(i)[j];
            ++stepNumber;
            switch (map.get(i)[j]) {
                case '|':
                    i = goDown ? i + 1 : i - 1;
                    lastPositionHorizontal = false;
                    break;
                case '-':
                    j = goRight ? j + 1 : j - 1;
                    lastPositionHorizontal = true;
                    break;
                case 'L':
                    goRight = !lastPositionHorizontal;
                    goDown = false;
                    i = lastPositionHorizontal ? i - 1 : i;
                    j = lastPositionHorizontal ? j : j + 1;
                    lastPositionHorizontal = !lastPositionHorizontal;
                    break;
                case 'J':
                    goRight = false;
                    goDown = false;
                    i = lastPositionHorizontal ? i - 1 : i;
                    j = lastPositionHorizontal ? j : j - 1;
                    lastPositionHorizontal = !lastPositionHorizontal;
                    break;
                case '7':
                    goRight = false;
                    goDown = lastPositionHorizontal;
                    i = lastPositionHorizontal ? i + 1 : i;
                    j = lastPositionHorizontal ? j : j - 1;
                    lastPositionHorizontal = !lastPositionHorizontal;
                    break;
                case 'F':
                    goRight = !lastPositionHorizontal;
                    goDown = lastPositionHorizontal;
                    i = lastPositionHorizontal ? i + 1 : i;
                    j = lastPositionHorizontal ? j : j + 1;
                    lastPositionHorizontal = !lastPositionHorizontal;
                    break;
                case 'S':
                    break;
                default:
                    break;
            }
        }

        return stepNumber / 2;
    }

    // last i: 118 last j 88 i: 118 j: 87 step number : 4350
    private static int findPath(int i, int j, int lastI, int lastJ, List<char[]> map, int stepNumber, boolean lastPositionHorizontal) {
        System.out.println("last i: " + lastI + " last j " + lastJ + " i: " + i + " j: " + j + " step number : " + stepNumber);
        switch (map.get(i)[j]) {
            case '|':
                return findPath(i > lastI ? i + 1 : i - 1, j, i, j, map, ++stepNumber, false);
            case '-':
                return findPath(i, j > lastJ ? j + 1 : j - 1, i, j, map, ++stepNumber, true);
            case 'L':
                return findPath(lastPositionHorizontal ? i - 1 : i, lastPositionHorizontal ? j : j + 1, i, j, map, ++stepNumber, !lastPositionHorizontal);
            case 'J':
                return findPath(lastPositionHorizontal ? i - 1 : i, lastPositionHorizontal ? j : j - 1, i, j, map, ++stepNumber, !lastPositionHorizontal);
            case '7':
                return findPath(lastPositionHorizontal ? i + 1 : i, lastPositionHorizontal ? j : j - 1, i, j, map, ++stepNumber, !lastPositionHorizontal);
            case 'F':
                return findPath(lastPositionHorizontal ? i + 1 : i, lastPositionHorizontal ? j : j + 1, i, j, map, ++stepNumber, !lastPositionHorizontal);
            case 'S':
                return ++stepNumber;
            default:
                return 1;
        }
    }
}