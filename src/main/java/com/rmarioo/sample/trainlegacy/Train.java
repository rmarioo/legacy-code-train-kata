package com.rmarioo.sample.trainlegacy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Train {
    public int ReservedSeats;
    public List<Seat> Seats;

    public Train(List<Seat> seats) {
        Seats = seats;
        for (Seat seat : seats) {
            if (seat.getBookingRef() !="")
                ReservedSeats ++;
        }
    }



    public Train(String trainTopol) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        TrainJson trainJson = objectMapper.readValue(trainTopol, TrainJson.class);

        Seats = new ArrayList<>();
        for (SeatJson seat : trainJson.seats) {
            if (seat.booking_reference!="")
                ReservedSeats ++;
            Seats.add(toSeat(seat));
        }
    }

    private Seat toSeat(SeatJson seat) {
        return new Seat(seat.coach,Integer.parseInt(seat.seat_number),seat.booking_reference);
    }
 /*   public Train(String trainTopol) throws IOException {

        Seats = new ArrayList<Seat>();

        //var sample:
        //"{\"seats\": {\"1A\": {\"booking_reference\": \"\", \"seat_number\": \"1\", \"coach\": \"A\"}, \"2A\": {\"booking_reference\": \"\", \"seat_number\": \"2\", \"coach\": \"A\"}}}";
        final ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Map<String, SeatJson>> stuff_in_stuff = objectMapper.readValue(trainTopol, new TypeReference<Map<String, Map<String, SeatJson>>>() {
        });

        for (Map<String, SeatJson> value : stuff_in_stuff.values()) {
            for (SeatJson seatJson : value.values()) {
                int seat_number = Integer.parseInt(seatJson.seat_number);
                Seats.add(new Seat(seatJson.coach, seat_number, seatJson.booking_reference));
                if (!(new Seat(seatJson.coach, seat_number, seatJson.booking_reference).getBookingRef() == "")) {
                    this.ReservedSeats++;
                }
            }
        }
    }*/

    public int getMaxSeat() {
        return this.Seats.size();
    }

    public boolean hasLessThanThreshold(int i) {
        return ReservedSeats < i;
    }
}