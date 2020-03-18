# Sleeping Barbers using Intrinsic Lock

The program solution consists of four java class – Barber class, Customer class, Shop class and WaitingRoom class, which is a nested class in the Shop class. The program uses synchronous blocks that provide intrinsic locks on a shared resource – here, the customer list.
	The Shop class in this program takes the number of seats in the waiting room (n_seats), number of barbers (n_barber), number of customers (n_customer) and mean (mean) and standard deviation of the time intervals (std) as input from the user. An executor service creates a thread pool of threads. It then initializes Barbers. The Barber class calls method cutHair() in WaitingRoom class. The WaitingRoom class uses wait() and notify() to synchronize the customer list. Any Barber thread waits for a Customer thread. When a customer thread is created, the Barber thread is notified. The Barber then cuts hair of customer for random intervals, with mean mean and standard deviation sd. The customers are also created at random intervals, with mean mean and standard deviation sd and are added to the customer queue. If the customer queue i.e. WaitingRoom is full, any new customer exits the shop.
  
##References:
https://github.com/ylegat/sleeping-barber - Referred program 
https://orajavasolutions.wordpress.com/tag/sleeping-barber-problem/ - Referred wait() and notify() in a program
