package com.skilldistillery.jets.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Airfield {

	// F I E L D S
	private List<Jet> jets = new ArrayList<>();

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
