package com.skilldistillery.jets.app;

public class FighterJet extends Jet implements CombatReady {

	// C O S T R U C T O R S
	public FighterJet(String model, String make, double speed, int range, long price, String type) {
		super(model, make, speed, range, price, type);
	}

	@Override
	public void fly() {

		System.out.println("I am a fighter jet");

	}

	@Override
	public void fight() {

		// TODO Auto-generated method stub

	}

}
