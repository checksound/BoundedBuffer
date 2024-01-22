package demo.boundedBuffer;
/**
 * This is the consumer thread for the bounded buffer problem.
 *
 * Figure 7.15
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Sixth Edition
 * Copyright John Wiley & Sons - 2003.
 */
import java.util.*;

public class Consumer implements Runnable
{
	
	private  Buffer buffer;
	
	public Consumer(Buffer b) {
		buffer = b;
	}

	public void run(){
		
		Date message;

		while (true)
		{
			// il thread si mette in attesa per un po'
			System.out.println("Consumer -  napping");
			SleepUtilities.nap(3);

			// Preleva un oggetto dal buffer
			System.out.println("Consumer - wants to consume.");

			message = (Date)buffer.remove();
			System.out.println("Consumer - " + message);
		}
		
	}

	
}


