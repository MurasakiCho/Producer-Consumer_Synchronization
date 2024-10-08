//CS 3502 W01 MINI PROJECT BRIANA ONEAL

//MAIN - PRODCONSYNC
public class ProdConSync {
    static final int numSlots = 10; //Number of slots in the buffer
    public static void main(String[] args) {
        //instantiations of the semaphores
        Semaphore mutex = new Semaphore(1);
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(numSlots);

        //instantiation of timer
        Timer timer = new Timer();
        System.out.println(timer.getTime());

        //instantiations of producer and consumer threads
        Producer producer = new Producer(mutex, full, empty, new Buffer(numSlots), timer);
        Consumer consumer = new Consumer(mutex, full, empty, new Buffer(numSlots), timer);

        //runs the threads
        producer.start();
        consumer.start();
    }
}
