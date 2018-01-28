public class TechnicalLead extends Employee{
	// Constant
	private final String title = "Te- Le-";
	
	/*
	 * Default constructor
	 */
	public TechnicalLead(){
		super(1);
	}
	
	/*
	 * @return 		returns the title of the employee
	 * @see Employee#getTitle()
	 */
	public String getTitle(){
		return this.title;
	}
}
