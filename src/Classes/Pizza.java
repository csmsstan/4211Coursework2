package Classes;

public class Pizza {
	int pizzaId;
	String pizzaName;
	public int pizzaCookTimeSeconds;
	
	public Pizza(int pizzaId, String pizzaName, int pizzaCookTimeSeconds) {
		this.pizzaId = pizzaId;
		this.pizzaName = pizzaName;
		this.pizzaCookTimeSeconds = pizzaCookTimeSeconds;
		}
	
	public void showPizzaDetails() {
		System.out.println("Pizza Name: " + pizzaName);
		System.out.println("Pizza ID: " + pizzaId);
		System.out.println("Time to cook: " + pizzaCookTimeSeconds + " seconds");
	}
}
	

