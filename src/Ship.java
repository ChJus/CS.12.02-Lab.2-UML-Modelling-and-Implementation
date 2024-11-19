import java.util.ArrayList;

public class Ship {
  private String name;
  private int passengerCapacity;
  private ArrayList<Destination> itinerary = new ArrayList<>();
  private ArrayList<Passenger> passengers = new ArrayList<>();
  private int passengerCount = 0;

  public Ship(String name, int passengerCapacity) {
    this.name = name;
    this.passengerCapacity = passengerCapacity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPassengerCapacity() {
    return passengerCapacity;
  }

  public void setPassengerCapacity(int passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }

  public ArrayList<Destination> getItinerary() {
    return itinerary;
  }

  public ArrayList<Passenger> getPassengers() {
    return passengers;
  }

  public int getPassengerCount() {
    return passengerCount;
  }

  public boolean addPassenger(Passenger passenger) {
    if (passengerCount < passengerCapacity) {
      passengerCount++;
      passengers.add(passenger);
      return true;
    }
    return false;
  }

  public boolean removePassenger(Passenger passenger) {
    if (passengers.contains(passenger)) {
      passengerCount--;
      return passengers.remove(passenger);
    }
    return false;
  }

  public void addStop(Destination stop) {
    itinerary.add(stop);
  }

  public void addStop(Destination stop, int index) throws IndexOutOfBoundsException {
    itinerary.add(index, stop);
  }

  public boolean removeStop(Destination stop) {
    return itinerary.remove(stop);
  }

  public void printItinerary() {
    System.out.println(name + ", fill status: " + passengerCount + "/" + passengerCapacity);
    for (Destination destination : itinerary) {
      destination.printDetails();
    }
  }

  public void printPassengerList() {
    System.out.println(name + ", fill status: " + passengerCount + "/" + passengerCapacity);
    for (Passenger passenger : passengers) {
      System.out.println(passenger.getName() + ", id: " + passenger.getId());
    }
  }

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
