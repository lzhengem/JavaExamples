import java.util.*;
import java.util.concurrent.*;

public class CookThread implements Runnable{
	
	private BlockingQueue<Food> queue;
	private List<Food> foodList;
	
	public CookThread(BlockingQueue<Food> queue, List<Food> foodList) {
		this.queue  = queue;
		this.foodList = foodList;
	}
	
	@Override
	public void run() {
//		try {
//			
//			System.out.println("Cook ready" );
//			System.out.println("COOK STARTING: " + food );
//			Thread.sleep(food.getCookTime() *1000);
//			System.out.println("Cook ending: " + food);
//			queue.put(food);
//			
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		try {
			for (Food food : foodList) {
				System.out.println("Cook ready" );
				System.out.println("COOK STARTING: " + food );
				Thread.sleep(food.getCookTime() * 1000);
				System.out.println("Cook ending: " + food);
				queue.put(food);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		
	}

}
