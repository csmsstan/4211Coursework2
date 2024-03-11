package pizzafactory;
//Javadoc here is broken, to be fixed
/**
 *  <h1> Pizza Factory Simulation Main </h1>
 *  
 *  This class acts as the main method for the simulation.
 *  
 *  @author Samuel Stanton
 *  @version 0.1
 *  @since 2024-03-11
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import pizzafactory.classes.Pizza;

public class pizzaFactorySimulationMain {
	
	private static Scanner input = new Scanner(System.in);
	
	private static final ArrayList<Pizza> pizzaMenu = new ArrayList<Pizza>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Call method to import menu as an array of Pizza objects
		
		loadMenu();
		
		// Console menu code + GUI code to go here
		
		
		}
		
	}
	
	/** Loads all the pizzas from menu.txt into associated arrayList PizzaMenu.
	 * 
	 * 
	 */
	private static void loadMenu() {
		
		// Initialise file reader: We are using a CSV to store the menu, so make sure to set the delimiter
		Scanner fileRead = null;
		boolean fileSetup = true;
		do {
			try {
				fileRead = new Scanner(new FileReader("menu.txt"));
				fileRead.useDelimiter(",");
				if (fileRead.hasNext()) {
					fileSetup = false;
				}
			} catch (FileNotFoundException e) {
				System.out.println(
						"The menu.txt file has not been found. Please make sure the file is present and press Enter to continue:");
				input.nextLine();
			}
		} while (fileSetup);
		
		// Read through file to create arraylist of pizzas:
		
		while (fileRead.hasNext()) {
			int pizzaId = fileRead.nextInt();
			String pizzaName = fileRead.next();
			int pizzaCookTimeSeconds = fileRead.nextInt();
			fileRead.nextLine();
			pizzaMenu.add(new Pizza(pizzaId, pizzaName, pizzaCookTimeSeconds));
		}
		fileRead.close();
	}
}
