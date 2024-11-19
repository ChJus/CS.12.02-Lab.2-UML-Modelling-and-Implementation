import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CruiseTest {
  /*
    Scenario description:
    - The ship 'Titanic' has a passenger capacity 1000
    - Titanic has 10 Premium Passengers (who don't need to pay for activities) with a balance between $500 and $1000.
    - Titanic has 10 Senior Passengers (who receive a 10% discount for activities) with a balance between $400 and $900.
    - Titanic has 10 Standard Passengers (who pay full price for activities) with balance between $500 and $1000.
    - After some consideration, 5 Standard Passengers decide to leave the cruise.
    - The ship has an initial travel itinerary through Paris-Barcelona-China but makes
      a last-minute alteration to Hong Kong-Paris-Barcelona-China. Due to the port being full in China,
      a final change is made to remove China from the final destination stop.
    - Each stop has one activity of each type (Attraction, TheaterShow, WalkingTour)
    - Passengers each sign up for an activity initially, but all decide to leave the activity and stay on the cruise ship
  */
  @Test
  void main() {
    // Test Ship class methods
    Random rand = new Random(); // Generate random details for each object
    Ship ship = new Ship("Titanic", 1000);

    for (int i = 0; i < 10; i++) { // Add 10 arbitrary PremiumPassengers
      PremiumPassenger temp = new PremiumPassenger("" + rand.nextLong(), ship, Math.abs(rand.nextFloat(500, 1000)));
      ship.addPassenger(temp);
    }

    assertEquals(10, ship.getPassengerCount()); // Ensure the 10 passengers are present

    for (int i = 0; i < 10; i++) { // Add 10 SeniorPassengers
      SeniorPassenger temp = new SeniorPassenger("" + rand.nextLong(), ship, Math.abs(rand.nextFloat(400, 900)));
      ship.addPassenger(temp);
    }

    assertEquals(20, ship.getPassengerCount()); // Ensure the total passenger count is now at 20

    for (int i = 0; i < 10; i++) { // Add 10 StandardPassengers and remove 5
      StandardPassenger temp = new StandardPassenger("" + rand.nextLong(), ship, Math.abs(rand.nextFloat(500, 1000)));
      ship.addPassenger(temp);
      if (i % 2 == 0) ship.removePassenger(temp);
    }

    assertEquals(25, ship.getPassengerCount()); // Ensure total passenger count is 25

    // Ensure ship details are accurate
    assertEquals("Titanic", ship.getName());
    assertEquals(1000, ship.getPassengerCapacity());

    // Add itinerary destinations using provided methods
    ship.addStop(new Destination("Paris"));
    ship.addStop(new Destination("Hong Kong"), 0); // Make Hong Kong the new first stop
    ship.addStop(new Destination("Barcelona"));
    ship.addStop(new Destination("China"));

    // Remove 'China' from final/ending destination
    ship.removeStop(ship.getItinerary().get(3));
    assertEquals("Hong Kong", ship.getItinerary().getFirst().getName());
    assertNotEquals("China", ship.getItinerary().getLast().getName());

    // Print information about ship, passengers, stops, activities
    ship.printItinerary();
    ship.printPassengerList();
    ship.printAvailableActivities();

    // Test Destination class methods
    for (Destination destination : ship.getItinerary()) {
      // Create a list of actors starring in the TheaterShow
      TreeMap<String, String> stars = new TreeMap<>();
      for (int i = 0; i < 10; i++) {
        // Randomly generates single-letter names
        stars.put(String.valueOf((char) ('A' + rand.nextInt(0, 25))), String.valueOf((char) ('a' + rand.nextInt(0, 25))));
      }

      // Create one activity of each type (Attraction, TheaterShow, WalkingTour)
      destination.addActivity(new Attraction("Hike", "Go walking in the mountains.", rand.nextFloat(0, 1000), 10, destination));
      destination.addActivity(new TheaterShow("Show", "Nice viewing experience.", rand.nextFloat(0, 1000), 15, destination, stars));
      destination.addActivity(new WalkingTour("Beach Walk", "Walk by the beach and ocean.", rand.nextFloat(0, 1000), 25, destination, rand.nextInt(5, 20)));

      // Test modifying methods to ensure changes are made
      destination.replaceActivity(destination.getActivities().getLast(), new WalkingTour("Ocean Walk", "Walk by the ocean.", rand.nextFloat(0, 1000), 25, destination, rand.nextInt(5, 20)));
      assertEquals("Ocean Walk", destination.getActivities().getLast().getName());
    }

    // Test Passenger and Activity class methods
    for (Destination destination : ship.getItinerary()) {
      for (Activity activity : destination.getActivities()) {
        for (Passenger passenger : ship.getPassengers()) {
          // Randomly sign passengers up for activities
          // Ensure checks are used so passengers don't sign for an activity
          // if it is full, they don't have enough money, or they have already signed up for an activity.
          float balance = passenger.getBalance();
          if (rand.nextDouble() > 0.8) {
            if (passenger.hasEnoughMoneyForActivity(activity) && !activity.isFull() && !passenger.hasActivityAtLocation(destination)) {
              assertEquals(true, passenger.signActivity(activity));
              passenger.signActivity(activity);
            } else {
              assertEquals(false, passenger.signActivity(activity));
            }
          }

          // Print information about the passenger
          // Note their activities should be printed along with their new balance
          passenger.printDetails();

          // Make passenger leave the activity
          // This final step checks to see if balance is reverted to original
          // value if a passenger decides to leave the activity.
          if (passenger.hasActivity(activity)) {
            assertEquals(true, passenger.leaveActivity(activity));
          } else {
            assertEquals(false, passenger.leaveActivity(activity));
          }

          assertEquals(Math.round(balance), Math.round(passenger.getBalance()));
        }
      }
    }
  }
}
