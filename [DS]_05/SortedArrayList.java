public class SortedArrayList<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 100;

    private int _size;
    private int _capacity;
    private E[] _elements;

    public int size() {
        return _size;
    }

    public void setSize(int newSize) {
        this._size = newSize;
    }

    public int capacity() {
        return _capacity;
    }

    public void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
    }

    public E[] elements() {
        return _elements;
    }

    public void setElements(E[] newElements) {
        this._elements = newElements;
    }


    public SortedArrayList(int givenCapacity) {
        this._capacity = givenCapacity;
        this.setElements((E[]) new Comparable[this.capacity()]); //todo ��� ���ظ� �� �ʿ䰡 �ִ�. �߿� �ڵ�
    }

    public boolean add(E newData) { //todo add �� ������ �°� �ڵ����� �ִ� �ڵ常 ������ �ȴ�.
        if (this.isFull()) { //�� �� ������ false
            return false;
        } else if (this.isEmpty()) {
            this.addToFirst(newData);
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (this.elements()[i].compareTo(newData) <= 0) {

                } else {
                    this.addTo(newData, i);
                }
            }
        }


        return true;

    }

    public boolean addTo(E anElement, int anOrder) {
        if (this.isFull()) {
            return false;
        } else {
            if ((anOrder >= 0) && (anOrder <= this._size)) {
                this.makeRoomAt(anOrder);
                this._elements[anOrder] = anElement;

                return true;
            } else {
                return false; // �߸��� ���� ��ġ
            }
        }
    }

    public boolean addToFirst(E anElement) {
        if (this.isFull()) {
            return false;
        } else {
            this.makeRoomAt(0);
            this._elements[0] = anElement;
            this._size++;
            return true;
        }
    }

    public boolean addToLast(E anElement) {
        return this.addTo(anElement, this.size());
    }

    private void makeRoomAt(int aPosition) {
        for (int i = this._size; i > aPosition; i--) {
            this._elements[i] = this._elements[i - 1];
        }

    }


    public E max() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.elements()[size() - 1];
        }

    }

    private boolean isFull() {
        return (this.size() == this.capacity());
    }

    private boolean isEmpty() {
        return (this.size() == 0);
    }

}
