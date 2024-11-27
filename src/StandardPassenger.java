public class StandardPassenger extends Passenger {
  // Creates Standard Passenger, who pays regular fare for activities
  public StandardPassenger(String name, Ship ship, float balance) {
    super(name, ship, balance);
    setDiscountRate(1.0f);
  }
}
