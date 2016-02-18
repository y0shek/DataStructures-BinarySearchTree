/**
 * The NotDeletedException extends Exception and is thrown when during the delete
 * operation of a BST, nothing is deleted.
 * 
 * @author Joshua Kellerman, CS 367
 */
public class NotDeletedException extends Exception {
	public NotDeletedException(){
	}
	public NotDeletedException(String msg){
		super(msg);
	}
}
