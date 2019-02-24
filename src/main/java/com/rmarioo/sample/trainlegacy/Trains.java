package com.rmarioo.sample.trainlegacy;

import java.util.HashMap;
import java.util.Map;

public class Trains
{
  private Map<String, TrainJson> trainsMap = new HashMap<>();

  public TrainJson find(String trainId) {
     return trainsMap.get(trainId);
  }

  public void addTrain(String trainId, TrainJson trainJson) {
    trainsMap.put(trainId,trainJson);
  }
}