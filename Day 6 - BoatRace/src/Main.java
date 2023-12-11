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

        List<Race> races = new ArrayList<>();

        String[] line1 = bufferedReader.readLine().replaceAll("\\s+$", "").split(":")[1].split("\\s+");
        String[] line2 = bufferedReader.readLine().replaceAll("\\s+$", "").split(":")[1].split("\\s+");
        for (int i = 1; i < line1.length; i++) {
            races.add(new Race(Long.parseLong(line2[i]), Long.parseLong(line1[i])));
        }


        int result = races.stream().map(Race::getNumberOfWaysToBeatRecord).reduce(1, (a, b) -> a * b);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    //Time:        52947594
    //Distance:   426137412791216
}