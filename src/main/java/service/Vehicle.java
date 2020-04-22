package service;

public class Vehicle {
    String id;
    String name;
    int hour;

    public Vehicle(String id, String name, int hour) {
        this.id = id;
        this.name = name;
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
