import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLot parkingLot = null;
    Owner owner = null;
    AirportSecurity airportSecurity = null;

    @Before
    public void set() {
        parkingLot = new ParkingLot();
        owner = new Owner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        boolean isParked = parkingLot.isParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            Vehicle vehicle = new Vehicle("1", "Car");
            parkingLot.park(vehicle);
            Vehicle vehicle1 = new Vehicle("2", "Car");
            boolean isParked = parkingLot.isParked(vehicle1);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_FULL, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isUnParked = parkingLot.isUnParked(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnFalse() {
        try {
            Vehicle vehicle = new Vehicle("1", "Car");
            parkingLot.park(vehicle);
            parkingLot.unPark(null);
            boolean isUnParked = parkingLot.isUnParked(vehicle);
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenOwner_ShouldReturnIsFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        Vehicle vehicle1 = new Vehicle("2", "Car");
        parkingLot.park(vehicle1);
        Assert.assertEquals("Parking Full", owner.getStatus());
    }

    @Test
    public void givenVehicle_WhenOwnerAndAadSecurity_ShouldReturnIsFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(airportSecurity);
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        Vehicle vehicle1 = new Vehicle("2", "car");
        parkingLot.park(vehicle1);
        Assert.assertEquals("Parking Full", owner.getStatus());
        Assert.assertEquals("Parking Full", airportSecurity.getStatus());
    }

    @Test
    public void givenVehicle_WhenParkingHaveSpace_ShouldReturnOwnerAndAirportSecurity() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(airportSecurity);
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        Vehicle vehicle1 = new Vehicle("2", "car");
        parkingLot.park(vehicle1);
        parkingLot.unPark(vehicle1);
        Assert.assertEquals("Have Parking Space", owner.getStatus());
        Assert.assertEquals("Have Parking Space", airportSecurity.getStatus());
    }

    @Test
    public void givenVehicle_WhenFindVehicle_ShouldReturnKey() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        Vehicle vehicle1 = new Vehicle("3", "Car");
        parkingLot.park(vehicle1);
        String key = parkingLot.getVehicle(vehicle1);
        Assert.assertEquals("2", key);
    }
}
