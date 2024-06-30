package org.ratelimiter.core;

import org.junit.Before;
import org.junit.Test;
import org.ratelimiter.model.Operation;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RateLimiterTest {

  private RateLimiter limiter;

  @Before
  public void initialize() {
    HashMap<String, Integer> consumerLimits  = new HashMap<>();
    consumerLimits.put("A", 3);
    consumerLimits.put("B", 4);
    consumerLimits.put("C", 5);
    limiter = new RateLimiter(consumerLimits, 10);
  }

  @Test
  public void testBasic() {
    limiter.increaseTime();
    limiter.increaseTime();
    limiter.increaseTime();
    assertEquals(limiter.getCurrentTime(), 3);
    assertEquals(limiter.getOperationsCnt(), 0);
    assertNull(limiter.getConsumerOperationsMap().get("A"));
  }

//  @Test
//  public void testWithOperationsInHappyPath() {
//    limiter.increaseTime();
//    limiter.increaseTime();
//    limiter.increaseTime();
//    limiter.submitOperation(new Operation("A", 3));
//    limiter.submitOperation(new Operation("C", 2));
//    limiter.submitOperation(new Operation("B", 4));
//    assertEquals(limiter.getCurrentTime(), 3);
//    assertEquals(limiter.getOperationsCnt(), 0);
//    assertNull(limiter.getConsumerOperationsMap().get("A"));
//  }
//
//  @Test
//  public void testWithConsumerLimitReaching() {
//    limiter.increaseTime();
//    limiter.increaseTime();
//    limiter.increaseTime();
//    assertEquals(limiter.getCurrentTime(), 3);
//    assertEquals(limiter.getOperationsCnt(), 0);
//    assertNull(limiter.getConsumerOperationsMap().get("A"));
//  }
//
//  @Test
//  public void testWithSystemLimitReaching() {
//    limiter.increaseTime();
//    limiter.increaseTime();
//    limiter.increaseTime();
//    assertEquals(limiter.getCurrentTime(), 3);
//    assertEquals(limiter.getOperationsCnt(), 0);
//    assertNull(limiter.getConsumerOperationsMap().get("A"));
//  }
}
