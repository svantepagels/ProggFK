package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] map;
	private double maxLoad = 0.75;
	private int capacity;
	private int size;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and
	 * the default maxLoad factor (0.75).
	 */
	public SimpleHashMap(){
		this(16);
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default maxLoad factor (0.75).
	 */
	public SimpleHashMap(int capacity){
		this.capacity = capacity;
		map = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
				
	}
	
	public String show(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < map.length; i++){
			sb.append(i + "\t");
			if(map[i] != null){
				Entry<K,V> e = map[i];
				while(e != null){
					sb.append(e.toString());
					e = e.next;
				}
			} else {
				sb.append("empty");
			}
			sb.append("\n");
			
			
		}
		return sb.toString();
		
	}

	private Entry<K,V> find(int index, K key){
		Entry<K,V> e = map[index];
		while(e!=null){
			if(e.getKey().equals(key)){
				return e;
			} 
			e = e.next;	
		}
		return null;
	}
	
	private int index(K key) {
		int index = key.hashCode() % capacity;
		index = Math.abs(index);
		return index;
	}
	
	private void rehash(){
		size = 0;
		capacity *= 2;
		Entry<K, V>[] oldArray = map;
		map = (Entry<K, V>[]) new Entry[capacity];
		for (int i = 0; i < oldArray.length; i++) {
			Entry<K, V> e = oldArray[i];
			while (e != null) {
				put(e.key, e.value);
				e = e.next;
			}
		}
	}
	@Override
	public V get(Object arg0) {
		K key	= (K) arg0;
		int index = index(key);
		Entry<K, V> e = find(index, key);
		if (e != null) {
			return e.value;
		}
		return null;
		
	}

	@Override
	public boolean isEmpty() {
		return size==0;		
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		Entry<K,V> e = find(index, arg0);
		// Om det redan finns objekt med samma key
		if(e!=null){
			return e.setValue(arg1);
		} else{
			Entry<K,V> newEntry = new Entry<K,V>(arg0, arg1);
			// Om det finns Entry-objekt på samma index
			if(map[index] != null){
				newEntry.next = map[index];
				map[index] = newEntry;
//			Om det inte finns något Entry-objekt på samma index
			} else{
				map[index] = newEntry;
			}
			size++;
			double temp = (double) size;
			double temp2 = (double) capacity;
			if((double) temp/temp2 > maxLoad){
				rehash();
			}			

		}
		
		return null;
	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		if (size==0){
			return null;
		} else if(find(index, key)==null){
			return null;
		} else if(map[index].getKey().equals(key)){
			V temp = map[index].getValue();
			map[index] = map[index].next;
			size--;
			return temp;	
			
		}
			Entry<K,V> e = map[index];
			while(e.next!=null){
				if(e.next.getKey().equals(key)){
					V temp = e.next.getValue();
					e.next = e.next.next;
					size--;
					return temp;
				}
				e = e.next;
			}
			return null;
		
	
		
	}

	@Override
	public int size() {
		
		return size;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {

			return key;
		}

		@Override
		public V getValue() {
		
				return value;
			
		}

		// Returnerar gamla value om det nya lagts till.
		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

		public String toString() {

			return key + " = " + value;

		}

	}
	
//	public static void main(String[] args){
//		String s = "sd";
//		int a = s.hashCode() % 16;
//		
//		System.out.println(a);
//		
//	
//		Map<Integer, Integer> m = new SimpleHashMap<Integer, Integer>(10);
//		for (int i = 1; i < 10; i++) {
//			m.put(i,i);
//		}
//	}

}
