import java.util.ArrayList;

public class Destination {
  private String name;
  private ArrayList<Activity> activities = new ArrayList<>();

  public Destination(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Activity> getActivities() {
    return activities;
  }

  public void addActivity(Activity activity) {
    if (this.activities.contains(activity)) return;
    this.activities.add(activity);
  }

  public boolean removeActivity(Activity activity) {
    return this.activities.remove(activity);
  }

  // Replaces an activity entry at the destination with another
  public boolean replaceActivity(Activity oldActivity, Activity newActivity) {
    if (this.activities.contains(oldActivity)) {
      int index = this.activities.indexOf(oldActivity);
      this.activities.remove(oldActivity);
      this.activities.add(index, newActivity);
      return true;
    }
    return false;
  }

  public void printDetails() {
    System.out.println();
    System.out.println(name);
    System.out.println("=".repeat(30));
    for (Activity activity : activities) {
      activity.printDetails();
    }
  }
}
