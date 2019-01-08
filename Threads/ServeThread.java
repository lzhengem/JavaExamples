import java.util.*;
import java.util.concurrent.*;

public class ServeThread implements Runnable {
	private BlockingQueue<Food> queue;
	private List<Food> foodList;
	
	public ServeThread(BlockingQueue<Food> queue, List<Food> foodList) {
		this.queue = queue;
		this.foodList = foodList;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < foodList.size(); i++) {
				System.out.println("Server ready");
				Food food = queue.take();
				System.out.println("Server STARTING: " + food);
				Thread.sleep(food.getServeTime() * 1000);
				System.out.println("Serve ending: " + food);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
