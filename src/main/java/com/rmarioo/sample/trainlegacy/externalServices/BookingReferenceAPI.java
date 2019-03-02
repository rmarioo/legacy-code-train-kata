package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.WebTicketManager.fromTestEnvironment;

import com.rmarioo.sample.trainlegacy.Seat;

import java.util.List;

public class BookingReferenceAPI {
  public static String createbookingRef() {

    if (fromTestEnvironment)
      throw new RuntimeException("External service should not be called directly from unit Test");
    else
      return new BookingReferenceService().createbookingRef();
  }

  public static String reserve(String trainId, List<Seat> availableSeats, String bookingRef) {

    if (fromTestEnvironment)
      throw new RuntimeException("External service should not be called directly from unit Test");
    else
      return new BookingReferenceService().reserve(trainId, availableSeats, bookingRef);
  }
}
