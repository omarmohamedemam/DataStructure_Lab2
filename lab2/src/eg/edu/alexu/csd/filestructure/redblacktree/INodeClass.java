package eg.edu.alexu.csd.filestructure.redblacktree;

public class INodeClass<T extends Comparable<T>,V> implements INode<T,V> {
	
	   private INode<T, V> parent;
	   private INode<T, V> rChild;
	   private INode<T, V> lChild;
	   private T key=null;
	   private V value=null;
	   private boolean red=true;
	   public INodeClass(T key,V value){
	        this.value=value;
	        this.key=key;
	        this.setLeftChild(new INodeClass<T, V>());
	        this.setRightChild(new INodeClass<T, V>());
	    }
	    public INodeClass (){
	        red=false;
	    }

	@Override
	public void setParent(INode<T, V> parent) {
		 this.parent=parent;
		
	}

	@Override
	public INode<T, V> getParent() {
		return this.parent;
	}

	@Override
	public void setLeftChild(INode<T, V> leftChild) {
		    this.lChild=leftChild;
	        if(leftChild==null) {
	        	return ;
	        }
	        leftChild.setParent(this);
		
	}

	@Override
	public INode<T, V> getLeftChild() {
		 return this.lChild;
	}

	@Override
	public void setRightChild(INode<T, V> rightChild) {
		 this.rChild=rightChild;
	        if(rightChild==null) {
	        	return;
	        }
	        rightChild.setParent(this);
		
	}

	@Override
	public INode<T, V> getRightChild() {
		return this.rChild;
	}

	@Override
	public T getKey() {
		return this.key;
	}

	@Override
	public void setKey(T key) {
		 this.key=key;
		
	}

	@Override
	public V getValue() {
		 return this.value;
	}

	@Override
	public void setValue(V value) {
		this.value=value;
		
	}

	@SuppressWarnings("unused")
	@Override
	public boolean getColor() {
		if(this==null) {
			return INode.BLACK;
		}
		else {
			return red;
		}
        
	}

	@Override
	public void setColor(boolean color) {
		this.red=color;
		
	}

	@Override
	public boolean isNull() {
		 return this.value == null;
	}

}
