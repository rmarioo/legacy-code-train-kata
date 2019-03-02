package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.externalServices.TrainDataService.trains;

import com.rmarioo.sample.trainlegacy.Seat;
import com.rmarioo.sample.trainlegacy.SeatJson;
import com.rmarioo.sample.trainlegacy.TrainJson;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BookingReferenceService {

    private final AtomicLong counter = new AtomicLong();

    public String reserve(String train, List<Seat> availableSeats, String bookingRef) {

        TrainJson trainJson = trains.find(train);

        for (Seat availableSeat : availableSeats) {
            SeatJson  seatJson = findSeat(availableSeat,trainJson.seats);
            seatJson.booking_reference = bookingRef;
        }

        return String.format(
            "{{\"train_id\": \"%s\", \"booking_reference\": \"%s\", \"seats\": %s}}",
            train,
            bookingRef,
            dumpSeats(availableSeats));
    }

    private SeatJson findSeat(Seat availableSeat, List<SeatJson> seats) {
        for (SeatJson seat : seats) {
            if (sameSeat(availableSeat,seat))
                return seat;
        }
        return null;
    }

    private boolean sameSeat(Seat availableSeat, SeatJson seat) {
        return availableSeat.getSeatNumber() == Integer.parseInt(seat.seat_number) &&
               availableSeat.getCoachName().equals(seat.coach);
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
        return  String.valueOf(counter.incrementAndGet());
    }
}
