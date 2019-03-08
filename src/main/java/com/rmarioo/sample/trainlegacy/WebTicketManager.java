package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI;

import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

  public Reservation reserve(String trainId, int requestedSeats) {

    Train train = findTrain(trainId);

    if (train.requestDoesnotExceeds70perc(requestedSeats))
    {
      List<Seat> availableSeats = train.findAvailableSeats(requestedSeats);

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

    if (availableSeats.size() == 0) {
      String output = String.format("Reserved seat(s): ", 0);
      System.out.println(output);
    }

    String bookingRef = createBookingReference();

    Boolean isSuccessful = makeReservation(trainId, availableSeats, bookingRef);

    return  (isSuccessful) ? new Reservation(trainId, bookingRef, availableSeats)
                           : noSeatsReservation(trainId);
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
