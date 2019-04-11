public class SortedLinkedList<E extends Comparable<E>> {
    private int _size; // ����Ʈ�� ������ �ִ� ������ ����
    private LinkedNode<E> _head; // LinkedChain �� �� �� ���


    public int size() {
        return this._size;
    }

    public void setSize(int _size) {
        this._size = _size;
    }

    public LinkedNode<E> head() {
        return _head;
    }

    public void setHead(LinkedNode<E> newHead) {
        this._head = newHead;
    }

    private boolean anElementDoesExistAt(int anOrder) {
        return ((anOrder >= 0) && (anOrder < this._size));
    }

    public SortedLinkedList(int dataSize) {
        this._head = null;
        this._size = 0;
    }

    public boolean isEmpty() {
        return (this._head == null);
        // �Ǵ�, return (this._size == 0) ;
    }

    public boolean isFull() {
        return false; // ������ full �� �ƴϴ�
    }


    public E elementAt(int anOrder) {
        if (this.anElementDoesExistAt(anOrder)) {
            LinkedNode<E> currentNode = this._head;
            int nodeCount = 0;
            while (nodeCount < anOrder) {
                currentNode = currentNode.next();
                nodeCount++;
            }
            return currentNode.element();
        } else {
            return null;
        }
    }


    public E first() {
        if (this.isEmpty()) {
            return null; // ������ ���Ұ� ������ �� �����Ƿ�
        } else {
            return elementAt(0);
            // �Ǵ� �̷���: return this._head.element();
        }
    }


    public E last() {
        if (this.isEmpty()) {
            return null; // ������ ���Ұ� ������ �� �����Ƿ�
        } else {
            return elementAt(this._size - 1);
        }
    }

    public int orderOf(E anElement) { // ���� �˻�
        int order = 0;
        LinkedNode<E> currentNode = this._head;
        while (currentNode != null &&
                (!currentNode.element().equals(anElement))) {
            order++;
            currentNode = currentNode.next();
        }
        if (currentNode == null) { // Not Found
            return -1; // �������� ������ -1 �� �����ֱ�� �Ѵ�
        } else {
            return order;
        }
    }

    public boolean doesContain(E anElement) { // Version 3
        LinkedNode<E> current = this._head;
        while (current != null) {
            if (current.element().equals(anElement)) {
                return true;
            }
            current = current.next();
        }
        return false;
    }


    public boolean add(E anElement) {
        if (this.isFull()) {
            return false;
        } else {
            LinkedNode<E> nodeForAdd = new LinkedNode<E>(anElement, null);
            if (this.isEmpty()) {
                this.setHead(nodeForAdd);
            } else { // ����Ʈ���� ��� �ϳ��� ��尡 �ִ�.
                LinkedNode<E> current = this.head(); // ���� ���ϴ� ���
                LinkedNode<E> previous = null; // current �� �� ���. ������ �Ϸ���, �� ��带 �˾ƾ� �Ѵ�.
                while (current != null) { // ����Ʈ�� ���� ������ �� ���� �� �˻��Ѵ�.
                    if (current.element().compareTo(anElement) > 0) { // ������ ���Ұ� ������ anElement ���� ũ��
                        break; // ������ ��ġ�� ã�� ���̹Ƿ� �� �˻� ����
                    }
                    previous = current; // �ƴ� ��� previous�� current �� ����
                    current = current.next(); // current �� ���� ���� �̵�
                }
                if (previous == null) { // anElement �� ���� �۴�. �� �տ� �����Ѵ�
                    nodeForAdd.setNext(this.head());
                    this.setHead(nodeForAdd);
                } else {
                    nodeForAdd.setNext(current); //
                    previous.setNext(nodeForAdd);
                }
            }
            this.setSize(this.size() + 1); // ũ�⸦ �ϳ� ������Ų��.
            return true;
        }

    }

    public E removeFrom(int anOrder) {
        if (!this.anElementDoesExistAt(anOrder)) { // ������ ���Ұ� ���ų�, �߸��� ��ġ
            return null;
        } else {
            // ����Ʈ�� ��� ���� ������, ������ ���Ұ� ����
            LinkedNode<E> removed = null;
            if (anOrder == 0) { // ������ ���Ұ� �� �� ����
                removed = this._head;
                this._head = this._head.next();
            } else { // ������ ������ ��ġ�� �� ���� �ƴϸ�, ���� ���Ұ� �� �� �̻�
                LinkedNode<E> previous = this._head;
                for (int i = 1; i < anOrder; i++) {
                    previous = previous.next(); // ������ ��ġ�� �� ��带 ã�´�
                }
                removed = previous.next();
                previous.setNext(removed.next());
            }
            this._size--;
            return removed.element();
        }
    }

    public boolean remove(E anElement) { // Version 2
        // �ܰ� 1: �־��� ������ ��ġ�� ã�´�
        LinkedNode<E> previous = null;
        LinkedNode<E> current = this._head;

        while ((current != null) && (!current.element().equals(anElement))) {
            previous = current;
            current = current.next();
        }
        // �ܰ� 2: �־��� ���Ұ� �����ϸ� �����Ѵ�.
        if (current == null) {
            return false; // Not Found
        } else {
            if (current == this._head) { // ������ ��尡 �� �� ���
                this._head = this._head.next();
            } else { // ������ ��� �տ� ��� (previous) �� ����
                previous.setNext(current.next());
            }
            this._size--;
            return true;
        }
    }

    public boolean replaceAt(E anElement, int anOrder) {
        if (!this.anElementDoesExistAt(anOrder)) {
            // ��ü�� ��尡 ���ų�, �߸��� ��ġ
            return false;
        } else {
            LinkedNode<E> current = this._head;
            for (int i = 0; i < anOrder; i++) {
                current = current.next();
                // ����s�� ��ü�� ��带 ã�´�
            }
            current.setElement(anElement);
            return true;
        }
    }

    public void clear() {
        this._head = null;
        this._size = 0;
    }

    public Iterator<E> listIterator() {
        return new ListIterator();
    }

    public E max() {
        return this.last();
    }

    private class ListIterator implements Iterator<E> { //inner class iterator ����
        private LinkedNode _nextNode;


        ListIterator() {
            this._nextNode = SortedLinkedList.this._head;

        }

        @Override
        public boolean hasNext() {

            return (this._nextNode != null);

        } //�������� Ȯ��

        @Override
        public E next() { //���� ���� �����ϰ� ���� ������ ������ �ű�
            if (this._nextNode == null) {
                return null;
            } else {
                E nextElement = (E) this._nextNode.element();
                this._nextNode = this._nextNode.next();
                return nextElement;
            }

        }
    }

}

