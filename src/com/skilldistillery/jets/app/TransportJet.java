package com.skilldistillery.jets.app;

public class TransportJet extends Jet implements CargoReady {

	// C O S T R U C T O R S
	public TransportJet(String model, String make, double speed, int range, long price, String type) {
		super(model, make, speed, range, price, type);
	}

	@Override
	public void fly() {

		System.out.println("I am a cargo jet!");

	}

	@Override
	public void loadCargo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void noLoadCargo() {
		// TODO Auto-generated method stub

	}

}
