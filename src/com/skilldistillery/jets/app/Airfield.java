package com.skilldistillery.jets.app;

import java.util.ArrayList;
import java.util.List;

public class Airfield {

	// F I E L D S
	private List<Jet> jets = new ArrayList<>();
	
	// M E T H O D S

	public List<Jet> getJets() {
		return jets;
	
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}

	
	public void addJet( Jet jet) {
		
		
		jets.add(jet);
	}


}
