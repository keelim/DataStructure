public class LinkedBag<E> {
    private int _size;
    private LinkedNode<E> _head;


    public LinkedBag() { //LinkedBag constructor
        this._size = 0;
        this._head = null;

    }

    public int size() { //size getter
        return this._size;
    }

    private void setSize(int _size) { //size setter 
        this._size = _size;
    }

    private LinkedNode<E> head() { //head getter
        return _head;
    }

    private void setHead(LinkedNode<E> _head) { //head setter
        this._head = _head;
    }

    public boolean isEmpty() {//��� �ִ����� Ȯ��
        return (this.size() == 0); //size �� 0������ Ȯ��
    }

    public boolean isFull() { // ���� �ִ��� Ȯ�� �޸𸮸�ƴ �߰� �� �� �ִ�. 
        return false;
    }


    public boolean doesContain(E anElement) { //��� �ִ����� Ȯ��
        LinkedNode<E> currentNode = this.head(); //node �� ����
        while (currentNode != null) {			//node �� null �� �ƴ� �� ����.
            if (currentNode.element().equals(anElement)) { 
                return true; //ã���� true ����
            }

            currentNode = currentNode.next();// ������ ���� ���
        }
        return false; //���� ������ false ����
    }


    public int frequencyOf(E anElement) { //�󵵼��� Ȯ��
        int frequencyCount = 0;			 //  ���� ����
        LinkedNode<E> currentNode = this.head(); //�ӽ� ��带 head�� �޴´�. 
        while (currentNode != null) { //�ӽ� ��尡 null�� �ƴϸ�  loop in
            if (currentNode.element().equals(anElement)) { //������
                frequencyCount++; // ���� ī��Ʈ�� �ø���. 
            }
            currentNode = currentNode.next(); //������ ���� ���
        }
        return frequencyCount; //ī��Ʈ ���� �����Ѵ�. 

    }

    public boolean add(E anElement) { //�������� false �� �����ϰ� �����ϸ� �߰� �Ѵ�.

        if (this.isFull()) { //���� �� �ִ����� Ȯ�� isFull() --> ������ false
            return false;
        } else {
            LinkedNode<E> newNode = new LinkedNode<>(); //��� ����
            newNode.setElement(anElement); //������Ʈ ���� ����
            newNode.setNext(this.head()); // ������ head�� ����
            this.setHead(newNode);       // head�� newNode�� ����
            this._size++;				// �������� ũ�⸦ �ø���. 
            return true;
        }

    }

    public boolean remove(E anElement) { //����
        if (this.isEmpty()) { 			//���� �� �ִ����� Ȯ�� isFull() --> ������ false
            return false;
        } else {
            LinkedNode<E> previousNode = null; 		 //�ӽ� ��� ����1
            LinkedNode<E> currentNode = this.head();//�ӽ� ��� ���� 2 �� ���� �տ� head �� ����
            boolean found = false;                 //�ӽ� �÷��� ����

            while (currentNode != null && !found) {             //���� ��尡 null�� �ƴϰų� �÷��װ� true
                if (currentNode.element().equals(anElement)) { //������Ʈ�� ����� ������Ʈ Ȯ��
                    found = true; 							  // ã���� true	
                } else {
                    previousNode = currentNode;        //���� ��带 ���� ���� �����Ѵ�. 
                    currentNode = currentNode.next(); //���� ����� next�� ���� ���� ����
                }
            }


            if (!found) { 	   //�÷��׿� ����
                return false; // ��ã���� false
            } else {
                if (currentNode == this.head()) {
                    this._head = this.head().next(); //boundary condition
                } else {
                    previousNode.setNext(currentNode.next()); //preViousNode �� next() currentNode�� next() 
                    this._size--; 							 // ����� ���δ�. 

                }
                return true;
            }
        }
    }

    public E removeAny() {     //� �κ��� ���Ÿ� �ϴ°�?
        if (this.isEmpty()) { //����ִ����� Ȯ��
            return null;	 //��� ������ null�� ����
        } else {
            E removedElement = this.head().element(); //�ӽ� ������Ʈ�� ����� ������Ʈ�� ����
            this._head = this.head().next();	     //head�� next�� head�� ����  		
            this._size--;							//size�� ���δ�. 
            return removedElement;				   //���� �Ǵ� ������Ʈ�� return 
        }
    }

    public void clear() { 	  //���� �ʱ�ȭ null�� �ʱ�Lȭ �ϰ� size�� 0���� �����.
        this.setSize(0);   	 //���� ����� 0���� �Ѵ�. 
        this.setHead(null); //head�� null ������ �Ѵ�. �������� garbage collection
    }

    E elementAt(int anOrder) { //�ε��� ��ġ�� ��ü�� ��ȯ�Ѵ�.
        if ((anOrder < 0) || (anOrder >= this.size())) {
            return null;
        } else {
            LinkedNode<E> currentNode = this.head();
            for (int i = 0; i < anOrder; i++) {
                currentNode = currentNode.next();
            }
            return currentNode.element();
        }
    }

    public E any() { 		   //��� ������Ʈ�� ����� �޴´�. 
        if (this.isEmpty()) { //��� �ִ����� Ȯ��
            return null;	 //null return 
        } else {
            return this.head().element(); //��� ���� ������ head�� ������Ʈ�� ����� �Ѵ�. 
        }

    }

}
