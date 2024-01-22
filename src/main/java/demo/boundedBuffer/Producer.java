package demo.boundedBuffer;
/**
 * This is the producer thread for the bounded buffer problem.
 *
 * Figure 7.14
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Sixth Edition
 * Copyright John Wiley & Sons - 2003.
 */


import java.util.*;

public class Producer implements Runnable
{

	private  Buffer buffer;

	public Producer(Buffer b) {
		buffer = b;
	}

	public void run(){
		
		Date message;

		while (true) {
			
			// il thread si mette in attesa per un po'
			System.out.println("Producer - napping");
			SleepUtilities.nap(2);

			// crea un oggetto e lo inserisce nel buffer
			message = new Date();
			System.out.println("Producer  - produced " + message);

			buffer.insert(message);
		}
	}

}
