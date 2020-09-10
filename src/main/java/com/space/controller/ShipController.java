package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.service.ShipValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/ships")
public class ShipController {
    @Autowired
    ShipService shipService;

    @GetMapping
    public List<Ship> getShips(@RequestParam(required = false) String name, @RequestParam(required = false) String planet,
                                  @RequestParam(required = false) ShipType shipType, @RequestParam(required = false) Long after,
                                  @RequestParam(required = false) Long before, @RequestParam(required = false) Boolean isUsed,
                                  @RequestParam(required = false) Double minSpeed, @RequestParam(required = false) Double maxSpeed,
                                  @RequestParam(required = false) Integer minCrewSize, @RequestParam(required = false) Integer maxCrewSize,
                                  @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating,
                                  @RequestParam(required = false) ShipOrder order, @RequestParam(defaultValue = "0") Integer pageNumber,
                                  @RequestParam(defaultValue = "3") Integer pageSize){
        Sort sort;
        if (order == null){
            sort = Sort.unsorted();
        } else {
            sort = Sort.by(order.getFieldName());
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return shipService.getShipsByCriteria(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, pageable);
    }

    @GetMapping("{id}")
    public Ship getShipById(@PathVariable Long id) {
        requireId(id);

        Optional<Ship> optionalShip = shipService.getShipById(id);

        return optionalShip.orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("{id}")
    public Ship updateShipById(@PathVariable Long id, @RequestBody Ship update) {
        System.out.println(update);
        ShipValidator shipValidator = new ShipValidator();
        try {
            shipValidator.validate(update);
        } catch (IllegalArgumentException e){
            throw new BadRequestIllegalArgumentException();
        }

        Ship currentShip = getShipById(id);
        currentShip.merge(update);

        Ship returnetShip =  shipService.saveShip(currentShip);

        System.out.println(returnetShip);

        return returnetShip;
    }

    @DeleteMapping("{id}")
    public void deleteShipByID(@PathVariable Long id){
        getShipById(id);

        shipService.deleteShipById(id);
    }

    @PostMapping
    public Ship createShip(@RequestBody Ship ship){
        ShipValidator shipValidator = new ShipValidator();

        try {
            shipValidator.setNullSensitivity(true);
            shipValidator.validate(ship);
        } catch (NullPointerException | IllegalArgumentException e){
            throw new BadRequestIllegalArgumentException();
        }

        ship.initializeIsUsed();
        ship.countShipRating();

        Ship returnedShip = shipService.saveShip(ship);

        System.out.println(returnedShip);

        return returnedShip;
    }

    @GetMapping("/count")
    public Long countShips(@RequestParam(required = false) String name, @RequestParam(required = false) String planet,
                           @RequestParam(required = false) ShipType shipType, @RequestParam(required = false) Long after,
                           @RequestParam(required = false) Long before, @RequestParam(required = false) Boolean isUsed,
                           @RequestParam(required = false) Double minSpeed, @RequestParam(required = false) Double maxSpeed,
                           @RequestParam(required = false) Integer minCrewSize, @RequestParam(required = false) Integer maxCrewSize,
                           @RequestParam(required = false) Double minRating, @RequestParam(required = false) Double maxRating){
        return shipService.getShipsCountByCriteria(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating);
    }

    private void requireId(Long id){
        if (id <= 0){
            throw new BadRequestIllegalArgumentException();
        }
    }

}
