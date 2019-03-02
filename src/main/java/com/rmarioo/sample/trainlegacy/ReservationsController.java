package com.rmarioo.sample.trainlegacy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import io.swagger.annotations.Api;

@Api(value="Train reservation controller", description="An api to make reservations")
@RestController
public class ReservationsController {

    @PostMapping("api/reservations")
    public String update(@RequestBody RequestDto requestDto) throws IOException {
        WebTicketManager.fromTestEnvironment = false;
        WebTicketManager webTicketManager = new WebTicketManager();
        return webTicketManager.reserve(requestDto.getTrain_id(), requestDto.getNumber_of_seats());
    }

    @GetMapping("api/value")
    public String get() {
        return "Reservations";
    }
}
