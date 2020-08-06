package assignment2;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		//creating a single instance of the CarShowroom class. This instance will be passed to constructors of Buyer and Seller objects
		CarShowroom cS = new CarShowroom();
		int day = 1;//this will be used to keep track of the day the programme is on
		Random rand = new Random();//will allow be used to randomise the number of buyers and sellers each day
		int numSellers;
		int numBuyers;

		while(day<=30) {//this will ensure 30 days are simulated
			//printing to the console announcing the number of cars available at the start of each day
			System.out.printf("Day %s beginning. There are %s cars in the showroom today.\n", day, cS.getCars().size());
			//ensuring that a random number of sellers and buyers between 0 and 3 can arrive at the dealership each day
			numSellers = rand.nextInt(4);
			numBuyers = rand.nextInt(4);
			//calling two initialisation methods that will create the buyer and seller objects and create their respective threads
			initBuy(numBuyers, cS);
			initSell(numSellers, cS);
			day++;
			//adding a small delay (1 second) at the end of each day (loop iteration) to slow down the execution of the programme
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("");
		}
	}
	
	//citation: Dr. Patrick Mannion. Enterprise Java Programming - Lecture 3a, slide 25
	//this is the initialise method for the buyer objects and their threrads
	public static void initBuy(int numBuyers, CarShowroom cS) {
		Thread t;//declaring a new instance of the Thread class
		Buyer b;
		for(int i=0; i<numBuyers; i++) {
			b = new Buyer(cS);
			t = new Thread(b);//initialising the instance of the Thread class and passing the Buyer object to it
			t.start();//starting the thread
		}
	}

	//this is the initialise method for the seller objects and their threads
	public static void initSell(int numSellers, CarShowroom cS) {
		Thread t;//declaring a new instance of the Thread class
		Seller s;
		for(int i=0; i<numSellers; i++) {
			s = new Seller(cS);
			t = new Thread(s);//initialising the instance of the Thread class and passing the Seller object to it
			t.start();//starting the thread
		}
	}
}