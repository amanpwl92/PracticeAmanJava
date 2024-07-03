package org.ratelimiter.core;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiterAdvancedTest {
  public static Map<Integer, Optional> map = new ConcurrentHashMap<>();
  private final int limit;
  private final int time;
  private final TimeUnit timeUnit;

  public RateLimiterAdvancedTest(int limit, int time, TimeUnit timeUnit) {
    this.limit = limit;
    this.time = time;
    this.timeUnit = timeUnit;
  }

  public static void main(String args[]) {
    RateLimiterAdvancedTest test1 = createTest(5, 1, TimeUnit.SECONDS);
    System.out.println(test1.rateLimit(1));
    System.out.println(test1.rateLimit(1));
    System.out.println(test1.rateLimit(1));
    System.out.println(test1.rateLimit(1));
    System.out.println(test1.rateLimit(2));
    System.out.println(test1.rateLimit(3));
    System.out.println(test1.rateLimit(2));
    System.out.println(test1.rateLimit(1));
    System.out.println(test1.rateLimit(1));

  }

  public static RateLimiterAdvancedTest createTest(int limit, int time, TimeUnit timeUnit){
    return new RateLimiterAdvancedTest(limit, time, timeUnit);
  }

  public boolean rateLimit(int customerId){
    if(customerId<=0){
      return true;
    }

    boolean isRequestAllowed = this.getCustomerRateLimiter(customerId).tryAccess();
    return isRequestAllowed;
  }

  public RateLimiterAdvanced getCustomerRateLimiter(int customerId){
    if(map.containsKey(customerId)){
      return (RateLimiterAdvanced) map.get(customerId).get();
    }

    RateLimiterAdvanced rateLimiterAdvanced = RateLimiterAdvanced.createCustomerRateLimiter(this.limit, this.time, this.timeUnit);
    map.put(customerId,  Optional.of(rateLimiterAdvanced));
    return rateLimiterAdvanced;
  }
}
