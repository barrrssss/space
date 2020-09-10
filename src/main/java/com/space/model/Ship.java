package com.space.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
public class Ship {
    //2800 year
    private static final Date minProdDate = new Date(26214000904377L);
    //3019 year
    private static final Date maxProdDate = new Date(33124877704396L);
    //3019 year
    private static Date currentDate = new Date(33124877704396L);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String planet;

    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    private Double speed;

    private Integer crewSize;

    private Double rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void countShipRating(){
        System.out.println("countShipRating method has been executed");
        double isUsedFactor = isUsed ? 0.5 : 1;

        System.out.println("Current Date = " + currentDate);
        System.out.println("Production Date = " + prodDate);

        double rating = (80 * speed * isUsedFactor)/(currentDate.getYear() - prodDate.getYear() + 1);

        setRating(round(rating, 2));
    }

    public void initializeIsUsed(){
        if (isUsed == null){
            setIsUsed(false);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void merge(Ship update){
        boolean countShipRating = false;

        if (update.name != null){
            this.name = update.name;
        }
        if (update.planet != null){
            this.planet = update.planet;
        }
        if (update.shipType != null){
            this.shipType = update.shipType;
        }
        if (update.prodDate != null){
            countShipRating = true;
            this.prodDate = update.prodDate;
        }
        if (update.isUsed != null){
            countShipRating = true;
            this.isUsed = update.isUsed;
        }
        if (update.speed != null){
            countShipRating = true;
            this.speed = update.speed;
        }
        if (update.crewSize != null){
            this.crewSize = update.crewSize;
        }

        if (countShipRating){
            countShipRating();
        }

    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }

}
