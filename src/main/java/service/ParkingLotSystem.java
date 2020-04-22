package service;

import exception.DriverType;
import exception.ParkingLotException;
import exception.VehicleType;

import java.util.ArrayList;

public class ParkingLotSystem {
    FirstLot firstLot = new FirstLot();
    SecondLot secondLot = new SecondLot();
    ArrayList list1 = firstLot.getList();
    ArrayList list2 = secondLot.getList();
    static int flag = 0;

    public void park(Vehicle vehicle) {
        switch (flag) {
            case 0:
                firstLot.getSlot(vehicle);
                flag++;
                break;
            case 1:
                secondLot.getSlot(vehicle);
                flag--;
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }

    public void unPark(Vehicle vehicle) {
        if (list1.contains(vehicle))
            firstLot.removeSlot(vehicle);
        if (list2.contains(vehicle))
            secondLot.removeSlot(vehicle);
    }

    public void getHandicapParking(Vehicle vehicle) {
        for (int slot = 1; slot <= list1.size(); slot++) {
            if (list2.size() == 0 || list1.get(slot) == null)
                firstLot.getSlot(vehicle);
            else if (list2.size() == 0 || list2.get(slot) == null)
                secondLot.getSlot(vehicle);
        }
    }

    public void largeCarParking(Vehicle vehicle) throws ParkingLotException {
        for(int slot=1; slot<=list1.size(); slot++) {
            if (list1.size() == 0 || list1.get(slot) == null && list1.get(slot+1) == null)
                firstLot.getSlot(vehicle);
            else if (list2.size() == 0 || list2.get(slot) == null && list2.get(slot+1) == null)
                secondLot.getSlot(vehicle);
            else
                throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_FULL,"Parking Full");
        }
    }

    public void getParking(Vehicle vehicle, DriverType driverType,VehicleType vehicleType) throws ParkingLotException {
        switch (driverType) {
            case NORMAL_DRIVER:
                if( VehicleType.SMALL_VEHICLE == vehicleType)
                    park(vehicle);
                else if(VehicleType.LARGE_VEHICLE == vehicleType && list1.size() != 0 || list2.size() != 0)
                    largeCarParking(vehicle);
                else
                    park(vehicle);
                break;
            case HANDICAP_DRIVER:
                getHandicapParking(vehicle);
                break;
            default:
                System.out.println("Invalid Type");
        }
    }
}
