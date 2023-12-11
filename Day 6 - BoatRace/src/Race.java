public class Race {

    private long record;
    private long time;

    public Race(long record, long time) {
        this.record = record;
        this.time = time;
    }

    public int getNumberOfWaysToBeatRecord() {
        int ways = 0;
        for (long velocity = 1; velocity < time; velocity++) {
            long distanceDone = (time - velocity) * velocity;
            if(distanceDone > record) {
                ways++;
            }
        }

        return ways;
    }
}
