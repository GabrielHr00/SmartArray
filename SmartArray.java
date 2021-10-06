import java.util.function.Consumer;

public class SmartArray {
    private final static int INITIAL_CAPACITY = 8;
    private int[] elements;
    private int size;

    public SmartArray(){
        this.elements = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(int element){
        if(this.size == elements.length){
            this.elements = grow();
        }
        this.elements[this.size] = element;
        this.size++;
    }

    private int[] grow() {
        int[] tempMassive = new int[elements.length * 2];
        System.arraycopy(this.elements, 0, tempMassive, 0, this.elements.length);
        return tempMassive;
    }

    public int get(int index){
        checkBounds(index);
        return this.elements[index];
    }

    public int size(){
        return this.size;
    }

    public int remove(int index){
        checkBounds(index);
        int removed = this.elements[index];

        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.elements[this.size - 1] = 0;
        this.size--;

        // check the while loop in the main class
        // NOTE: when there is a cicle when, when we add and at the same time remove
        // the array doesn't resize everytime, but when it is really 1/4 of the real size
        if(this.size <= this.elements.length / 4){
            this.elements = shrink();
        }
        return removed;
    }

    private int[] shrink() {
        int reduceFactor = 2;

        if(this.elements.length / reduceFactor >= INITIAL_CAPACITY) {
            int[] newElements = new int[this.elements.length / reduceFactor];
            for (int i = 0; i < this.size; i++) {
                newElements[i] = this.elements[i];
            }
            return newElements;
        }
        return this.elements;
    }

    private void checkBounds(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index " + index + " out of bound with size " + size + "!");
        }
    }

    public boolean contains(int element){
        for (int i = 0; i < this.size; i++) {
            if(this.elements[i] == element){
                return true;
            }
        }
        return false;
    }

    // don't use when the array is empty
    // NOTE: just use add(int element)
    public void add(int index, int element){
        checkBounds(index);

        int lastElement = this.elements[this.size - 1];

        for (int i = this.size - 1; i > index; i--) {
            this.elements[i] = this.elements[i-1];
        }

        this.elements[index] = element;
        add(lastElement);
    }

    public void forEach(Consumer<Integer> consumer){
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.elements[i]);
        }
    }
}
