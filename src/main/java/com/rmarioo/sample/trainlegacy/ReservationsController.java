package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.BookingReferenceAPI;
import com.rmarioo.sample.trainlegacy.externalServices.Reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import io.swagger.annotations.Api;

@Api(value="Train reservation controller", description="An api to make reservations")
@RestController
public class ReservationsController {

    @PostMapping("api/reservations")
    public Reservation update(@RequestBody RequestDto requestDto) throws IOException {

        WebTicketManager webTicketManager = new WebTicketManager();
        return webTicketManager.reserve(requestDto.getTrain_id(), requestDto.getNumber_of_seats());
    }

    @DeleteMapping("api/reservations")
    public ResponseEntity deleteReservations() {
        BookingReferenceAPI.deleteReservations();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("api/reservations")
    public ResponseEntity<List<Reservation>> findReservations()
    {
        return ResponseEntity.ok(BookingReferenceAPI.findReservations());
    }



}
