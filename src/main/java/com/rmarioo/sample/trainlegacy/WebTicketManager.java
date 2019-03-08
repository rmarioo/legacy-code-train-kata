package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

  public Reservation reserve(String trainId, int requestedSeats) {

    Train trainInst = TrainDataAPI.findTrain(trainId);

    if (requestDoesNotExceeds70percOfTrainAvailability(requestedSeats, trainInst))
    {
      if (enoughtSeatsAvailable(requestedSeats, trainInst))
      {
        String bookingRef = BookingReferenceAPI.createbookingRef();
        return makeReservation(trainId, availableSeats(requestedSeats, trainInst), bookingRef);
      }
      else
        return emptyReservation(trainId);
    }

    return emptyReservation(trainId);
  }



  protected boolean enoughtSeatsAvailable(int requestedSeats, Train trainInst) {
    return availableSeats(requestedSeats, trainInst).size() == requestedSeats;
  }


  protected Reservation emptyReservation(String trainId) {
    return new Reservation(trainId,"", Arrays.asList());
  }

  protected Reservation makeReservation(String trainId, List<Seat> availableSeats, String bookingRef) {
    applyBookingRef(availableSeats, bookingRef);


    Boolean isSuccessful = BookingReferenceAPI.reserve(trainId, availableSeats, bookingRef);
    if  (isSuccessful)
        return new Reservation(trainId, bookingRef, availableSeats);
    else
        return emptyReservation(trainId);
  }

  protected void applyBookingRef(List<Seat> availableSeats, String bookingRef) {
        for (Seat availableSeat : availableSeats) {
            availableSeat.setBookingRef(bookingRef);
        }
    }

    protected List<Seat> availableSeats(int requestedSeats, Train trainInst) {
        List<Seat> availableSeats = new ArrayList<>();
        for (int index = 0, i = 0; index < trainInst.Seats.size(); index++) {
            Seat each = trainInst.Seats.get(index);
            if (each.getBookingRef() == "") {
                i++;
                if (i <= requestedSeats) {
                    availableSeats.add(each);
                }
            }
        }
        return availableSeats;
    }

    protected boolean requestDoesNotExceeds70percOfTrainAvailability(int requestedSeats, Train trainInst) {
        return (trainInst.getReservedSeats() + requestedSeats) <= Math.floor(0.70 * trainInst.getMaxSeat());
    }


}
