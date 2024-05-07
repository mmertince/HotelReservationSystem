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
			checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
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
		checkInitialization();
		if((newPosition>=1)&&(newPosition<=numberOfEntries+1)) {
			if(newPosition<=numberOfEntries)
				makeRoom(newPosition);
			list[newPosition]=newEntry;
			numberOfEntries++;
			ensureCapacity();
		}
		else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	}
	public T remove() {
		checkInitialization();
		assert !isEmpty();
		T result=list[numberOfEntries];
		numberOfEntries--; 
		return result;
	}
	public T remove(int givenPosition) {
		checkInitialization();
		if((givenPosition>=1)&&(givenPosition<=numberOfEntries)) {
			assert !isEmpty();
			T result=list[givenPosition];
			if(givenPosition<numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return result;
		}	
		else
			throw new IndexOutOfBoundsException("Illegal position given to removal operation.");
	}
	public void clear() {
		checkInitialization();
		while(!isEmpty()) {
			remove();
		}
	}
	public T replace(int givenPosition,T newEntry) {
		checkInitialization();
		if((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
			assert !isEmpty();
			T originalEntry=list[givenPosition];
			list[givenPosition]=newEntry;
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}
	public T getEntry(int givenPosition) {
		checkInitialization();
		if((givenPosition>=1)&&(givenPosition<=numberOfEntries)) {
			assert !isEmpty();
			return list[givenPosition];
		}
		else 
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}
	public T[] toArray() {
		checkInitialization();
		T[] result=(T[])new Object[numberOfEntries];
		for(int i=0;i<numberOfEntries;i++) {
			result[i]=list[i+1];
		}
		return result;
	}
	public boolean contains(T anEntry) {
		checkInitialization();
		boolean found=false;
		int index=1;
		while(!found&&(index<=numberOfEntries)) {
			if(anEntry.equals(list[index]))
				found=true;
			index++;
		}
		return found;
	}
	public int getLength() {
		return numberOfEntries;
	}
	public boolean isEmpty() {
		return numberOfEntries==0;
	}
	private void removeGap(int givenPosition) {
		assert(givenPosition>=1)&&(givenPosition<numberOfEntries);
		int removedIndex=givenPosition;
		int lastIndex=numberOfEntries;
		for(int i=removedIndex;i<lastIndex;i++) {
			list[i]=list[i+1];
		}
	}
	private void makeRoom(int newPosition) {
		assert(newPosition>=1)&&(newPosition<=numberOfEntries+1);
		int newIndex=newPosition;
		int lastIndex=numberOfEntries;
		for(int i=lastIndex;i>=newIndex;i--) {
			list[i+1]=list[i];
		} 
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
