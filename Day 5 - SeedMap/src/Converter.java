package src;

public class Converter {

    private long end;
    private long start;
    private long offset;

    public Converter(long end, long start, long offset) {
        this.end = end;
        this.start = start;
        this.offset = offset;
    }

    public long convert(long number) {
        return end + Math.abs(number-start);
    }

    public boolean canBeConverted(long number) {
        return start <= number && number <= start - 1 + offset;
    }

    public long getEnd() {
        return end;
    }

    public long getStart() {
        return start;
    }

    public long getOffset() {
        return offset;
    }
}
