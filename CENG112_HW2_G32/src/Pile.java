import java.util.Arrays;
import java.util.EmptyStackException;

public class Pile<T> implements StackADT<T> {
	private T[] pile;
	private int topIndex;
	private boolean initialized = false;
	public static final int DEFAULT_CAPACITY = 5;
	public static final int MAX_CAPACITY = 100;

	public Pile() {
		this(DEFAULT_CAPACITY);
	}

	public Pile(int initialCapacity) {
		checkCapacity(initialCapacity);

		T[] tempPile = (T[]) new Object[initialCapacity];
		pile = tempPile;
		topIndex = -1;
		initialized = true;
	}

	public void push(T newEntry) {
		checkInitialization();
		ensureCapacity();
		pile[topIndex + 1] = newEntry;
		topIndex++;
	}

	public T peek() {
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else
			return pile[topIndex];
	}

	public T pop() {
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else {
			T top = pile[topIndex];
			pile[topIndex] = null;
			topIndex--;
			return top;
		}
	}

	public void clear() {
		checkInitialization();
		while (!isEmpty()) {
			this.pop();
		}
	}

	public boolean isEmpty() {
		return topIndex == -1;
	}

	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			throw new RuntimeException("Capacity error!");
		}
	}

	private void checkInitialization() {
		if (!initialized)
			throw new RuntimeException("Pile object is not initialized properly");
	}

	private void ensureCapacity() {
		if (topIndex == pile.length - 1) {
			int newLength = 2 * pile.length;
			checkCapacity(newLength);
			pile = Arrays.copyOf(pile, newLength);
		}
	}
	public void printInfo() {
		for(int i=topIndex;i>=0;i--) {
			System.out.println(pile[i]);
		}
	}

}
