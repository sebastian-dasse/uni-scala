package actorthread;

import org.junit.Test;

/**
 * Can create up to 104.400 Threads
 * (on an Intel i7-3517U CPU @ 1.9h GHz with 4.00 GB RAM under Windows 8.1 Pro).
 */
public class ThreadCreationTest {

	@Test
	public void howManyThreads() {
		for (int i = 0; i < 10000000; i++) {
			new UselessThread().start();
			if (i % 100 == 0) {
				System.out.printf("Thread %d started.\n", i);
			}
		}
	}
	
	class UselessThread extends Thread {
		@Override
		public void run() {
			try {
				sleep(120000);
			} catch (InterruptedException e) {}
		}
	}
}