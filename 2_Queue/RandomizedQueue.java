import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] randQ;
	private int n;
	private queueIterator iterator;
	
	private class queueIterator implements Iterator<Item> {
		private int current = n;
		
		public boolean hasNext() {
			return (current != randQ.length-1);
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Remove operation is not supported");
		}
		
		public Item next() {
			current = StdRandom.uniform(0, n);
			
			while (randQ[current] == null) {
				current = StdRandom.uniform(0, n);
			}
			
			Item item = randQ[current];
			if (item == null) {
				throw new java.util.NoSuchElementException("No next element present");
			}
			
			return item;
		}
    }
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		randQ = (Item[]) new Object[2];
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
		if (n == randQ.length) {
			resize(2*n);
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
		randQ[index] = null;
		n--;
		if (n > 0 && n == randQ.length/4) {
			resize(n/2);
		}
		return retVal;
	}
	
	private void resize(int size) {
		Item[] temp = (Item[]) new Object[size];
		int count = 0;
		for (int i = 0; i < randQ.length; i++) {
			if (randQ[i] == null) {
				continue;
			}
			temp[count] = randQ[i];
			count++;
		}
		randQ = temp;
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		int current = StdRandom.uniform(0, n); 
		while (randQ[current] == null) {
			current = StdRandom.uniform(0, n);
		}
		return randQ[current];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		iterator = new queueIterator();
		return iterator;
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