import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Card> cards = new ArrayList<>();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            String[] card = line.replaceAll("\\s+$", "").split(":\\s+")[1].split("\\s+");
            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> myNumbers = new ArrayList<>();
            boolean endedWinningNumbers = false;
            for (String s : card) {
                if (s.equals("|")) {
                    endedWinningNumbers = true;
                    continue;
                }
                if (!endedWinningNumbers) {
                    winningNumbers.add(Integer.parseInt(s));
                } else {
                    myNumbers.add(Integer.parseInt(s));
                }
            }
            cards.add(new Card(winningNumbers, myNumbers));
        }
        Long[] data = new Long[cards.size()];
        Arrays.fill(data, 1L);

        List<Long> amounts = Arrays.asList(data);

        for (int i = 0; i < cards.size(); i++) {
            long wins = cards.get(i).getAmount();
            for (int j = 0; j < amounts.get(i); j++) {
                for (int k = i + 1; k < cards.size() && k < i + wins + 1; k++) {
                    amounts.set(k, amounts.get(k) + 1);
                }
            }

        }

        long result = amounts.stream().mapToLong(Long::longValue).sum();
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}