/**
 * Class DiningPhilosophers
 * The main starter.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */

package a3_26789579;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DiningPhilosophers
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/**
	 * This default may be overridden from the command line
	 */
	public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 5;

	/**
	 * Dining "iterations" per philosopher thread
	 * while they are socializing there
	 */
	public static final int DINING_STEPS = 10;

	/**
	 * Our shared monitor for the philosphers to consult
	 */
	public static Monitor soMonitor = null;

	/*
	 * -------
	 * Methods
	 * -------
	 */

	/**
	 * Main system starts up right here
	 */
	public static void main(String[] argv)
	{
		try
		{
			/*
			 * TODO:
			 * Should be settable from the command line
			 * or the default if no arguments supplied.
			 */
			int iPhilosophers = userInput();

			// Make the monitor aware of how many philosophers there are
			soMonitor = new Monitor(iPhilosophers);

			// Space for all the philosophers
			Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];

			// Let 'em sit down
			for(int j = 0; j < iPhilosophers; j++)
			{
				aoPhilosophers[j] = new Philosopher();
				aoPhilosophers[j].start();
			}

			System.out.println
			(
				iPhilosophers +
				" philosopher(s) came in for a dinner."
			);

			// Main waits for all its children to die...
			// I mean, philosophers to finish their dinner.
			for(int j = 0; j < iPhilosophers; j++)
				aoPhilosophers[j].join();

			System.out.println("All philosophers have left. System terminates normally.");
			//int x=Math.floorMod(-1, 4);
			//System.out.println(x);
		}
		catch(InterruptedException e)
		{
			System.err.println("main():");
			reportException(e);
			System.exit(1);
		}
	} // main()

	/**
	 * Outputs exception information to STDERR
	 * @param poException Exception object to dump to STDERR
	 */
	public static void reportException(Exception poException)
	{
		System.err.println("Caught exception : " + poException.getClass().getName());
		System.err.println("Message          : " + poException.getMessage());
		System.err.println("Stack Trace      : ");
		poException.printStackTrace(System.err);
	}
	public static int userInputScanner(){
	Scanner input = new Scanner(System.in);
	System.out.print("Enter number of philosophers: ");
	
	int num1;
	try{
	num1 = input.nextInt();
	}
	
	catch(Exception e){
		System.out.println("Your input is invalid."
				+ "Default will be used");
		return DEFAULT_NUMBER_OF_PHILOSOPHERS;
	}
	
	if(num1<0)
	{
		System.out.println("Your number is invalid. "
					+ "Default will be used");
		return DEFAULT_NUMBER_OF_PHILOSOPHERS;
	}
	
	return num1;
	}
	
	public static int userInput(){
		int result=DEFAULT_NUMBER_OF_PHILOSOPHERS;
		
		String input= JOptionPane.showInputDialog("Please enter number"
				+ " of pholosophers: ");
		
		int resultOne;
		
		try{
			resultOne = Integer.parseInt(input);
			}
			
			catch(Exception e){
				System.out.println("Your input is invalid."
						+ "Default will be used");
				return DEFAULT_NUMBER_OF_PHILOSOPHERS;
			}
		
		
		if(resultOne<0){
			JOptionPane.showMessageDialog(null, "Your number is invalid. "
					+ "Default will be used");
			return result;
		}
		
		return resultOne;
		
	}
}

// EOF
