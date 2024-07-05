package org.ratelimiter.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiterThreadSafeUsingQueue {

  public class HitCounter {
    ReentrantLock lock;
    Queue<Long> q = null;
    public HitCounter() {
      lock = new ReentrantLock();
      q = new LinkedList<Long>();
    }

    public boolean hit(long timestamp) {
      lock.lock();
      while(!q.isEmpty() && timestamp-q.peek() >= TIME_LIMIT) q.poll();

      if(q.size() < REQUEST_LIMIT)
      {
        q.offer(timestamp);
        lock.unlock();
        return true;
      }
      lock.unlock();
      return false;

    }
  }

  private final int REQUEST_LIMIT = 3;
  private final long TIME_LIMIT = 1000L;

  private final Map<String, RateLimiterThreadSafeUsingQueue.HitCounter> clientHitMap = new HashMap<>();

  public boolean isAllow(String client_id, long curTime) {

    if (curTime == 1000) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException ex) {
        System.out.println("exception came");
      }
    }
//    long curTime = System.currentTimeMillis();

    // computeIfAbsent-it will guarantee atomicity of getting entry from map and inserting in case key is missing.
    // If we do get and then put (in case of null) using basic code, that wont' be atomic
    RateLimiterThreadSafeUsingQueue.HitCounter h = clientHitMap.computeIfAbsent(client_id, (key) -> new HitCounter());
    return h.hit(curTime);
  }
}
