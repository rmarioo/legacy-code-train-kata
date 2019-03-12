package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingService;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataRepository;

import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

  private final BookingService bookingService;
  private TrainDataRepository trainDataRepository;

  public WebTicketManager(TrainDataRepository trainDataRepository,
                          BookingService bookingService)
  {
    this.trainDataRepository = trainDataRepository;
    this.bookingService = bookingService;
  }

  public Reservation reserve(String trainName, int requestedSeats) {

    Train train = trainDataRepository.findTrain(trainName);

    if (train.hasAvailabilityFor(requestedSeats))
    {
      List<Seat> availableSeats = train.availableSeatsFor(requestedSeats);

      String bookingRef    = bookingService.createbookingRef();
      Boolean isReservationSuccesful = bookingService.reserve(trainName, availableSeats, bookingRef);

      return (isReservationSuccesful)
          ? new Reservation(trainName, bookingRef, availableSeats)
          : noSeatsReservation(trainName);
    }

    return noSeatsReservation(trainName);
  }


  private Reservation noSeatsReservation(String trainId) {
    return new Reservation(trainId, "", Arrays.asList());
  }


}
