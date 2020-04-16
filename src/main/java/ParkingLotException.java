public class ParkingLotException extends Exception {
    public ExceptionType type;
    public String message;

    public enum ExceptionType {
        PARKING_FULL, NO_SUCH_VEHICLE;
    }

    ParkingLotException(ExceptionType type, String message) {
        this.type = type;
        this.message = message;
    }
}
