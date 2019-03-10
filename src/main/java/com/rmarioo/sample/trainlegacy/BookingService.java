package com.rmarioo.sample.trainlegacy;

import java.util.List;

public interface BookingService {

  String createbookingRef();

  Boolean reserve(String trainId, List<Seat> availableSeats, String bookingRef);
}
