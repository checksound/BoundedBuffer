package demo.boundedBuffer;
/**
 * This creates the buffer and the producer and consumer threads.
 *
 * Figure 7.16
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Sixth Edition
 * Copyright John Wiley & Sons - 2003.
 */
public class Factory
{
	public static void main(String args[]) {
		
		// crea il buffer condiviso
		Buffer server = new BoundedBuffer();

		// Crea i thread per il produttore e il consumatore
		// Entrambi condividono lo stesso buffer
		Thread producerThread = new Thread(new Producer(server));
		Thread consumerThread = new Thread(new Consumer(server));

		// avvia i due thread
		producerThread.start();
		consumerThread.start();
	}
}
