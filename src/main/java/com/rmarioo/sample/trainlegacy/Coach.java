package com.rmarioo.sample.trainlegacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<Seat> firstAvailableSeats(int requestedSeats) {

    List<Seat> allAvailable = this.seats.stream()
        .filter(seat -> !seat.hasReservation())
        .limit(requestedSeats)
        .collect(Collectors.toList());

    return allAvailable.size() == requestedSeats ? allAvailable : Arrays.asList();
  }
}
