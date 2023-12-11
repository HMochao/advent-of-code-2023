import java.util.List;

public class Card {

    private List<Integer> winningNumbers;
    private List<Integer> myNumbers;

    public Card(List<Integer> winningNumbers, List<Integer> myNumbers) {
        this.winningNumbers = winningNumbers;
        this.myNumbers = myNumbers;
    }

    public long getPoints() {
        long amount = myNumbers.stream().filter(n -> winningNumbers.contains(n)).count();
        long res = amount == 0 ? 0L : 1L;
        for (int i = 0; i < amount-1; i++) {
            res*=2;
        }
        return res;
    }

    public long getAmount() {
        return myNumbers.stream().filter(n -> winningNumbers.contains(n)).count();
    }
}
