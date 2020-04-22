package service;

import java.util.ArrayList;

public class FirstLot {
    int capacity = 2;
    static ArrayList parkingSlot = new ArrayList();

    public void getSlot(Vehicle vehicle) {
        if (parkingSlot.size() == 0) {
            for (int slot = 1; slot <= 4; slot++) {
                parkingSlot.add(null);
            }
        }

        for (int slot = 1; slot <= capacity; slot++) {
            if (parkingSlot.get(slot) == null) {
                parkingSlot.set(slot, vehicle);
                break;
            }
        }
    }

    public void removeSlot(Vehicle vehicle) {
        for (int slot = 1; slot <= capacity; slot++) {
            if (parkingSlot.get(slot) == vehicle) {
                parkingSlot.set(slot, null);
                break;
            }
        }
    }

    public ArrayList getList() {
        return parkingSlot;
    }
}
