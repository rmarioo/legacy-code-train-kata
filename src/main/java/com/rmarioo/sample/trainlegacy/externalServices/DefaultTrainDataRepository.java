package com.rmarioo.sample.trainlegacy.externalServices;

import static com.rmarioo.sample.trainlegacy.TrainApplication.fromTestEnvironment;

import com.rmarioo.sample.trainlegacy.CallCollaboratorsFromTestException;
import com.rmarioo.sample.trainlegacy.Train;
import com.rmarioo.sample.trainlegacy.Trains;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultTrainDataRepository implements TrainDataRepository
{
  @Override
  public Train findTrain(String trainId) {
    return TrainDataAPI.findTrain(trainId);
  }

  public static class TrainDataAPI {


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
        return Trains.getInstance().getTrainsMap().values().stream().collect(Collectors.toList());
    }

    public static class TrainDataService {


      public Train findTrain(String trainId)
        {
          return Trains.getInstance().find(trainId);

        }

    }
  }
}
