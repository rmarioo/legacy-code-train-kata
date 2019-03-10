package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;

import java.util.List;

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
}
