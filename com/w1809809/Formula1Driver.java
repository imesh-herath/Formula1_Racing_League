package com.w1809809;

import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numberOfGoldMedals;
    private int numberOfSilverMedals;
    private int numberOfBronzeMedals;
    private int numberOfParticipation;
    private int numberOfFastestLaps;
    private int totalPoints;
    private int numberOfHatTricks;

    public Formula1Driver(int numberOfGoldMedals, int numberOfSilverMedals, int numberOfBronzeMedals, int numberOfParticipation, int numberOfFastestLaps, int totalPoints, int numberOfHatTricks){
        this.numberOfGoldMedals = numberOfGoldMedals;
        this.numberOfSilverMedals = numberOfSilverMedals;
        this.numberOfBronzeMedals = numberOfBronzeMedals;
        this.numberOfParticipation = numberOfParticipation;
        this.numberOfFastestLaps = numberOfFastestLaps;
        this.totalPoints = totalPoints;
        this.numberOfHatTricks = numberOfHatTricks;
    }

    public Formula1Driver(int driverNumber, String nameOfTheDriver, String locationOfTheDriver, String teamNameOfTheDriver, int numberOfGoldMedals, int numberOfSilverMedals, int numberOfBronzeMedals, int numberOfParticipation, int numberOfFastestLaps, int totalPoints, int numberOfHatTricks){
        super(driverNumber, nameOfTheDriver, locationOfTheDriver, teamNameOfTheDriver);
        this.numberOfGoldMedals = numberOfGoldMedals;
        this.numberOfSilverMedals = numberOfSilverMedals;
        this.numberOfBronzeMedals = numberOfBronzeMedals;
        this.numberOfParticipation = numberOfParticipation;
        this.numberOfFastestLaps = numberOfFastestLaps;
        this.totalPoints = totalPoints;
        this.numberOfHatTricks = numberOfHatTricks;
    }

    public int getNumberOfGoldMedals() {
        return numberOfGoldMedals;
    }

    public void setNumberOfGoldMedals(int numberOfGoldMedals) {
        this.numberOfGoldMedals = numberOfGoldMedals;
    }

    public int getNumberOfSilverMedals() {
        return numberOfSilverMedals;
    }

    public void setNumberOfSilverMedals(int numberOfSilverMedals) {
        this.numberOfSilverMedals = numberOfSilverMedals;
    }

    public int getNumberOfBronzeMedals() {
        return numberOfBronzeMedals;
    }

    public void setNumberOfBronzeMedals(int numberOfBronzeMedals) {
        this.numberOfBronzeMedals = numberOfBronzeMedals;
    }

    public int getNumberOfParticipation() {
        return numberOfParticipation;
    }

    public void setNumberOfParticipation(int numberOfParticipation) {
        this.numberOfParticipation = numberOfParticipation;
    }

    public int getNumberOfFastestLaps() {
        return numberOfFastestLaps;
    }

    public void setNumberOfFastestLaps(int numberOfFastestLaps) {
        this.numberOfFastestLaps = numberOfFastestLaps;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getNumberOfHatTricks() {
        return numberOfHatTricks;
    }

    public void setNumberOfHatTricks(int numberOfHatTricks) {
        this.numberOfHatTricks = numberOfHatTricks;
    }


}
