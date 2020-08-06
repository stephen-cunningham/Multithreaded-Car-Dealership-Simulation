package assignment2;

import java.util.ArrayList;

public class CarShowroom {
	private int capacity = 5;//this is the max capacity of the showroom (i.e. the max number of cars the showroom can hold at once)
	private ArrayList<Car> cars;//this will hold a list of cars (i.e. every car currently in the showroom)

	CarShowroom(){
		this.cars = new ArrayList<>();//creates an ArrayList that stores the cars associated with the CarShowroom object
	}
	
	//this checks if the showroom is empty and returns a Boolean
	public Boolean isEmpty() {
		if(this.cars.size() == 0) {//if there are no cars, then true
			return true;
		}
		return false;//false if greater than 0 cars in the ArrayList
	}
	
	//this checks if the showroom is empty and returns a Boolean
	public Boolean isFull() {
		if(this.cars.size() == capacity) {//true if the showroom has reached capacity
			return true;
		}
		return false;//false if there are less than 5 cars
	}
	
	//this adds a car to the showroom (will be used by the seller when they sell a car to the showroom)
	public Car addCar(Car c) {
		this.cars.add(c);
		return c;//returns the car object for the sake of printing its details
	}
	
	//this takes a car from the showroom (will be used by the buyer when they buy a car from the showroom)
	public Car takeCar() {
		Car c = this.cars.remove(0);//this ensures the first (oldest) car in the list is removed when the Buyer object 'buys' from the showroom
		return c;//returns the car object for the sake of printing its details
	}
	
	//this is a getter for the list of cars in the showroom - used to get the number of cars in the showroom at the start of every day
	public ArrayList<Car> getCars() {
		return cars;
	}
}