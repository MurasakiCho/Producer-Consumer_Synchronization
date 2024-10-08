import java.util.Random;

//CONSUMER
public class Consumer extends Thread {
    private Semaphore mutex;
    private Semaphore full;
    private Semaphore empty;
    Buffer buffer;
    Timer timer;

    public Consumer(Semaphore mutex, Semaphore full, Semaphore empty, Buffer buffer, Timer timer) {
        this.mutex = mutex;
        this.full = full;
        this.empty = empty;
        this.buffer = buffer;
        this.timer = timer;
    }

    public long consumerDelay() { //produces random delay time for consumer
        Random random = new Random();
        return random.nextLong(1, 4);
    }

    public void run() {
        while (true) {
            try {
                System.out.println(timer.getTime() + " Consumer is requesting a full slot.");
                full.swait(); //checks for full slot
                mutex.swait(); //attempts to get exclusive access
                sleep(consumerDelay()); //delay time for consumer
                System.out.println(timer.getTime() + " Consumer removes character " + buffer.getChar() + " from the buffer.");
                buffer.get(); //gets char from buffer
                System.out.println(timer.getTime() + " Consumer releases mutex.");
                mutex.signal(); //release critical section
                System.out.println(timer.getTime() + " Consumer adds 1 empty slot.");
                empty.signal(); //increment empty slots
                //consume data item
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }
}
