//The existing Node.java is not generic and works only with int so this is an improvement. 
//I have also included the hashingUsingLinkedListAndGenerics.java file to go with it.

public class MapNode<K, V> {
	K key;
	V value;
	MapNode<K, V> next;
	
	public MapNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
}
