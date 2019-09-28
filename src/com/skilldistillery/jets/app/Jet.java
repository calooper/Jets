package com.skilldistillery.jets.app;

public abstract class Jet {

	// F I E L D S
	private String model;
	private double speed;
	private int range;
	private long price;
	private int ceiling;
	private String make;

	// C O N S T R U C T O R S
	public Jet() {

	}

	public Jet(String model, String make, double speed, int range, long price, int ceiling, String type) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.ceiling = ceiling;
		this.make = make;

	}

	// M E T H O D S
	public abstract void fly();

	public double getSpeedInMach(double speedMPH) {

		return speedMPH / (767);

	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getCeiling() {
		return ceiling;
	}

	public void setCeiling(int ceiling) {
		this.ceiling = ceiling;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(" Model = ");
		builder.append(model);
		builder.append(", Speed = ");
		builder.append(speed);
		builder.append(", Range = ");
		builder.append(range);
		builder.append(", Price = ");
		builder.append(price);
		builder.append(", Ceiling = ");
		builder.append(ceiling);
		builder.append(", Make = ");
		builder.append(make);
		return builder.toString();
	}

}
