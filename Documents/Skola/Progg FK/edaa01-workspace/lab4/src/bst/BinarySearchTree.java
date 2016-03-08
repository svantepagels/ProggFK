package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;
	boolean addHelp;

	public static void main(String[] args) {
		int[] v = { 1, -6, 7, 12, -3, 4, 0 };
		BinarySearchTree<Integer> x = new BinarySearchTree<Integer>();
		for (int i = 0; i < v.length; i++) {
			x.add(v[i]);
		}
		x.printTree();
		BSTVisualizer y = new BSTVisualizer("Tree", 300, 300);
		y.drawTree(x);
		x.rebuild();
		y.drawTree(x);
		
	}

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = add(root, x);
		if (addHelp) {
			size++;
		}
		return addHelp;
	}

	private BinaryNode<E> add(BinaryNode<E> n, E x) {
		if (n == null) {
			addHelp = true;
			return new BinaryNode<E>(x);
		} else if (x.compareTo(n.element) == 0) {
			addHelp = false;
			return n;
		} else if (x.compareTo(n.element) < 0) {
			n.left = add(n.left, x);
			return n;
		} else {
			n.right = add(n.right, x);
			return n;
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		} else {
			int left = 1 + height(n.left);
			int right = 1 + height(n.right);
			if (left >= right) {
				return left;
			} else {
				return right;
			}
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		System.out.println("Dödens träd");
		printTree(root);

	}

	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}

	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a, 0, a.length-1);
		
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null){
			index = toArray(n.left, a, index);
			a[index++] = n.element;
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last){
			return null;
		} else {
			BinaryNode<E> root;
			int middle = (first + last+1)/2;
			root = new BinaryNode<E>(a[middle]);
			root.left = buildTree(a, first, middle-1);
			root.right = buildTree(a, middle+1, last);
			return root;
		}
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
