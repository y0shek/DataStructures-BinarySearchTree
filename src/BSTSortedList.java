import java.util.Iterator;
import java.util.List;
/**
 * The BSTSortedList class implements the SortedListADT and is made up of
 * a Binary Search Tree of nodes.
 * 
 * @author Joshua Kellerman, CS 367
 * @credits Class Website
 */
public class BSTSortedList<K extends Comparable<K>> implements SortedListADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems = 0;     // the number of items in the sorted list

    public BSTSortedList(){
    	root = null; 
    	} 
    
    /**
     * Inserts the given key into the Sorted List.
     * 
     * @param key the key value to insert into the Sorted List
     */
    public void insert(K key){
        try {
			root = insert(root, key);
		} catch (DuplicateException e) {
			System.out.println("Error: Duplicate Value.");
		}
    }
    
    private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException{
    	numItems ++; //increment items
    	if (n == null) {
            return new BSTnode<K>(key, null, null);
        }
        if (n.getKey().equals(key)) {
        	numItems --; //decrement if duplicate to even back out
            throw new DuplicateException();
        }
        if (key.compareTo(n.getKey()) < 0) {
            // add key to the left subtree
            n.setLeft( insert(n.getLeft(), key) );
            return n;
        }
        else {
            // add key to the right subtree
            n.setRight( insert(n.getRight(), key) );
            return n;
        }
    }
    
    /**
     * Deletes the given key from the Sorted List.  If the key is in the Sorted
     * List, the key is deleted and true is returned.  If the key is not in the
     * Sorted List, the Sorted List is unchanged and false is returned.
     * @param key the key value to delete from the Sorted List
     * @return true if the deletion is successful (i.e., the key was in the 
     * Sorted List and has been removed), false otherwise (i.e., the key was not
     * in the Sorted List)
     */
    public boolean delete(K key){
    	try {
			root = delete(root, key);
			numItems --; //remove an item from numItems if successful
			return true;
		} catch (NotDeletedException e) {
			return false;
		}
    }
    /**
     * Deletes the given key from the Sorted List.  If the key is in the Sorted
     * List, the key is deleted and the new list is returned.
     * @param BSTnode<K> the root, key the key value to delete from the Sorted List
     * @return true if the deletion is successful (i.e., the key was in the 
     * Sorted List and has been removed), false otherwise (i.e., the key was not
     * in the Sorted List)
     */
    private BSTnode<K> delete(BSTnode<K> n, K key) throws NotDeletedException {
        if (n == null) { //base case
        	throw new NotDeletedException(); //quit method and return false
        }
        if (key.equals(n.getKey())) {
        	if (n.getLeft() == null && n.getRight() == null) {
                return null; //delete key if it is root
            }
            if (n.getLeft() == null) {
                return n.getRight(); //get right child
            }
            if (n.getRight() == null) {
                return n.getLeft(); //get left child
            }
            K smallVal = smallest(n.getRight()); //for choosing smallest value
            n.setKey(smallVal);
            n.setRight( delete(n.getRight(), smallVal) ); //recursive call
            return n; //return the BST
        }
        else if (key.compareTo(n.getKey()) < 0) {
            n.setLeft( delete(n.getLeft(), key) );
            return n; //traverse left
        }
        else if (key.compareTo(n.getKey()) > 0) {
            n.setRight( delete(n.getRight(), key) );
            return n; //traverse right
        } else throw new NotDeletedException();
    }
    /**
     * A helper method to find the smallest value in a BST
     * @param n the node to find smallest
     * @return the smallest value
     */
    private K smallest(BSTnode<K> n){
     if (n.getLeft() == null) {
         return n.getKey();
     } else {
         return smallest(n.getLeft());
     }
 }
    /**
     * Searches for the given key in the Sorted List and returns it.  If the 
     * key is not in the Sorted List, null is returned.
     * @param key the key to search for
     * @return the key from the Sorted List, if it is there; null if the key is
     * not in the Sorted List
     */
    public K lookup(K key){
    	return lookup(root, key);
    }
    /**
     * Private method, searches for the given key in the Sorted List and returns it.  
     * If the key is not in the Sorted List, null is returned.
     * @param key the key to search for
     * @return the key from the Sorted List, if it is there; null if the key is
     * not in the Sorted List
     */
    private K lookup(BSTnode<K> n, K key){
        if (n == null) { //return null if list is empty
            return null;
        }
        
        if (n.getKey().equals(key)) { //return if n equals key holder
            return n.getKey();
        }
        
        if (key.compareTo(n.getKey()) < 0) {
            // key < this node's key; look in left subtree
            return lookup(n.getLeft(), key);
        } 
        else {
            // key > this node's key; look in right subtree
            return lookup(n.getRight(), key);
        }
    }
    
    /**
     * Returns the number of items in the Sorted List
     * @return the number of items in the Sorted List
     */
    public int size(){
    	return numItems;
    }
    
    /**
     * Returns true if and only if the Sorted List is empty.
     * @return true if the Sorted List is empty, false otherwise
     */
    public boolean isEmpty(){
    	if (root == null){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Returns an iterator over the Sorted List that iterates over the items in 
     * the Sorted List from smallest to largest.
     * @return an iterator over the Sorted List
     */
    public Iterator<K> iterator(){
    	return new BSTSortedListIterator<K>(root);
    }
}
