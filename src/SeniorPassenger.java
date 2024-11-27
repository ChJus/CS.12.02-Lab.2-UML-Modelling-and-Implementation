public class SeniorPassenger extends Passenger {
  // Creates Senior Passenger, which are passengers with 10% discount for activities
  public SeniorPassenger(String name, Ship ship, float balance) {
    super(name, ship, balance);
    this.setDiscountRate(0.9f);
  }
}
