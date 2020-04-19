import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkingLot implements IParkingLotSystem {
    int parkingLotCapacity = 2;
    private LinkedHashMap<String, Object> parkingMap = new LinkedHashMap<String, Object>();
    private List<IParkingObserver> observers = new ArrayList<>();

    //DEFAULT CONSTRUCTOR
    public ParkingLot() {
    }

    //METHOD FOR PARKING VEHICLE
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (this.parkingMap.size() <= parkingLotCapacity) {
            parkingMap.put(vehicle.getId(), vehicle);
        } else if (parkingMap.size() == parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_FULL, "Parking Lot is Full");
        if (parkingMap.size() == parkingLotCapacity)
            notifyObservers("Parking Lot Full");
    }

    //METHOD FOR UNPARK VEHICLE
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle");
        if (parkingMap.containsKey(vehicle.getId())) {
            parkingMap.remove(vehicle.getId());
        }
    }

    @Override
    public void notifyObservers(String message) {
        for (IParkingObserver list : observers) {
            list.update(message);
        }
    }

    //METHOD FOR CHECK VEHICLE PARKED
    public boolean isParked(Vehicle vehicle) {
        if (parkingMap.containsKey(vehicle.getId()))
            return true;
        return false;
    }

    //METHOD FOR CHECK VEHICLE UNPARKED
    public boolean isUnParked(Vehicle vehicle) {
        if (!parkingMap.containsKey(vehicle.getId()))
            return true;
        return false;
    }
}
        