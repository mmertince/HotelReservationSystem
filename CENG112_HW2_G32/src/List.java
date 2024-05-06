import java.util.Arrays;

public class List<T> implements ListADT<T> {
	private T[] list;
	private int numberOfEntries;
	private boolean initialized=false;
	private static final int DEFAULT_CAPACITY=50;
	private static final int MAX_CAPACITY=500;
	
	public List() {
		this(DEFAULT_CAPACITY);
	}
	public List(int initialCapacity) {
		if(initialCapacity<DEFAULT_CAPACITY)
			initialCapacity=DEFAULT_CAPACITY;
		else
			checkCapacty(initialCapacity);
		T[] tempList=(T[])new Object[initialCapacity+1];
		list=tempList;
		numberOfEntries=0;
		initialized=true;
	}
	public void add(T newEntry) {
		checkInitialization();
		list[numberOfEntries+1]=newEntry;
		numberOfEntries++;
		ensureCapacity();
	}
	public void add(int newPosition,T newEntry) {
		//implementation needed
	}
	public T remove() {
		//implementation needed
	}
	public T remove(int givenPosition) {
		//implementation needed
	}
	public void clear() {
		//implementation needed
	}
	public T replace(int givenPosition,T newEntry) {
		//implementation needed
	}
	public T getEntry(int givenPosition) {
		//implementation needed
	}
	public T[] toArray() {
		checkInitialization();
		T[] result=(T[])new Object[numberOfEntries];
		for(int i=0;i<numberOfEntries;i++) {
			result[i]=list[i+1];
		}
	}
	public boolean contains(T anEntry) {
		//implementation needed
	}
	public int getLength() {
		return numberOfEntries;
	}
	public boolean isEmpty() {
		return numberOfEntries==0;
	}
	private void ensureCapacity() {
		int capacity=list.length-1;
		if(numberOfEntries>=capacity) {
			int newCapacity=capacity*2;
			checkCapacity(newCapacity);
			list=Arrays.copyOf(list, newCapacity+1);
		}
	}
	
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			throw new RuntimeException("Capacity error!");
		}
	}

	private void checkInitialization() {
		if (!initialized)
			throw new RuntimeException("List object is not initialized properly");
	}
}
