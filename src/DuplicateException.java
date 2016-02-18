/**
 * The DuplicateException extends Exception and is thrown when a duplicate
 * value in the BST is detected.
 * 
 * @author Joshua Kellerman, CS 367
 */
public class DuplicateException extends Exception {
		public DuplicateException(){	
		}
		public DuplicateException(String msg){
			super(msg);
		}
	}
