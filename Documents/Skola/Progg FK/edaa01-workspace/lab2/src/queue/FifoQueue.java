package queue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		size = 0;
		last = null;
		
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
		if (last==null) {
			temp = new QueueNode<E>(x);
			last = temp;
			last.next = temp;
		} else {
			temp = new QueueNode<E>(x);
			QueueNode<E> temp2 = last;
			temp.next = last.next;
			last.next = temp;
			last = temp;
		}
		size++;
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (last==null) {
			return null;
		}
		E temp = last.next.element;
		if(last.next==last){
			last = null;
		}else{
			last.next = last.next.next;
		}
		
		if(size>=0){
			size--;	
		}
		
		return temp;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (last==null) {
			return null;
		}
		return last.next.element;
	}
	
	//Appends the queue q to the current queue. q is positioned behind the current queue.
	
	public void append(FifoQueue<E> q){
		if(q.size() == 0) {
			return;
		} else if (size == 0){
			last = q.last;
			size = q.size();
		} else{
			QueueNode<E> temp = last;
			last.next = q.last.next;
			q.last.next = temp.next;
			last = q.last;
			size = size + q.size();
		}
		
	}
	
	//Removes all queue nodes by setting.
	
	public void removeAll(){
		last = null;
		size = 0;
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
