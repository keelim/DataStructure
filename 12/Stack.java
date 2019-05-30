public interface Stack<E> { //StackInterface
    public int size();

    public boolean isFull();

    public boolean isEmpty();

    boolean push(E anElement);

    public E pop();

    public E peek();

    public void clear();
}