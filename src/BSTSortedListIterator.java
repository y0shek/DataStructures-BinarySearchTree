import java.util.*;

/**
 * BSTSortedListIterator implements an iterator for a binary search tree (BST)
 * implementation of a Sorted List.
 * @author Joshua Kellerman, CS-367
 */
public class BSTSortedListIterator<K extends Comparable<K>> implements Iterator<K> {

	private Stack<BSTnode<K>> stack; //for keeping track of nodes
	
	/**
	 * Constructor sets up the stack for a pre-order traversal.
	 * @param root The bst tree root
	 */
	public BSTSortedListIterator(BSTnode<K> root) {
		stack = new Stack<BSTnode<K>>();
		stack.push(root);
		pushL(root.getLeft());
	} 
	/**
	 * returns true iff the stack has a next element.
	 * @return boolean true if stack is not empty
	 */
    public boolean hasNext() {
    	return !stack.isEmpty();
    }
	/**
	 * returns the next Acronym in the stack
	 * @return K the key of the next Acronym
	 */
    public K next() throws NoSuchElementException {
    	if(stack.isEmpty()){
    		throw new NoSuchElementException("No Such Element!");
    	}
    	BSTnode<K> next = stack.pop();
    	pushL(next.getRight());
    	return next.getKey();
    	
    	//Hint: Remember you are using pre-order traversal. The next node is at 
    	//the top of the stack. Pop it, then push its right child. Iteratively 
    	//push all its left children onto the stack as done in the constructor.
    	//Finally, return the key from the popped node. Don't forget to 
	//throw a NoSuchElementException if there is no next node.
    }
	/**
	 * Helper method to implement recursive traversal of the BST
	 * @param BSTnode<K> the BSTnode to push from.
	 */
    private void pushL(BSTnode<K> n){
    	if(n == null){
    		return;
    	}
    	stack.push(n);
    	pushL(n.getLeft());
    }
	/**
	 * Unsupported Operation
	 */
    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }
}
