import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.Character.isDigit;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> eligibleNumbers = new ArrayList<>();
        List<char[]> map = new ArrayList<>();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String nums = line.replaceAll("\\s+$", "");
            map.add(nums.toCharArray());
        }
        long result = 0;

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                result += isGearEligible(map, i, j);
            }
        }

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static int addNumber(List<char[]> map, int i, int j, List<Integer> eligibleNumbers) {
        int firstPosition = 0;

        for (int k = j; k >= 0; k--) {
            if (!isDigit(map.get(i)[k])) {
                firstPosition = k + 1;
                break;
            }
        }

        int lastPosition = map.get(i).length;
        for (int k = firstPosition; k < map.get(i).length; k++) {
            if (!isDigit(map.get(i)[k])) {
                lastPosition = k;
                break;
            }
        }
        StringBuilder number = new StringBuilder();
        for (int k = firstPosition; k < lastPosition; k++) {
            number.append(map.get(i)[k]);
        }

        eligibleNumbers.add(Integer.parseInt(number.toString()));

        if (lastPosition < map.get(i).length - 1) {
            return lastPosition;
        } else {
            return map.get(i).length;
        }
    }

    private static int getNumber(List<char[]> map, int i, int j) {
        int firstPosition = 0;

        for (int k = j; k >= 0; k--) {
            if (!isDigit(map.get(i)[k])) {
                firstPosition = k + 1;
                break;
            }
        }

        int lastPosition = map.get(i).length;
        for (int k = firstPosition; k < map.get(i).length; k++) {
            if (!isDigit(map.get(i)[k])) {
                lastPosition = k;
                break;
            }
        }
        StringBuilder number = new StringBuilder();
        for (int k = firstPosition; k < lastPosition; k++) {
            number.append(map.get(i)[k]);
        }
        return Integer.parseInt(number.toString());
    }

    private static boolean isEligible(List<char[]> map, int i, int j) {
        return isDigit(map.get(i)[j]) && (
                (j - 1 > 0 && isSymbol(map.get(i)[j - 1])) ||
                        (j + 1 < map.get(i).length && isSymbol(map.get(i)[j + 1])) ||
                        (i - 1 > 0 && isSymbol(map.get(i - 1)[j])) ||
                        (i + 1 < map.size() && isSymbol(map.get(i + 1)[j])) ||
                        (j - 1 > 0 && i - 1 > 0 && isSymbol(map.get(i - 1)[j - 1])) ||
                        (j - 1 > 0 && i + 1 < map.size() && isSymbol(map.get(i + 1)[j - 1])) ||
                        (j + 1 < map.get(i).length && i - 1 > 0 && isSymbol(map.get(i - 1)[j + 1])) ||
                        (j + 1 < map.get(i).length && i + 1 < map.size() && isSymbol(map.get(i + 1)[j + 1]))
        )
                ;
    }

    private static long isGearEligible(List<char[]> map, int i, int j) {
        if (map.get(i)[j] == '*') {
            Set<Integer> eligible = new HashSet<>();
            if (j - 1 >= 0 && isDigit(map.get(i)[j - 1])) {
                eligible.add(getNumber(map, i, j - 1));
            }
            if (j + 1 < map.get(i).length && isDigit(map.get(i)[j + 1])) {
                eligible.add(getNumber(map, i, j + 1));
            }
            if (i - 1 >= 0 && isDigit(map.get(i - 1)[j])) {
                eligible.add(getNumber(map, i - 1, j));
            }
            if (i + 1 < map.size() && isDigit(map.get(i + 1)[j])) {
                eligible.add(getNumber(map, i + 1, j));
            }
            if (j - 1 >= 0 && i - 1 >= 0 && isDigit(map.get(i - 1)[j - 1])) {
                eligible.add(getNumber(map, i - 1, j - 1));
            }
            if (j - 1 >= 0 && i + 1 < map.size() && isDigit(map.get(i + 1)[j - 1])) {
                eligible.add(getNumber(map, i + 1, j - 1));
            }
            if (j + 1 < map.get(i).length && i - 1 >= 0 && isDigit(map.get(i - 1)[j + 1])) {
                eligible.add(getNumber(map, i - 1, j + 1));
            }
            if (j + 1 < map.get(i).length && i + 1 < map.size() && isDigit(map.get(i + 1)[j + 1])) {
                eligible.add(getNumber(map, i + 1, j + 1));
            }
            if (eligible.size() == 2) {
                Iterator<Integer> iterator = eligible.iterator();
                return (long) iterator.next() * iterator.next();
            }
            return 0;
        }
        return 0;

    }

    private static boolean isSymbol(char s) {
        return s != '.' && !isDigit(s);
    }
}