
public class Call {
	// Instant fields
	private int duration;			// duration of the call in milliseconds
	private int id;
	
	/*
	 * Default Constructor
	 * 
	 * @param duration the duration of the call in milliseconds
	 */
	public Call(int duration, int id){
		this.duration = duration;
		this.id = id;
	}
	
	/*
	 * Default Constructor
	 * 
	 * @param duration the duration of the call in milliseconds
	 */
	public Call(int duration){
		this.duration = duration;
		this.id = 0;
	}
	
	/*
	 * Tags the call with a specific id
	 * @param id 	the id invoice of the call
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/*
	 * Returns the id tag of the call
	 * @return 		id the id invoice of the call
	 */
	public int getId(){
		return id;
	}
	
	/*
	 * Set the duration of the call in milliseconds
	 * 
	 * @param duration the duration of the call in milliseconds
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	/*
	 * Get the duration of call in milliseconds
	 * 
	 * @return 			the duration of the call in milliseconds
	 */
	public int getDuration(){
		return duration;
	}
}
