package pl.adamsiedlecki.Pickeri.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table
public class FruitDelivery {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Long fruitPickerId;

    @Column

    private List<FruitType> fruitType;

    @Column
    private Long packageAmount;

    @Column
    private LocalDateTime deliveryTime;

    @Column
    private String comment;

    @Column
    private String fruitVariety;

    @Column
    private BigDecimal fruitWeight;

    private GeoLocalization geoLocalization;

    public FruitDelivery() {
    }

    public FruitDelivery(Long fruitPickerId, List<FruitType> fruitType, Long packageAmount, String comment,String fruitVariety, LocalDateTime deliveryTime) {
        this.fruitPickerId = fruitPickerId;
        this.fruitType = fruitType;
        this.packageAmount = packageAmount;
        this.deliveryTime = deliveryTime;
        this.comment = comment;
        this.fruitVariety = fruitVariety;
    }

    public void setFruitPickerId(Long fruitPickerId) {
        this.fruitPickerId = fruitPickerId;
    }

    public BigDecimal getFruitWeight() {
        return fruitWeight;
    }

    public void setFruitWeight(BigDecimal fruitWeight) {
        this.fruitWeight = fruitWeight;
    }

    public GeoLocalization getGeoLocalization() {
        return geoLocalization;
    }

    public void setGeoLocalization(GeoLocalization geoLocalization) {
        this.geoLocalization = geoLocalization;
    }

    public String getFruitVariety() {
        return fruitVariety;
    }

    public void setFruitVariety(String fruitVariety) {
        this.fruitVariety = fruitVariety;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<FruitType> getFruitType() {
        return fruitType;
    }

    public void setFruitType(List<FruitType> fruitType) {
        this.fruitType = fruitType;
    }

    public Long getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Long packageAmount) {
        this.packageAmount = packageAmount;
    }

    public String getDeliveryTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return deliveryTime.format(formatter);
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public long getFruitPickerId() {
        return fruitPickerId;
    }

    public void setFruitPickerId(long fruitPickerId) {
        this.fruitPickerId = fruitPickerId;
    }

}
