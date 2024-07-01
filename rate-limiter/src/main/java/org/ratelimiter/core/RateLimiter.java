package org.ratelimiter.core;

import org.ratelimiter.exceptions.LimitNotFoundException;
import org.ratelimiter.exceptions.RateLimitException;
import org.ratelimiter.model.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RateLimiter {
  private HashMap<String, Integer> consumerLimits;
  private int systemLimit;
  private int currentTime;
  private int operationsCnt;
  private HashMap<String, List<Operation>> consumerOperationsMap;

  public RateLimiter(HashMap<String, Integer> consumerLimits, int systemLimit) {
    this.consumerLimits = consumerLimits;
    this.systemLimit = systemLimit;
    this.currentTime = 0;
    this.operationsCnt = 0;
    this.consumerOperationsMap = new HashMap<>();
  }

  public int getCurrentTime() {
    return currentTime;
  }

  public int getOperationsCnt() {
    return operationsCnt;
  }

  public HashMap<String, List<Operation>> getConsumerOperationsMap() {
    return consumerOperationsMap;
  }

  public void increaseTime() {
    currentTime = currentTime+1;
    List<Operation> operations;
    int totalCnt=0;
    for(Map.Entry<String, List<Operation>> entry : consumerOperationsMap.entrySet()) {
      operations = entry.getValue();
      operations = operations.stream().filter(op -> op.getEndTime() > currentTime).collect(Collectors.toList());
      entry.setValue(operations);
      totalCnt = totalCnt + operations.size();
    }

    operationsCnt = totalCnt;
  }

  public void submitOperation(Operation op) {
    if (operationsCnt >= systemLimit) {
      throw new RateLimitException();
    }

    if (!consumerLimits.containsKey(op.getConsumerId())) {
      throw new LimitNotFoundException();
    }

    if (consumerOperationsMap.containsKey(op.getConsumerId())) {
      if (consumerOperationsMap.get(op.getConsumerId()).size() >= consumerLimits.get(op.getConsumerId())) {
        throw new RateLimitException();
      }
    }

    operationsCnt++;
    op.setEndTime(currentTime + op.getRuntime());
    if (!consumerOperationsMap.containsKey(op.getConsumerId())) {
      consumerOperationsMap.put(op.getConsumerId(), new ArrayList<>());
    }
    consumerOperationsMap.get(op.getConsumerId()).add(op);

  }
}
