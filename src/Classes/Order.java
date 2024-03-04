package Classes;
public class Order {
	int orderNumber;
	int customerNumber;
	Pizza orderPizza;
	int pizzaQuantity;
	int orderCookTimeSeconds;
	boolean isOrderCooked = false;
	
// Constructor for an order:
	
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
	
// Serve an order, but only if it's cooked:
	
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
