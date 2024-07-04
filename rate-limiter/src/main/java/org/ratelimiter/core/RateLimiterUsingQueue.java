package org.ratelimiter.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiterUsingQueue {
  public class HitCounter {
    Queue<Long> q = null;
    public HitCounter() {
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

  private final Map<String, HitCounter> clientHitMap = new HashMap<String, HitCounter>();

  public boolean isAllow(String client_id, long curTime) {

    if (curTime == 1000) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException ex) {
        System.out.println("exception came");
      }
    }
    HitCounter h = clientHitMap.get(client_id);

//    long curTime = System.currentTimeMillis();

    if(h == null) {
      h = new HitCounter();
      clientHitMap.put(client_id, h);
    }

    return h.hit(curTime);
  }
}
