package Sleeping_Barber;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Shop {
    public static int n_seats,mean,std;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Opening up shop \nEnter number of seats in waiting room: ");
        Scanner scan = new Scanner(System.in);
        n_seats = scan.nextInt();
        System.out.println("Enter number of barbers in shop: ");
        int n_barber = scan.nextInt();
        System.out.println("Enter number of customers: ");
        int n_customer = scan.nextInt();
        System.out.println("Enter mean: ");
        mean = scan.nextInt();
        System.out.println("Enter standard deviation: ");
        std = scan.nextInt();
        System.out.println("Barbers are sleeping ");
        WaitingRoom waiting = new WaitingRoom();
        ExecutorService executorService = Executors.newFixedThreadPool(n_customer + n_barber);
        for (int i = 0; i < n_barber; i++) {
            executorService.submit(new Barber(waiting));
        }
        List<Customer> customers = new ArrayList<Customer>();
        int c = 0;
        Random r = new Random();

        while(++c<=n_customer){
            Customer new_customer = new Customer(waiting);
            customers.add(new_customer);
            executorService.submit(new_customer);
            long n=Math.abs((long)((r.nextGaussian()*std)+mean));
            Thread.sleep(n);
        }
        executorService.shutdownNow();
    }

    public static class WaitingRoom {

        List<Customer> chairs = new LinkedList<Customer>();

        public void cutHair(int id)
        {
            Customer customer;
            int f=0;
            synchronized (chairs)
            {

                while(chairs.size()==0)
                {
                    try
                    {
                        chairs.wait();
                    }
                    catch(InterruptedException iex)
                    {
                        iex.printStackTrace();
                    }
                    f=1;
                }
                customer = ((LinkedList<Customer>) chairs).poll();

            }
            long duration=0;
            try
            {   if(f==1){
                System.out.println("Customer " + customer + " finds Barber " + id + " sleeping and wakes him up.");
                f=0;
                }
                else {
                System.out.println("Barber " + id + " finds Customer " + customer + " sleeping and wakes him up.");
                }
                Random r = new Random();
                System.out.println("Barber "+id+" cuts hair of Customer "+customer);
                duration = Math.abs((long)((r.nextGaussian()*std)+mean));
                TimeUnit.MILLISECONDS.sleep(duration);
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
            System.out.println("Barber "+id+" finished cutting hair of Customer "+customer+ " in "+duration+ " milliseconds and customer pays and exits.");
//            if (((LinkedList<Customer>) chairs).poll()==null){
//                System.out.println("Barber " + id + " goes to sleep as there are no customers.");
//            }
        }

        public void add(Customer customer)
        {
            System.out.println("Customer "+customer+ " enters the shop.");

            synchronized (chairs)
            {
                if(chairs.size() == n_seats)
                {
                    System.out.println("No chair available for Customer "+customer);
                    System.out.println("Customer "+customer+" leaves");
                    return ;
                }

                ((LinkedList<Customer>)chairs).offer(customer);
                System.out.println("Customer "+customer+ " gets a chair and sleeps.");

                if(chairs.size()==1)
                    chairs.notify();
            }
        }
    }
}