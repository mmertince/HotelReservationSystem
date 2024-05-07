
public class WaitingLine<T> implements QueueADT<T> {
	private T queue[];
	private int frontIndex;
	private int backIndex;
	private boolean initialized=false;
	private static final int DEFAULT_CAPACITY=20;
	private static final int MAX_CAPACITY=100;
	
	public WaitingLine() {
		this(DEFAULT_CAPACITY);
	}
	public WaitingLine(int initialCapacity) {
		checkCapacity(initialCapacity);
		T[] tempQueue=(T[])new Object[initialCapacity+1];
		queue=tempQueue;
		frontIndex=1;
		backIndex=0;
		initialized=true;
	}
	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex=(backIndex+1)%queue.length;
		queue[backIndex]=newEntry;
	}
	public T dequeue() {
		checkInitialization();
		if(isEmpty())
			throw new RuntimeException("Waiting Line is empty.");
		else {
			T front=queue[frontIndex];
			queue[frontIndex]=null;
			frontIndex=(frontIndex+1)%queue.length;
			return front;
		}
	}
	public T getFront() {
		checkInitialization();
		if(isEmpty())
			throw new RuntimeException("Waiting Line is empty.");
		else 
			return queue[frontIndex];
	}
	public boolean isEmpty() {
		return frontIndex==((backIndex+1)%queue.length);
	}
	public void clear() {
		checkInitialization();
		while (!isEmpty()) {
			this.dequeue();
		}
	}
	public void printInfo() {
		for(int i=frontIndex;i<=backIndex;i++) {
			System.out.println(queue[i]);
		}
	}
	private void ensureCapacity() {
		if(frontIndex==((backIndex+2)%queue.length)) { 
			T[] oldQueue=queue;
			int oldSize=oldQueue.length;
			int newSize=2*oldSize;
			checkCapacity(newSize);
			T[] tempQueue=(T[])new Object[newSize];
			queue=tempQueue;
			for(int i=0;i<oldSize-1;i++) {
				queue[i]=oldQueue[frontIndex];
				frontIndex=(frontIndex+1)%oldSize;
			}
			frontIndex=0;
			backIndex=oldSize-2;
		}
	}
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			throw new RuntimeException("Capacity error!");
		}
	}

	private void checkInitialization() {
		if (!initialized)
			throw new RuntimeException("Waiting Line object is not initialized properly");
	}
}
