import java.util.LinkedHashMap;

public class Attendant {
    Owner owner = new Owner();

    public String parkVehicle(LinkedHashMap<String, Object> parkingMap) {
        String key = owner.parkingAttendant(parkingMap);
        return key;
    }

    public void UnParkVehicle(String key) {
        owner.unParkingAttendant(key);
    }
}
