package assignment2;

import java.util.concurrent.atomic.AtomicInteger;

public class Buyer implements Runnable{//implementing the Runnable interface to ensure each instance of Buyer runs on a separate thread
	private static AtomicInteger buyerNum = new AtomicInteger(1);//this will be used to count the total number of unique buyers
	private static AtomicInteger purchaseCounter = new AtomicInteger(1);//this will be used to count the total numbers of purchases from the showroom
	private CarShowroom cS;

	public Buyer(CarShowroom cS) {//accepting an instance of CarShowroom as an argument
		this.cS = cS;//storing the reference to the CarShowroom object in a private field
	}
	
	//this run method will be called when the thread is started (t.start() in Main.java initBuy(int, CarShowroom) method)
	//this is where the Buyer interacts with the CarShowroom
	public  void run() {
		int uniqueBuyerId = buyerNum.getAndIncrement();//storing the unique id in private integer field and assigning to the Buyer when it interacts with showroom
		System.out.printf("A new buyer #%s just appeared.\n", uniqueBuyerId);
		synchronized(cS){//synchronising on the showroom object
			while(cS.isEmpty()) {//checking if there is at least one Car to buy in the showroom
				System.out.printf("Buyer #%s wants to buy a car, but the showroom is empty.\n", uniqueBuyerId);
				try {
					cS.wait();//this suspends the thread while the showroom is empty. The thread will be woken up by a Seller thread when a car is sold to showroom
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.printf("Buyer %s bought a %s. This is purchase number %s.\n", uniqueBuyerId, cS.takeCar(), purchaseCounter.getAndIncrement());
			/*this notifies the Seller threads when a car is bought from the showroom.
			 *  Important for waking seller threads that are waiting for a full showroom to become not full
			 */
			cS.notifyAll();
		}
	}
}