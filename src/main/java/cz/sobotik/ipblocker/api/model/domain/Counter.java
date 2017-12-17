package cz.sobotik.ipblocker.api.model.domain;

import java.time.Instant;

/**
 * POJO class to hold counter for every required entity
 * and a time when was counter started.
 */
public class Counter {

  // time when counter expire and will be reset
  private Instant expireAt;

  // value of counter
  private Long counter;

  public Long getCounter() {
    return counter;
  }

  public void setCounter(Long counter) {
    this.counter = counter;
  }

  public Instant getExpireAt() {
    return expireAt;
  }

  public void setExpireAt(Instant expireAt) {
    this.expireAt = expireAt;
  }
}
