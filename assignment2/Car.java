package assignment2;

import java.util.Random;

public class Car {
	private String reg, colour;
	private double saleVal;
	
	public   String counties[] = {"C", "CE", "CN", "CW", "D", "DL", "G", "KE", "KK", "KY", "L", "LD", "LH", "LM", "LS", "MH", "MN",
			"MO", "OY", "RN", "SO", "T", "W", "WH", "WX", "WW"};
	public static final String colours[] = {"red", "blue", "purple", "green", "yellow", "orange", "black", "white", "brown", "silver"};
	public static final String years[] = {"90", "91", "92", "93", "94", "95", "96", "97", "98", "99",
			"00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	public Car(){
		Random rand = new Random();//creating an instance of the Random class so a random number generator can be used
		//implementing a string format for the car registration. The year, county and build number (between 1 and 10000) are all randomly generated
		this.setReg(String.format("%s-%s-%s", years[rand.nextInt(years.length)], counties[rand.nextInt(counties.length)], rand.nextInt(9999) + 1));
		this.setSaleVal(rand.nextInt(19000) + 1000);//random sale value between 20000 and 1000
		this.setColour(colours[rand.nextInt(colours.length)]);//random colour from the colours array
		rand = null;//nullifying the Random object to prepare it for Garbage collection
	}
	
	//setters and getters for the three fields used in the creating of the Car objects
	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public double getSaleVal() {
		return saleVal;
	}

	public void setSaleVal(double saleVal) {
		this.saleVal = saleVal;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	@Override
	//this will be used to easily print the details of a car to the console
	public String toString() {
		return getColour() + " car with registration " + getReg() + " worth €" + getSaleVal();
	}
}