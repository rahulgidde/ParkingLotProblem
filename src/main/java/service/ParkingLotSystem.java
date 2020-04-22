package service;

import exception.DriverType;

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

    public void getParking(Vehicle vehicle, DriverType type) {
        switch (type) {
            case NORMAL_DRIVER:
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
