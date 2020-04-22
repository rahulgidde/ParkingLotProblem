package service;

import exception.DriverType;
import exception.ParkingLotException;
import exception.VehicleType;
import observer.IParkingObserver;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    //CONSTANT
    int PARKING_LOT_CAPACITY = 2;
    int CHARGES_PER_HOUR = 10;

    static int count = 0;
    FirstLot firstLot = new FirstLot();
    SecondLot secondLot = new SecondLot();
    ArrayList list1 = firstLot.getList();
    ArrayList list2 = secondLot.getList();

    private List<IParkingObserver> observers = new ArrayList<>();
    ParkingLotSystem attendant = new ParkingLotSystem();

    //DEFAULT CONSTRUCTOR
    public ParkingLot() {

    }

    //METHOD FOR ADD OBSERVER
    public void addObserver(IParkingObserver observer) {
        this.observers.add(observer);
    }

    //METHOD FOR PARKING VEHICLE
    public void park(Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
        if (count <= PARKING_LOT_CAPACITY) {
            attendant.getParking(vehicle, driverType, vehicleType);
            count++;
            chargeVehicle(vehicle);
        } else if (count >= PARKING_LOT_CAPACITY)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_FULL, "Parking Lot is Full");
        if (count == PARKING_LOT_CAPACITY)
            notifyObservers("Parking Full");
    }

    //METHOD FOR UNPARK VEHICLE
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle");
        attendant.unPark(vehicle);
        notifyObservers("Have Parking Space");
    }

    //METHOD FOR NOTIFY OBSERVERS
    public void notifyObservers(String message) {
        for (IParkingObserver list : observers) {
            list.update(message);
        }
    }

    //METHOD FOR CHECK VEHICLE PARKED
    public boolean isParked(Vehicle vehicle) {
        if (list1.contains(vehicle))
            return true;
        return false;
    }

    //METHOD FOR CHECK VEHICLE UNPARKED
    public boolean isUnParked(Vehicle vehicle) {
        if (list1.contains(vehicle))
            return false;
        return true;
    }

    //METHOD FOR FIND VEHICLE
    public boolean getVehicle(Vehicle vehicle) {
        if (list1.contains(vehicle))
            return true;
        else if (list2.contains(vehicle))
            return true;
        return false;
    }

    //METHOD FOR CHARGE PARKING VEHICLES
    public int chargeVehicle(Vehicle vehicle) {
        int totalCharges = vehicle.getHour() * CHARGES_PER_HOUR;
        return totalCharges;
    }
}
        