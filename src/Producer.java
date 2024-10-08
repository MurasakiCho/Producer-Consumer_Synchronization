import java.util.Random;

//PRODUCER
public class Producer extends Thread {
    private Semaphore mutex;
    private Semaphore full;
    private Semaphore empty;
    Buffer buffer;
    Timer timer;

    //constructor
    public Producer(Semaphore mutex, Semaphore full, Semaphore empty, Buffer buffer, Timer timer) {
        this.mutex = mutex;
        this.full = full;
        this.empty = empty;
        this.buffer = buffer;
        this.timer = timer;
    }

    public char produceChar() { //produces a random char for producer
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    public long producerDelay() { //produces random critical section delay time for producer
        Random random = new Random();
        return random.nextLong(8, 16);
    }

    public void run() {
        while (true) {
            try {
                System.out.println(timer.getTime() + " Producer is producing an item.");
                char c = produceChar();//produce char
                System.out.println(timer.getTime() + " Producer is requesting an empty slot.");
                empty.swait(); //checks for empty slot
                mutex.swait(); //attempts to get exclusive access
                sleep(producerDelay()); //delay time for producer
                System.out.println(timer.getTime() + " Producer put character " + c + " into the buffer.");
                buffer.put(c); //put char into buffer
                System.out.println(timer.getTime() + " Producer releases mutex.");
                mutex.signal(); //release critical section
                System.out.println(timer.getTime() + " Producer adds 1 full slot.");
                full.signal(); //increment full slots
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }
}
