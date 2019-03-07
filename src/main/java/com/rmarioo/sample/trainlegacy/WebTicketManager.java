package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;
import com.rmarioo.sample.trainlegacy.externalServices.Reservation;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebTicketManager
{

    public Reservation reserve(String trainId, int seats) throws IOException {
        List<Seat> availableSeats = new ArrayList<>();
        int count = 0;
        Train result;
        String bookingRef;

        Train trainInst = TrainDataAPI.findTrain(trainId);

        result = trainInst;

        if ((trainInst.getReservedSeats() + seats) <= Math.floor(0.70 * trainInst.getMaxSeat())) {
            int numberOfReserv = 0;
            for (int index = 0, i = 0; index < trainInst.Seats.size(); index++) {
                Seat each = trainInst.Seats.get(index);
                if (each.getBookingRef() == "") {
                    i++;
                    if (i <= seats) {
                        availableSeats.add(each);
                    }
                }
            }

            for (Seat seat : availableSeats) {
                count++;
            }

            int reservedSets = 0;

            if (count != seats) {
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

            if (numberOfReserv == seats) {

                if (reservedSets == 0) {
                    String output = String.format("Reserved seat(s): ", reservedSets);
                    System.out.println(output);
                }

                String todod = "[TODOD]";

                return BookingReferenceAPI.reserve(trainId, availableSeats, bookingRef);
            }

        }

       return new Reservation(trainId,"", Arrays.asList());
    }



}
