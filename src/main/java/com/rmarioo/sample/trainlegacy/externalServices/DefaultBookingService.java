package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;

import com.rmarioo.sample.trainlegacy.CallCollaboratorsFromTestException;
import com.rmarioo.sample.trainlegacy.Reservation;
import com.rmarioo.sample.trainlegacy.Seat;
import com.rmarioo.sample.trainlegacy.Train;
import com.rmarioo.sample.trainlegacy.Trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class DefaultBookingService implements BookingService {

  @Override
  public String createbookingRef() {
    return BookingReferenceAPI.createbookingRef();
  }

  @Override
  public Boolean reserve(String trainId, List<Seat> availableSeats, String bookingRef)
  {
    return BookingReferenceAPI.reserve(trainId, availableSeats, bookingRef);
  }

  public static class BookingReferenceAPI {
    public static String createbookingRef() {

      if (fromTestEnvironment)
        throw new CallCollaboratorsFromTestException(BookingReferenceAPI.class);
      else
        return new BookingReferenceService().createbookingRef();
    }

    public static Boolean reserve(String trainId, List<Seat> availableSeats, String bookingRef) {

      if (fromTestEnvironment)
        throw new CallCollaboratorsFromTestException(BookingReferenceAPI.class);
      else
        return new BookingReferenceService().reserve(trainId, availableSeats, bookingRef);
    }

    public static void deleteReservations() {
      if (fromTestEnvironment)
        throw new CallCollaboratorsFromTestException(BookingReferenceAPI.class);
      else
        new BookingReferenceService().deleteReservations();
    }

    public static List<Reservation> findReservations() {
      if (fromTestEnvironment)
        throw new CallCollaboratorsFromTestException(BookingReferenceAPI.class);
      else
       return new BookingReferenceService().findReservations();
    }

    public static class BookingReferenceService {

        private static final AtomicLong counter = new AtomicLong();

        public Boolean reserve(String trainId, List<Seat> availableSeats, String bookingRef) {

            Train train = Trains.getInstance().find(trainId);
            train.reserveSeats(availableSeats, bookingRef);

            return true;
        }




        public String createbookingRef() {
            return "booking_ref_"+ counter.incrementAndGet();
        }

        public void deleteReservations() {

            Trains.getInstance().deleteAllTrainReservations();
        }

        public static List<Reservation> findReservations() {

            List<Reservation> reservations = Trains.getInstance().getTrainsMap().keySet().stream()
                .map(id -> reservations(id, Trains.getInstance().getTrainsMap().get(id))).flatMap(List::stream)
                .collect(Collectors.toList());

            return reservations;
        }

        private static List<Reservation> reservations(String trainId, Train train) {

            Map<String, Reservation> reservations = new HashMap<>();

          List<Seat> seats = train.allSeats();
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
  }
}
