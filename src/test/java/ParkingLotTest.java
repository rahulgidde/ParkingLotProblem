import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLot parkingLot = null;
    Object vehicle = null;

    @Before
    public void set() {
        parkingLot = new ParkingLot();
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingLot.park(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLot.park(vehicle);
        boolean isParked = parkingLot.park(vehicle);
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean isUnParked = parkingLot.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
