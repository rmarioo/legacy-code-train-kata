package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;

import com.rmarioo.sample.trainlegacy.Train;

public class TrainDataAPI {


  public static Train findTrain(String trainId) {

    if (fromTestEnvironment)
      throw new RuntimeException("External service should not be called directly from unit Test");
    else
    return new TrainDataService().findTrain(trainId);
  }
}
