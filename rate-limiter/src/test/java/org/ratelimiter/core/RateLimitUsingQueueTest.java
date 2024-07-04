package org.ratelimiter.core;

import org.junit.Test;

public class RateLimitUsingQueueTest {

  public static void testThreadSafety() {


  // code in this method is not guaranteed to run in order because of threads and we can see rate limiting allowing
  // requests even in queue is full thus breaching the limit we have set. To test this, we also need to add some
  // sleep in isAllow method other wise things execute in sequence because we dont have much logic and hence thread
  // dont pause even for a nanosecond. Sleep here could be compared with time that could go to fetch consumer's rate
  // limit & active requests count from a shared storage (eg redis). We are able to see rate limit thread safety issue
  // even with a sleep on 50 ms though will smaller sleep we might not see it always but still easily produceable

/*
*
* ONE OF THE RESPONSE WHERE WE COULD SEE THREAD SAFETY ISSUE
*
* Starting test
allowed flag in main thread for c1 attempt 1 = true
allowed flag in main thread for c1 attempt 2 = true
allowed flag in main thread for c2 attempt 1 = true
allowed flag in main thread for c2 attempt 2 = true
Ending test
allowed flag in thrad 4 for c1 = true
allowed flag in thrad 3 for c1 = true -> this should not have been allowed
allowed flag in thrad 5 for c1 = false
allowed flag in thrad 6 for c1 = false
allowed flag in thrad 7 for c1 = false
allowed flag in thrad 8 for c2 = true
allowed flag in thrad 11 for c2 = false
allowed flag in thrad 9 for c2 = false
allowed flag in thrad 10 for c2 = false
*/


/*
*
* ANOTHER RESPONSE WHERE WE COULD SEE THREAD SAFETY ISSUE
*
Starting test
allowed flag in main thread for c1 attempt 1 = true
allowed flag in main thread for c1 attempt 2 = true
allowed flag in main thread for c2 attempt 1 = true
allowed flag in main thread for c2 attempt 2 = true
Ending test
allowed flag in thrad 4 for c1 = true
allowed flag in thrad 3 for c1 = true -> this should not have been allowed
allowed flag in thrad 9 for c2 = true -> this should not have been allowed
allowed flag in thrad 6 for c1 = false
allowed flag in thrad 7 for c1 = false
allowed flag in thrad 5 for c1 = false
allowed flag in thrad 10 for c2 = true
allowed flag in thrad 8 for c2 = false
allowed flag in thrad 11 for c2 = false
* */


/*
 ANOTHER RESPONSE WHERE WE COULD SEE THREAD SAFETY ISSUE

Starting test
allowed flag in main thread for c1 attempt 1 = true
allowed flag in main thread for c1 attempt 2 = true
allowed flag in main thread for c2 attempt 1 = true
allowed flag in main thread for c2 attempt 2 = true
Ending test
allowed flag in thrad 3 for c1 = true
allowed flag in thrad 5 for c1 = false
allowed flag in thrad 4 for c1 = false
allowed flag in thrad 10 for c2 = false
allowed flag in thrad 7 for c1 = false
allowed flag in thrad 8 for c2 = true
allowed flag in thrad 6 for c1 = false
allowed flag in thrad 9 for c2 = true -> this should not have been allowed
allowed flag in thrad 11 for c2 = false

 */

    RateLimiterUsingQueue rateLimiterUsingQueue = new RateLimiterUsingQueue();
    System.out.println("Starting test");

    System.out.println("allowed flag in main thread for c1 attempt 1 = " + rateLimiterUsingQueue.isAllow("C1", 1));


    System.out.println("allowed flag in main thread for c1 attempt 2 = " + rateLimiterUsingQueue.isAllow("C1", 999));

    System.out.println("allowed flag in main thread for c2 attempt 1 = " + rateLimiterUsingQueue.isAllow("C2", 1));


    System.out.println("allowed flag in main thread for c2 attempt 2 = " + rateLimiterUsingQueue.isAllow("C2", 999));


    (new Thread(() -> {
      System.out.println("allowed flag in thrad 3 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 4 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 5 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 6 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 7 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 8 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 9 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 10 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));
    })).start();

    (new Thread(() -> {
      System.out.println("allowed flag in thrad 11 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));
    })).start();



    System.out.println("Ending test");

  }

  public static void testWithoutThreads() {

    // this code is guaranteed to run in order because we are not using threads.
    // All requests will happen one by one and hence rate limiting will work properly

    RateLimiterUsingQueue rateLimiterUsingQueue = new RateLimiterUsingQueue();
    System.out.println("Starting test");

    System.out.println("allowed flag in main thread for c1 attempt 1 = " + rateLimiterUsingQueue.isAllow("C1", 1));


    System.out.println("allowed flag in main thread for c1 attempt 2 = " + rateLimiterUsingQueue.isAllow("C1", 999));

    System.out.println("allowed flag in main thread for c2 attempt 1 = " + rateLimiterUsingQueue.isAllow("C2", 1));


    System.out.println("allowed flag in main thread for c2 attempt 2 = " + rateLimiterUsingQueue.isAllow("C2", 999));


    System.out.println("allowed flag in thrad 3 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));

    System.out.println("allowed flag in thrad 4 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));

    System.out.println("allowed flag in thrad 5 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));

    System.out.println("allowed flag in thrad 6 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));

    System.out.println("allowed flag in thrad 7 for c1 = " + rateLimiterUsingQueue.isAllow("C1", 1000));

    System.out.println("allowed flag in thrad 8 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));

    System.out.println("allowed flag in thrad 9 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));

    System.out.println("allowed flag in thrad 10 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));


    System.out.println("allowed flag in thrad 11 for c2 = " + rateLimiterUsingQueue.isAllow("C2", 1000));

    System.out.println("Ending test");

  }

  public static void main(String... s) {

    testThreadSafety();

//    testWithoutThreads();

  }
}



