/**
 * The InvalidAcronymException extends Exception and is thrown when an acronym
 * is invalid.
 * 
 * @author Joshua Kellerman, CS-367
 */
public class InvalidAcronymException extends Exception {
	public InvalidAcronymException(){	
	}
	public InvalidAcronymException(String msg){
		super(msg);
	}
}
