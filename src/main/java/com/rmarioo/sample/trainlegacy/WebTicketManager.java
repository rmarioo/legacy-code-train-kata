package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

    public Reservation reserve(String trainId, int requestedSeats) throws IOException {
        int count = 0;
        String bookingRef;

        Train trainInst = TrainDataAPI.findTrain(trainId);

        Train result = trainInst;

        if ((trainInst.getReservedSeats() + requestedSeats) <= Math.floor(0.70 * trainInst.getMaxSeat())) {
            int numberOfReserv = 0;
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

            for (Seat seat : availableSeats) {
                count++;
            }

            int reservedSets = 0;

            if (count != requestedSeats) {
                return new Reservation(trainId,"", Arrays.asList());
            }
            else {
                bookingRef = BookingReferenceAPI.createbookingRef();
                
                for (Seat availableSeat : availableSeats) {
                    availableSeat.setBookingRef(bookingRef);
                    numberOfReserv++;
                    reservedSets++;
                }
            }

            if (numberOfReserv == requestedSeats) {

                if (reservedSets == 0) {
                    String output = String.format("Reserved seat(s): ", reservedSets);
                    System.out.println(output);
                }

                String todod = "[TODOD]";

                Boolean isSuccessful = BookingReferenceAPI.reserve(trainId, availableSeats, bookingRef);
                if  (isSuccessful)
                    return new Reservation(trainId, bookingRef, availableSeats);
                else
                    return new Reservation(trainId,"", Arrays.asList());

            }

        }

       return new Reservation(trainId,"", Arrays.asList());
    }



}
