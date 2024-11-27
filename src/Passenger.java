import java.util.ArrayList;

public abstract class Passenger {
  private String name; // Name of passenger
  private int id;      // ID of passenger (unique)
  private ArrayList<Activity> activities = new ArrayList<>(); // List of participating activities
  private ArrayList<Destination> stops = new ArrayList<>();   // Stops where passenger has activities
  private Ship ship;  // Ship that passenger is on

  private float balance;  // Passenger's balance
  private float discountRate; // Discount rate (different for different passengers — Premium, Senior, Standard)
  private static int idCounter = 0; // Global ID counter that increments when new passenger is created, leading to a unique ID per person

  protected Passenger(String name, Ship ship, float balance) {
    // Perform common initialization functionality
    setName(name);
    setShip(ship);
    setBalance(balance);
    setId(Passenger.getIdCounter());

    // Increment ID counter to generate unique ID per passenger
    Passenger.incrementCounter();
  }

  // Returns unique ID number that can be used for new passenger
  protected static int getIdCounter() {
    return idCounter;
  }

  // Increments ID number (so it is unique per passenger)
  protected static void incrementCounter() {
    idCounter++;
  }

  // Decrements ID number (can be used when a passenger is removed)
  protected static void decrementCounter() {
    idCounter--;
  }

  // Returns passenger name
  public String getName() {
    return name;
  }

  // Changes passenger name
  public void setName(String name) {
    this.name = name;
  }

  // Returns passenger ID
  public int getId() {
    return id;
  }

  // Changes passenger ID
  protected void setId(int id) {
    this.id = id;
  }

  // Returns activities of passenger
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

  // Checks if passenger has an activity at a specific location
  // This is used to check if they still can sign up for an activity at a location
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

  // Checks if passenger is in an activity
  public boolean hasActivity(Activity activity) {
    return activities.contains(activity);
  }

  // Return the locations where the passenger has an activity
  public ArrayList<Destination> getStops() {
    return stops;
  }

  // Returns ship passenger is on
  public Ship getShip() {
    return ship;
  }

  // Changes passenger's ship
  public void setShip(Ship ship) {
    this.ship = ship;
  }

  // Returns passenger's balance
  public float getBalance() {
    return balance;
  }

  // Changes passenger's balance
  public void setBalance(float balance) {
    this.balance = balance;
  }

  // Top up passenger balance by amount
  public void topup(float money) {
    this.balance += money;
  }

  // Deduct passenger balance by amount
  public void deduct(float cost) {
    this.balance -= cost;
  }

  // Check if passenger has enough money to sign up for an activity
  public boolean hasEnoughMoneyForActivity(Activity activity) {
    return (activity.getCost() * this.discountRate) <= getBalance();
  }

  // Print details about passenger (name, id, balance, discount rate, activities signed and cost)
  public void printDetails() {
    System.out.println(name + " [id: " + id + "]" + " [balance: " + balance + "]" + " [discountRate: " + discountRate + "]");
    for (Activity activity : activities) {
      activity.printDetails();
      System.out.println("Paid: " + activity.getCost() * discountRate + "\n");
    }
  }

  // Allows subclasses to alter discount rate
  // E.g., Senior Passengers (10% off), Premium Passengers (free), Standard Passengers (0% off)
  protected void setDiscountRate(float discountRate) {
    this.discountRate = discountRate;
  }
}
