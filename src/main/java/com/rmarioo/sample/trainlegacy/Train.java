package com.rmarioo.sample.trainlegacy;

import java.util.List;

public class Train {
    public int ReservedSeats;
    public List<Seat> Seats;
    public String name;

    public Train(String name,List<Seat> seats) {
        this.name = name;
        Seats = seats;
        for (Seat seat : seats) {
            if (seat.getBookingRef() != null && !seat.getBookingRef().isEmpty())
                ReservedSeats++;
        }
    }

    public int getMaxSeat() {
        return this.Seats.size();
    }

}