import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;

public class TestFifoQueueAppend<E> {
	private FifoQueue<Integer> a;
	private FifoQueue<Integer> b;

//	@Before
//	public void setUp() throws Exception {
//		a = new FifoQueue<Integer>();
//		b = new FifoQueue<Integer>();
//		
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		a.removeAll();
//		b.removeAll();
//	}

	@Test
	public void twoEmptyQueues() {
		a.append(b);
		assertTrue("Queue size not 0", a.size()==0);
		assertTrue("Queue not empty", a.peek()==null);
	}
	
	@Test
	public void appendEmptyQueue() {
		int[] c = {1, 2, 3, 4, 5, 6, 7};
		for(int i : c){
			a.offer(i);
		}
		a.append(b);
		assertTrue("Queue size not 7", a.size()==7);
		assertTrue("Last element not 7", a.peek()==7);
	}
	@Test
	public void appendToEmptyQueue() {
		int[] c = {8, 9, 10, 11, 12, 13, 14};
		for(int i : c){
			a.offer(i);
		}
		b.append(a);
		assertTrue("Queue size not 7", a.size()==7);
		assertTrue("Last element not 7", a.peek()==14);
	}
	@Test
	public void appendTwoQueues(){
		int[] c = {1, 2, 3, 4, 5, 6, 7};
		for(int i : c){
			a.offer(i);
		}
		int[] d = {8, 9, 10, 11, 12, 13, 14};
		for(int i : d){
			b.offer(i);
		}	
		b.append(a);
		assertTrue("Queue size not 14", a.size()==14);
		assertTrue("Last element not 7", a.peek()==7);
	}
}
