# Bounded Buffer - Produttore/Consumatore

```java
package demo.boundedBuffer;

/**
* An interface for buffers
*
* Figure 4.9
*
* @author Gagne, Galvin, Silberschatz
* Operating System Concepts with Java - Sixth Edition
* Copyright John Wiley & Sons - 2003.
  */


public interface Buffer
{
/**
* insert an item into the Buffer.
* Note this may be either a blocking
* or non-blocking operation.
*/
public abstract void insert(Object item);

	/**
	 * remove an item from the Buffer.
	 * Note this may be either a blocking
	 * or non-blocking operation.
	 */
	public abstract Object remove();
}
```

```java
package demo.boundedBuffer;

/**
* BoundedBuffer.java
*
* This program implements the bounded buffer using Java synchronization.
*
* Figure 7.31
*
* @author Gagne, Galvin, Silberschatz
* Operating System Concepts with Java - Sixth Edition
* Copyright John Wiley & Sons - 2003.
  */

public class BoundedBuffer implements Buffer
{
private static final int   BUFFER_SIZE = 5;

	private int count; // numero di oggetti nel buffer
	private int in;   // index della prossima posizione libera per l'inserimento
	private int out;  // index della prossima posizione piena per il prelevamento
	private Object[] buffer;

	public BoundedBuffer()
	{
		// Il buffer e' inizialmente vuoto
		count = 0;
		in = 0;
		out = 0;

		buffer = new Object[BUFFER_SIZE];
	}

	public synchronized void insert(Object item) {

		// se il buffer e' pieno chiamo il metodo wait
		// il thread entra nel wait set
		while (count == BUFFER_SIZE) {
			try {
				wait();
			}
			catch (InterruptedException e) { }
		}

		// aggiungo un oggetto al buffer
		++count;
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;

		if (count == BUFFER_SIZE)
			System.out.println("Producer Entered " + item + " Buffer FULL");
		else
			System.out.println("Producer Entered " + item + " Buffer Size = " +  count);

		// Sveglio uno dei thread presenti nel wait set
		notify();
	}

	public synchronized Object remove() {
		Object item;

		// se il buffer e' vouto chiamo il metodo wait
		// il thread entra nel wait set
		while (count == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) { }
		}

		// rimuovo un ogggetto dal buffer
		--count;
		item = buffer[out];
		out = (out + 1) % BUFFER_SIZE;

		if (count == 0)
			System.out.println("Consumer Consumed " + item + " Buffer EMPTY");
		else
			System.out.println("Consumer Consumed " + item + " Buffer Size = " + count);

		// Sveglio uno dei thread presenti nel wait set
		notify();

		return item;
	}


}

```

L'applicazione [demo.boundedBuffer.Factory](./src/main/java/demo/boundedBuffer/Factory.java), 
il thread produttore [demo.boundedBuffer.Producer](./src/main/java/demo/boundedBuffer/Producer.java) 
e quello consumatore [demo.boundedBuffer.Consumer](./src/main/java/demo/boundedBuffer/Consumer.java).