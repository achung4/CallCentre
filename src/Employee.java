public class Employee {
	// Instant fields
	private String title;
	private int level;
	
	/*
	 * Default constructor
	 * 
	 * @param level the level of the employee
	 */
	public Employee(int level){
		this.level = level;
	}
	
	/*
	 * Returns the title of the employee
	 * @return		the title of the employee
	 */
	public String getTitle(){
		return this.title;
	}
	
	/*
	 * Sets the title of the employee
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/*
	 * Returns the level of the employee
	 */
	public int getLevel(){
		return level;
	}
	
	/*
	 * Sets the level of the employee
	 */
	public void setLevel(int level){
		this.level = level;
	}
}
