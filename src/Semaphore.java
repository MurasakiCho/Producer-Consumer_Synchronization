//CLASS SEMAPHORE
public class Semaphore {
    private int sem;

    public Semaphore(int sem) {
        this.sem = sem; //initializing sem
    }

    public synchronized void swait() {
        while (sem <= 0) { //thread will wait when sem <= 0
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        sem--; //when sem > 0, while loop ends and sem is decremented
    }

    public synchronized void signal() {
        sem++;  //sem incremented (sem = 1)
        notify(); //wakes up a waiting thread
    }
}
