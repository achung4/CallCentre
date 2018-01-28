import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 
 */
public class CallCentre implements CallCentreInterface{
	private Lock changeLock;
	private Condition incomingCallCondition;
	private Queue<Call> callQueue;
	private ArrayList<ArrayBlockingQueue<Employee>> employeeList;
	private int callQuota;
	private int callHandled;
	private int callCounter;
	
	/*
	 * Constructor
	 * 
	 * @param callQueue		the call queue
	 * @param employeeList	the list of employees
	 * @param callQuota		the total call quota for the day 
	 */
	public CallCentre(Queue<Call> callQueue, ArrayList<ArrayBlockingQueue<Employee>> employeeList, int callQuota){
		this.changeLock = new ReentrantLock();
		this.incomingCallCondition = changeLock.newCondition();
		this.callHandled = 0;
		this.callCounter = 0;
		this.callQueue = callQueue;
		this.employeeList = employeeList;
		this.callQuota = callQuota;
	}
	
	/*
	 * Receive a call and add it to the call queue
	 * @see CallCentreInterface#receiveCall(Call)
	 */
	public void receiveCall(Call call){
		changeLock.lock();
		try{
			callCounter++;
			call.setId(callCounter);
			callQueue.add(call);
			System.out.println("Received new call# "+call.getId());
			incomingCallCondition.signalAll();
		}
		finally{
			changeLock.unlock();
		}
	}
	
	/*
	 * Handles the call if an employee is available
	 * @see CallCentreInterface#handleCall()
	 */
	public boolean handleCall() throws InterruptedException{
		// if call handled is less than the call quota or call queue is not empty
		if(callHandled < callQuota || !callQueue.isEmpty()){
			Call c = null;
			Employee e = null;
			changeLock.lock();
			try{
				while(callHandled < callQuota && callQueue.isEmpty()){
					incomingCallCondition.await();
				}
				// get the call
				c = callQueue.poll();
				// get the lowest level available employee
				e = getHandler();
			}
			finally{
				changeLock.unlock();
			}
			// if an employee is avialable
			if(e!=null){
				// if there are is an incoming call then handle it
				if(c!=null){
					callHandled++;
					System.out.println(e.getTitle()+ " handles call# "+ c.getId()+ " with duration: " + c.getDuration()/1000+ " seconds.");
					Thread.sleep(c.getDuration());
					employeeList.get(e.getLevel()).put(e);	
				}
				// otherwise end the thread because there won't be more expecting calls
				else{
					//System.out.println(" -stopped");
					return false;
				}	
				// else if there is no incoming call then do nothing
			}	
			// call has been handled 
			return true;
		}
		// otherwise end the thread because total call quota has been met and no more calls in the call queue
		else{
			//System.out.println(" -stopped");
			return false;
		}		
	}	
	
	/*
	 * Private helper method to get the lowest level available employee
	 * @return 		the lowest level available employee
	 */
	private Employee getHandler() throws InterruptedException{
		for(ArrayBlockingQueue<Employee> level : employeeList){
			if(!level.isEmpty())
				return level.take();
		}
		return null;
	}
}