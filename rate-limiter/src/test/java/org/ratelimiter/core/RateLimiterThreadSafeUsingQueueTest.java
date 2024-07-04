package org.ratelimiter.core;

public class RateLimiterThreadSafeUsingQueueTest {

  public static void testThreadSafety() {
    // code in this method is not guaranteed to run in order because of threads but still we can see rate limiting
    // working as it should. It is not allowing access to concurrent requests(threads) because we have made our
    // rate limiting logic thread safe. The order of threads is not guaranteed but it is guaranteed that rate limiting
    // will only allow 1 thread from c1 at 1000 ms and 1 thread from c2 at 1000 ms.
    // In non thread safe code, more than one thread for same consumer were allowed access


    RateLimiterThreadSafeUsingQueue rateLimiterUsingQueue = new RateLimiterThreadSafeUsingQueue();
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

  public static void main(String... s) {

    testThreadSafety();


  }
}
