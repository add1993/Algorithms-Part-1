import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int size;
	// private DequeIterator iterator;
	
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
	
    private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Remove operation is not supported");
		}
		
		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException("No next element present");
			}
			Item item = current.item;
			current = current.next; 
			return item;
		}
    }
	
	// construct an empty deque
	public Deque() {
		size = 0;
		first = null;
		last = null;
		// iterator = new DequeIterator();
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return first == null;
	}
	
	// return the number of items on the deque
	public int size() {
		return size;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Item is null");
		}
		
		Node temp = new Node();
		temp.item = item;
		temp.next = first;
		temp.prev = null;
		if (first != null) {
			first.prev = temp;
		}
		first = temp;
		
		if (last == null) {
			last = temp;
		}
		
		size++;
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Item is null");
		}
		
		Node temp = new Node();
		temp.item = item;
		if (last != null) {
			last.next = temp;
		}
		temp.next = null;
		temp.prev = last;
		last = temp;
		
		if (first == null) {
			first = temp;
		}
		size++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Deque is empty");
		}
		
		Node temp = first;
		first = first.next;
		if (first != null) {
			first.prev = null;
		}
		size--;
		
		if (size == 0) {
			first = null;
			last = null;
		}
		
		return temp.item;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Deque is empty");
		}
		
		Node temp = last;
		last = last.prev;
		if (last != null) {
			last.next = null;
		}
		size--;
		
		if (size == 0) {
			first = null;
			last = null;
		}
		
		return temp.item;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	public static void main(String[] args) {
		
	}// unit testing (optional)
}