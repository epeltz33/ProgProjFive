package adts;

import interfaces.QueueInterface;
import nodes.LLNode;

public class LLQ<E> implements QueueInterface<E> {

    LLNode<E> front;
    LLNode<E> rear;

    @Override
    public void enqueue(E element) {
        LLNode<E> newNode = new LLNode<>(element);
        if (rear == null) {
            rear = front = newNode;
        }
        else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    @Override
    public E dequeue() {
        E temp = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return rear == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public String toString() {
        StringBuilder qStr = new StringBuilder("queue, front to rear: ");
        LLNode<E> ptr  = front;
        while (ptr != null) {
            qStr.append(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        return qStr.toString();
    }

}



