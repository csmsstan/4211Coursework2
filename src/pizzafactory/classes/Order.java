
package pizzafactory.classes;

/**
 * <h1> Order </h1>
 * 
 * The order class represents a customer's order, which can then be cooked and served.
 * 
 * @author Samuel Stanton
 * @version 0.2
 * @since 2024-03-04
 */


public class Order {
	int orderNumber;
	int customerNumber;
	Pizza orderPizza;
	int pizzaQuantity;
	int orderCookTimeSeconds;
	boolean isOrderCooked = false;
	
/** Constructs a new order with a number, customer number, pizza to be ordered and quantity.
 * 
 * @param orderNumber The ID of the order. Should be auto-incremented when the order is created.
 * @param customerNumber The ID of the customer who created the order.
 * @param orderPizza The pizza to be ordered.
 * @param pizzaQuantity The amount of pizzas that need to be cooked. 
 */
	
	public Order(int orderNumber,
				 int customerNumber,
				 Pizza orderPizza,
				 int pizzaQuantity) {
		this.orderNumber = orderNumber;
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
			System.out.println("Order " + orderNumber + " isn't cooked yet, cannot serve!");
			return false;
		}
		else {
			System.out.println("Order served to Customer " + customerNumber);
			return true;
		}
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
}
