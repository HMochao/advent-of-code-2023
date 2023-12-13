import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        List<List<Character>> map = new ArrayList<>();
        int currentLine = 0;
        Set<Integer> rowsWithGalaxies = new HashSet<>();
        Set<Integer> columnsWithGalaxies = new HashSet<>();
        List<Pair<Integer, Integer>> galaxies = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            char[] chars = line.replaceAll("\\s+$", "").toCharArray();

            if (line.contains("#")) {
                rowsWithGalaxies.add(currentLine);
            }
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '#') {
                    columnsWithGalaxies.add(i);
                }
            }
            map.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            currentLine++;
        }

        List<Integer> rowsWithoutGalaxies = IntStream.range(0, map.get(0).size()).filter(a -> !rowsWithGalaxies.contains(a)).boxed().collect(Collectors.toList());
        List<Integer> columnsWithoutGalaxies = IntStream.range(0, map.size()).filter(a -> !columnsWithGalaxies.contains(a)).boxed().collect(Collectors.toList());

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == '#')
                    galaxies.add(new Pair<>(i, j));
            }
        }


        int result = 0;
        long result2 = 0;

        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Pair<Integer, Integer> xy1 = galaxies.get(i);
                Pair<Integer, Integer> xy2 = galaxies.get(j);

                Integer x1 = xy1.getKey();
                Integer x2 = xy2.getKey();
                Integer y1 = xy1.getValue();
                Integer y2 = xy2.getValue();
                int xRawsWithoutGalaxies = (int) IntStream.range(Math.min(x1, x2), Math.max(x1, x2)).filter(rowsWithoutGalaxies::contains).count();
                int xColumnsWithoutGalaxies = (int) IntStream.range(Math.min(y1, y2), Math.max(y1, y2)).filter(columnsWithoutGalaxies::contains).count();
                result += Math.abs(x1 - x2) + xRawsWithoutGalaxies + Math.abs(y1 - y2) + xColumnsWithoutGalaxies;
                result2 += Math.abs(x1 - x2) + xRawsWithoutGalaxies * 999999L + Math.abs(y1 - y2) + xColumnsWithoutGalaxies * 999999L;
            }
        }


        bufferedWriter.write("Part 1 result: " + result + "\n");
        bufferedWriter.write("Part 2 result: " + result2);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}