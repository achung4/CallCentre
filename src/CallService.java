/*
 * A call service runnable that makes periodic call services from a call centre when a call is received
 */
public class CallService implements Runnable{
	// Instant fields
	private CallCentre company;
	private boolean running;
		
	/*
	 * Constructor
	 * 
	 * @param company 	the company to be populated with calls
	 */
	public CallService(CallCentre company){
		this.company = company;
	}
	
	/*
	 * Handle calls if there are some in queue
	 */
	public void run(){
		try {
			// while company is handling calls, do service
			while(company.handleCall()){
			} 
		}catch (InterruptedException e) {}
	}
	
	/*
	 * Starts the thread
	 */	
	public void start(){
		running = true;
		new Thread(this).start();
	}

	/*
	 * Stops the thread
	 */
	public void stop(){
		running = false;
	}
}
