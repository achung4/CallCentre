import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * @author Angelo Chung (August 2015)
 * Imagine you have a call center with three levels of employees: fresher, technical lead (TL), product manager (PM). 
 * There can be multiple employees, but only one TL or PM. An incoming telephone call must be allocated to a fresher who is free. 
 * If a fresher can’t handle the call, he or she must escalate the call to technical lead. If the TL is not free or not able to handle it, then the call should be escalated to PM.
 *
 * Design the classes and data structures for this problem.
 */
public class CallCentreThreadRunner {
	/*
	 * Main method
	 */
	public static void main(String[] args) throws InterruptedException{
		final int NUM_OF_FRESHERS = 3;
		final int NUM_OF_TL = 1;
		final int NUM_OF_PM = 1;
		
		final int NUM_OF_CALLS = 12;
		final int NUM_OF_EMPLOYEES = NUM_OF_FRESHERS + NUM_OF_TL + NUM_OF_PM;
		
		final int MIN_DELAY = 0;
		final int MAX_DELAY = 3;
	
		welcome();
		
		// Initialize call queue
	 	Queue<Call> callQueue = new LinkedList<Call>();
	 	// Fill test calls queue
	 	Call[] testQueue = test5(NUM_OF_CALLS);
	 	
	 	// Initialize employee list and fill with employees
		ArrayList<ArrayBlockingQueue<Employee>> employeeList = new ArrayList<ArrayBlockingQueue<Employee>>();
		employeeList.add(new ArrayBlockingQueue<Employee>(NUM_OF_FRESHERS));
		employeeList.add(new ArrayBlockingQueue<Employee>(NUM_OF_TL));
		employeeList.add(new ArrayBlockingQueue<Employee>(NUM_OF_PM));
		for(int i = 0; i < NUM_OF_FRESHERS; i++){		// able to have more than 1 Freshers
			employeeList.get(0).add(new Fresher());
		}
		for(int i = 0; i < NUM_OF_TL; i++){				// able to have more than 1 Technical Lead
			employeeList.get(1).add(new TechnicalLead());
		}
		for(int i = 0; i < NUM_OF_PM; i++){				// able to have more than 1 Product Manager
			employeeList.get(2).add(new ProductManager());
		}
		
		// Initialize CallCentre
		CallCentre company = new CallCentre(callQueue, employeeList, NUM_OF_CALLS);
		

		 //Number of employees must equal to the number of threads
		for(int i = 0; i < NUM_OF_EMPLOYEES; i++){
			// If number of calls is less than employees then you don't need more employees
			if(i < NUM_OF_CALLS){
				Thread.sleep(1);
				new CallService(company).start();
			}
		}
		
		//Number of calls must equal to the number of threads
		for(int i = 0; i < NUM_OF_CALLS; i++){
			delayNextCall(MIN_DELAY,MAX_DELAY);
			new CallReceive(company,testQueue[i]).start();
		}		
	}
	
	/*
	 * Private helper method to delay incoming calls 
	 */
	private static void delayNextCall(int min, int max){
		try{
			Random rand = new Random();
			int randomSecond = rand.nextInt((max - min) + 1) + min;
			// multiply by 1000 because sleep(int milliseconds)
			Thread.sleep(randomSecond*1000 + 1);
		}
		catch(InterruptedException exception){}
	}
	
	/*
	 * Private helper method to give a random duration for a call
	 */
	private static int randomDuration(int min, int max){

			Random rand = new Random();
			int randomSecond = rand.nextInt((max - min) + 1) + min;
			// multiply by 1000 because sleep(int milliseconds)
			return randomSecond*1000 + 1;
	}
	
	
	// -- Test cases to try out -- //
	/*
	 * Populate the call queue
	 * Test 1 : Create an array of Call with 'K_SECOND' second(s) duration
	 * 
	 * @param numCalls	the number of calls in the queue
	 * @return 			array of Call 
	 */
	private static Call[] test1(int numCalls){
		final int K_SECOND = 10;
		Call[] queue = new Call[numCalls];
	 	for(int i = 0; i < numCalls; i++){
	 		// multiply by 1000 because Call(int milliseconds + offset)
	 		queue[i] = new Call(K_SECOND*1000 + 1);
 		}
	 	return queue;
	}
	
	/*
	 * Populate the call queue
	 * Test 2 : everyone becomes busy except for Freshers so they'll handle all calls
	 * Precondtion : delay = 0 when receiving call for testing purposes
	 * 
	 * @param numCalls	the number of calls in the queue
	 * @return 			array of Call 
	 */
	private static Call[] test2(int numCalls){
		Call[] queue = new Call[numCalls];	// Expected out put:
	 	queue[0] = new Call(1000 + 1);		// Fresher
	 	queue[1] = new Call(1000 + 1); 		// Fresher	
	 	queue[2] = new Call(1000 + 1);		// Fresher
	 	queue[3] = new Call(20000 + 1);	 	// TL
	 	queue[4] = new Call(20000 + 1);		// PM
	 	queue[5] = new Call(1000 + 1);		// Fresher
	 	queue[6] = new Call(1000 + 1);		// Fresher
	 	queue[7] = new Call(1000 + 1);		// Fresher
	 	queue[8] = new Call(1000 + 1);		// Fresher
	 	queue[9] = new Call(1000 + 1);		// Fresher
	 	queue[10] = new Call(1000 + 1);		// Fresher
	 	queue[11] = new Call(1000 + 1);		// Fresher
	 	return queue;
	}
	
	/*
	 * Populate the call queue
	 * Test 3 : everyone becomes busy except for TL so he'll/she'll handle all other calls
	 * Precondtion : delay = 0 when receiving call for testing purposes
	 * 
	 * @param numCalls	the number of calls in the queue
	 * @return 			array of Call 
	 */
	private static Call[] test3(int numCalls){
		Call[] queue = new Call[numCalls];	// Expected out put:
	 	queue[0] = new Call(20000 + 1);		// Fresher
	 	queue[1] = new Call(20000 + 1); 	// Fresher	
	 	queue[2] = new Call(20000 + 1);		// Fresher
	 	queue[3] = new Call(1000 + 1); 		// TL
	 	queue[4] = new Call(20000 + 1);		// PM
	 	queue[5] = new Call(1000 + 1);		// TL
	 	queue[6] = new Call(1000 + 1);		// TL
	 	queue[7] = new Call(1000 + 1);		// TL
	 	queue[8] = new Call(1000 + 1);		// TL
	 	queue[9] = new Call(1000 + 1);		// TL
	 	queue[10] = new Call(1000 + 1);		// TL
	 	queue[11] = new Call(1000 + 1);		// TL
	 	return queue;
	}
	
	/*
	 * Populate the call queue
	 * Test 4 : everyone becomes busy except for PM so he'll/she'll handle all other calls
	 * Precondtion : delay = 0 when receiving call for testing purposes
	 * 
	 * @param numCalls	the number of calls in the queue
	 * @return 			array of Call 
	 */
	private static Call[] test4(int numCalls){
		Call[] queue = new Call[numCalls];	// Expected out put:
	 	queue[0] = new Call(20000 + 1);		// Fresher
	 	queue[1] = new Call(20000 + 1); 	// Fresher	
	 	queue[2] = new Call(20000 + 1);		// Fresher
	 	queue[3] = new Call(20000 + 1); 	// TL
	 	queue[4] = new Call(1000 + 1);		// PM
	 	queue[5] = new Call(1000 + 1);		// PM
	 	queue[6] = new Call(1000 + 1);		// PM
	 	queue[7] = new Call(1000 + 1);		// PM
	 	queue[8] = new Call(1000 + 1);		// PM
	 	queue[9] = new Call(1000 + 1);		// PM
	 	queue[10] = new Call(1000 + 1);		// PM
	 	queue[11] = new Call(1000 + 1);		// PM
	 	return queue;
	}
	
	/*
	 * Populate the call queue
	 * Test 5 : Create an array of Call with random duration with given min/max duration
	 * 			At this test, call delay can also be set to random by using the delayNextCall function
	 * 
	 * @param numCalls	the number of calls in the queue
	 * @return 			array of Call 
	 */
	private static Call[] test5(int numCalls){
		final int MIN_DURATION = 10;
		final int MAX_DURATION = 15;
		Call[] queue = new Call[numCalls];
	 	for(int i = 0; i < numCalls; i++){
	 		queue[i] = new Call(randomDuration(MIN_DURATION,MAX_DURATION));
 		}
	 	return queue;
	}
	
	/*
	 * Private helper method to print out a welcome sign 
	 */
	private static void welcome(){
		System.out.println("Welcome to Genesis Gaming Inc. -- Call Centre \n");
	}
}
