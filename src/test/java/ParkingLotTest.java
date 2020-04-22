import exception.ParkingLotException;
import observer.AirportSecurity;
import observer.Owner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ParkingLot;
import service.Vehicle;

import static exception.DriverType.HANDICAP_DRIVER;
import static exception.DriverType.NORMAL_DRIVER;
import static exception.VehicleType.LARGE_VEHICLE;
import static exception.VehicleType.SMALL_VEHICLE;

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
        Vehicle vehicle = new Vehicle("1", "Car", 4);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        boolean isParked = parkingLot.isParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            Vehicle vehicle = new Vehicle("1", "Car", 3);
            parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
            Vehicle vehicle1 = new Vehicle("2", "Car", 2);
            boolean isParked = parkingLot.isParked(vehicle1);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_FULL, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("1", "Car", 1);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        parkingLot.unPark(vehicle);
        boolean isUnParked = parkingLot.isUnParked(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnFalse() {
        try {
            Vehicle vehicle = new Vehicle("1", "Car", 2);
            parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
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
        Vehicle vehicle = new Vehicle("1", "Car", 4);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        Vehicle vehicle1 = new Vehicle("2", "Car", 3);
        parkingLot.park(vehicle1, NORMAL_DRIVER, SMALL_VEHICLE);
        Assert.assertEquals("Parking Full", owner.getStatus());
    }

    @Test
    public void givenVehicle_WhenOwnerAndAadSecurity_ShouldReturnIsFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(airportSecurity);
        Vehicle vehicle = new Vehicle("1", "Car", 2);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        Vehicle vehicle1 = new Vehicle("2", "car", 1);
        parkingLot.park(vehicle1, NORMAL_DRIVER, SMALL_VEHICLE);
        Assert.assertEquals("Parking Full", owner.getStatus());
        Assert.assertEquals("Parking Full", airportSecurity.getStatus());
    }

    @Test
    public void givenVehicle_WhenParkingHaveSpace_ShouldReturnOwnerAndAirportSecurity() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(airportSecurity);
        Vehicle vehicle = new Vehicle("1", "Car", 2);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        Vehicle vehicle1 = new Vehicle("2", "car", 4);
        parkingLot.park(vehicle1, HANDICAP_DRIVER, SMALL_VEHICLE);
        parkingLot.unPark(vehicle1);
        Assert.assertEquals("Have Parking Space", owner.getStatus());
        Assert.assertEquals("Have Parking Space", airportSecurity.getStatus());
    }

    @Test
    public void givenVehicle_WhenFindVehicle_ShouldReturnKey() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car", 4);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        Vehicle vehicle1 = new Vehicle("3", "Car", 3);
        parkingLot.park(vehicle1, NORMAL_DRIVER, SMALL_VEHICLE);
        boolean result = parkingLot.getVehicle(vehicle1);
        Assert.assertEquals(true, result);
    }

    @Test
    public void givenVehicle_WhenPark_ShouldReturnCharges() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car", 4);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        Vehicle vehicle1 = new Vehicle("3", "Car", 3);
        parkingLot.park(vehicle1, NORMAL_DRIVER, SMALL_VEHICLE);
        int parkingCharges = parkingLot.chargeVehicle(vehicle);
        Assert.assertEquals(40, parkingCharges);
    }

    @Test
    public void givenDriverType_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car", 4);
        parkingLot.park(vehicle, NORMAL_DRIVER, SMALL_VEHICLE);
        Vehicle vehicle1 = new Vehicle("3", "Car", 3);
        parkingLot.park(vehicle1, HANDICAP_DRIVER, SMALL_VEHICLE);
        boolean result = parkingLot.getVehicle(vehicle1);
        Assert.assertEquals(true, result);
    }

    @Test
    public void givenDriverTypeAndVehicleType_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car", 4);
        parkingLot.park(vehicle, NORMAL_DRIVER, LARGE_VEHICLE);
        Vehicle vehicle1 = new Vehicle("3", "Car", 3);
        parkingLot.park(vehicle1, HANDICAP_DRIVER, SMALL_VEHICLE);
        boolean result = parkingLot.getVehicle(vehicle1);
        Assert.assertEquals(true, result);
    }
}
