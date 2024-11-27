import java.util.TreeMap;

public class TheaterShow extends Activity {
  // Stores alphabetically ordered map of stars (actor name and role name)
  private TreeMap<String, String> stars;

  // Create theater activity with name, description, cost, capacity, location, stars
  public TheaterShow(String name, String description, float cost, int capacity, Destination location, TreeMap<String, String> stars) {
    setName(name);
    setDescription(description);
    setCost(cost);
    setCapacity(capacity);
    setLocation(location);
    this.stars = stars;
  }

  // Returns ordered list of stars performing (actor name, role name)
  public TreeMap<String, String> getStars() {
    return stars;
  }

  // Sets list of stars performing
  public void setStars(TreeMap<String, String> stars) {
    this.stars = stars;
  }

  // Overrides printDetails() method of Activity to also print list of stars
  @Override
  public void printDetails() {
    super.printDetails();
    System.out.println(stars);
  }
}
