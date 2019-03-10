package com.rmarioo.sample.trainlegacy;

import com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI;

public class DefaultTrainDataRepository implements TrainDataRepository
{
  @Override
  public Train findTrain(String trainId) {
    return TrainDataAPI.findTrain(trainId);
  }
}
