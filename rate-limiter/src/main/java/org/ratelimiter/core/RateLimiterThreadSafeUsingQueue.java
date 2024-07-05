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
      // this method needs to be thread safe as it is allowing reads/writes on queue
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

    RateLimiterThreadSafeUsingQueue.HitCounter h = clientHitMap.get(client_id);
    if(h == null) {
      h = new RateLimiterThreadSafeUsingQueue.HitCounter();
      clientHitMap.put(client_id, h);
    }
    return h.hit(curTime);
  }
}
