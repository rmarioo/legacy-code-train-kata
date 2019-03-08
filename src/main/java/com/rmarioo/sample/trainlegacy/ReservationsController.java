package com.rmarioo.sample.trainlegacy;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.ResponseEntity.status;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import io.swagger.annotations.Api;

@Api(value="Train reservation controller", description="An api to make reservations")
@RestController
public class ReservationsController {

    @PostMapping("api/reservations")
    public ResponseEntity update(@RequestBody RequestDto requestDto) throws IOException {

        WebTicketManager webTicketManager = new WebTicketManager();
        Reservation reservation = webTicketManager.reserve(requestDto.getTrain_id(), requestDto.getNumber_of_seats());
        return (!reservation.availableSeats.isEmpty())
            ? status(200).body(reservation)
            : status(NOT_ACCEPTABLE).body("reservation failed - no seat reserved");
    }

    @DeleteMapping("api/reservations")
    public ResponseEntity deleteReservations() {
        BookingReferenceAPI.deleteReservations();
        return ResponseEntity.noContent().build();
    }

/*
    @GetMapping("api/reservations")
    public ResponseEntity<List<Reservation>> findReservations()
    {
        return ResponseEntity.ok(BookingReferenceAPI.findReservations());
    }

*/


}
