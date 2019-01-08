import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class HomeworkM11Streams {

	public static void main(String[] args) throws Exception {

		List<Customer> customerList = new ArrayList<>();
		fillList(customerList);
		
		// Q1: How many customers in CA?
		System.out.print("Q1: Should print 20: ");
		long caCustomters =  customerList.stream().filter(customer -> customer.getState().equalsIgnoreCase("CA")).count();
		System.out.println(caCustomters);
		
		// Q2: Create a list of all priority customers in MA.
		System.out.println("\nQ2: Should print \n[ Sasin, Anna (ID:  AS1G) (Priority Customer),  Case, Justin (ID:  JCT1) (Priority Customer)]: ");
		List<Customer> maPriorityList =  // YOUR ANSWER HERE
		customerList.stream().filter(customer -> customer.getState().equalsIgnoreCase("MA") && customer.isPriority())
							.collect(Collectors.toList());
		System.out.println(maPriorityList);	

		// Q3: How much money have all customers spent (combined)?
		System.out.print("\nQ3: Should print 330518.0: ");
		double total =  // YOUR ANSWER HERE
		customerList.stream().reduce(0.0,
								(subtotal,customer) -> subtotal + customer.getAmountSpent(),
								(total1, total2) -> total1 + total2);
		System.out.println(total);
		
		// Q4: How much money have all priority customers spent (combined)?
		System.out.print("\nQ4: Should print 226177.0: ");
		double priorityTotal =  // YOUR ANSWER HERE
		customerList.stream().filter(customer -> customer.isPriority())
				.reduce(0.0,
				(subtotal,customer) -> subtotal + customer.getAmountSpent(),
				(total1, total2) -> total1 + total2);
		System.out.println(priorityTotal);
		
		// Q5: Create a map of all WY priority customers (key=id, value=customer)
		System.out.println("\nQ5: Should print\n{ PTC8= Turner, Paige (ID:  PTC8) (Priority Customer),  BS20= Seville, Barbara (ID:  BS20) (Priority Customer),  BCG5= Cade, Barry (ID:  BCG5) (Priority Customer),  LK71= King, Leigh (ID:  LK71) (Priority Customer)}");
		Map<String, Customer> wyCustomers = // YOUR ANSWER HERE
		customerList.stream().filter(customer -> customer.isPriority() && customer.getState().equalsIgnoreCase("Wy"))
							.collect(Collectors.toMap(Customer::getId, Function.identity()));
		System.out.println(wyCustomers);
		
		// Q6: What is the greatest amount of money spent by a NY priority customer?
		System.out.print("\nQ6: Should print 9207.0: ");
		double nyHighAmount = // YOUR ANSWER HERE
		customerList.stream().filter(customer -> customer.isPriority() && customer.getState().equalsIgnoreCase("Ny"))
							.max((cust1,cust2) -> Double.compare(cust1.getAmountSpent(), cust2.getAmountSpent()))
							.get().getAmountSpent();
		System.out.println(nyHighAmount);
		
		
		 //Q7: Find all customers that spent > 9000.
        // Print a comma-separated String of all customer IDs for customers that spent > 9000:            
        System.out.println("\nQ7: Should print: \nAD62,AS1G,CV62,HW32,JCT1,KA74,OB63,PTC8,WP90");
        String highIDList =  // YOUR ANSWER HERE
        customerList.stream().filter(customer -> customer.getAmountSpent() > 9000)
        				.map(customer -> customer.getId())//map creates a Stream<String> of ids
        				.collect(Collectors.joining(","));
        System.out.println(highIDList);
        				
      
        
		//Q8: Find any customer that has spent > 9800. 
		// Print the amount spent by the customer. If there is none, nothing should be printed.
		// Note: you can test your code with a lower amount, too, to see something printed.
		System.out.println("\nQ8: Should print nothing: ");
		// YOUR ANSWER HERE
		Map<Object, Object> spentMoreThanMap = customerList.stream()				
				.filter(customer->customer.getAmountSpent()> 9800)
				.collect(Collectors.toMap(
						customer -> customer.getAmountSpent(),//paramerter 1- customer Id of any customer who spent more than 9800
						customer -> customer
					));
		System.out.println(spentMoreThanMap);
		 
		// Q9: Find the sum of the numbers represented in an String array.
		String[] numWords = {"1", "2", "3", "4", "5", "6"}; 
		int sum = Stream.of(numWords).mapToInt(Integer::parseInt).sum();// YOUR ANSWER HERE
		System.out.println("\nQ9: Sum is 21: " + sum);
		
		
		 
		// Q10: Create a String of the numbers represented in the array, separated by semicolons.
		Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String concat = Stream.of(nums).map(Object::toString).collect(Collectors.joining(";")); // YOUR ANSWER HERE
		System.out.println("\nQ10: Should print: \n1;2;3;4;5;6;7;8;9;10 \n" + concat);
		
		
		// Q11: Create an infinite stream of random integers in the range 1-100.
		// Keep only the numbers that are multiples of 3.
		// Print the first 10 of these numbers.
		System.out.println("\nQ11: Will print 10 numbers that are multiples of 3 between 1-100:");
		// YOUR ANSWER HERE
        Random generator = new Random(); 
        IntStream randomIntegerStream = generator.ints(1, 100);
        randomIntegerStream.filter( num -> num%3==0 ).limit(10).forEach(System.out::println);
		
		 
		// Q12: Print the top 9-highest-scoring scrabble word in the list.
		// Note: a method is provided below to convert from char to score.
		// Hint: you might consider writing another method to find the score of a word!
		List<String> scrabbleWords = Files.readAllLines(Paths.get("words.txt"), Charset.forName("Cp1252"));
		System.out.println("\nQ12 Should print: " + "\n\tpizzazz worth 45 points"+"\n\tpizazz worth 35 points" +"\n\tjazzily worth 35 points" +
				"\n\tquizzed worth 35 points" + "\n\tjacuzzi worth 34 points" + "\n\tquizzer worth 34 points" +
				"\n\tquizzes worth 34 points" + "\n\tjazzy worth 33 points" + "\n\tjazzing worth 33 points" ); 
		// YOUR ANSWER HERE
		//first find score for each word: turn each word to a characters, then add up score for each character group (or each word)
		//filter words that have 9 highest score
		//print the word with their score
		System.out.println();
		Stream<String> scrabbleStream = scrabbleWords.stream();
		scrabbleStream.sorted(
				(word1, word2) ->{
					int word1score= 0;
					int word2score= 0;
					for (int i = 0; i < word1.length(); i++) {
						char letter = word1.charAt(i);
						word1score += charToScore(letter);
					}
					for (int i = 0; i < word2.length(); i++) {
						char letter = word2.charAt(i);
						word2score += charToScore(letter);
					}
					return Integer.compare(word2score, word1score);
					}).limit(9).forEach(
							scrable ->{
								int score = 0;
								for (int i = 0; i < scrable.length(); i++) {
									char letter = scrable.charAt(i);
									score += charToScore(letter);
								}
								System.out.println("\t" + scrable + " worth " + score+ " points");
							});
		;
		
//		scrabbleStream.mapToInt( scrabble ->{
//			int score= 0;
//			for (int i = 0; i < scrabble.length(); i++) {
//				char letter = scrabble.charAt(i);
//				score += charToScore(letter);
//			}
//			return score;
//			}).forEach(System.out::println);
//		;
		
//		scrabbleStream.collect(Collectors.groupingBy(
//				scrabble -> scrabble.toString(),
//				Collectors.summingInt(
//							scrabble -> {
//								int score= 0;
//								for (int i = 0; i < scrabble.length(); i++) {
//									char letter = scrabble.charAt(i);
//									score += charToScore(letter);
//								}
//								return score;
//								})
//				
//				))
////		forEach((key, value) -> System.out.println(key + " " + value))
//		;
		
		
		
		
		// EXTRA CREDIT
		// Add an additional Customer or word-related query! Be creative!

}

	private static int charToScore(char c) {
		switch (c) {
			case 'a': case 'e': case 'i': case 'o': case 'u':
			case 'n': case 'r': case 't': case 'l': case 's':
				return 1;
			case 'g': case 'd': 
				return 2;
			case 'b': case 'c': case 'm': case 'p': 
				return 3;	
			case 'f': case 'h': case 'v': case 'w': case 'y':
				return 4;	
			case 'k': 
				return 5;
			case 'j': case 'x': 
				return 8;
			case 'q': case 'z': 
				return 10;		
			default:
				return -1;
		}
	}
	
	private static void fillList(List<Customer> list) {
		try (Scanner fileScan = new Scanner(
				new FileReader(new File("Customers.csv")))) {
		
			while(fileScan.hasNext()) {
				String line = fileScan.nextLine();
				Scanner lineScan = new Scanner(line);
				lineScan.useDelimiter(",");
				String firstName = lineScan.next();
				String lastName = lineScan.next();
				String id = lineScan.next();
				String state = lineScan.next();
				boolean priority = Boolean.parseBoolean(lineScan.next());
				double amount = Double.parseDouble(lineScan.next());
				Customer c = new Customer(firstName, lastName, id, state, priority, amount);
				list.add(c);
			}
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
