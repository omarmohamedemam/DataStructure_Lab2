package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import java.util.Set;

public class ITreeMapClass<T extends Comparable<T>,V>implements ITreeMap<T,V>  {
    private IRedBlackTreeClass<T, V> RBTree ;
    public ITreeMapClass() {
    	RBTree = new IRedBlackTreeClass<T, V>() ;
    }
	@Override
	public Entry<T, V> ceilingEntry(T key) {
		  if (key == null) {
	            throw new RuntimeErrorException(new Error());
	        }
	        if (!RBTree.isEmpty()){
	            if(RBTree.contains(key)){
	            	RBTree.inorder(RBTree.getRoot());
	                Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
	                Iterator<V> iteratorValue = RBTree.getCollectionValue().iterator();
	                while(iteratorKey.hasNext()) {
	                    T tokey   = iteratorKey.next();
	                    V value   = iteratorValue.next() ;
	                    if(key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0 ){
	                        Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(tokey, value) ;
	                        return mapEntry ;
	                    }
	                }
	            }
	        }
	        return null;
	}

	@Override
	public T ceilingKey(T key) {
		if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        if (!RBTree.isEmpty()){
            if(RBTree.contains(key)){
            	RBTree.inorder(RBTree.getRoot());
                Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
                while(iteratorKey.hasNext()) {
                    T tokey   = iteratorKey.next();
                    if(key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0 ){
                        return tokey ;
                    }
                }
            }
        }
        return null;
	}

	@Override
	public void clear() {
		 if(!RBTree.isEmpty())
	            this.RBTree.clear();
		
	}

	@Override
	public boolean containsKey(T key) {
		 if (key == null) {
	            throw new RuntimeErrorException(new Error());
	        }
	        if (!RBTree.isEmpty()) {
	            if(RBTree.contains(key)){
	                return true ;
	            }
	        }
	        return false ;
	}

	@Override
	public boolean containsValue(V value) {
		if (value == null) {
            throw new RuntimeErrorException(new Error());
        }
        if (!RBTree.isEmpty()) {
        	RBTree.inorder(RBTree.getRoot());
            Collection<V> values = RBTree.getCollectionValue() ;
            return values.contains(value) ;
        }
        return false;
	}

	@Override
	public Set<Entry<T, V>> entrySet() {
		Set<Map.Entry<T, V>> entrySet = new LinkedHashSet<>();
        if (!RBTree.isEmpty()) {
        	RBTree.inorder( RBTree.getRoot());

            Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
            Iterator<V> iteratorValue = RBTree.getCollectionValue().iterator();

            while(iteratorKey.hasNext()) {
                T key   = iteratorKey.next();
                V value   = iteratorValue.next() ;
                Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(key, value) ;

                entrySet.add( mapEntry );
            }

        }
        return entrySet;
	}

	@Override
	public Entry<T, V> firstEntry() {
		 if (!RBTree.isEmpty()) {
	            INode<T,V> node  = RBTree.getFirst(RBTree.getRoot()) ;
	            Map.Entry<T,V> mapEntry = new MapEntry<>( node.getKey(), node.getValue() ) ;
	            return mapEntry ;
	        }
	        return null;
	}

	@Override
	public T firstKey() {
		if (!RBTree.isEmpty()) {
            INode<T,V> node  = RBTree.getFirst(RBTree.getRoot()) ;
            return node.getKey() ;
        }
        return null;
	}

	@Override
	public Entry<T, V> floorEntry(T key) {
		 if(key == null ){
	            throw new RuntimeErrorException(new Error()) ;
	        }
	            if (!RBTree.isEmpty()) {
	                if (RBTree.contains(key)) {
	                	RBTree.inorder(RBTree.getRoot());
	                    Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
	                    Iterator<V> iteratorValue = RBTree.getCollectionValue().iterator();

	                    while (iteratorKey.hasNext()) {
	                        T tokey = iteratorKey.next();
	                        V value = iteratorValue.next();
	                        if (key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0) {
	                            Map.Entry<T,V> mapEntry = new MapEntry<>(tokey, value) ;
	                            return mapEntry ;
	                        }
	                    }
	                }
	            }
	            return null;
	}

	@Override
	public T floorKey(T key) {
		 if(key == null ){
		        throw new RuntimeErrorException(new Error()) ;
		    }
		        if (!RBTree.isEmpty()){
		            if(RBTree.contains(key)){
		            	RBTree.inorder(RBTree.getRoot());
		                Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
		                while(iteratorKey.hasNext()) {
		                    T tokey   = iteratorKey.next();
		                    if(key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0 ){
		                        return tokey ;
		                    }
		                }
		            }
		        }
		        return null;
	}

	@Override
	public V get(T key) {
		 if (RBTree.contains(key)){
	            return (V) RBTree.search(key);
	        }
	        return null;
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey) {
		ArrayList<Map.Entry<T, V>> entryArrayList = new ArrayList<Map.Entry<T,V>>() ;
        if(RBTree.contains(toKey)){
        	RBTree.inorder(RBTree.getRoot());
            Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
            Iterator<V> iteratorValue = RBTree.getCollectionValue().iterator();
            while(iteratorKey.hasNext()) {
                T key   = iteratorKey.next();
                V value =  iteratorValue.next();
                if(key.compareTo(toKey) < 0){
                    Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(key, value) ;
                    entryArrayList.add( mapEntry );
                }
            }
        }
        return entryArrayList;
	}

	@Override
	public ArrayList<Entry<T, V>> headMap(T toKey, boolean inclusive) {
		ArrayList<Map.Entry<T, V>> entryArrayList = new ArrayList<>();
        if(RBTree.contains(toKey)){
        	RBTree.inorder(RBTree.getRoot());
            Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
            Iterator<T> iteratorValue = (Iterator<T>) RBTree.getCollectionValue().iterator();
            while(iteratorKey.hasNext()) {
                T key   = iteratorKey.next();
                V value = (V) iteratorValue.next();
                if(key.compareTo(toKey) < 0 || (key.compareTo(toKey) == 0 && inclusive) ){
                    Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(key, value) ;
                    entryArrayList.add( mapEntry );
                }
            }
        }
        return entryArrayList;
	}

	@Override
	public Set<T> keySet() {
	 Set<T> entryArrayList = new LinkedHashSet<>();
        if (!RBTree.isEmpty()){
            if (!RBTree.isEmpty()){
            	RBTree.inorder( RBTree.getRoot() ) ;
                Iterator<T> iteratorKey = RBTree.getCollectionKey().iterator();
                while(iteratorKey.hasNext()) {
                    T key   = iteratorKey.next();
                        entryArrayList.add( key );
                    }
                }
            }

        return entryArrayList ;
	}

	@Override
	public Entry<T, V> lastEntry() {
		 if (!RBTree.isEmpty()) {
	            INode<T,V> node = RBTree.getLast(RBTree.getRoot()) ;
	            Map.Entry<T,V> mapEntry = new MapEntry<>(node.getKey(), node.getValue()) ;
	            return mapEntry ;
	        }
	        return null;
	}

	@Override
	public T lastKey() {
	    if (!RBTree.isEmpty()) {
            INode<T,V> node =   RBTree.getLast(RBTree.getRoot());
            return  node.getKey();
        }
        return null;
	}

	@Override
	public Entry<T, V> pollFirstEntry() {
		try {

            if (!RBTree.isEmpty()) {
                INode<T, V> node = RBTree.getFirst(RBTree.getRoot());
                RBTree.delete(node.getKey());
                Map.Entry<T, V> mapEntry = new MapEntry<>(node.getKey(), node.getValue());
                return mapEntry;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
	}

	@Override
	public Entry<T, V> pollLastEntry() {
		try {
            if (!RBTree.isEmpty()) {
                INode<T, V> node = RBTree.getLast(RBTree.getRoot());
                RBTree.delete(node.getKey());
                Map.Entry<T, V> mapEntry = new MapEntry<>(node.getKey(), node.getValue());
                return mapEntry;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
	}

	@Override
	public void put(T key, V value) {
	
		        if (key == null || value == null){
		            throw new RuntimeErrorException(new Error()) ;
		        }
		        RBTree.insert(key,value);
		
	}

	@Override
	public void putAll(Map<T, V> map) {
		if (map == null) {
            throw new RuntimeErrorException(new Error());
        }

        for (Map.Entry<T, V> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new RuntimeErrorException(new Error());
            } else {
            	RBTree.insert(entry.getKey(), entry.getValue());
            }
        }
		
	}

	@Override
	public boolean remove(T key) {
		 if (!RBTree.isEmpty()){
            return  RBTree.delete(key) ;
        }
        return false;
	}

	@Override
	public int size() {
		return  RBTree.getSize() ;
	}

	@Override
	public Collection<V> values() {
		 if (!RBTree.isEmpty()){
	            INode root = RBTree.getRoot() ;
	            RBTree.inorder((INodeClass<T, V>) root) ;
	            return RBTree.getCollectionValue() ;
	        }
	        return null;
	}

}
