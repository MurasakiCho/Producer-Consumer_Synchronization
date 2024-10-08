//TIMER
//timestamps for the threads
public class Timer {

    static final long startTime = System.nanoTime();
    long endTime = 0;

    public String getTime() {
        endTime = System.nanoTime();
        return "Time: " + String.format("%.3f", ((float) (endTime - startTime) / 1000000000));
    }
}
