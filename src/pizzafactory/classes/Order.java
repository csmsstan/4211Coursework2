
package pizzafactory.classes;

/**
 * <h1> Order </h1>
 * 
 * The order class represents a customer's order, which can then be cooked and served.
 * 
 * @author Samuel Stanton
 * @version 0.3
 * @since 2024-03-04
 */


public class Order implements Comparable<Order> {
	int orderId;
	int customerNumber;
	Pizza orderPizza;
	int pizzaQuantity;
	int orderCookTimeSeconds;
	boolean isOrderCooked = false;
	
/** Constructs a new order with a number, customer number, pizza to be ordered and quantity.
 * 
 * @param orderId The ID of the order. Should be auto-incremented when the order is created.
 * @param customerNumber The ID of the customer who created the order.
 * @param orderPizza The pizza to be ordered.
 * @param pizzaQuantity The amount of pizzas that need to be cooked. 
 */
	
	public Order(int orderId,
				 int customerNumber,
				 Pizza orderPizza,
				 int pizzaQuantity) {
		this.orderId = orderId;
		this.customerNumber = customerNumber;
		this.orderPizza = orderPizza;
		this.pizzaQuantity = pizzaQuantity;
		this.orderCookTimeSeconds = orderPizza.getPizzaCookTimeSeconds() * pizzaQuantity;
	}
	
/** Serves an order to the customer who ordered it, as long as the order has been cooked first.
 * 
 * @return A boolean representing if the order has been successfully served or not.
 */
	
	public boolean serveOrder() {
		if (!isOrderCooked) {
			System.out.println("Order " + orderId + " isn't cooked yet, cannot serve!");
			return false;
		}
		else {
			System.out.println("Order served to Customer " + customerNumber);
			return true;
		}
	}	

/**	
 * Returns the numerical ID of the order.
 * @return An integer representing the order's ID. A greater ID should indicate a more recent order.
 */
	public int getOrderId() {
		return orderId;
	}	
/**	
 * Returns the total cook time of the order.
 * @return An integer representing the order's total cook time in seconds.
 */
	public int getOrderCookTime() {
		return orderCookTimeSeconds;
	}
/**	
 * Returns the number of the customer who placed the order.
 * @return An integer representing the customer's ID number.
 */	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
/**
 * Collects all information about the order and outputs it in a human-readable format.
 * @return A string representing all variables of the order.	
 */
	public String toString() {
		return "Order ID: " + orderId + "\n" 
	+ "Customer Number: " + customerNumber + "\n" 
	+ "Pizza: " + orderPizza.getPizzaName() + "\n" 
	+ "Pizza Quantity: " + pizzaQuantity + "\n" 
	+ "Total Cook Time: " + orderCookTimeSeconds + " Seconds"; 
	}
	
/**
 * Compares two orders (order1, order2) based on their order IDs.
 * @param order2 The order to compare this order to.
 * @return An integer based on which order is higher. If order1 is higher, a value greater than zero will be returned. If order2 is higher, a value less than zero will be returned.	
 */
	@Override
	public int compareTo(Order order2) {
		return Integer.compare(getOrderId(), order2.getOrderId());
	}
	
}
