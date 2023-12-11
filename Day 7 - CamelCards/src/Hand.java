import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    private String cards;
    private String sortedCards;

    private int value;


    public Hand(String cards, int value) {
        this.cards = cards;
        char[] arr = cards.toCharArray();
        Arrays.sort(arr);
        this.sortedCards = new String(arr);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getHandType() {
        if(isFiveOfAKind()) {
            return 6;
        } else if (isFourOfAKind(true)) {
            return 5;
        } else if (isFullHouse(true)) {
            return 4;
        } else if (isThreeOfAKind(true)) {
            return 3;
        } else if (isTwoPair(true)) {
            return 2;
        } else if (isOnePair(true)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int countJokers() {
        return (int) cards.chars().filter(c -> c == 'J').count();
    }

    private boolean isFiveOfAKind() {
        int jokers = countJokers();
        if(isFourOfAKind(false) && (jokers == 1 || jokers == 4)) {
            return true;
        }
        if(isFullHouse(false) && (jokers == 2 || jokers == 3)) {
            return true;
        }
        if(isThreeOfAKind(false) && jokers == 2) {
            return true;
        }

        return sortedCards.chars().filter(c -> c == sortedCards.charAt(2)).count() == 5;
    }

    private boolean isFourOfAKind(boolean withJoker) {
        if(withJoker) {
            int jokers = countJokers();
            if (jokers == 3) {
                return true;
            }
            if (isThreeOfAKind(false) && jokers == 1) {
                return true;
            }
            if (isTwoPair(false) && jokers == 2) {
                return true;
            }
        }
        return sortedCards.chars().filter(c -> c == sortedCards.charAt(2)).count() == 4;
    }

    private boolean isFullHouse(boolean withJoker) {
        if(withJoker) {
            int jokers = countJokers();
            if (isTwoPair(false) && jokers == 1) {
                return true;
            }
        }
        return sortedCards.chars().filter(c -> c == sortedCards.charAt(2)).count() == 3 && (
                sortedCards.chars().filter(c -> c == sortedCards.charAt(0)).count() == 2 || sortedCards.chars().filter(c -> c == sortedCards.charAt(4)).count() == 2
                );
    }

    private boolean isThreeOfAKind(boolean withJoker) {
        if(withJoker) {
            int jokers = countJokers();
            if (jokers == 2) {
                return true;
            }
            if (isOnePair(false) && jokers == 1) {
                return true;
            }
        }
        return sortedCards.chars().filter(c -> c == sortedCards.charAt(2)).count() == 3;
    }

    private boolean isTwoPair(boolean withJoker) {
        if(withJoker) {
            int jokers = countJokers();
            if (jokers == 2) {
                return true;
            }
        }
        return sortedCards.chars().filter(c -> c == sortedCards.charAt(1)).count() == 2 && sortedCards.chars().filter(c -> c == sortedCards.charAt(3)).count() == 2;
    }

    private boolean isOnePair(boolean withJoker) {
        if(withJoker) {
            int jokers = countJokers();
            if (jokers == 1) {
                return true;
            }
        }
        return cards.chars().filter(c -> c == sortedCards.charAt(0)).count() == 2 || sortedCards.chars().filter(c -> c == sortedCards.charAt(2)).count() == 2 || sortedCards.chars().filter(c -> c == sortedCards.charAt(4)).count() == 2;
    }


    @Override
    public int compareTo(Hand o) {
        if(this.getHandType() > o.getHandType()) {
            return 1;
        } else if (this.getHandType() < o.getHandType()) {
            return -1;
        } else {
            return compareHighestCard(this.cards, o.cards);
        }
    }
    
    private int compareHighestCard(String hand1, String hand2) {
        for (int i = 0; i < hand1.length(); i++) {
            if (getCardValue(hand1.charAt(i)) > getCardValue(hand2.charAt(i))) {
                return 1;
            } else if (getCardValue(hand1.charAt(i)) < getCardValue(hand2.charAt(i))) {
                return -1;
            }
        }
        return 0;
    }

    private int getCardValue(char label) {
        switch (label) {
            case 'A':
                return 13;
            case 'K':
                return 12;
            case 'Q':
                return 11;
            case 'T':
                return 9;
            case '9':
                return 8;
            case '8':
                return 7;
            case '7':
                return 6;
            case '6':
                return 5;
            case '5':
                return 4;
            case '4':
                return 3;
            case '3':
                return 2;
            case '2':
                return 1;
            case 'J':
                return 0;
            default:
                return 0;
        }
    }
}
