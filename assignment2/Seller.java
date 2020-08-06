package assignment2;

import java.util.concurrent.atomic.AtomicInteger;

public class Seller implements Runnable{//implementing the Runnable interface to ensure each instance of Seller runs on a separate thread
	private static AtomicInteger sellerNum = new AtomicInteger(1);//this will be used to count the total number of unique sellers
	private static AtomicInteger saleCounter = new AtomicInteger(1);//this will be used to count the total numbers of sales to the showroom
	private CarShowroom cS;
	private Car car;

	public Seller(CarShowroom cS) {//accepting an instance of CarShowroom as an argument
		this.cS = cS;//storing the reference to the CarShowroom object in a private field
		this.car = new Car();//creating a new Car object for the Seller object
	}
	
	//this run method will be called when the thread is started (t.start() in Main.java initSell(int, CarShowroom) method)
	//this is where the Seller interacts with the CarShowroom
	public void run() {
		int uniqueSellerId = sellerNum.getAndIncrement();//storing the unique id in private integer field and assigning to the Seller when it interacts with showroom
		System.out.printf("A new seller #%s just appeared with a %s.\n", uniqueSellerId, this.car.toString());
		synchronized(cS) {//synchronising on the showroom object
			while(cS.isFull()) {//
				System.out.printf("Seller %s is trying to sell a car, but the showroom is full.\n", uniqueSellerId);
				try {
					cS.wait();//this suspends the thread while the showroom is full. The thread will be woken up by a buyer thread when a car is bought from showroom
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cS.addCar(this.car);//adding a car to the showroom (sale to the showroom by the seller)
			System.out.printf("Seller %s sold their %s to the showroom. This is sale number %s.\n", uniqueSellerId, this.car, saleCounter.getAndIncrement());
			/*this notifies the Buyer threads when a car is sold to the showroom.
			 *  Important for waking buyer threads that are waiting for an empty showroom to become not empty
			 */
			cS.notifyAll();
		}
	}
}