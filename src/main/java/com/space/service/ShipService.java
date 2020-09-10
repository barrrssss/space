package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    ShipRepo shipRepo;

//
//    public Ship saveShip(Ship ship){
//        return shipRepo.findAll()
//    }
//
//    public Ship deleteShip(Ship ship){
//
//    }

    public Optional<Ship> getShipById(Long id){
        return shipRepo.findById(id);
    }


    public List<Ship> getShipsByCriteria(String name, String planet, ShipType shipType,
                                            Long after, Long before, Boolean isUsed,
                                            Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                            Integer maxCrewSize, Double minRating, Double maxRating,
                                            Pageable pageable) {
        return shipRepo.findAllByCriteria(name, planet, shipType, newDateOrElseNull(after), newDateOrElseNull(before), isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, pageable);
    }

    public Long getShipsCountByCriteria(String name, String planet, ShipType shipType,
                                         Long after, Long before, Boolean isUsed,
                                         Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                         Integer maxCrewSize, Double minRating, Double maxRating) {
        return shipRepo.countByCriteria(name, planet, shipType, newDateOrElseNull(after), newDateOrElseNull(before), isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating);
    }

    public Ship saveShip(Ship ship) {
        return shipRepo.save(ship);
    }

    public void deleteShipById(Long id){
        shipRepo.deleteById(id);
    }

    private Date newDateOrElseNull(Long time){
        if (time == null){
            return null;
        }

        return new Date(time);
    }
}
