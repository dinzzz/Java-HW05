package hr.fer.zemris.java.hw05.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that represents a simple Hashtable collection used for storage of the
 * data. The idea of hashtables are storing the elements by their keys, so it
 * simplicify their access.
 * 
 * @author Dinz
 *
 * @param <K>
 *            Key of the Hashtable element.
 * @param <V>
 *            Value of the Hashtable element.
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {
	/**
	 * Minimum capacity of the table.
	 */
	private static final int MINIMUM_CAPACITY = 1;
	/**
	 * Default capacity of the table.
	 */
	private static final int DEFAULT_CAPACITY = 16;
	/**
	 * Threshold to maintain the table capacity.
	 */
	private static final double THRESHOLD = 0.75;

	/**
	 * Inner class that represents one entry in the Hashtable.
	 * 
	 * @author Dinz
	 *
	 * @param <K>
	 *            Key of the entry.
	 * @param <V>
	 *            Value of the entry.
	 */
	public static class TableEntry<K, V> {
		/**
		 * Key of the entry.
		 */
		private Object key;
		/**
		 * Value of the entry.
		 */
		private Object value;
		/**
		 * Element that succeeds the current one in the slot list.
		 */
		TableEntry<K, V> next;

		/**
		 * Constructs a new table entry from the given key, value and next element.
		 * 
		 * @param key
		 *            Key of the entry.
		 * @param value
		 *            Value of the entry.
		 * @param next
		 *            Element that succeeds the current one in the slot list.
		 */
		public TableEntry(Object key, Object value, TableEntry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;

		}

		/**
		 * Gets the value of the entry.
		 * 
		 * @return Value of the entry.
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Sets the value of the entry.
		 * 
		 * @param value
		 *            New value of the entry.
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * Gets the key of the entry.
		 * 
		 * @return Key of the entry.
		 */
		public Object getKey() {
			return key;
		}

	}

	/**
	 * A TableEntry array that stores the elements inside the Hashtable.
	 */
	private TableEntry<K, V>[] table;
	/**
	 * Number of elements stored in the table.
	 */
	private int size;
	/**
	 * Number of modifications made externaly to the table.
	 */
	private int modificationCount = 0;

	@SuppressWarnings("unchecked")
	/**
	 * Constructs a new SimpleHashtable with default capacity.
	 */
	public SimpleHashtable() {
		table = (TableEntry<K, V>[]) new TableEntry[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	/**
	 * Constructs a new SimpleHashtable with the given capacity.
	 * 
	 * @param capacity
	 *            Desired capacity.
	 * @throws IllegalArgumentException
	 *             if the capacity is lower then the minimum.
	 */
	public SimpleHashtable(int capacity) {
		if (capacity < MINIMUM_CAPACITY) {
			throw new IllegalArgumentException("Capacity must not be fewer then 1!");
		}

		int i = 1;
		while (i < capacity) {
			i <<= 1;
		}

		table = (TableEntry<K, V>[]) new TableEntry[i];
	}

	/**
	 * Inserts one element into the table using the key to define the position
	 * inside the table. If the element with the given key is already in the table,
	 * the new element overwrites it.
	 * 
	 * @param key
	 *            Key of the element.
	 * @param value
	 *            Value of the element.
	 * @throws IllegalArgumentException
	 *             If the key is null.
	 */
	public void put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}

		int slot = this.tableSlot(key);

		for (TableEntry<K, V> entry = table[slot]; entry != null; entry = entry.next) {
			if (entry.getKey().equals(key)) {
				entry.setValue(value);
				return;
			}
		}

		if (this.size + 1 >= table.length * THRESHOLD) {
			this.reallocateTable();
		}

		slot = this.tableSlot(key);
		addEntry(key, value, slot);
		modificationCount++;
		this.size++;

	}

	/**
	 * Private method that adds the entry into the table, created to implement the
	 * method to put the element into the table properly.
	 * 
	 * @param key
	 *            Key of the element.
	 * @param value
	 *            Value of the element.
	 * @param slot
	 *            Slot where the element should be added.
	 */
	private void addEntry(K key, V value, int slot) {
		TableEntry<K, V> entry = table[slot];

		if (entry == null) {
			table[slot] = new TableEntry<K, V>(key, value, null);
		} else {

			while (entry.next != null) {
				entry = entry.next;
			}

			entry.next = new TableEntry<K, V>(key, value, null);
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Gets the value of the element from the given key.
	 * 
	 * @param key
	 *            Key of the element.
	 * @return Value with the given key.
	 * @throws IllegalArgumentException
	 *             If the key is null.
	 */
	public V get(Object key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null.");
		}

		int slot = this.tableSlot(key);

		for (TableEntry<K, V> entry = table[slot]; entry != null; entry = entry.next) {
			if (entry.getKey().equals(key)) {
				return (V) entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Returns the size of the table.
	 * 
	 * @return Size of the table.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Checks if the table contains the given key.
	 * 
	 * @param key
	 *            Key to be checked.
	 * @return True if the key is present in the table, False otherwise.
	 * @throws IllegalArgumentException
	 *             If the key is null.
	 */
	public boolean containsKey(Object key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null!");
		}

		int slot = this.tableSlot(key);

		for (TableEntry<K, V> entry = table[slot]; entry != null; entry = entry.next) {
			if (entry.getKey().equals(key)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Checks if the table contains the given value.
	 * 
	 * @param value
	 *            Value to be checked.
	 * @return True if the table contains the value, False otherwise.
	 */
	public boolean containsValue(Object value) {
		@SuppressWarnings("unchecked")
		V valueCheck = (V) value;
		for (int i = 0; i < table.length; i++) {
			for (TableEntry<K, V> entry = table[i]; entry != null; entry = entry.next) {
				if (entry.getValue().equals(valueCheck)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Removes the element with the given key from the table.
	 * 
	 * @param key
	 *            Key of the element to be removed.
	 */
	public void remove(Object key) {
		int slot = this.tableSlot(key);
		// prvi u listi
		if (table[slot].getKey().equals(key)) {
			table[slot] = table[slot].next;
			this.size--;
			modificationCount++;
			return;
		}
		// u sredini/na kraju

		for (TableEntry<K, V> entry = table[slot]; entry != null; entry = entry.next) {
			if (entry.next.getKey().equals(key)) {
				TableEntry<K, V> next = entry.next.next;
				entry.next = next;
				size--;
				modificationCount++;
				return;
			}
		}
	}

	public boolean isEmpty() {
		if (this.size != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Clears the whole table.
	 */
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}

		this.size = 0;
		modificationCount++;
	}

	/**
	 * Transforms the table to a string.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		sb.append("[");
		for (int i = 0; i < table.length; i++) {
			for (TableEntry<K, V> entry = table[i]; entry != null; entry = entry.next) {
				sb.append(entry.getKey() + "=" + entry.getValue());
				counter++;
				if (counter != this.size) {
					sb.append(", ");
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@SuppressWarnings({ "unchecked" })
	/**
	 * Method that reallocates the table when the capacity is running out.
	 */
	private void reallocateTable() {
		TableEntry<K, V>[] tempTable = (TableEntry<K, V>[]) new TableEntry[2 * table.length];

		for (int i = 0; i < table.length; i++) {
			for (TableEntry<K, V> entry = table[i]; entry != null; entry = entry.next) {
				addToReallocatedTable((K) entry.getKey(), (V) entry.getValue(), tempTable);
			}
		}
		table = tempTable;

	}

	/**
	 * Method that adds the element from the table to the new reallocated table.
	 * 
	 * @param key
	 *            Key of the element.
	 * @param value
	 *            Value of the element.
	 * @param tempTable
	 *            New array of entries that is used in reallocation.
	 */
	private void addToReallocatedTable(K key, V value, TableEntry<K, V>[] tempTable) {
		int slot = this.reTableSlot(key, tempTable.length);
		TableEntry<K, V> entry = tempTable[slot];

		if (entry == null) {
			tempTable[slot] = new TableEntry<K, V>(key, value, null);
		}

		else {

			while (entry.next != null) {
				entry = entry.next;
			}

			entry.next = new TableEntry<K, V>(key, value, null);
		}

	}

	/**
	 * Calculates the slot where the element should be added based on the Hashtable
	 * capacity and hashcode of the key
	 * 
	 * @param key
	 *            Key of the element.
	 * @return Slot where the element should be added.
	 */
	private int tableSlot(Object key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	/**
	 * Calculates the slot where the element should be added when the table is being
	 * reallocated.
	 * 
	 * @param key
	 *            Key of the element.
	 * @param reLength
	 *            Length of the newly reallocated table.
	 * @return Slot where the element should be added.
	 */
	private int reTableSlot(Object key, int reLength) {
		return Math.abs(key.hashCode()) % reLength;

	}

	/**
	 * Instanciates a new iterator for the SimpleHashtable.
	 */
	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}

	/**
	 * Inner class that implements the iterator interface for the SimpleHashtable
	 * 
	 * @author Dinz
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {
		/**
		 * Count of the internal modifications.
		 */
		int modCount;
		/**
		 * Flag that defines if the element was already removed.
		 */
		boolean wasRemoved = false;
		/**
		 * Current entry.
		 */
		TableEntry<K, V> current;
		/**
		 * Previous entry.
		 */
		TableEntry<K, V> previous;

		/**
		 * Constructs a new IteratorImpl.
		 */
		public IteratorImpl() {
			this.modCount = modificationCount;
		}

		/**
		 * Method that checks if the current element has next element in the table.
		 */
		@Override
		public boolean hasNext() {
			if (modificationCount != modCount) {
				throw new ConcurrentModificationException("External modification is not permited.");
			}

			if (current == null) {
				if (hasNextSlot(0)) {
					return true;
				}
			}

			int slotNumber = tableSlot(current.getKey());

			boolean hasNextSlot = hasNextSlot(slotNumber);

			if (current.next != null || hasNextSlot) {
				return true;
			}

			return false;
		}

		/**
		 * Method that gets the next element from the table.
		 */
		@Override
		public TableEntry<K, V> next() {
			if (modificationCount != modCount) {
				throw new ConcurrentModificationException("External modification is not permited.");
			}
			wasRemoved = false;
			previous = current;

			// prvi element
			if (current == null) {
				for (int i = 0; i < table.length; i++) {
					if (table[i] != null) {
						current = table[i];
						break;
					}
				}
				if (current == null) {
					throw new NoSuchElementException("The table is empty.");
				}
				return current;
			}
			// kraj prvog elementa

			if (this.hasNext()) {
				int slot = tableSlot(current.getKey());
				current = getNext(slot);
				return current;
			} else {
				throw new NoSuchElementException("There is no elements left.");
			}
		}

		/**
		 * Removes the element from the table.
		 */
		public void remove() {
			if (modificationCount != modCount) {
				throw new ConcurrentModificationException("External modification is not permited.");
			}
			if (wasRemoved) {
				throw new IllegalStateException("Can't use remove twice!");
			}
			Object key = current.getKey();
			SimpleHashtable.this.remove(key);
			wasRemoved = true;
			current = previous;

			modCount++;
		}

		/**
		 * Method that checks if the element has an element in the succeeding slots.
		 * 
		 * @param slot
		 *            Slot of the current element.
		 * @return True if the element in the succeeding slots exists, False otherwise.
		 */
		private boolean hasNextSlot(int slot) {
			for (int i = slot + 1; i < table.length; i++) {
				if (table[i] != null) {
					return true;
				}
			}

			return false;
		}

		/**
		 * Gets the next element from the table based on the slot.
		 * 
		 * @param slot
		 *            Slot of the current element.
		 * @return Next element.
		 */
		private TableEntry<K, V> getNext(int slot) {
			// prvo u listi
			if (current.next != null) {
				return current.next;
			} else {
				for (int i = slot + 1; i < table.length; i++) {
					if (table[i] != null) {
						return table[i];
					}
				}
			}
			return null; // Unreachable
		}

	}
}
