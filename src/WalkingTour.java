public class WalkingTour extends Activity {
  private float distance; // Distance of trail walked

  // Create walking activity with name, description, cost, capacity, location, distance
  public WalkingTour(String name, String description, float cost, int capacity, Destination location, float distance) {
    setName(name);
    setDescription(description);
    setCost(cost);
    setCapacity(capacity);
    setLocation(location);
    this.distance = distance;
  }

  // Returns distance walked in activity
  public float getDistance() {
    return distance;
  }

  // Changes distance walked in activity
  public void setDistance(float distance) {
    this.distance = distance;
  }

  // Overrides printDetails() in Activity to also print the distance walked in activity
  @Override
  public void printDetails() {
    super.printDetails();
    System.out.println("Distance: " + distance);
  }
}
