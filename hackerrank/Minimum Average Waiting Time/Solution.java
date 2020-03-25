import java.io.*;
import java.util.*;

public class Solution {

    /*
     * Complete the minimumAverage function below.
     */
    static long minimumAverage(int[][] customers) {
        /*
         * Write your code here.
         */

         PriorityQueue<Customer> pqTime = new PriorityQueue<>(new TimeComparator());
         for(int i = 0; i < customers.length; i++) {
             Customer c = new Customer(customers[i][0], customers[i][1]);
             pqTime.add(c);
         }

         PriorityQueue<Customer> pqPizza = new PriorityQueue<>(new PizzaComparator());
         long waiting = 0;
         long currTime = 0;

         while(pqTime.size() > 0 || pqPizza.size() > 0) {
             while(pqTime.size() > 0 && pqTime.peek().time <= currTime) {
                 pqPizza.add(pqTime.poll());
             }

             if(pqPizza.size() > 0) {
                 Customer c = pqPizza.poll();
                 currTime += c.pizza;
                 waiting += currTime - c.time;
             } else {
                 pqPizza.add(pqTime.poll());
                 currTime = pqPizza.peek().time;
             }
         }

         return waiting / customers.length;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int[][] customers = new int[n][2];

        for (int customersRowItr = 0; customersRowItr < n; customersRowItr++) {
            String[] customersRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            for (int customersColumnItr = 0; customersColumnItr < 2; customersColumnItr++) {
                int customersItem = Integer.parseInt(customersRowItems[customersColumnItr]);
                customers[customersRowItr][customersColumnItr] = customersItem;
            }
        }

        long result = minimumAverage(customers);

        System.out.println(String.valueOf(result));
        
        scanner.close();
    }
}

class Customer {
    long time, pizza;

    Customer(long time, long pizza) {
        this.time = time;
        this.pizza = pizza;
    }
}

class TimeComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.time < o2.time) return -1;
        if(o1.time > o2.time) return 1;
        return 0;
    }

}

class PizzaComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.pizza < o2.pizza) return -1;
        if(o1.pizza > o2.pizza) return 1;
        return 0;
    }

}
