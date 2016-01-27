package queue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	public QueueNode<E> last;
	private int size;

	public FifoQueue() {

	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		private QueueIterator() {
			pos = last;
		}

		@Override
		public boolean hasNext() {

			return (pos != null);
		}

		@Override
		public E next() {

			return pos.next.element;
		}

	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param x
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E x) {
		QueueNode<E> temp;
		if (isEmpty()) {
			temp = new QueueNode<E>(x);
			last = temp;
			last.next = temp;
		} else {
			temp = new QueueNode<E>(x);
			temp.next = last.next;
			last = temp;
		}
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		E temp = last.element;
		last = last.next;
		return temp;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return last.element;
	}
	
	//Appends the queue q to the current queue. q is positioned behind the current queue.
	
	public void append(FifoQueue<E> q){
		last.next = q.last.next;
		last = q.last;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

}
