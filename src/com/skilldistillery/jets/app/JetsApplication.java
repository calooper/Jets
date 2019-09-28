package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class JetsApplication {

	// F I E L D S
	private Airfield airfield;

	// M E T H O D S
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		JetsApplication japp = new JetsApplication();
		japp.airfield = new Airfield();
		japp.loadTexts(japp.airfield);
		scanner.close();

	}

	public void loadTexts(Airfield airield) {
		String[] fields;
		String line = "";
		Jet j;

		try (BufferedReader bufIn = new BufferedReader(new FileReader("jetinfo.txt"))) {

			while ((line = bufIn.readLine()) != null) {

				fields = line.split(",");
				String model = fields[0];
				String make = fields[1];
				double speed = Double.parseDouble(fields[2]);
				int range = Integer.parseInt(fields[3]);
				long price = Long.parseLong(fields[4]);
				int ceiling = Integer.parseInt(fields[5]);
				String type = (fields[6]);

				if (type.contentEquals("F")) {
					j = new FighterJet(model, make, speed, range, price, ceiling, type);
					airfield.addJet(j);

				} else if (type.contentEquals("C")) {
					j = new TransportJet(model, make, speed, range, price, ceiling, type);
					airfield.addJet(j);
				} else if (type.contentEquals("T")) {
					j = new TrainerJet(model, make, speed, range, price, ceiling, type);
					airfield.addJet(j);
				}

			}
		} catch (IOException e) {
			System.err.println(e);
		}

		displayMainMenu(airield);
	}

	public void displayMainMenu(Airfield airield) {
		Scanner scanner = new Scanner(System.in);

		boolean keepDisplaying = true;
		while (keepDisplaying == true) {
			System.out.println("-------------------------------------");
			System.out.println("1. List Fleet");
			System.out.println("2. Fly all jets");
			System.out.println("3. View fastest jet");
			System.out.println("4. View jet with longest range ");
			System.out.println("5. Load all Cargo jets");
			System.out.println("6. Dogfight!!!");
			System.out.println("7. Add a jet to the fleet");
			System.out.println("8. Remove a jet from the fleet ");
			System.out.println("9. Quit ");
			System.out.println("-------------------------------------");
			int input = scanner.nextInt();

			switch (input) {
			case 1:
				listAllJets();
				break;
			case 2:
				flyAllJets();
				break;
			case 3:
				fastestJet();
				break;
			case 4:
				longestRange();
				break;
			case 5:
				loadCargo();
				break;
			case 6:
				fightReady();
				break;
			case 7:
				UserAddJet(scanner);
				break;
			case 8:
				removeJet(scanner);
				break;
			case 9:
				System.out.println("Program ending...");
				keepDisplaying = false;
				break;
			default:
				continue;

			}

		}

	}

	public void listAllJets() {
		int count = 0;
		List<Jet> jets = airfield.getJets();
		for (Jet jet : jets) {
			System.out.println(("Index # " + count + " " + jet));
			count++;

		}

	}

	public void flyAllJets() {

		List<Jet> jets = airfield.getJets();
		for (Jet jet : jets) {
			System.out.println();
			jet.fly();
			System.out.println(jet.getMake() + " " + jet.getModel());
			System.out.println("At top speed: " + jet.getSpeed() + " MPH, I have a max");
			System.out.printf("flight time time of %.2f", (jet.getRange() / jet.getSpeed()));
			System.out.print(" hours\n");

		}

	}

	public void fastestJet() {

		List<Jet> jets = airfield.getJets();
		Jet jetinfo = Collections.max(jets, Comparator.comparing(s -> s.getSpeed()));
		System.out.println("MPH: " + jetinfo.getSpeed());
		System.out.printf("MACH: %.3f\n", jetinfo.getSpeedInMach(jetinfo.getSpeed()));
		System.out.println(jetinfo.getMake() + "\n" + jetinfo.getModel());

	}

	public void longestRange() {

		List<Jet> jets = airfield.getJets();
		Jet jetinfo = Collections.max(jets, Comparator.comparing(s -> s.getRange()));
		System.out.println("Range: " + jetinfo.getRange());
		System.out.println(jetinfo.getMake() + "\n" + jetinfo.getModel());

	}

	public void UserAddJet(Scanner scanner) {

		Jet j;

		System.out.println("Type of jet? ( F = fighter, T = trainer, C  = transport)");
		String type = scanner.next();

		if (type.equals("F") || type.equals("T") || type.equals("C")) {

			System.out.println("What is the make? ");
			String make = scanner.next();
			System.out.println("What is the model? ");
			String model = scanner.next();
			System.out.println("What is the speed? ");
			Double speed = scanner.nextDouble();
			System.out.println("What is the range? ");
			int range = scanner.nextInt();
			System.out.println("What is the price? ");
			long price = scanner.nextLong();
			System.out.println("What is the ceiling? ");
			int ceiling = scanner.nextInt();

			if (type.contentEquals("F")) {
				j = new FighterJet(model, make, speed, range, price, ceiling, type);
				airfield.addJet(j);

			} else if (type.contentEquals("C")) {
				j = new TransportJet(model, make, speed, range, price, ceiling, type);
				airfield.addJet(j);
			} else if (type.contentEquals("T")) {
				j = new TrainerJet(model, make, speed, range, price, ceiling, type);
				airfield.addJet(j);
			}

		}

	}

	public void removeJet(Scanner scanner) {
		System.out.println("Which aircraft would you like to remove?");
		System.out.println("Please select a reference number");
		listAllJets();
		int indexRemove = scanner.nextInt();

		airfield.getJets().remove(indexRemove);

	}

	public void loadCargo() {
		List<Jet> jets = airfield.getJets();
		for (Jet jet : jets) {
			if (jet instanceof CargoReady) {
				System.out.println("Cargo ready!");

			}
		}

	}

	public void fightReady() {
		List<Jet> jets = airfield.getJets();
		for (Jet jet : jets) {
			if (jet instanceof CombatReady) {
				System.out.println("Fight ready!");

			}
		}

	}
}
