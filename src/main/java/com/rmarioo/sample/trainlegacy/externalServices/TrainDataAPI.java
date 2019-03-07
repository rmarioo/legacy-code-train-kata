package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;
import static com.rmarioo.sample.trainlegacy.externalServices.TrainDataAPI.TrainDataService.trains;
import static java.util.Arrays.asList;

import com.rmarioo.sample.trainlegacy.Seat;
import com.rmarioo.sample.trainlegacy.Train;
import com.rmarioo.sample.trainlegacy.CallCollaboratorsFromTestException;
import com.rmarioo.sample.trainlegacy.Trains;

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

  public static class TrainDataService {

    public static Trains trains = initializeTrains();

    private static Trains initializeTrains() {

      Trains trains = new Trains();
      String name = "first";
      trains.addTrain(name,
          new Train(name,
          asList(
              new Seat("A",1),new Seat("A",2),new Seat("A",3),new Seat("A", 4),
              new Seat("B",1),new Seat("B",2),new Seat("B",3),new Seat("B", 4)
              )));
      return trains;
    }

    public Train findTrain(String trainId)
      {
        return trains.find(trainId);

      }

  }
}
