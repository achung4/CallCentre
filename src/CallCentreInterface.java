/*
 * Describes any class whose objects recieves and handles calls
 */
public interface CallCentreInterface {
	/*
	 * Receives a call and add it to the call queue
	 * 
	 * @param call the call to be received
	 */
	void receiveCall(Call call);
	
	/*
	 * If a call is still in queue and total call quota is still not met 
	 * Then an employee handle a call
	 * 
	 * @return true if thread is done handling calls otherwise false
	 */
	boolean handleCall() throws InterruptedException;
}
