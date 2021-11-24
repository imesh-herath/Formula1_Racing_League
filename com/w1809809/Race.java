package com.w1809809;

import java.io.Serializable;
import java.util.Map;

//assuming_that_one_race_can_only_participate_10_formula1_drivers
public class Race implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date date;
    private Map <Formula1Driver, Integer> driverPlaceMap ;

    public Race(Date date, Map<Formula1Driver, Integer> driverPlaceMap) {
        this.date = date;
        this.driverPlaceMap = driverPlaceMap;
    }

    public Race() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Formula1Driver, Integer> getDriverPlaceMap() {
        return driverPlaceMap;
    }

    public void setDriverPlaceMap(Map<Formula1Driver, Integer> driverPlaceMap) {
        this.driverPlaceMap = driverPlaceMap;
    }
}
