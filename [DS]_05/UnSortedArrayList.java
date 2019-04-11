
public class UnSortedArrayList<E extends Comparable<E>> { //todo?
    private static final int DEFAULT_CAPACITY = 1000;

    private int _size;
    private int _capacity;
    private E[] _elements;

    public int size() {
        return _size;
    } //size getter

    public void setSize(int newSize) {
        this._size = newSize;
    } //size setter

    public int capacity() {
        return _capacity;
    } //capacity getter

    public void setCapacity(int newCapacity) {
        this._capacity = newCapacity;
    } //capacity setter

    public E[] elements() {
        return _elements;
    } //elements getter

    public void setElements(E[] newElements) {
        this._elements = newElements;
    } //elements setter

    @SuppressWarnings("unchecked")
    public UnSortedArrayList(int givenCapacity) { //parameter constructor
        this.setCapacity(givenCapacity);
        this.setElements((E[]) new Object[this.capacity()]);
    }

    public UnSortedArrayList() {
        this(DEFAULT_CAPACITY); //자기 자신을 호출

    }  //constructor

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean isFull() {
        return (this.size() == this.capacity());
    }

    public boolean doesContain(E anElement) {
        for (int i = 0; i < this.size(); i++) {
            if (this.elements()[i].equals(anElement)) {
                return true;
            }

        }
        return false;
    }

    public int frequencyOf(E anElement) {
        int frequencyCount = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.elements()[i].equals(anElement)) {
                frequencyCount++;
            }
        }

        return frequencyCount;
    }

    public int orderOf(E anElement) {
        for (int order = 0; order < this.size(); order++) {
            if (this.elements()[order].equals(anElement)) {
                return order;
            }
        }
        return -1;
    }

    private boolean anElementDoesExistAt(int anOrder) {
        return ((anOrder >= 0) && (anOrder < this.size()));

    }

    public E elementAt(int anOrder) {
        int position = anOrder;

        if (this.anElementDoesExistAt(anOrder)) {
            return this._elements[position];

        } else {
            return null;

        }
    }

    public E last() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this._elements[this.size() - 1];
        }
    }

    public E first() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this._elements[0];
        }

    }

    public boolean addTo(E anElement, int anOrder) {
        if (this.isFull()) {
            return false;

        } else if (anOrder < 0 || anOrder > this.size()) {
            return false;

        } else {
            this.makeRoomAt(anOrder);
            this.elements()[anOrder] = anElement;
            this._size++;
            return true;
        }
    }

    private void makeRoomAt(int aPosition) {
        for (int i = this.size(); i > aPosition; i--) {
            this.elements()[i] = this.elements()[i - 1]; //필요한 부분을 땡긴다.
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

    public boolean add(E anElement) {
        return this.addToLast(anElement);
    } //todo add를 순서에 맞게 자동으로 넣는 코드만 만들면 된다.

    public E removeFrom(int anOrder) {
        if (anOrder < 0 || anOrder >= this.size()) {
            return null;

        } else {
            E removedElement = this.elements()[anOrder];
            this.removeGapAt(anOrder);
            this.setSize(this.size() - 1);
            return removedElement;
        }

    }

    private void removeGapAt(int aPositon) {
        for (int i = aPositon + 1; i < this.size(); i++) {
            this.elements()[i - 1] = this.elements()[i];

        }
        this.elements()[this.size() - 1] = null;
    }

    public E removeFirst() {
        return removeFrom(0);
    }

    public E removeLast() {
        return removeFrom(this.size() - 1);
    }

    public E removeAny() {
        return removeLast();
    }

    public boolean replaceAt(E anElement, int anOrder) {
        if (this.anElementDoesExistAt(anOrder)) {
            this._elements[anOrder] = anElement;
            return true;
        } else {
            return false;
        }
    }

    public Iterator<E> iterator() { //iterator 생성
        return (new ListIterator());
    }

    public E max() {
        if (this.isEmpty()) {
            return null;
        } else {
            E maxElement = this.elements()[0];
            for (int i = 1; i < this.size(); i++) {
                if (maxElement.compareTo(this.elements()[i]) < 0) {
                    maxElement = this.elements()[i];
                }
            }
            return maxElement;
        }
    }

    private class ListIterator implements Iterator<E> { //inner class iterator 구현

        private int _nextPosition;

        private int nextPosition() {
            return _nextPosition;
        }//nextPosition getter

        private void setNextPosition(int newNextPostion) {
            this._nextPosition = newNextPostion;
        } //nextPosition setter

        private ListIterator() {
            this.setNextPosition(0);
        }

        @Override
        public boolean hasNext() {
            return (this.nextPosition() < UnSortedArrayList.this.size());
        } //다음값의 확인

        @Override
        public E next() { //현재 값을 리턴하고 다음 값으로 포지션 옮김
            E nextElement = null;
            if (this.hasNext()) {
                nextElement = UnSortedArrayList.this.elements()[this.nextPosition()];
                this.setNextPosition(this.nextPosition() + 1);
            }

            return nextElement;
        }
    }


}
