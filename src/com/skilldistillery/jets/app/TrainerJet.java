package com.skilldistillery.jets.app;

public class TrainerJet extends Jet {

	// C O S T R U C T O R S
	public TrainerJet(String model, String make, double speed, int range, long price, String type) {
		super(model, make, speed, range, price, type);
	}

	@Override
	public void fly() {

		System.out.println("I am a trainer jet");

	}

}
