package pizzafactory.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1> OvenThread </h1>
 * 
 * The OvenThread class manages ovens that cook the pizza.
 * 
 * @author Ewa Wagner
 * 
 */

public class OvenThread extends Thread{
	private Pizza pizza;
	private String ovenName;
    private List<String> productsCreated;
    private int totalProductionTime;
    private volatile boolean stopRequested;
    private int updateRate = 1000; // Time in milliseconds between each Production Tick
    private boolean isCooking = true;
    
    /**
     * Constructs a new oven thread given a name and a pizza to cook.
     * 
     * @param ovenName The name of the oven.
     * @param pizza The pizza that needs to be cooked.
     */
    public OvenThread(String ovenName, Pizza pizza) {
        this.ovenName = ovenName;
    	this.pizza = pizza;
        this.productsCreated = new ArrayList<>();
        this.totalProductionTime = 0;
        this.stopRequested = false;
    }
    
    /**
     * Sends a request to the current thread to stop running.
     */
    public void requestStop() {
        stopRequested = true;
    }
    
    /**
     * Runs the thread, cooking the pizza that was passed to the oven upon creation.
     */
    public void run() {
        int tickCounter = 0;
        while (!stopRequested && tickCounter <= pizza.getPizzaCookTimeSeconds()) {
        	try { Thread.sleep(updateRate); } catch (InterruptedException e) { }              
                 
            int percentCompleted = tickCounter * 100 / pizza.getPizzaCookTimeSeconds();            
            totalProductionTime++;
			tickCounter++;
			if (tickCounter -1 == pizza.getPizzaCookTimeSeconds()) {
				productsCreated.add(pizza.getPizzaName());				
				isCooking = false;
				stopRequested = true;
				tickCounter = 0;
				System.out.println(this.ovenName + " is completed");
			} 	
			else {
				System.out.println(this.ovenName + " is currently at "+ percentCompleted + "%");
			}
        }
        //
    }
    
    /**
     * Get a list of the oven's products.
     * @return A list of strings indicating what products the oven has created.
     */
    public List<String> getProductsCreated() {
        return productsCreated;
    }
	
    /**
     * Get the name of the pizza that the oven is currently cooking.
     * @return The name of the current pizza in the oven as a string.
     */
	public String getPizzaName(){
		return pizza.getPizzaName();
	}
    
    /**
     * Get the total time that the pizza will take to cook.
     * @return The pizza's cook time in seconds.
     */
    public int getTotalPizzaCookTime() {
        return pizza.getPizzaCookTimeSeconds();
    }

    /**
     * Get the total time that the oven has been cooking for.
     * @return The total production time in seconds.
     * [SS]
     */
    public int getTotalProductionTime() {
        return totalProductionTime;
    }

    /** 
     * Get information on whether the oven is currently cooking or not.
     * @return A boolean signifying whether the oven is currently cooking a pizza.
     */
    public boolean getCookingStatus() {
    	return isCooking;
    }
}
