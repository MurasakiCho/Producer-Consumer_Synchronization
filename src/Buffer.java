import java.time.LocalTime;

//BUFFER
public class Buffer {
    static char[] buffer;
    static int position;
    static LocalTime time;

    public Buffer(int numSlots) { //Constructor
        buffer = new char[numSlots];
        int position = 0;
    }

    public synchronized void put(char c) {
        buffer[position] = c; //append char to array
        position++;
    }

    public synchronized void get() {
        buffer[position - 1] = ' '; //remove char from array
        position -= position - 1;
    }

    public char getChar() {
        return buffer[position - 1]; //returns char at end of array
    }

}
