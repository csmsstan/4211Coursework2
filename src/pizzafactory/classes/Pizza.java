package pizzafactory.classes;

/**
 * <h1> Pizza </h1>
 * 
 * The pizza class represents the main object involved in orders.
 * 
 * @author Samuel Stanton
 * @version 0.1
 * @since 2024-03-04
 */

public class Pizza {
	
	int pizzaId;
	String pizzaName;
	public int pizzaCookTimeSeconds;
	
	/** Creates a pizza with the specific ID, name and cook time.
	 * 
	 * @param pizzaId The pizza's ID for reference.
	 * @param pizzaName The name of the pizza.
	 * @param pizzaCookTimeSeconds How long the pizza will take to cook in seconds.
	 */
	public Pizza(int pizzaId, String pizzaName, int pizzaCookTimeSeconds) {
		this.pizzaId = pizzaId;
		this.pizzaName = pizzaName;
		this.pizzaCookTimeSeconds = pizzaCookTimeSeconds;
		}
	
	/** Gets the pizza's details and prints them to console.
	 * 
	 */
	public void showPizzaDetails() {
		System.out.println("Pizza Name: " + pizzaName);
		System.out.println("Pizza ID: " + pizzaId);
		System.out.println("Time to cook: " + pizzaCookTimeSeconds + " seconds");
	}
}
	

