package com.rmarioo.sample.trainlegacy.externalServices.traindata;

import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;
import static com.rmarioo.sample.trainlegacy.externalServices.traindata.TrainDataService.trains;

import com.rmarioo.sample.trainlegacy.Train;
import com.rmarioo.sample.trainlegacy.CallCollaboratorsFromTestException;

import java.util.List;
import java.util.stream.Collectors;

public class TrainDataAPI {


  public static Train findTrain(String trainId) {

    if (fromTestEnvironment)
      throw new CallCollaboratorsFromTestException(TrainDataAPI.class);
    else
    return new TrainDataService().findTrain(trainId);
  }

  public static List<Train> findTrains()
  {
    if (fromTestEnvironment)
      throw  new CallCollaboratorsFromTestException(TrainDataAPI.class);
    else
      return trains.getTrainsMap().values().stream().collect(Collectors.toList());
  }
}
