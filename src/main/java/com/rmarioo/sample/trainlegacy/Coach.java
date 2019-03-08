package com.rmarioo.sample.trainlegacy;

import java.util.ArrayList;
import java.util.List;

public class Coach {

  private final String name;
  private List<Seat> seats = new ArrayList<>();

  public Coach(String name) {

    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<Seat> getSeats() {
    return seats;
  }
}
