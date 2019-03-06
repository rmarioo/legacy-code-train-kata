package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;
import static com.rmarioo.sample.trainlegacy.externalServices.TrainDataService.trains;

import com.rmarioo.sample.trainlegacy.Train;

import java.util.List;
import java.util.stream.Collectors;

public class TrainDataAPI {


  public static Train findTrain(String trainId) {

    if (fromTestEnvironment)
      throw new RuntimeException("External service should not be called directly from unit Test");
    else
    return new TrainDataService().findTrain(trainId);
  }

  public static List<Train> findTrains()
  {
    if (fromTestEnvironment)
      throw new RuntimeException("External service should not be called directly from unit Test");
    else
      return trains.getTrainsMap().values().stream().collect(Collectors.toList());
  }
}
