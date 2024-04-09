package pizzafactory.classes;
import java.util.ArrayList;
import java.util.Random;

/** 
 * A customer who may place an order for a pizza.
 * 
 * @author Samuel Stanton
 * @version 0.1
 * @since 19/03/2024
 *
 */
public class Customer {
	static int customerNumber;
	Pizza customerPizza;
	int pizzaQuantity;
	Order customerOrder;
	ArrayList<Pizza> pizzaMenu;
	private Random randomGenerator;
	static boolean isServed;
	
	/**
	 * Creates a new customer with a randomly generated order.
	 * 
	 * @param customerNumber the customer's ID number.
	 * @param pizzaMenu A copy of the menu, for the customer to look at. (there is probably a better way of doing this)
	 */
	public Customer(int customerNumber, ArrayList<Pizza> pizzaMenu) {
		Customer.customerNumber = customerNumber;
		this.pizzaMenu = pizzaMenu;
		isServed = false;
		randomGenerator = new Random();
		int menuItem = randomGenerator.nextInt(pizzaMenu.size());
		customerPizza = pizzaMenu.get(menuItem);
		pizzaQuantity = randomGenerator.nextInt(5);
	}

	/**
	 * Creates a new customer with a specific order.
	 * 
	 * @param customerNumber the customer's ID number.
	 * @param customerPizza the pizza that the customer wants to order.
  	 * @param pizzaQuantity the amount of pizzas that the customer wants to order.
	 * 
	 */
		public Customer(int customerNumber, Pizza customerPizza, int pizzaQuantity) {
		Customer.customerNumber = customerNumber;
		isServed = false;
		this.customerPizza = customerPizza;
		this.pizzaQuantity = pizzaQuantity;
	}
	/** Prompts the customer to create a new order.
	 * 
	 * @param orderId The ID that the customer's order will have.
	 * @return an Order object containing what the customer has chosen to order. 
	 */
	public Order placeOrder(int orderId) {
		Order customerOrder = new Order(orderId, customerNumber, customerPizza, pizzaQuantity);
		return customerOrder;
		
	}
	
	/** Serves the order to the customer, checking if it is the correct order first.
	 * 
	 * @param servedOrder The order that is to be served.
	 * @return a boolean indicating whether the order has been successfully served or not.
	 */
	public static boolean serveCustomer(Order servedOrder) {
		if (servedOrder.customerNumber == customerNumber && servedOrder.isOrderCooked) {
			isServed = true;
			return true;
		} else {
			System.out.println("Order not served: There is a problem with the order!");
			return false;
		}
		
	}
	
}
