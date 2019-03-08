package com.rmarioo.sample.trainlegacy;

import java.util.ArrayList;
import java.util.List;

public class Train {
    public List<Seat> Seats;
    public String name;

    public Train(String name,List<Seat> seats) {
        this.name = name;
        Seats = seats;
    }

    public int getReservedSeats()
    {
       return this.Seats.stream().filter(seat -> seat.hasReservation())
                                  .map(e -> 1).reduce(0, Integer::sum);
    }

    public int getMaxSeat() {
        return this.Seats.size();
    }

    public void reserveSeats(List<Seat> availableSeats, String bookingRef) {
        for (Seat availableSeat : availableSeats) {
            Seat seat = findSeat(availableSeat, Seats);
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

  protected boolean requestDoesnotExceeds70perc(int seats) {
    return (getReservedSeats() + seats) <= Math.floor(0.70 * getMaxSeat());
  }

    protected List<Seat> findAvailableSeats(int seats) {
          List<Seat> availableSeats = new ArrayList<>();
          for (int index = 0, i = 0; index < Seats.size(); index++) {
              Seat each = Seats.get(index);
              if (each.getBookingRef() == "") {
                  i++;
                  if (i <= seats) {
                      availableSeats.add(each);
                  }
              }
          }
          return availableSeats;
      }
}