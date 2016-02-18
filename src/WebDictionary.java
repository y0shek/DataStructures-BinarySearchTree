import java.util.*;
import java.io.*;
/**
 * The WebDictionary creates and manipulates a sorted list of Internet acronyms.
 * It includes user console IO and File IO
 * @author Joshua Kellerman CS-367  
 */
public class WebDictionary {
	/**
	 * The main method provides the user interface as described in the program 
	 * write-up.  You will need to add to the code given here.
	 * 
	 * @param args the command-line arguments that determine how input and 
	 * output is done in the game:
	 * <ul>
	 *   <li>if there are no command-line arguments, then console input and 
	 *   console output are used</li>
	 *   <li>if there is one command-line argument, then it is treated as the
	 *   name of the file from which to get input and output is sent to the
	 *   console</li>
	 *   <li>if there are two command-line arguments, then the first is the name
	 *   of the file from which to get the input and the second is the name of 
	 *   the file to which to sent the output</li>
	 * </ul>
	 * 
	 * You may add additional static variables or methods as needed.
	 */

	public static void main(String[] args) throws IOException {
		Scanner in = null;         // for input
		PrintStream out = null;    // for output
		SortedListADT<Acronym> dictionary = new BSTSortedList<Acronym>();  
		// the acronym dictionary
		boolean echo = false;	   // whether or not to echo the user input

		// Set up where to send input and output
		switch (args.length) {
		case 0: // if all input and output is through console
			in = new Scanner(System.in);
			out = new PrintStream(System.out);
			break;

		case 1: // if input is by file and output through console
			try{
				in = new Scanner(new File(args[0]));
			} catch (FileNotFoundException e){
				System.out.println("File Not Found!");
			}
			out = System.out;
			echo = true; //print copy into console
			break;

		case 2:  // if input and output are by file
			try{
				in = new Scanner(new File(args[0]));
				out = new PrintStream(new File(args[1]));
			} catch (FileNotFoundException b) {
				System.out.println("File Not Found!");
				System.exit(0);
			}
			echo = true; //print copy into file
			break;

		default:
			System.err.println("Invalid command-line arguments");
			System.exit(0);
		}

		boolean again = true; //as long as the user hasn't typed 'q'
		while (again) {
			out.print("enter choice (a, d, f, p, q): ");
			out.flush();
			String input = in.nextLine();
			if (echo) out.println(input);
			if (input.length() == 0) {
				out.println("invalid input");
			}

			else {
				// We will have our program be case-insensitive, so we'll 
				// convert the first character to lower-case.
				char choice = input.substring(0, 1).toLowerCase().charAt(0);
				String remainder = "";  // used to hold the remainder of input
				// trim off any leading or trailing spaces
				remainder = input.substring(1).trim();

				switch (choice) {

				// add an acronym 
				// format: a acronym:meaning
				case 'a':  
					if(!remainder.contains(":")){ //sentinel
						out.println("Invalid Input");
						break;
					}
					String[] parts = remainder.split(":"); //split string to
					try{								   //first and second words
						dictionary.insert(new Acronym(parts[0], parts[1]));
					} //end try for NullPointerException
					catch (InvalidAcronymException e){
						out.println("Invalid Acronym");
					}
					break;

					// delete an acronym
					// format: d acronym
				case 'd':
					if(remainder.equals("")){
						break;
					}
					boolean deleted = false; //flag
					try{ 
						deleted = dictionary.delete(new Acronym(remainder, "xx"));
					} catch (InvalidAcronymException e){ //create new Acronym
						out.println("Invalid Acronym");  //to compare with BST
					}									 //nodes and delete

					if(!deleted){
						out.println("Not Present"); //if not deleted
					} else {
						out.println(remainder.toUpperCase() + " deleted.");
					}
					break;

					// find an acronym
					// format: f acronym
				case 'f':   
					boolean found = false; //flag
					if(!dictionary.isEmpty()){ //make sure dictionary is not empty
						Iterator<Acronym> itr = dictionary.iterator();
						while (itr.hasNext()){ //iterate through till found
							Acronym temp = itr.next();
							if(temp.getAcronym().equals(remainder.toUpperCase())){
								found = true;
								out.println(temp.toString());
								break;
							}
						}
						if (!found){
							out.println("Not Present");
						}
					}
					break;

					// print the contents of the dictionary in sorted order
					// format: p
				case 'p':  
					if (dictionary.isEmpty()){ //if empty
						out.println("Acronym Dictionary is Empty.");
						break;
					}
					Iterator<Acronym >itr = dictionary.iterator();
					while (itr.hasNext()){
						out.println(itr.next().toString()); //print list of all
					}										//the dictionary
					break;									//words.

					// quit - this does not need to be changed
					// format: q
				case 'q':  
					again = false;
					out.println("done");
					break;

				default:   // anything else is invalid
					out.println("invalid choice");
				} // end switch
			} // end else
		} // end while
	} // end main
} // end class
