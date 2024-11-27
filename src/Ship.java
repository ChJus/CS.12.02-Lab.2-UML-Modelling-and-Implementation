import java.util.ArrayList;

public class Ship {
  private String name; // Name of ship
  private int passengerCapacity; // Maximum passenger capacity
  private ArrayList<Destination> itinerary = new ArrayList<>(); // Ordered list of stops/destinations in itinerary
  private ArrayList<Passenger> passengers = new ArrayList<>(); // List of passengers on ship
  private int passengerCount = 0; // Passenger count on ship

  // Creates ship with name and maximum passenger capacity
  public Ship(String name, int passengerCapacity) {
    this.name = name;
    this.passengerCapacity = passengerCapacity;
  }

  // Returns name of ship
  public String getName() {
    return name;
  }

  // Changes name of ship
  public void setName(String name) {
    this.name = name;
  }

  // Returns maximum passenger capacity
  public int getPassengerCapacity() {
    return passengerCapacity;
  }

  // Changes maximum passenger capacity
  public void setPassengerCapacity(int passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }

  // Returns ship itinerary (list of destination stops)
  public ArrayList<Destination> getItinerary() {
    return itinerary;
  }

  // Returns list of passengers aboard ship
  public ArrayList<Passenger> getPassengers() {
    return passengers;
  }

  // Returns number of passengers on ship
  public int getPassengerCount() {
    return passengerCount;
  }

  // Adds passenger to cruise ship (if ship is not full)
  public boolean addPassenger(Passenger passenger) {
    if (passengerCount < passengerCapacity) {
      passengerCount++;
      passengers.add(passenger);
      return true;
    }
    return false;
  }

  // Remove passenger from cruise ship
  public boolean removePassenger(Passenger passenger) {
    if (passengers.contains(passenger)) {
      passengerCount--;
      return passengers.remove(passenger);
    }
    return false;
  }

  // Adds stop to end of itinerary
  public void addStop(Destination stop) {
    itinerary.add(stop);
  }

  // Adds stop to specific index in itinerary
  public void addStop(Destination stop, int index) throws IndexOutOfBoundsException {
    itinerary.add(index, stop);
  }

  // Removes stop from itinerary
  public boolean removeStop(Destination stop) {
    return itinerary.remove(stop);
  }

  // Prints ship itinerary (ship name, number of passengers and maximum capacity, destinations in itinerary)
  public void printItinerary() {
    System.out.println(name + ", fill status: " + passengerCount + "/" + passengerCapacity);
    for (Destination destination : itinerary) {
      destination.printDetails();
    }
  }

  // Prints list of passengers (ship name, passenger count and maximum capacity, passenger information)
  public void printPassengerList() {
    System.out.println(name + ", fill status: " + passengerCount + "/" + passengerCapacity);
    for (Passenger passenger : passengers) {
      System.out.println(passenger.getName() + ", id: " + passenger.getId());
    }
  }

  // Prints list of still-available activities in each destination in itinerary
  public void printAvailableActivities() {
    System.out.println(name + ", available activities: ");
    for (Destination destination : itinerary) {
      System.out.println(destination.getName());
      for (Activity activity : destination.getActivities()) {
        if (!activity.isFull()) {
          activity.printDetails();
        }
      }
    }
  }
}
