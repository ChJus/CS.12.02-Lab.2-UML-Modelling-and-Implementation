public class Attraction extends Activity {
  // Attraction (name, description, cost, capacity, and location)
  public Attraction(String name, String description, float cost, int capacity, Destination location) {
    setName(name);
    setDescription(description);
    setCost(cost);
    setCapacity(capacity);
    setLocation(location);
  }
}
