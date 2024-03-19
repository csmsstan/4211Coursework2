package pizzafactory;

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
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import pizzafactory.classes.Order;
import pizzafactory.classes.Pizza;

public class pizzaFactorySimulationMain {
	
	private static Scanner input = new Scanner(System.in);
	
	public static final ArrayList<Pizza> pizzaMenu = new ArrayList<Pizza>();
	
	public static PriorityBlockingQueue<Order> OrderBlockingQueue = new PriorityBlockingQueue<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Call method to import menu as an array of Pizza objects
		
		loadMenu();
		
		// Console menu code + GUI code to go here
		
		//Test code. Use OrderBlockingQueue.add() to add new orders to the queue, either created in the parameters or passed in from elsewhere.
		OrderBlockingQueue.add(	new Order(1, 1, pizzaMenu.get(0), 3));
		OrderBlockingQueue.add( new Order(2, 2, pizzaMenu.get(1), 2));
		OrderBlockingQueue.add( new Order(3, 3, pizzaMenu.get(3), 1));
		
		while(true) {
			//Use OrderBlockingQueue.poll() to pull the next order out of the queue:
			Order o = OrderBlockingQueue.poll();
			System.out.println(o.toString());
		}
		
		}
		
	
	
	/** Loads all the pizzas from menu.csv into associated arrayList PizzaMenu.
	 * 
	 * @exception FileNotFoundException An exception will be thrown if menu.csv is not found in the root of the project.
	 */
	private static void loadMenu() {
		
		// Initialise file reader: We are using a CSV to store the menu, so make sure to set the delimiter
		Scanner fileRead = null;
		boolean fileSetup = true;
		do {
			try {
				fileRead = new Scanner(new FileReader("menu.csv"));
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
