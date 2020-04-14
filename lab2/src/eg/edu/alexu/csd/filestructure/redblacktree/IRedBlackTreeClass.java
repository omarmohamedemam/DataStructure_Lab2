package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;

import javax.management.RuntimeErrorException;

public class IRedBlackTreeClass<T extends Comparable<T>, V> implements IRedBlackTree<T,V>  {
	
	private INode<T,V> root;
    private int size=0;
    
	@Override
	public INode<T, V> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void clear() {
		 size=0;
	     root=null;
		
	}

	@Override
	public V search(T key) {
		 if (key == null) {
	            throw new RuntimeErrorException(new Error());
	        }
	        try {
	            INode<T,V> required_node = root;
	            int temp = required_node.getKey().compareTo(key);

	            while (temp!=0) {
	                if (temp>0){
	                	required_node=required_node.getLeftChild();
	                    temp = required_node.getKey().compareTo(key);

	                }else if(temp<0) {
	                	required_node = required_node.getRightChild();
	                    temp = required_node.getKey().compareTo(key);
	                }
	            }
	            return  required_node.getValue();
	        }catch (NullPointerException e){
	            return null;
	        }
	}

	@Override
	public boolean contains(T key) {
		 if(key==null)throw new RuntimeErrorException(new Error());
	        try {
	            INode<T,V> required_node = root;
	            int temp = required_node.getKey().compareTo(key);

	            while (temp!=0) {
	                if (temp>0){
	                	required_node=required_node.getLeftChild();
	                	temp = required_node.getKey().compareTo(key);

	                }else if(temp<0) {
	                	required_node = required_node.getRightChild();
	                	temp = required_node.getKey().compareTo(key);
	                }
	            }
	            return true;
	        }catch (NullPointerException e){
	            return false;
	        }
	}

	@Override
	public void insert(T key, V value) {
		if (key == null || value == null) {
            throw new RuntimeErrorException(new Error());
        }
        if(!this.isEmpty()){
        if(contains(key)){
        	New_search(key).setValue(value);
            return;
        }}
        this.size++;
            INode<T,V> newNode = new INodeClass(key,value);
            if(this.root == null || this.root.isNull() ){
                newNode.setColor(INode.BLACK);
                this.root=newNode;
                return;
            }
            INode<T,V> N = this.root;

            int temp = key.compareTo(N.getKey());
            while (true) {
                if (temp > 0) {
                    if(!N.getRightChild().isNull())
                        N=N.getRightChild();
                    else {
                        N.setRightChild(newNode);
                        break;
                    }

                }else if(temp<0) {
                    if(!N.getLeftChild().isNull())
                        N=N.getLeftChild();
                    else {
                        N.setLeftChild(newNode);
                        break;
                    }
                }else{
                    if(N.getLeftChild().isNull()) {
                        N.setLeftChild(newNode);
                        break;
                    }
                    else if(N.getRightChild().isNull()) {
                        N.setRightChild(newNode);
                        break;
                    }
                    else {
                        N = N.getLeftChild();
                    }

                }
                temp = key.compareTo(N.getKey());
            }
            After_insert(newNode);

		
	}

	@Override
	public boolean delete(T key) {
		if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        return delete_operation(this.root,key);

    }
	//------------------------------------Additional Functions--------------------------------------------
	// same search function but returns node instead of value
	private INode<T,V> New_search(T key){
        try {
            INode<T,V> Req_node = root;
            int temp = Req_node.getKey().compareTo(key);

            while (temp!=0) {
                if (temp>0){
                	Req_node=Req_node.getLeftChild();
                	temp = Req_node.getKey().compareTo(key);

                }else if(temp<0) {
                	Req_node = Req_node.getRightChild();
                	temp = Req_node.getKey().compareTo(key);
                }
            }
            return  Req_node;
        }catch (NullPointerException e){
            return null;
        }
    }
	//-----------------------------------------------------------
	//Reconstruct the tree after deletion using left and right rotation
	private void After_Delete(INode<T,V> x) {
        INode<T,V> temp;
        while (x != root && x.getColor() == INode.BLACK) {
            if (x == x.getParent().getLeftChild()) {
            	temp = x.getParent().getRightChild();
                if (temp.getColor() == INode.RED) {
                	temp.setColor(INode.BLACK);
                    x.getParent().setColor(INode.RED);
                    rotateLeft(x.getParent());
                    temp = x.getParent().getRightChild();
                }

                if (temp.getLeftChild().getColor() == INode.BLACK && temp.getRightChild().getColor() == INode.BLACK) {
                	temp.setColor(INode.RED);
                    x = x.getParent();
                } else {
                    if (temp.getRightChild().getColor() == INode.BLACK) {
                    	temp.getLeftChild().setColor(INode.BLACK);
                    	temp.setColor(INode.RED);
                        rotateRight(temp);
                        temp = x.getParent().getRightChild();
                    }

                    temp.setColor(x.getParent().getColor());
                    x.getParent().setColor(INode.BLACK);
                    temp.getRightChild().setColor(INode.BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {
            	temp = x.getParent().getLeftChild();
                if (temp.getColor()== INode.RED) {
                	temp.setColor(INode.BLACK);
                    x.getParent().setColor(INode.RED);
                    rotateRight(x.getParent());
                    temp = x.getParent().getLeftChild();
                }

                if (temp.getRightChild().getColor()== INode.BLACK) {
                	temp.setColor(INode.RED);
                    x =x.getParent();
                } else {
                    if (temp.getLeftChild().getColor() == INode.BLACK) {
                        temp.getRightChild().setColor(INode.BLACK);
                        temp.setColor(INode.RED);
                        rotateLeft(temp);
                        temp = x.getParent().getLeftChild();
                    }

                    temp.setColor(x.getParent().getColor());
                    x.getParent().setColor(INode.BLACK);
                    temp.getLeftChild().setColor(INode.BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(INode.BLACK);
    }
	//--------------------------------------------------------
	// delete node using the root and the node key instead of the key only
    private boolean delete_operation(INode<T,V> node,T key){
        if (node.isNull())return false;

        int c = node.getKey().compareTo(key);
        while (c!=0){
            try {
                if(c>0){
                    node=node.getLeftChild();
                    c=node.getKey().compareTo(key);
                }
                if(c<0){
                    node=node.getRightChild();
                    c=node.getKey().compareTo(key);

                }

            }catch (NullPointerException e ){
                return false;
            }
        }
        size--;

        if(node.getRightChild().isNull() && node.getLeftChild().isNull()) {
            if(node==root){root=new INodeClass<T,V>();return true;}
            After_Delete(node);
            if(node.getParent().getRightChild()==node)node.getParent().setRightChild(new INodeClass<T,V>());
            if(node.getParent().getLeftChild()==node)node.getParent().setLeftChild(new INodeClass<T,V>());

        }
        else if( node.getRightChild().isNull()){
            if(node.getColor()==INode.BLACK)node.getLeftChild().setColor(INode.BLACK);
            if(node==root){
                root=node.getLeftChild();
                root.setParent(null);
                return true;
            }
            if(node.getParent().getRightChild()==node)node.getParent().setRightChild(node.getLeftChild());
            else
                node.getParent().setLeftChild(node.getLeftChild());

        }
        else if(node.getLeftChild().isNull()) {
            if(node.getColor()==INode.BLACK)node.getRightChild().setColor(INode.BLACK);
            if(node==root){
                root=node.getRightChild();
                root.setParent(null);
                return true;
            }
            if(node.getParent().getRightChild()==node)node.getParent().setRightChild(node.getRightChild());
            else
                node.getParent().setLeftChild(node.getRightChild());

        }
        else {
            INode<T, V> successor = findSuccessor(node);
            node.setKey(successor.getKey());
            node.setValue(successor.getValue());
            delete_operation(successor,successor.getKey());

        }
        return true;
    }
    //-----------------------------------------------------------
    //reconstruct the tree after insertion 
    private void After_insert(INode<T,V> node){
        //If parent is black then return
        if(node==root||node.getParent().getColor()==INode.BLACK)return;
        //else if the parent is RED --> Do some work
        //if Node's uncle is Black or Null
        if(getUncle(node).isNull()||getUncle(node).getColor()==INode.BLACK ){
            //RR , LL , RL or LR
            if(node.getParent()==node.getParent().getParent().getRightChild()){
                //R?
                if(node==node.getParent().getRightChild()){
                    //RR
                    node.getParent().getParent().setColor(INode.RED);
                    node.getParent().setColor(INode.BLACK);
                    rotateLeft(node.getParent().getParent());


                }
                else {
                    //RL

                    rotateRight(node.getParent());
                    After_insert(node.getRightChild());
                }
            }else {
                //L?
                if(node==node.getParent().getRightChild()){
                    //LR
                    rotateLeft(node.getParent());
                    After_insert(node.getLeftChild());
                }else {
                    //LL
                    node.getParent().getParent().setColor(INode.RED);
                    node.getParent().setColor(INode.BLACK);
                    rotateRight(node.getParent().getParent());
                }
            }

        }else {
            node.getParent().setColor(INode.BLACK);
            getUncle(node).setColor(INode.BLACK);
            if(node.getParent().getParent()!=root)node.getParent().getParent().setColor(INode.RED);
            After_insert(node.getParent().getParent());
        }
    }
    //-----------------------------------------------------
    // rotate left operation 
    private void rotateLeft(INode<T,V> node){

        INode y = node.getRightChild();
        node.setRightChild( y.getLeftChild());
        if(!y.getLeftChild().isNull()){
            y.getLeftChild().setParent( node);
        }
        y.setParent(node.getParent());
        if(node.getParent()==null)
            this.root = y;
        else if(node == node.getParent().getLeftChild()){
            node.getParent().setLeftChild(y);
        }
        else{
            node.getParent().setRightChild(y);
        }
        y.setLeftChild(node);
        node.setParent(y);
    }
//---------------------------------------------------------------
    //rotate right operation
    private void rotateRight(INode<T,V> x){
        INode y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if(!y.getRightChild().isNull()){
            y.getRightChild().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null ){
            this.root = y;
        }
        else if(x == x.getParent().getRightChild()){
            x.getParent().setRightChild(y);
        }
        else{
            x.getParent().setLeftChild(y);
        }
        y.setRightChild(x);
        x.setParent(y);

    }
    //--------------------------------------------------------
    private INode<T,V> getUncle(INode<T,V> node){
        try {
            INode<T, V> grandParent = node.getParent().getParent();
            if (node.getParent() == grandParent.getRightChild()) return grandParent.getLeftChild();
            else return grandParent.getRightChild();
        }catch (NullPointerException e){
            return null;
        }
    }
    //---------------------------------------------------
    private INode<T,V> findSuccessor(INode<T,V> node){
        node=node.getLeftChild();
        while(!node.getRightChild().isNull())node=node.getRightChild();
        return node;
    }
    //------------------------------------------------
    //--------------------------other functions will be used in tree map---------------------
    private Collection<V> collectionValue  = new ArrayList<>() ;
    private Collection<T> collectionKey = new ArrayList<>();

//-----------------------------------------
    public Collection<T> getCollectionKey() {
        return collectionKey;
    }
//----------------------------------------

    void inorder(INode<T,V> node)
    {
        if (node.isNull())
            return  ;

        //  left subtree
        inorder( node.getLeftChild());
        collectionKey.add(node.getKey());
        collectionValue.add(node.getValue());
        // right subtree
        inorder(node.getRightChild());
    }
    //-----------------------------------------

    public Collection<V> getCollectionValue() {
        return collectionValue;
    }

//-------------------------------
    public int getSize() {
        return size;
    }
    //----------------------------------------
    INode<T,V> getLast(INode<T,V>   node){

        while(! node.getRightChild().isNull()){
            node = node.getRightChild() ;
        }
        return node ;
    }
    //----------------------------------------
    INode<T,V> getFirst(INode<T,V>   node){

        while(!node.getLeftChild().isNull()){
            node = node.getLeftChild() ;
        }
        return node ;
    }
    //------------------------------------------
}
    