package pizzafactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import pizzafactory.classes.Customer;
import pizzafactory.classes.Order;
import pizzafactory.classes.OvenThread;
import pizzafactory.classes.Pizza;

/**
 *  
 *  This class acts as the main method for the simulation.
 *  
 *  @author Samuel Stanton
 *  @author Ewa Wagner
 *  @version 0.2
 *  @since 2024-03-11
 *  
 */

public class pizzaFactorySimulationMain {
	
	private static Scanner input = new Scanner(System.in);
	
	private static int runningOvens = 5;
	
	/**
	 * An ArrayList holding all pizza objects for reference.
	 */
	public static final ArrayList<Pizza> pizzaMenu = new ArrayList<Pizza>();
	
	/**
	 * An ArrayList holding a list of all customer objects that have been used.
	 */
	public static ArrayList<Customer> totalCustomerHistory = new ArrayList<Customer>();
	
	/**
	 * An ArrayList holding all past orders.
	 */
	public static ArrayList<Order> totalOrderHistory = new ArrayList<Order>(); 
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Call method to import menu as an array of Pizza objects
		
		loadMenu();
		
		// Have user choose how they want order to be decided:
		
		int nextOrderId = 0;
		int nextCustomerId = 0;
		boolean customerIsRandom = false;
		while (true) {
			System.out.print("================== \n"
					+ "Option Select: \n"
					+ "==================\n"
					+ "1. New Random Customer \n"
					+ "2. New Customer (Custom Order) \n"
					+ "================== \n"
					+ "Pick: \n> ");
			
			int menuOption = input.nextInt();
			if (menuOption == 1) {
				customerIsRandom = true; 
			} else if (menuOption == 2) {
				customerIsRandom = false; 	
			} else {
				System.out.println("\nYour input is invalid! \n");
				continue;
			}
			
			//Depending on option selected, either generate a random customer or let the user specify, then come back here
			Customer customer = createCustomer(customerIsRandom, nextCustomerId);
			nextCustomerId++;
			totalCustomerHistory.add(customer);
			
			int pizzaDone = 0;
			int userPizzaQuantity = customer.getPizzaQuantity();
			int toDoPizza = userPizzaQuantity;
			
			//Set up customer order to be cooked
			Order customerOrder = customer.placeOrder(nextOrderId);
			nextOrderId++;
			System.out.println("Order to be cooked: \n" + customerOrder.toString());
			
			//Saves each customerOrder to totalOrderHistory, can be used to review total order history each customer made
			totalOrderHistory.add(customerOrder);
			
			OvenThread[] oven = new OvenThread[runningOvens];
			while(userPizzaQuantity != pizzaDone) {
				for(int i = 0; i < runningOvens; i++) {
					// Runs if there is no oven existing and if there is pizza left to do
					if (oven[i] == null && toDoPizza > 0) {
						// Create oven
						oven[i]  = new OvenThread("Oven "+i, customerOrder.getOrderPizza()); 
						// Starts oven
						oven[i].start();
						toDoPizza--;
					} 
					// Runs if oven[i] is not null and if oven is finished with cooking
					else if (oven[i] != null && oven[i].getCookingStatus() == false) {
						pizzaDone++;
						try {
							// Set order to cooked
							customerOrder.cookOrder();
							// Stops oven
							oven[i].join();
							// Resets oven[i] to null
							oven[i] = null; 
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
					
				}
				//Sleep 0.5s
		        try {
		            Thread.sleep(500);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			}
			
			// Checks if pizza is served to the correct customer by checking their customerNumber
			if (Customer.serveCustomer(customerOrder)) {
				System.out.println("Customer Id with "+ customerOrder.getCustomerNumber() + ", your pizza is finished and ready to be served.");
			}else {
				continue;
			}

			System.out.print("Type Anything and Press Enter to Order Again...");
			input.next();
			
		}
				
	}
	/** Creates a new customer for the factory simulation based on user requirements.	
	 * 
	 * @param isRandom Decides whether the customer is to be generated randomly or specified.
	 * @param nextCustomerId The ID that the customer should have.
	 * @return The completed, constructed customer object.
  	 * [SS} 
	 */
	
	public static Customer createCustomer(boolean isRandom, int nextCustomerId) {
		if (isRandom) {
			Customer outputCustomer = new Customer (nextCustomerId, pizzaMenu);
			return outputCustomer;
		}
		else {
			boolean pizzaSelectionComplete = false;
			int userPizzaId = 0;
			while (!pizzaSelectionComplete) {
				System.out.print("==================\n"
						+ "Menu Selection\n"
						+ "==================\n"
						+ "Options:\n"
						+ getPizzaMenu()
						+ "==================\n"
						+ "Pick: \n> ");

				userPizzaId = input.nextInt();
				boolean success;
				try {
					Pizza pizzaTest = pizzaMenu.get(userPizzaId);
					success = true;
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("Pizza with ID " + userPizzaId + "does not exist, please try again.");
					success = false;
				}
				
				if (success) {
					pizzaSelectionComplete = true;
				}
			}
				
			Pizza pizzaSelected = getUserPizzaByPizzaId(userPizzaId);
			System.out.print("Please Select Quantity \n> ");

			int userPizzaQuantity = input.nextInt();

			double customerPizzaWaitTime = Math.ceil((double)userPizzaQuantity/runningOvens) * pizzaSelected.getPizzaCookTimeSeconds();

			System.out.println("Your Wait Time is: " + customerPizzaWaitTime + "s");


			Customer outputCustomer = new Customer(nextCustomerId, pizzaSelected, userPizzaQuantity);
			return outputCustomer;
			
		}
		
	}

	/** Goes through pizzaMenu and stores getPizzaId() and getPizzaName() in menu string
	 * 
	 * @author Ewa Wagner
	 * @return The pizza ID and name as a string.
	 */
	public static String getPizzaMenu() {
		
		String menu = "";
		for (int i = 0; i < pizzaMenu.size(); i++) {
			menu += (pizzaMenu.get(i).getPizzaId()
					+". "
					+ pizzaMenu.get(i).getPizzaName()
					+ "\n");
		}
		return menu;
	}
	
	/** Iterates through the menu and gets the relevant pizza object using its ID.
	 * 
	 * @author Ewa Wagner
	 * @param pizzaId The ID of the pizza we want to acquire.
	 * @return The pizza that was requested.
	 */
	public static Pizza getUserPizzaByPizzaId(int pizzaId) {
		// Checks in pizzaMenu if getPizzaId() matches with user input
		for (int i = 0; i < pizzaMenu.size(); i++) {
			if (pizzaMenu.get(i).getPizzaId() == pizzaId) {
				return pizzaMenu.get(i);
			}
		}
		// If user input dosen't match with pizzaId, return nothing
		return null;
	}
	
	/** Loads all the pizzas from menu.csv into associated arrayList PizzaMenu.
	 * 
	 * @exception FileNotFoundException An exception will be thrown if menu.csv is not found in the root of the project.
	 * @author Samuel Stanton
	 * 
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
