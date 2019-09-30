package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
		String file = "jetinfo.txt";
		japp.loadTexts(japp.airfield, file);
		scanner.close();
		

	}

	public List<Jet> loadTexts(Airfield airield, String file) {
		String[] fields;
		String line = "";
		Jet j;

		try (BufferedReader bufIn = new BufferedReader(new FileReader(file))) {

			while ((line = bufIn.readLine()) != null) {

				fields = line.split(",");
				String model = fields[0];
				String make = fields[1];
				double speed = Double.parseDouble(fields[2]);
				int range = Integer.parseInt(fields[3]);
				long price = Long.parseLong(fields[4]);
				String type = (fields[5]);

				if (type.contentEquals("F")) {
					j = new FighterJet(model, make, speed, range, price, type);
					airfield.addJet(j);

				} else if (type.contentEquals("C")) {
					j = new TransportJet(model, make, speed, range, price, type);
					airfield.addJet(j);
				} else if (type.contentEquals("T")) {
					j = new TrainerJet(model, make, speed, range, price, type);
					airfield.addJet(j);
				}

			}
		} catch (IOException e) {
			System.err.println(e);
		}

		displayMainMenu(airield);
		return airfield.getJets();
	}

	public void displayMainMenu(Airfield airield) {
		Scanner scanner = new Scanner(System.in);

		boolean keepDisplaying = true;
		while (keepDisplaying == true) {
			System.out.println("-------------------------------------");
			System.out.println("1. List Fleet");
			System.out.println("2. Fly a jets");
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
				flyAJet(scanner, airfield);
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
				removeJet(scanner, airield);
				break;
			case 9:
				try {
					saveFile(scanner);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Program terminated.");
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

	public void flyAJet(Scanner scanner, Airfield airfield) {
		boolean keepGoing = true;

		while (keepGoing == true) {
			System.out.println("-----------------------");
			System.out.println("1. Fly all jets");
			System.out.println("2. Fly a specific jet");
			System.out.println("3. Go back to main");
			System.out.println("-----------------------");
			int userChoice = scanner.nextInt();

			switch (userChoice) {
			case 1:
				flyAllJets();
				break;
			case 2:
				flySpecificJets(scanner);
				break;
			case 3:
				displayMainMenu(airfield);
				break;

			default:
				break;
			}
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

	public void flySpecificJets(Scanner scanner) {

		System.out.println("Please type the model of the plane you want to fly. e.g. F/A-18 \n");
		listAllJets();
		String userInput = scanner.next();
		List<Jet> jets = airfield.getJets();
		for (Jet jet : jets) {

			if (jet.getModel().contentEquals(userInput)) {
				System.out.println();
				jet.fly();
				System.out.println(jet.getMake() + " " + jet.getModel());
				System.out.println("At top speed: " + jet.getSpeed() + " MPH, I have a max");
				System.out.printf("flight time time of %.2f", (jet.getRange() / jet.getSpeed()));
				System.out.print(" hours\n");
			}
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

		System.out.println("What type of jet do you wan to add to the airfield inventory? ");
		System.out.println("F = fighter, T = trainer, C  = transport");
		String type = scanner.next();

		if (type.equalsIgnoreCase("F") || type.equalsIgnoreCase("T") || type.equalsIgnoreCase("C")) {

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

			if (type.equalsIgnoreCase("F")) {
				j = new FighterJet(model, make, speed, range, price, type);
				airfield.addJet(j);

			} else if (type.equalsIgnoreCase("C")) {
				j = new TransportJet(model, make, speed, range, price, type);
				airfield.addJet(j);
			} else if (type.equalsIgnoreCase("T")) {
				j = new TrainerJet(model, make, speed, range, price, type);
				airfield.addJet(j);
			}

		}

	}

	public void removeJet(Scanner scanner, Airfield airield) {
		System.out.println("Which aircraft would you like to remove?");
		System.out.println("Please select an index number: ");
		listAllJets();
		int indexRemove = scanner.nextInt();

		System.out.println("Are you sure you want to remove: Y / N ");
		String userInput = scanner.next();
		if (userInput.equalsIgnoreCase("Y")) {

			System.out.println();
			airfield.getJets().remove(indexRemove);
		} else if (userInput.equalsIgnoreCase("N")) {
			displayMainMenu(airield);
		} else
			removeJet(scanner, airield);

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

	public void saveFile(Scanner scanner) throws IOException {


		System.out.println("Do you want to save your current inventory to a text file? (Y / N)");
		String userInput = scanner.next();

		if (userInput.equalsIgnoreCase("Y")) {
			
			System.out.println("What do you want to name the file? ");
			String fileName = scanner.next();

			List<Jet> jets = airfield.getJets();
			FileWriter writer = new FileWriter( fileName + ".txt");

			for (Jet jet : jets) {
				writer.write(jet + System.lineSeparator());
			}
			System.out.println("File successfully saved as " + fileName + ".txt");
			writer.close();
		}

		else { int status = 0;
		System.out.println("Program terminated");
		System.exit(status);
			

		}
	
	}
}
