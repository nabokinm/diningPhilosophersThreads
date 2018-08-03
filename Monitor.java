/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
package a3_26789579;
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
	
	boolean [] chopsticks;
	static boolean isSomeoneTalking=false;


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
	
	chopsticks=new boolean[piNumberOfPhilosophers];
	
	
	for (int i = 0; i < chopsticks.length; i++) {
		
		chopsticks[i]=false;
	}
	
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		//distinguish even/odd threads to avoid starvation
		if(piTID%2==0)
		{
			//pickup first. if even first take one before
			while(chopsticks[(piTID-1)%chopsticks.length]){
				try {
					
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end while
			
			//now set to true
			chopsticks[(piTID-1)%chopsticks.length]=true;
			System.out.println("Philosopher " + piTID + "picks up first chopstick" );
			
			//now take remaining chopstick
			while(chopsticks[(piTID)%chopsticks.length]){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end while
		
			chopsticks[(piTID)%chopsticks.length]=true;
			System.out.println("Philosopher " + piTID + "picks up second chopstick" );
		
		
	
		
		}//endIf
		
		//if odd thread do the opposite -take chopstick after, than - before
		else{
			
			//take chopstick after, than previous - opposite of even
			//thread strategy
			while(chopsticks[(piTID)%chopsticks.length]){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end while
		
			chopsticks[(piTID)%chopsticks.length]=true;
			System.out.println("Philosopher " + piTID + "picks up first chopstick" );
			
			
			//take second chopstick
			
			while(chopsticks[(piTID-1)%chopsticks.length]){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//while
			
			
			//if even first take one before
			chopsticks[(piTID-1)%chopsticks.length]=true;
			System.out.println("Philosopher " + piTID + "picks up second chopstick" );
		}//endElse
		
	}



	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
			
			
		chopsticks[(piTID-1)%chopsticks.length]=false;
		chopsticks[(piTID)%chopsticks.length]=false;
		notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 * @throws InterruptedException 
	 */
	public synchronized void requestTalk()
	
	{
		
		while(isSomeoneTalking){
			try {
				this.wait();
								
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		isSomeoneTalking=true;
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		isSomeoneTalking=false;
		notifyAll();
	}
	
	
	
}

// EOF
