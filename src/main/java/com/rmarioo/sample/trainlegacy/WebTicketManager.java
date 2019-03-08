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

        if (requestDoesNotExceeds70perc(requestedSeats, trainInst)) {
            List<Seat> availableSeats = availableSeats(requestedSeats, trainInst);


            String bookingRef;
            if (availableSeats.size() == requestedSeats) {
                bookingRef = BookingReferenceAPI.createbookingRef();

                applyBookingRef(availableSeats, bookingRef);

                if (availableSeats.size() == 0) {
                    String output = String.format("Reserved seat(s): ", 0);
                    System.out.println(output);
                }

                String todod = "[TODOD]";

                Boolean isSuccessful = BookingReferenceAPI.reserve(trainId, availableSeats, bookingRef);
                if  (isSuccessful)
                    return new Reservation(trainId, bookingRef, availableSeats);
                else
                    return new Reservation(trainId,"", Arrays.asList());

            } else {
                return new Reservation(trainId,"", Arrays.asList());
            }



        }

       return new Reservation(trainId,"", Arrays.asList());
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

    protected boolean requestDoesNotExceeds70perc(int requestedSeats, Train trainInst) {
        return (trainInst.getReservedSeats() + requestedSeats) <= Math.floor(0.70 * trainInst.getMaxSeat());
    }


}
