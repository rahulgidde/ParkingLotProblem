import java.util.Iterator;
import java.util.LinkedHashMap;

public class Owner implements IParkingObserver {
    private String status;
    int lotNumber = 1;
    String key;

    @Override
    public void update(String message) {
        this.status = message;
    }

    public String getStatus() {
        return status;
    }


    public String parkingAttendant(LinkedHashMap<String, Object> parkingMap) {
        if (parkingMap.size() == 0)
            this.key = String.valueOf(lotNumber);
        Iterator<String> itr = parkingMap.keySet().iterator();
        while (itr.hasNext()) {
            key = itr.next();
            if (parkingMap.get(key) != key)
                key = String.valueOf(lotNumber);
        }
        lotNumber++;
        return key;
    }

    public void unParkingAttendant(String key) {
        this.key = key;
    }
}
