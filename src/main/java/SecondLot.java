import java.util.ArrayList;

public class SecondLot {
    int capacity = 2;
    static ArrayList parkingSlot = new ArrayList();

    public void getSlot(Vehicle vehicle) {
        if (parkingSlot.size() == 0) {
            for (int i = 0; i <= 4; i++) {
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
