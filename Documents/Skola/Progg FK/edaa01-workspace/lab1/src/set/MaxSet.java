package set;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;

	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}

	/**
	 * Returns the currently largest element in this set. pre: the set is not
	 * empty post: the set is unchanged
	 * 
	 * @return the currently largest element in this set
	 * @throws NoSuchElementException
	 *             if this set is empty
	 */
	public E getMax() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return maxElement;
	}

	/**
	 * x Adds the specified element to this set, if it is not already present. x
	 * post: x is added to the set if it is not already present
	 * 
	 * @param x
	 *            the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		if (maxElement == null || maxElement.compareTo(x) < 0) {
			maxElement = x;
		}
		return super.add(x);
	}

	/**
	 * Removes the specified element from this set if it is present. post: x is
	 * removed if it was present
	 * 
	 * @param x
	 *            the element to remove - if present
	 * @return true if the set contained the specified element
	 */

	public boolean remove(E x) {
		/**
		 * if(maxElement.equals(x)){ Iterator<? extends E> itr = iterator(); E
		 * temp = itr.next(); while(itr.hasNext()){
		 * if(maxElement.compareTo(itr.next())>0){ temp = itr.next();
		 * maxElement=temp; } } return true; } return false;
		 * 
		 * 
		 * }
		 */
		if (super.remove(x)) {
			if (maxElement.compareTo(x) == 0) {
				maxElement = null;
				Iterator<? extends E> itr = iterator();
				if (itr.hasNext()) {
					E temp = itr.next();
					while (itr.hasNext()) {
						E temp2 = itr.next();
						if (temp.compareTo(temp2) < 0) {
							temp = temp2;
						}
					}
					maxElement = temp;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Adds all of the elements in the specified set, for which it is possible,
	 * to this set. post: all elements, for which it is possible, in the
	 * specified set are added to this set.
	 * 
	 * @return true if this set changed as a result of the call
	 */
	public boolean addAll(SimpleSet<? extends E> c) {
		int origSize = size();
		for (E temp : c) {
			add(temp);
		}
		return origSize != size();
	}

}