import java.util.TreeMap;

public class TheaterShow extends Activity {
  private TreeMap<String, String> stars = new TreeMap<>();

  public TheaterShow(String name, String description, float cost, int capacity, Destination location, TreeMap<String, String> stars) {
    setName(name);
    setDescription(description);
    setCost(cost);
    setCapacity(capacity);
    setLocation(location);
    this.stars = stars;
  }

  public TreeMap<String, String> getStars() {
    return stars;
  }

  public void setStars(TreeMap<String, String> stars) {
    this.stars = stars;
  }

  @Override
  public void printDetails() {
    super.printDetails();
    System.out.println(stars);
  }
}
