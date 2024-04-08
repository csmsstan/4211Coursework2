package pizzafactory.classes;

/**
 * 
 * The pizza class represents the main object involved in orders.
 * 
 * @author Samuel Stanton
 * @version 0.2
 * @since 2024-03-04
 */

public class Pizza {
	
	int pizzaId;
	String pizzaName;
	int pizzaCookTimeSeconds;
	
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
	
	/** Returns the pizza's name.
	 * @return The pizza's name as a string.
	 */
	public String getPizzaName() {
		return pizzaName;
	}
	
	/** Returns the pizza's ID.
	 * @return The pizza's ID as an integer.
	 */
	public int getPizzaId() {
		return pizzaId;
	}
	
	/** Returns the pizza's cook time.
	 * @return The pizza's cook time in seconds as an integer.
	 */
	public int getPizzaCookTimeSeconds() {
		return pizzaCookTimeSeconds;
	}
	/** Returns the details of the pizza in a human-readable format.
	 * @return All variables of the pizza in a formatted string.
	 */
	public String toString() {
		return "Pizza ID: " + pizzaId + "\n" 
	+ "Pizza: " + pizzaName + "\n" 
	+ "Pizza Cook Time: " + pizzaCookTimeSeconds + " Seconds"; 
	}
}
	

