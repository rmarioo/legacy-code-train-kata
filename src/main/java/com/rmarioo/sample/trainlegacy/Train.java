package com.rmarioo.sample.trainlegacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Train {
  public List<Coach> coaches;
  public String name;

  public Train(String name, List<Seat> seats) {
    this.name = name;
    coaches = createFrom(seats);
  }

  private List<Coach> createFrom(List<Seat> seats) {

    Map<String, Coach> coachesMap = new HashMap<>();
    for (Seat seat : seats) {
      if (coachesMap.get(seat.getCoachName()) == null)
        coachesMap.put(seat.getCoachName(), new Coach(seat.getCoachName()));
      coachesMap.get(seat.getCoachName()).getSeats().add(seat);
    }
    return new ArrayList<>(coachesMap.values());
  }

  public int getReservedSeats() {

    return allSeats().stream().filter(seat -> seat.hasReservation())
        .map(e -> 1).reduce(0, Integer::sum);
  }

  public int getMaxSeat() {

    return allSeats().size();
  }

  public void reserveSeats(List<Seat> availableSeats, String bookingRef) {
    for (Seat availableSeat : availableSeats) {

      Seat seat = findSeat(availableSeat, allSeats());
      seat.setBookingRef(bookingRef);
    }

  }

  private Seat findSeat(Seat availableSeat, List<Seat> seats) {
    for (Seat seat : seats) {
      if (sameSeat(availableSeat, seat))
        return seat;
    }
    return null;
  }

  private boolean sameSeat(Seat availableSeat, Seat seat) {
    return availableSeat.getSeatNumber() == seat.getSeatNumber() &&
        availableSeat.getCoachName().equals(seat.getCoachName());
  }

  public boolean requestDoesnotExceeds70perc(int seats) {
    return (getReservedSeats() + seats) <= Math.floor(0.70 * getMaxSeat());
  }

  public List<Seat> availableSeatsFor(int seats) {
    List<Seat> availableSeats = new ArrayList<>();

    for (int index = 0, i = 0; index < allSeats().size(); index++) {

      Seat each = allSeats().get(index);
      if (each.getBookingRef() == "") {
        i++;
        if (i <= seats) {
          availableSeats.add(each);
        }
      }
    }
    return availableSeats;
  }

  public List<Seat> allSeats() {
    List<Seat> seats = new ArrayList<>();
    for (Coach coach : coaches) {
      seats.addAll(coach.getSeats());
    }
    return seats;
  }

  public boolean enoughAvailableSeats(int requestedSeats) {
    return availableSeatsFor(requestedSeats).size() >= requestedSeats;
  }

}