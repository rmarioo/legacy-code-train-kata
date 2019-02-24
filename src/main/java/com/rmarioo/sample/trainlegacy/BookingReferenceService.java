package com.rmarioo.sample.trainlegacy;

import java.util.List;

public class BookingReferenceService {

    public String reserve(String train, List<Seat> availableSeats, String bookingRef) {
        return String.format(
            "{{\"train_id\": \"%s\", \"booking_reference\": \"%s\", \"seats\": %s}}",
            train,
            bookingRef,
            dumpSeats(availableSeats));
    }

    private String dumpSeats(List<Seat> seats) {
        StringBuilder sb = new StringBuilder("[");

        boolean firstTime = true;

        for (Seat seat : seats) {
            if (!firstTime) {
                sb.append(", ");
            } else {
                firstTime = false;
            }

            sb.append(String.format("\"%d%s\"", seat.getSeatNumber(), seat.getCoachName()));
        }

        sb.append("]");

        return sb.toString();
    }


    public String createbookingRef() {
        return "1234";
    }
}
