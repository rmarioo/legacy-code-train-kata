package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

  public Reservation reserve(String trainId, int requestedSeats) {

    Train train = findTrain(trainId);

    if (train.requestDoesnotExceeds70percentofAvailability(requestedSeats))
    {
      List<Seat> availableSeats = findAvailableSeats(requestedSeats, train);

      return (availableSeats.size() == requestedSeats)
        ? reserveSeats(trainId, availableSeats)
        : noSeatsReservation(trainId);
    }

    return noSeatsReservation(trainId);
  }

  protected Reservation noSeatsReservation(String trainId) {
    return new Reservation(trainId, "", Arrays.asList());
  }

  protected Reservation reserveSeats(String trainId, List<Seat> availableSeats) {
    String bookingRef = createBookingReference();

    if (availableSeats.size() == 0) {
      String output = String.format("Reserved seat(s): ", 0);
      System.out.println(output);
    }


    Boolean isSuccessful = makeReservation(trainId, availableSeats, bookingRef);
    if  (isSuccessful)
      return new Reservation(trainId, bookingRef, availableSeats);
    else
      return noSeatsReservation(trainId);
  }

  protected List<Seat> findAvailableSeats(int seats, Train train) {
        List<Seat> availableSeats = new ArrayList<>();
        for (int index = 0, i = 0; index < train.Seats.size(); index++) {
            Seat each = train.Seats.get(index);
            if (each.getBookingRef() == "") {
                i++;
                if (i <= seats) {
                    availableSeats.add(each);
                }
            }
        }
        return availableSeats;
    }

    protected Boolean makeReservation(String trainId, List<Seat> availableSeats, String bookingRef) {
        return BookingReferenceAPI.reserve(trainId, availableSeats, bookingRef);
    }

    protected String createBookingReference() {
        String bookingRef;
        bookingRef = BookingReferenceAPI.createbookingRef();
        return bookingRef;
    }

    protected Train findTrain(String trainId) {
        return TrainDataAPI.findTrain(trainId);
    }


}
