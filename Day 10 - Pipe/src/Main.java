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
        List<char[]> resultLoop = new ArrayList<>();
        int startI = 0;
        int startJ = 0;
        int currentLine = 0;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String nums = line.replaceAll("\\s+$", "");
            if (nums.contains("S")) {
                startI = currentLine + 1;
                startJ = nums.indexOf("S");
            }
            map.add(nums.toCharArray());
            resultLoop.add(nums.toCharArray());
            currentLine++;
        }

        int partOneResult = getPartOneResult(map, startI, startJ, resultLoop) * 2;
        int area = getPartTwoResult(map, resultLoop);

        bufferedWriter.write("Part 1 result: " + partOneResult/2 + "\n");
        bufferedWriter.write("Part 2 result: " + area);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static int getPartTwoResult(List<char[]> map, List<char[]> resultLoop) {
        int area = 0;
        for (int i = 0; i < map.size(); i++) {
            boolean isInside = false;
            for (int j = 0; j < map.get(i).length; j++) {
                char current = map.get(i)[j];
                if(isInside && !isPartOfPipeLoop(resultLoop.get(i)[j])) {
                    area++;
                } else if(isPartOfPipeLoop(resultLoop.get(i)[j]) && (current == '|' || current == '7' || current == 'F' || current == 'S')) {
                    isInside = !isInside;
                }
            }
        }
        return area;
    }

    private static boolean isPartOfPipeLoop(char c) {
        return c == 'S';
    }

    private static int getPartOneResult(List<char[]> map, int i, int j, List<char[]> resultLoop) {
        char finish = '.';
        int stepNumber = 0;
        boolean lastPositionHorizontal = false;
        boolean goDown = true;
        boolean goRight = false;
        while (finish != 'S') {
            finish = map.get(i)[j];
            resultLoop.get(i)[j] = 'S';
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
}