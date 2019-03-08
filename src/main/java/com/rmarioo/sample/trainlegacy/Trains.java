package com.rmarioo.sample.trainlegacy;

import java.util.HashMap;
import java.util.Map;

public class Trains
{
  private Map<String, Train> trainsMap = new HashMap<>();

  public Train find(String trainId) {
     return trainsMap.get(trainId);
  }

  public void addTrain(String trainId, Train train) {
    trainsMap.put(trainId,train);
  }

  public void deleteAllTrainReservations() {
    trainsMap.values().stream().forEach(train -> resetReservations(train));
  }

  private void resetReservations(Train train) {

    train.allSeats().stream().forEach(seat -> seat.setBookingRef(""));
  }

  public Map<String, Train> getTrainsMap() {
    return trainsMap;
  }
}
