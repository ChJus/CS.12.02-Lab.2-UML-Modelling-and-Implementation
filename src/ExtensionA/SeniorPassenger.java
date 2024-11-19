package ExtensionA;

public class SeniorPassenger extends Passenger {
  public SeniorPassenger(String name, Ship ship, float balance) {
    super(name, ship, balance);
    this.setDiscountRate(0.9f);
  }
}
