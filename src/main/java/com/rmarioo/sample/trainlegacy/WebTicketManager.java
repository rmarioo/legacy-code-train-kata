package com.rmarioo.sample.trainlegacy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebTicketManager {
    private final BookingReferenceService bookingReferenceService;
    private final TrainDataService trainDataService;

    public WebTicketManager() {
        bookingReferenceService = new BookingReferenceService();
        trainDataService = new TrainDataService();
    }

    public String reserve(String trainId, int seats) throws IOException {
        List<Seat> availableSeats = new ArrayList<Seat>();
        int count = 0;
        String result = "";
        String bookingRef;

        // get the train
        String JsonTrain = trainDataService.invoke(trainId);

        result = JsonTrain;

        Train trainInst = new Train(JsonTrain);
        if ((trainInst.ReservedSeats + seats) <= Math.floor(0.70 * trainInst.getMaxSeat())) {
            int numberOfReserv = 0;
            // find seats to reserve
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
                return String.format("{{\"train_id\": \"%s\", \"booking_reference\": \"\", \"seats\": []}}",
                        trainId);
            } else {
                bookingRef = bookingReferenceService.createbookingRef();
                
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

                return bookingReferenceService.reserve(trainId, availableSeats, bookingRef);
            }

        }
        return String.format("{{\"train_id\": \"%s\", \"booking_reference\": \"\", \"seats\": []}}", trainId);
    }



}
