/*
 * A call receive runnable that makes periodic call receives to the call centre
 */
public class CallReceive implements Runnable {
	// Instant fields
	private CallCentre company;
	private Call call;
	
	/*
	 * Constructor
	 * 
	 * @param company 	the company to be populated with calls
	 * @param call 		the call to be received
	 */
	public CallReceive(CallCentre company, Call call){
		this.company = company;
		this.call = call;
	}
	
	/*
	 * Recieve call and add it into the call queue to be handled
	 */
	public void run(){
		company.receiveCall(call);
	}

	/*
	 * Starts the thread
	 */
	public void start(){
		new Thread(this).start();
	}
}
