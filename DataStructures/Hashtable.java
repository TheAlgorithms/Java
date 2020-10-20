/*
 * Data Structures and Algorithms.
 * Copyright (C) 2016 Rafael Guterres Jeffman
 *
 * See the LICENSE file accompanying this source code, for
 * licensing restrictions that might apply.
 *
 */

package datastructures;

public class Hashtable<K, V> implements java.lang.Iterable<K> {
	
	private class HashKeyIterator implements java.util.Iterator<K> {
		int current = 0;
		int index = -1;
		
		@SuppressWarnings("unchecked")
		@Override
		public K next() {
			for (index += 1; index < table.length; index++) {
				if (table[index] != null) {
					current++;
					return ((HashEntry)table[index]).key;
				}
			}
			return null;
		}
		@Override
		public boolean hasNext() {
			return current < size();
		}
	}

	private static <K> int[] generateHashes(K key, int hashCount) {
		int[] hs = new int[hashCount];
		java.util.Random rand = new java.util.Random();
		rand.setSeed(key.hashCode());
		for (int i = 0; i < hashCount; ++i)
			hs[i] = Math.abs(rand.nextInt());
		return hs;
	}

	private class HashEntry {
		K key;
		V value;
		int[] hashes;
		
		public HashEntry(K key, V value, int hashCount) {
			this.key = key;
			this.value = value;
			this.hashes = generateHashes(key, hashCount);
		}
	}
	
	private final int MAX_TRIES;
	private int hashCount;
	private Object[] table;
	private int elementCount;

	/**
	 * Create a new hash table using Cuckoo Hashing, with two hashes.
	 */
	public Hashtable() {
		this(2);
	}
	
	/**
	 * Create a new hash table using Cuckoo Hashing, with a given number
	 * of hashes.
	 * @param hashCount The number of hashes to use.
	 */
	public Hashtable(int hashCount) {
		if (hashCount < 2)
			hashCount = 2;
		MAX_TRIES = hashCount*5;
		this.hashCount = hashCount;
		this.elementCount = 0;
		table = new Object[5];
	}

	private int mod(int h) {
		return h % table.length;
	}

	private int[] createBacktrack() {
		int[] backtrack = new int[MAX_TRIES];
		for (int i = 0; i < MAX_TRIES; i++)
			backtrack[i] = -1;
		return backtrack;
	}
	
	/**
	 * Insert a new key-value pair into the table.
	 * @param key The key to the value.
	 * @param value The value associated with the key.
	 * @throws DuplicateKeyException If the key already exists.
	 */
	public void put(K key, V value)
			throws DuplicateKeyException
	{
		HashEntry entry = new HashEntry(key, value, hashCount);
		insert(entry, createBacktrack(), 0, mod(entry.hashes[0]));
	}

	private int count(int[] backtrack, int pos) {
		int count = 1;
		for (int v : backtrack)
			if (v == pos)
				count++;
		return count;
	}

	@SuppressWarnings("unchecked")
	private void insert(HashEntry entry, int[] backtrack, int i, int pos)
			throws DuplicateKeyException
	{
		if (i >= MAX_TRIES)
			rehash(entry);
		else
		if (table[pos] == null) {
			table[pos] = entry;
			elementCount++;
			return;
		} else {
			if (pos == 0 && ((HashEntry)table[pos]).key.equals(entry.key))
				throw new DuplicateKeyException("Duplicate key:" + entry.key);
			else {
				HashEntry cuckoo = (HashEntry)table[pos];
				if (count(backtrack, pos) > hashCount) {
					rehash(entry);
				} else {
					table[pos] = entry;
					backtrack[i] = pos;
					for (int h = 0; h < hashCount; h++) {
						if (mod(cuckoo.hashes[h]) == pos) {
							int nextpos = mod(cuckoo.hashes[(h+1) % hashCount]);
							insert(cuckoo, backtrack, i+1, nextpos);
							break;
						}
					}
				}
			}
		}
	}

	
	/**
	 * Rehashes the table. It does not control for key attacks, so, with the
	 * correct keys it could enter an infinite loop.
	 */
	@SuppressWarnings("unchecked")
	private void rehash(HashEntry entry)
				throws DuplicateKeyException
	{
		int count = elementCount;
		Object[] oldtable = table;
		table = new Object[oldtable.length * 2];
		for (Object o : oldtable) {
			if (o != null) {
				HashEntry e = (HashEntry)o;
				insert(e,createBacktrack(),0,mod(e.hashes[0]));
			}
		}
		elementCount = count;
		insert(entry,createBacktrack(),0,mod(entry.hashes[0]));
	}


	/**
	 * Returns a value value associated with a given key, or null, if the
	 * key is not found.
	 * @param key The key to search for.
	 * @return The value associated with the key, or null.
	 */
	public V get(K key) {
		int[] hashes = generateHashes(key, hashCount);
		for (int h : hashes) {
			if (table[mod(h)] != null) {
				@SuppressWarnings("unchecked")
				HashEntry entry = (HashEntry)table[mod(h)];
				if (entry.key.equals(key))
					return entry.value;
			}
		}
		return null;
	}

	/**
	 * Remove
	 * @param key The key to search for.
	 * @return The value associated with the key, or null.
	 */
	public void set(K key, V value) {
		remove(key);
		try {
			put(key, value);
		} catch (DuplicateKeyException e) {
			// should never get here..
			e.printStackTrace();
		}
	}

	/**
	 * Change the value associated with an existing key, or add a new
	 * value if the key is not found.
	 * @param key The key to search for.
	 * @return The value associated with the key, or null.
	 */
	public void remove(K key) {
		int[] hashes = generateHashes(key, hashCount);
		for (int h : hashes) {
			if (table[mod(h)] != null) {
				@SuppressWarnings("unchecked")
				HashEntry entry = (HashEntry)table[mod(h)];
				if (entry.key.equals(key)) {
					table[mod(h)] = null;
					return;
				}
			}
		}
	}

	
	/**
	 * Queries the current load factor do the table.
	 * @return The ratio between the number of elements stored and the
	 * amount of memory used for it.
	 */
	public double loadFactor() {
		return elementCount / (1.0 * table.length);
	}

	/**
	 * Queries the number of elements currently stored in the hash table.
	 * @return The number of elements stored.
	 */
	public int size() {
		return elementCount;
	}

	@Override
	public java.util.Iterator<K> iterator() {
		return (java.util.Iterator<K>)(new HashKeyIterator());
	}
}
