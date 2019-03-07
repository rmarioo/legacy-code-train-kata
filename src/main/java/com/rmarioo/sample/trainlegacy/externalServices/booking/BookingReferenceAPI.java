package com.rmarioo.sample.trainlegacy.externalServices.booking;


import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;

import com.rmarioo.sample.trainlegacy.Reservation;
import com.rmarioo.sample.trainlegacy.Seat;
import com.rmarioo.sample.trainlegacy.CallCollaboratorsFromTestException;

import java.util.List;

public class BookingReferenceAPI {
  public static String createbookingRef() {

    if (fromTestEnvironment)
      throw new CallCollaboratorsFromTestException(BookingReferenceAPI.class);
    else
      return new BookingReferenceService().createbookingRef();
  }

  public static Reservation reserve(String trainId, List<Seat> availableSeats, String bookingRef) {

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

}
