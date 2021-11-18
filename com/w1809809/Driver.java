package com.w1809809;

import java.io.Serializable;

public abstract class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    private int driverNumber;
    private String nameOfTheDriver;
    private String locationOfTheDriver;
    private String teamNameOfTheDriver;

    public Driver(){
    }

    public Driver(int driverNumber, String nameOfTheDriver, String locationOfTheDriver, String teamNameOfTheDriver){
        this.driverNumber = driverNumber;
        this.nameOfTheDriver = nameOfTheDriver;
        this.locationOfTheDriver = locationOfTheDriver;
        this.teamNameOfTheDriver = teamNameOfTheDriver;
    }

    public static long getSerialVersionUID(){
        return serialVersionUID;
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getNameOfTheDriver() {
        return nameOfTheDriver;
    }

    public void setNameOfTheDriver(String nameOfTheDriver) {
        this.nameOfTheDriver = nameOfTheDriver;
    }

    public String getLocationOfTheDriver() {
        return locationOfTheDriver;
    }

    public void setLocationOfTheDriver(String locationOfTheDriver) {
        this.locationOfTheDriver = locationOfTheDriver;
    }

    public String getTeamNameOfTheDriver() {
        return teamNameOfTheDriver;
    }

    public void setTeamNameOfTheDriver(String teamNameOfTheDriver) {
        this.teamNameOfTheDriver = teamNameOfTheDriver;
    }
}
