import java.util.*;
import java.util.concurrent.*;

public class FoodTester {

	public static void main(String[] args) {
		List<Food> foodList = new ArrayList<>();
		foodList.add(new Food("Spinach Dip", 2, 1));
		foodList.add(new Food("Burger", 5, 1));
		foodList.add(new Food("Pasta", 4, 3));
		foodList.add(new Food("Baked Alaska", 6, 20));
		foodList.add(new Food("Salad", 1, 1));
		foodList.add(new Food("Bruchetta", 3, 1));
		foodList.add(new Food("Bread", 1, 1));
		foodList.add(new Food("Fried Green Tomatoes", 2, 1));
		
		// INITIALIZE AND START YOUR THREADS HERE!
//		Thread cooker = null;  
//		Thread server = null;
		BlockingQueue<Food> queue = new ArrayBlockingQueue<>(2);
		Thread cooker= new Thread(new CookThread(queue, foodList));
		Thread server= new Thread(new ServeThread(queue,foodList));
		cooker.start();
		server.start();
			
		int programTimeCounter=0;
		while(Thread.activeCount()>1) {
			System.out.println("TIME " + programTimeCounter);
			programTimeCounter++;
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				
			}
		}
		
		// USE STREAMS HERE ON THE INITIAL LIST! 
		// NOTE: THIS PART HAS NOTHING TO DO WITH THE THREADS- JUST MORE STREAM PRACTICE! :)
		// USE METHOD REFERENCES!
//		int totalCookTime = -1;
//		int totalServeTime = -1;
		int totalCookTime = foodList.stream().mapToInt( food -> food.getCookTime()).sum();
		int totalServeTime = foodList.stream().mapToInt( food -> food.getServeTime()).sum();
		System.out.println("Total Cook Time = " + totalCookTime);
		System.out.println("Total Serve Time = " + totalServeTime);	
		System.out.println("Program Time = " + programTimeCounter);
		
	}

}
