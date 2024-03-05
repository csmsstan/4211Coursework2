
package Classes;

/**
 * <h1> Order </h1>
 * 
 * The order class represents a customer's order, which can then be cooked and served.
 * 
 * @author Samuel Stanton
 * @version 0.1
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
		this.orderCookTimeSeconds = orderPizza.pizzaCookTimeSeconds * pizzaQuantity;
	}
	
/** Serves an order to the customer who ordered it, as long as the order has been cooked first.
 * 
 * @return A boolean representing if the order has been successfully served or not.
 */
	
	public boolean serveOrder() {
		if (!isOrderCooked) {
			System.out.println("Order " + orderNumber + " isn't cooked yet!");
			return false;
		}
		else {
			System.out.println("Order served to Customer " + customerNumber);
			return true;
		}
	}	
	
}
