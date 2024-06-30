package org.ratelimiter.model;

import java.util.UUID;

public class Operation {

  private UUID id;
  private String consumerId;
  private int runtime;

  private int endTime;

  public Operation(String consumerId, int runtime) {
    this.id = UUID.randomUUID();
    this.consumerId = consumerId;
    this.runtime = runtime;
  }

  public UUID getId() {
    return id;
  }

  public String getConsumerId() {
    return consumerId;
  }

  public int getRuntime() {
    return runtime;
  }

  public int getEndTime() {
    return endTime;
  }

  public void setEndTime(int endTime) {
    this.endTime = endTime;
  }
}
