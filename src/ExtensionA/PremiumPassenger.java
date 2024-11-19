package ExtensionA;

public class PremiumPassenger extends Passenger {
  public PremiumPassenger(String name, Ship ship, float balance) {
    super(name, ship, balance);
    setDiscountRate(0f);
  }
}
