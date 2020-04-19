public class AirportSecurity implements IParkingObserver {
    String status;

    @Override
    public void update(String message) {
        this.status = message;
    }
}
