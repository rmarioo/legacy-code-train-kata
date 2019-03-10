package com.rmarioo.sample.trainlegacy;

import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

  private final BookingService bookingService;
  private  TrainDataRepository trainDataRepository;

  public WebTicketManager(TrainDataRepository trainDataRepository,
                          BookingService bookingService)
  {
    this.trainDataRepository = trainDataRepository;
    this.bookingService = bookingService;
  }

  public Reservation reserve(String trainId, int requestedSeats) {

    Train train = trainDataRepository.findTrain(trainId);

    if (train.requestDoesnotExceeds70perc(requestedSeats))
    {
      List<Seat> availableSeats = train.findAvailableSeats(requestedSeats);

      String bookingRef    = bookingService.createbookingRef();
      Boolean isSuccessful = bookingService.reserve(trainId, availableSeats, bookingRef);

      return (isSuccessful && availableSeats.size() == requestedSeats)
          ? new Reservation(trainId, bookingRef, availableSeats)
          : noSeatsReservation(trainId);
    }

    return noSeatsReservation(trainId);
  }

  private Reservation noSeatsReservation(String trainId) {
    return new Reservation(trainId, "", Arrays.asList());
  }


}
