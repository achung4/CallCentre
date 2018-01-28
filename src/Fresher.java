public class Fresher extends Employee {
	// Constant
	private final String title = "Fresher";
	
	/*
	 * Default constructor
	 */
	public Fresher(){
		super(0);
	}
	
	/*
	 * @return 		returns the title of the employee
	 */
	public String getTitle(){
		return this.title;
	}
}
