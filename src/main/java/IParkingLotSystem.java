public interface IParkingLotSystem {
    public void park(Vehicle vehicle) throws ParkingLotException;

    public void unPark(Vehicle vehicle) throws ParkingLotException;

    public void notifyObservers(String message);
}
