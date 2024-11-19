package ExtensionA;

import java.util.ArrayList;

public abstract class Passenger {
  private String name;
  private int id;
  private ArrayList<Activity> activities = new ArrayList<>();
  private ArrayList<Destination> stops = new ArrayList<>();
  private Ship ship;

  private float balance;
  private float discountRate;
  private static int idCounter = 0;

  protected Passenger(String name, Ship ship, float balance) {
    setName(name);
    setShip(ship);
    setBalance(balance);
    setId(Passenger.getIdCounter());
    Passenger.incrementCounter();
  }

  protected static int getIdCounter() {
    return idCounter;
  }

  protected static void incrementCounter() {
    idCounter++;
  }

  protected static void decrementCounter() {
    idCounter--;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  protected void setId(int id) {
    this.id = id;
  }

  public ArrayList<Activity> getActivities() {
    return activities;
  }

  // Note the participant handles signing and leaving the activity
  // A participant can sign up for the activity if: 1) activity isn't full,
  // 2) they have enough money to pay, and 3) they haven't already signed
  // up for an activity at the location.
  public boolean signActivity(Activity activity) {
    if (activity.isFull() || !hasEnoughMoneyForActivity(activity) || hasActivityAtLocation(activity.getLocation())) {
      return false;
    } else {
      // Pay for activity
      deduct(activity.getCost() * this.discountRate);

      // Add activity to participant list and update that list in the corresponding Activity object
      activity.addParticipant(this);
      activities.add(activity);
      stops.add(activity.getLocation()); // Stores list of places where participant has activity
      return true;
    }
  }

  public boolean hasActivityAtLocation(Destination location) {
    return stops.contains(location);
  }

  // Leaves activity
  // This method should revert all changes made in the signActivity method
  public boolean leaveActivity(Activity activity) {
    if (activities.contains(activity)) {
      topup(activity.getCost() * this.discountRate);
      activity.removeParticipant(this);
      activities.remove(activity);
      stops.remove(activity.getLocation());
      return true;
    }
    return false;
  }

  public boolean hasActivity(Activity activity) {
    return activities.contains(activity);
  }

  public ArrayList<Destination> getStops() {
    return stops;
  }

  public Ship getShip() {
    return ship;
  }

  public void setShip(Ship ship) {
    this.ship = ship;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public void topup(float money) {
    this.balance += money;
  }

  public void deduct(float cost) {
    this.balance -= cost;
  }

  public boolean hasEnoughMoneyForActivity(Activity activity) {
    return (activity.getCost() * this.discountRate) <= getBalance();
  }

  public void printDetails() {
    System.out.println(name + " [id: " + id + "]" + " [balance: " + balance + "]" + " [discountRate: " + discountRate + "]");
    for (Activity activity : activities) {
      activity.printDetails();
      System.out.println("Paid: " + activity.getCost() * discountRate + "\n");
    }
  }

  protected void setDiscountRate(float discountRate) {
    this.discountRate = discountRate;
  }
}
