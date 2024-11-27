public class PremiumPassenger extends Passenger {
  // Creates Premium Passenger, which are passengers who don't need to pay for participating in activities
  public PremiumPassenger(String name, Ship ship, float balance) {
    super(name, ship, balance);
    setDiscountRate(0f);
  }
}
