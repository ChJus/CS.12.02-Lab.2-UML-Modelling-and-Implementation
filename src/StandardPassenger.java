public class StandardPassenger extends Passenger {
  public StandardPassenger(String name, Ship ship, float balance) {
    super(name, ship, balance);
    setDiscountRate(1.0f);
  }
}
