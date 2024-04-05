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

    public OvenThread(String ovenName, Pizza pizza) {
        this.ovenName = ovenName;
    	this.pizza = pizza;
        this.productsCreated = new ArrayList<>();
        this.totalProductionTime = 0;
        this.stopRequested = false;
    }

    public void requestStop() {
        stopRequested = true;
    }

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

    public List<String> getProductsCreated() {
        return productsCreated;
    }
	
	public String getPizzaName(){
		return pizza.getPizzaName();
	}

    public int getTotalPizzaCookTime() {
        return pizza.getPizzaCookTimeSeconds();
    }
    
    public boolean getCookingStatus() {
    	return isCooking;
    }
}
