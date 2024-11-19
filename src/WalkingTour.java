public class WalkingTour extends Activity {
  private float distance;

  public WalkingTour(String name, String description, float cost, int capacity, Destination location, float distance) {
    setName(name);
    setDescription(description);
    setCost(cost);
    setCapacity(capacity);
    setLocation(location);
    this.distance = distance;
  }

  public float getDistance() {
    return distance;
  }

  public void setDistance(float distance) {
    this.distance = distance;
  }

  @Override
  public void printDetails() {
    super.printDetails();
    System.out.println("Distance: " + distance);
  }
}
