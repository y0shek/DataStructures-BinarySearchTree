/**
 * The Acronym class is instantiable and stores an acronym with meaning and
 * acronym String values associated.
 * 
 * @author Joshua Kellerman, CS 367
 */
public class Acronym implements Comparable<Acronym> {
	private String acronym;
	private String meaning;

	/**
	 * Constructor for an acronym, checks if the Acronym is correct and creates it.
	 * @param acronym - acronym, meaning - meaning of acronym
	 */
	public Acronym(String acronym, String meaning) throws InvalidAcronymException{
		checkAcronym(acronym); //check if valid
		checkAcronym(meaning); //check if valid
		this.acronym = acronym.toUpperCase(); //all Acronyms to upper case
		this.meaning = meaning;
	} //end constructor
	/**
	 * Getter for Acronym
	 * @return the String acronym
	 */
	public String getAcronym(){
		return this.acronym;
	} //end getter
	/**
	 * Getter for meaning
	 * @return the String meaning
	 */
	public String getMeaning(){
		return this.meaning;
	} //end setter
	/**
	 * Returns a negative integer if first object is less than second, and a
	 * positive integer if more.
	 * @return the String acronym
	 * @override compareTo in comparable
	 */
	public int compareTo(Acronym addr) {
		return this.getAcronym().compareTo(addr.getAcronym());
	} //end compareTo
	/**
	 * Returns true if objects are equal
	 * @return boolean if objects are equal
	 * @override equals, Strings acronym to acronym
	 */
	public boolean equals(Object other){
		if (other != null && this.getAcronym().equals //compares Acronym Strings
				(((Acronym)other).getAcronym())){
			return true;
		}
		return false;
	} //end equals
	/**
	 * Returns String out of an Acronym object
	 * @return Acronym toString
	 * @override toString format: acronym(meaning)
	 */
	public String toString(){
		return this.getAcronym() + "(" + this.getMeaning() + ")";
	} //end toString
	/**
	 * Checks whether an Acronym is valid.
	 * @param the Acronym or Meaning to check.
	 */
	public void checkAcronym(String str) throws InvalidAcronymException{
		if (str.charAt(0) ==' ' || str.length() < 2 || str.contains(":")){
			throw new InvalidAcronymException(); //checks condition for Acronym
			//and meaning
		} //end if
	} //end checkAcronym
} //end class
