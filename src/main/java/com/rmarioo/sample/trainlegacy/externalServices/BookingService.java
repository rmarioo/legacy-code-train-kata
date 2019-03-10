package com.rmarioo.sample.trainlegacy.externalServices;

import com.rmarioo.sample.trainlegacy.Seat;

import java.util.List;

public interface BookingService {

  String createbookingRef();

  Boolean reserve(String trainId, List<Seat> availableSeats, String bookingRef);
}
