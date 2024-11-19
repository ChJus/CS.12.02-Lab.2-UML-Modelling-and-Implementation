public class Attraction extends Activity {
  public Attraction(String name, String description, float cost, int capacity, Destination location) {
    setName(name);
    setDescription(description);
    setCost(cost);
    setCapacity(capacity);
    setLocation(location);
  }
}
