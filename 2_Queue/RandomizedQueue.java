import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] randQ;
	private int n;
	private int SIZE;
	
	private class queueIterator implements Iterator<Item> {
		private int subsize = n;
		private int current;
		private Item[] shuffleQ;
		
		public queueIterator() {
			shuffleQ = (Item[]) new Object[subsize];
			for (int i = 0; i < subsize; i++) {
				shuffleQ[i] = randQ[i];
			}
			StdRandom.shuffle(shuffleQ);
			current = 0;
		}
		
		public boolean hasNext() {
			return (current >= 0 && current < n);
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Remove operation is not supported");
		}
		
		public Item next() {
			if (current >= n || shuffleQ[current] == null) {
				throw new NoSuchElementException("Queue empty");
			}
			
			Item item = shuffleQ[current];
			current++;
			return item;
		}
    }
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		randQ = (Item[]) new Object[2];
		SIZE = 2;
		n = 0;
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {
		return (n == 0);
	}
	
	// return the number of items on the randomized queue
	public int size() {
		return n;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Item is null");
		}
		
		if (n == randQ.length) {
			SIZE = 2*SIZE;
			resize(SIZE);
		}
		randQ[n++] = item;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue empty");
		}
		int index = StdRandom.uniform(0, n);
		Item retVal = randQ[index];
		randQ[index] = randQ[n-1];
		randQ[n-1] = null;
		// resize(SIZE);
		n--;

		if (n > 0 && n == randQ.length/4) {
			SIZE = SIZE/2;
			resize(SIZE);
		}
		
		return retVal;
	}
	
	private void resize(int size) {
		Item[] temp = (Item[]) new Object[size];
		
		for (int i = 0; i < n; i++) {
			temp[i] = randQ[i];
		}
		randQ = temp;
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Deque is empty");
		}
		
		int current = StdRandom.uniform(0, n); 
		/* while (randQ[current] == null) {
			current = StdRandom.uniform(0, n);
		} */
		return randQ[current];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new queueIterator();
	}
	
	/* private void iterate(int k) {
		Iterator<Item> iterator = iterator();
		while (k != 0) {
			StdOut.println(iterator.next());
			k--;
		}
	} */
	
	// unit testing (optional)
	public static void main(String[] args) {
		
	}
}