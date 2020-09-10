package com.space.repository;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
//import org.springframework.data.repository.query.Param;
//import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

public interface ShipRepo extends JpaRepository<Ship, Long> {
//    default List<Ship> findAllByCriteria(@Param("name") @Nullable String name, String planet, ShipType shipType, Date after, Date before, Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating, Pageable pageable){
//        return findAllByCriteries(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating, pageable);
//    }

    @Query(value = "SELECT s FROM Ship s WHERE (:name IS NULL OR s.name LIKE %:name%) AND (:planet IS NULL OR s.planet LIKE %:planet%) AND (:shipType IS NULL OR s.shipType = :shipType) AND (:after IS NULL OR s.prodDate >= :after) AND (:before IS NULL OR s.prodDate <= :before) AND (:isUsed IS NULL OR s.isUsed = :isUsed) AND (:minSpeed IS NULL OR s.speed >= :minSpeed) AND (:maxSpeed IS NULL OR s.speed <= :maxSpeed) AND (:minCrewSize IS NULL OR s.crewSize >= :minCrewSize) AND (:maxCrewSize IS NULL OR s.crewSize <= :maxCrewSize) AND (:minRating IS NULL OR s.rating >= :minRating) AND (:maxRating IS NULL OR s.rating <= :maxRating)")
    List<Ship> findAllByCriteria(@Param("name") String name, @Param("planet") String planet, @Param("shipType") ShipType shipType, @Param("after") Date after, @Param("before") Date before, @Param("isUsed") Boolean isUsed, @Param("minSpeed") Double minSpeed, @Param("maxSpeed") Double maxSpeed, @Param("minCrewSize") Integer minCrewSize, @Param("maxCrewSize") Integer maxCrewSize, @Param("minRating") Double minRating, @Param("maxRating") Double maxRating, Pageable pageable);


    @Query(value = "SELECT COUNT(s) FROM Ship s WHERE (:name IS NULL OR s.name LIKE %:name%) AND (:planet IS NULL OR s.planet LIKE %:planet%) AND (:shipType IS NULL OR s.shipType = :shipType) AND (:after IS NULL OR s.prodDate >= :after) AND (:before IS NULL OR s.prodDate <= :before) AND (:isUsed IS NULL OR s.isUsed = :isUsed) AND (:minSpeed IS NULL OR s.speed >= :minSpeed) AND (:maxSpeed IS NULL OR s.speed <= :maxSpeed) AND (:minCrewSize IS NULL OR s.crewSize >= :minCrewSize) AND (:maxCrewSize IS NULL OR s.crewSize <= :maxCrewSize) AND (:minRating IS NULL OR s.rating >= :minRating) AND (:maxRating IS NULL OR s.rating <= :maxRating)")
    Long countByCriteria(@Param("name") String name, @Param("planet") String planet, @Param("shipType") ShipType shipType, @Param("after") Date after, @Param("before") Date before, @Param("isUsed") Boolean isUsed, @Param("minSpeed") Double minSpeed, @Param("maxSpeed") Double maxSpeed, @Param("minCrewSize") Integer minCrewSize, @Param("maxCrewSize") Integer maxCrewSize, @Param("minRating") Double minRating, @Param("maxRating") Double maxRating);

}
