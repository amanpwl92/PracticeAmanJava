package org.ratelimiter.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// X request per Y seconds for each user

public class RateLimiterUsingSemphore {
  private final Semaphore semaphore;
  private final int maxLimit;
  private final int time;
  private final TimeUnit timeUnit;

  public static RateLimiterUsingSemphore createCustomerRateLimiter(int limit, int time, TimeUnit timeUnit){
    RateLimiterUsingSemphore rateLimiterUsingSemphore = new RateLimiterUsingSemphore(limit, time, timeUnit);
    rateLimiterUsingSemphore.scheduleRelease();
    return rateLimiterUsingSemphore;
  }

  private RateLimiterUsingSemphore(int limit, int time, TimeUnit timeUnit){
    this.semaphore = new Semaphore(limit);
    this.maxLimit = limit;
    this.time = time;
    this.timeUnit = timeUnit;
  }

  public boolean tryAccess(){
    return this.semaphore.tryAcquire();
  }

  //10, 2
  private void scheduleRelease(){
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.schedule(() -> {
      semaphore.release(this.maxLimit - semaphore.availablePermits());
    }, time, timeUnit);
  }

}
