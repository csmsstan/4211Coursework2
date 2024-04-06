package pizzafactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import pizzafactory.classes.Customer;
import pizzafactory.classes.Order;
import pizzafactory.classes.OvenThread;
import pizzafactory.classes.Pizza;

/**
 *  <h1> Pizza Factory Simulation Main </h1>
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
	
	public static final ArrayList<Pizza> pizzaMenu = new ArrayList<Pizza>();
	
	public static ArrayList<Customer> totalCustomerHistory = new ArrayList<Customer>();
	
	public static ArrayList<Order> totalOrderHistory = new ArrayList<Order>(); 
	
	public static PriorityBlockingQueue<Order> OrderBlockingQueue = new PriorityBlockingQueue<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Call method to import menu as an array of Pizza objects
		
		loadMenu();
		
		// Console menu code + GUI code to go here
		
		int nextOrderId = 0;
		int nextCustomerId = 0;
		
		while (true) {
			System.out.print("==================\n"
					+ "Menu Selection\n"
					+ "==================\n"
					+ "Options:\n"
					+ getPizzaMenu()
					+ "==================\n"
					+ "Pick: \n> ");
			
			int userPizzaId = input.nextInt();
			
			Pizza pizzaSelected = getUserPizzaByPizzaId(userPizzaId);
			
			if (pizzaSelected == null) {
				System.out.println("Pizza Selected with Id" + userPizzaId + " Does Not Exist, Please Try Again.");
				continue;
			}
				
			System.out.print("Please Select Quantity \n> ");
			
			int userPizzaQuantity = input.nextInt();
			
			int pizzaDone = 0;
			int toDoPizza = userPizzaQuantity;
			
			double customerPizzaWaitTime = Math.ceil((double)userPizzaQuantity/runningOvens) * pizzaSelected.getPizzaCookTimeSeconds();
			
			System.out.println("Your Wait Time is: " + customerPizzaWaitTime + "s");
			
			
			Customer customer = new Customer(nextCustomerId, pizzaSelected, userPizzaQuantity);
			nextCustomerId++;
			totalCustomerHistory.add(customer);
			
			
			Order customerOrder = customer.placeOrder(nextOrderId);
			nextOrderId++;
			//Saves each customerOrder to totalOrderHistory, can be used to review total order history each customer made
			totalOrderHistory.add(customerOrder);
			System.out.println("Your Customer Id is: " + customerOrder.getCustomerNumber());
			
			OvenThread[] oven = new OvenThread[runningOvens];
			while(userPizzaQuantity != pizzaDone) {
				for(int i = 0; i < runningOvens; i++) {
					// Runs if there is no oven existing and if there is pizza left to do
					if (oven[i] == null && toDoPizza > 0) {
						// Create oven
						oven[i]  = new OvenThread("Oven "+i, pizzaSelected); 
						// Starts oven
						oven[i].start();
						toDoPizza--;
					} 
					// Runs if oven[i] is not null and if oven is finished with cooking
					else if (oven[i] != null && oven[i].getCookingStatus() == false) {
						pizzaDone++;
						try {
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
			if (customer.serveCustomer(customerOrder)) {
				System.out.println("Customer Id with "+ customerOrder.getCustomerNumber() + ", your pizza is finished and ready to be served.");
			}else {
				continue;
			}

			System.out.print("Press Enter to Order Again...");
			input.next();
			
		}
				
	}
		
	//Goes through pizzaMenu and stores getPizzaId() and getPizzaName() in menu string
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
