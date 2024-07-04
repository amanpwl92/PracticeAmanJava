package org.ratelimiter.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterThreadSafeUsingQueue {

  public class HitCounter {
//    ConcurrentLinkedQueue<AtomicLong> q = null;
    Queue<Long> q = null;
    public HitCounter() {
//      q = new ConcurrentLinkedQueue<>();
      q = new LinkedList<Long>();
    }

    public boolean hit(long timestamp) {
      while(!q.isEmpty() && timestamp-q.peek() >= TIME_LIMIT) q.poll();

      if(q.size() < REQUEST_LIMIT)
      {
        q.offer(timestamp);
        return true;
      }

      return false;
    }
  }

  private final int REQUEST_LIMIT = 3;
  private final long TIME_LIMIT = 1000L;

  private final Map<String, RateLimiterThreadSafeUsingQueue.HitCounter> clientHitMap = new HashMap<>();
//  private final Map<String, RateLimiterThreadSafeUsingQueue.HitCounter> clientHitMap = new ConcurrentHashMap<>();

  public boolean isAllow(String client_id, long curTime) {

    if (curTime == 1000) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException ex) {
        System.out.println("exception came");
      }
    }


//    long curTime = System.currentTimeMillis();
    synchronized (this) {
      RateLimiterThreadSafeUsingQueue.HitCounter h = clientHitMap.get(client_id);
      if (h == null) {
        h = new RateLimiterThreadSafeUsingQueue.HitCounter();
        clientHitMap.put(client_id, h);
      }

      return h.hit(curTime);
    }


  }
}
