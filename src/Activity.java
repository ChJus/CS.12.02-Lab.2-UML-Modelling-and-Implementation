import java.util.ArrayList;

public abstract class Activity {
  private String name;
  private String description;
  private float cost;
  private int capacity;
  private int participantCount = 0;
  private Destination location;
  private final ArrayList<Passenger> participants = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getParticipantCount() {
    return participantCount;
  }

  public Destination getLocation() {
    return location;
  }

  public void setLocation(Destination location) {
    this.location = location;
  }

  public boolean isFull() {
    return participantCount == capacity;
  }

  public void addParticipant(Passenger participant) {
    participants.add(participant);
    participantCount++;
  }

  public boolean removeParticipant(Passenger participant) {
    // If the participant is in the activity, remove them from the activity list
    // And remember to alter the participant count.
    if (participants.contains(participant)) {
      participantCount--;
      participants.remove(participant);
      return true;
    }
    return false;
  }

  public boolean hasParticipant(Passenger participant) {
    return participants.contains(participant);
  }

  public void printDetails() {
    System.out.println(name + " (" + location.getName() + ")" + ": " + description);
    System.out.println("$" + cost + ", fill capacity: " + participantCount + "/" + capacity);
  }
}
