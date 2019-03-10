package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.DefaultTrainDataRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;

@Api(value="Train topology", description="Train topology information")
@RestController
public class TrainInfoController {


    @GetMapping("api/trains")
    public ResponseEntity<List<Train>> findReservations()
    {
       return ResponseEntity.ok(DefaultTrainDataRepository.TrainDataAPI.findTrains());
    }

}
