package com.rmarioo.sample.trainlegacy;

import java.util.ArrayList;
import java.util.List;

public class Coach {

  private final String name;
  private List<Seat> seats = new ArrayList<>();

  public Coach(String name) {

    this(name,new ArrayList<>());
  }

  public Coach(String name, List<Seat> seats) {

    this.name = name;
    this.seats = seats;
  }

  public String getName() {
    return name;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public boolean hasEnoughSeatsFor(int requestedSeats) {
    long availableSeats = seats.stream().filter(seat -> !seat.hasReservation()).count();
    return availableSeats >= requestedSeats;
  }
}
