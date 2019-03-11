# Legacy code train kata

Partially adapted from 
([42skills kata-TrainReservation](https://github.com/42skillz/kata-TrainReservation)) 
 idea from ([from Emily Bache's kata](https://github.com/emilybache/KataTrainReservation))

Railway operators aren't always known for their use of cutting edge technology, and in this case they're a little behind the times. The railway people want you to help them to improve their online booking service. They'd like to be able to not only sell tickets online, but to decide exactly which seats should be reserved, at the time of booking.

You're working on the "TicketOffice" service, and your next task is to implement the feature for 
reserving seats on a particular train. The railway operator has a service-oriented architecture, 
and both the interface you'll need to fulfill, and some services you'll need to use are already implemented.  


## Business Rules around Reservations

There are various business rules and policies around which seats may be reserved. For a train 
overall, no more than 70% of seats may be reserved in advance. However, there is another business
 rule that says you _must_ put all the seats for one reservation in the same coach.  
 
## Your task and constraints 

The previous guys already provided an implementation but it the code is difficult to read and there 
is a bug so not all business rules are satisfied.
You should find the bug and fix it.

But you have some constraints:
- You cannot change the code if is not covered by tests ( only few changes are allowed to make 
the code testables)
- You cannot put the code that fixes the bug directly in the actual code, instead you 
should make a new ( missing domain ) to emerge in new class and then apply the change mainly on this new classes  

It is recommended to follow those steps in order:

1. in order to have a better understanding , do a sketch refactoring and the throw it away
2. create a safety net of tests to certify the actual behaviuor ( only unit test allowed - no 
integration tests! )
3. refactor the code in order and let the domain to "emerge" in particular the domain in which you 
should apply the change that fixes the code
4. apply the change ( it should be and "easy" and "trivial" change so no more than 2 - 3 commits 
are allowed )


### How to launch the TrainApplication

Run TrainApplication class

navigate to http://localhost:8080/swagger-ui.html 

Here you will find two endpoints:


1. train-info-controller 
2. reservations-controller


train-info-controller will give you a list of train topology:
Actually there is one train with name "first" with 2 coaches and 4 seats in each coach
 
```json
[
  {
    "Seats": [
      {
        "coachName": "A",
        "seatNumber": 1,
        "bookingRef": ""
      },
      {
        "coachName": "A",
        "seatNumber": 2,
        "bookingRef": ""
      },
      {
        "coachName": "A",
        "seatNumber": 3,
        "bookingRef": ""
      },
      {
        "coachName": "A",
        "seatNumber": 4,
        "bookingRef": ""
      },
      {
        "coachName": "B",
        "seatNumber": 1,
        "bookingRef": ""
      },
      {
        "coachName": "B",
        "seatNumber": 2,
        "bookingRef": ""
      },
      {
        "coachName": "B",
        "seatNumber": 3,
        "bookingRef": ""
      },
      {
        "coachName": "B",
        "seatNumber": 4,
        "bookingRef": ""
      }
    ],
    "name": "first",
    "reservedSeats": 0,
    "maxSeat": 8
  }
]
```

with the reservations-controller you can do some reservation and look at the output to check if 
the reservation is successfull

Yuo can also see the reserved seats by using the train-info-controller.

### Solutions

There is not only one solution to this kata.

If you want to take a look at one you can look at the branches

- ([master](https://github.com/rmarioo/legacy-code-train-kata)) 
- ([Step 1 Understand](https://github.com/rmarioo/legacy-code-train-kata/tree/step_1_understand)) 
- ([Step_2_Vover](https://github.com/rmarioo/legacy-code-train-kata/tree/step_2_cover)) 
- ([Step_3_Refactor](https://github.com/rmarioo/legacy-code-train-kata/tree/step_3_refactor)) 
- ([Step_4_Apply the change](https://github.com/rmarioo/legacy-code-train-kata/tree/step_4_apply_change)) 
- ([Final result](https://github.com/rmarioo/legacy-code-train-kata/tree/final)) 






