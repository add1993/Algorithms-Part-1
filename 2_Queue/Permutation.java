import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
	
	private Iterator<String> iterator;
	private RandomizedQueue randObj;
	
	public Permutation() {
		randObj = new RandomizedQueue();
		iterator = randObj.iterator();
	}
	
	private void insert(String item) {
		randObj.enqueue(item);
	}
	
	private Iterator<String> getIterator() {
		return iterator;
	}
	
	private void iterate(int n) {
		while (n != 0) {
			StdOut.println(iterator.next());
			n--;
		}
	}
	
	public static void main(String[] args) {
		Permutation perm = new Permutation();
		int k = StdIn.readInt();
			
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			perm.insert(item);
		}
		
		perm.iterate(k);
	   
	}
}