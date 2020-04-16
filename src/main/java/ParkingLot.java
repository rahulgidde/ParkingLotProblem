public class ParkingLot {
    //VARIABLE
    private Object vehicle;

    //DEFAULT CONSTRUCTOR
    public ParkingLot() {
    }

    //METHOD FOR PARKING VEHICLE
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_FULL, "Parking Lot is Full");
        this.vehicle = vehicle;
    }

    //METHOD FOR UNPARK VEHICLE
    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle");
        if (this.vehicle != null)
            this.vehicle = null;
    }

    //METHOD FOR CHECK VEHICLE PARKED
    public boolean isParked(Object vehicle) {
        if (this.vehicle != null)
            return false;
        return true;
    }

    //METHOD FOR CHECK VEHICLE UNPARKED
    public boolean isUnParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
        