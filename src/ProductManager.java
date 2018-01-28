public class ProductManager extends Employee{
	// Constant
	private final String title = "Pr- Ma-";
	
	/*
	 * Default constructor
	 */
	public ProductManager(){
		super(2);
	}
	
	/*
	 * @return 		returns the title of the employee
	 * @see Employee#getTitle()
	 */
	public String getTitle(){
		return this.title;
	}
}
