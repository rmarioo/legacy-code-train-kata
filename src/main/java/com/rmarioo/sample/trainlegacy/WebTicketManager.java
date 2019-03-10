package com.rmarioo.sample.trainlegacy;

import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

  private final BookingService bookingService;
  private  TrainDataRepository trainDataRepository;

  public WebTicketManager(TrainDataRepository trainDataRepository, BookingService bookingService) {
    this.trainDataRepository = trainDataRepository;
    this.bookingService = bookingService;
  }

  public Reservation reserve(String trainId, int requestedSeats) {

    Train train = findTrain(trainId);

    boolean enoughSeats = train.coaches.stream().anyMatch(coach -> coach.hasEnoughSeatsFor(requestedSeats));
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


    String bookingRef = createBookingReference();

    Boolean isSuccessful = makeReservation(trainId, availableSeats, bookingRef);

    return  (isSuccessful) ? new Reservation(trainId, bookingRef, availableSeats)
                           : noSeatsReservation(trainId);
  }

  protected Boolean makeReservation(String trainId, List<Seat> availableSeats, String bookingRef) {
    return bookingService.reserve(trainId, availableSeats, bookingRef);
    }


  protected String createBookingReference() {
      return bookingService.createbookingRef();
    }

  protected Train findTrain(String trainId) {
        return trainDataRepository.findTrain(trainId);
    }


}
