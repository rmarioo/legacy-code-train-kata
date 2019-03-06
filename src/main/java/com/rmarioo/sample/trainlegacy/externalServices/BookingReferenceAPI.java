package com.rmarioo.sample.trainlegacy.externalServices;


import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;

import com.rmarioo.sample.trainlegacy.Seat;

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

  public static class CallCollaboratorsFromTestException extends RuntimeException
  {
    public CallCollaboratorsFromTestException(Class aClass) {
      super("External service " + aClass.getSimpleName() + " should not be called directly from unit Test");
    }
  }

}
