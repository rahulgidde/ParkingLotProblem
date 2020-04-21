import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkingLot implements IParkingLotSystem {

    //CONSTANT
    int PARKING_LOT_CAPACITY = 2;
    int CHARGES_PER_HOUR = 10;

    //VARIABLE
    String key;

    private LinkedHashMap<String, Object> parkingMap = new LinkedHashMap<String, Object>();
    private List<IParkingObserver> observers = new ArrayList<>();
    Attendant attendant = new Attendant();


    //DEFAULT CONSTRUCTOR
    public ParkingLot() {
    }

    //METHOD FOR ADD OBSERVER
    public void addObserver(IParkingObserver observer) {
        this.observers.add(observer);
    }

    //METHOD FOR PARKING VEHICLE
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (this.parkingMap.size() <= PARKING_LOT_CAPACITY) {
            key = attendant.parkVehicle(parkingMap);
            parkingMap.put(key, vehicle);
            chargeVehicle(vehicle);
        } else if (parkingMap.size() == PARKING_LOT_CAPACITY)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_FULL, "Parking Lot is Full");
        if (parkingMap.size() == PARKING_LOT_CAPACITY)
            notifyObservers("Parking Full");
    }

    //METHOD FOR UNPARK VEHICLE
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle");
        if (parkingMap.containsKey(key)) {
            attendant.UnParkVehicle(key);
            parkingMap.remove(key);
            notifyObservers("Have Parking Space");
        }
    }

    //METHOD FOR NOTIFY OBSERVERS
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

    //METHOD FOR FIND VEHICLE
    public String getVehicle(Vehicle vehicle) {
        Iterator<String> itr = parkingMap.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            if (parkingMap.get(key) == vehicle)
                return key;
        }
        return null;
    }

    //METHOD FOR CHARGE PARKING VEHICLES
    public int chargeVehicle(Vehicle vehicle) {
        int totalCharges = vehicle.getHour() * CHARGES_PER_HOUR;
        return totalCharges;
    }
}
        