package com.space.service;

import com.space.model.Ship;

import java.util.Date;
import java.util.Objects;

public class ShipValidator {
    //2800 year
    private static final Date minProdDate = new Date(26214000904377L);
    //3019 year
    private static final Date maxProdDate = new Date(33124877704396L);
    
    private boolean nullSensitivity = false;

    public void validate(Ship ship){
        validateName(requireNotNull(ship.getName()));
        validatePlanet(requireNotNull(ship.getPlanet()));
        requireNotNull(ship.getShipType());
        validateProdDate(requireNotNull(ship.getProdDate()));
        validateSpeed(requireNotNull(ship.getSpeed()));
        validateCrewSize(requireNotNull(ship.getCrewSize()));
    }

    private void validateName(String name){
        if (name == null) return;

        if (name.isEmpty() || name.length() > 50){
            throw new IllegalArgumentException();
        }
    }

    private void validatePlanet(String planet){
        if (planet == null) return;

        if (planet.isEmpty() || planet.length() > 50){
            throw new IllegalArgumentException();
        }
    }

    private void validateProdDate(Date prodDate){
        if (prodDate == null) return;

        if (prodDate.before(minProdDate) || prodDate.after(maxProdDate)){
            throw new IllegalArgumentException();
        }
    }


    private void validateSpeed(Double speed){
        if (speed == null) return;

        if (speed > 0.99 || speed < 0.01){
            throw new IllegalArgumentException();
        }
    }

    private void validateCrewSize(Integer crewSize){
        if (crewSize == null) return;

        if (crewSize < 1 || crewSize > 9999){
            throw new IllegalArgumentException();
        }
    }

    public void setNullSensitivity(boolean nullSensitivity) {
        this.nullSensitivity = nullSensitivity;
    }

    private <T> T requireNotNull(T t){
        if (nullSensitivity){
            Objects.requireNonNull(t);
        }

        return t;
    }
}
