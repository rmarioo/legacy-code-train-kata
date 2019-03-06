package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.externalServices.TrainDataService.trains;

import com.rmarioo.sample.trainlegacy.Seat;
import com.rmarioo.sample.trainlegacy.Train;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BookingReferenceService {

    private static final AtomicLong counter = new AtomicLong();

    public Reservation reserve(String trainId, List<Seat> availableSeats, String bookingRef) {

        Train train = trains.find(trainId);

        for (Seat availableSeat : availableSeats) {
            Seat seat = findSeat(availableSeat, train.Seats);
            seat.setBookingRef(bookingRef);
        }

        return new Reservation(trainId, bookingRef, availableSeats);
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


    public String createbookingRef() {
        return "booking_ref_"+ counter.incrementAndGet();
    }

    public void deleteReservations() {

        trains.deleteAllTrainReservations();
    }

    public static List<Reservation> findReservations() {

        List<Reservation> reservations = trains.getTrainsMap().keySet().stream()
            .map(id -> reservations(id, trains.getTrainsMap().get(id))).flatMap(List::stream)
            .collect(Collectors.toList());

        return reservations;
    }

    private static List<Reservation> reservations(String trainId, Train train) {

        Map<String, Reservation> reservations = new HashMap<>();

        List<Seat> seats = train.Seats;
        for (Seat seat : seats) {
            String bookingRef = seat.getBookingRef();
            if (!bookingRef.isEmpty()) {

                Reservation reservation = reservations.get(bookingRef) != null
                    ? reservations.get(bookingRef)
                    : new Reservation(trainId, bookingRef, new ArrayList<>());

                reservation.availableSeats.add(seat);
                reservations.put(bookingRef,reservation);
            }

        }
        return new ArrayList<>(reservations.values());
    }

}