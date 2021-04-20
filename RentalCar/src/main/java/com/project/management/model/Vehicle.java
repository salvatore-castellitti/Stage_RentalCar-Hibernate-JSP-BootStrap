package com.project.management.model;

import javax.persistence.*;

@Entity
@Table(name ="vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "houseProducer")
    private String houseProducer;

    @Column(name = "model")
    private String model;

    @Column(name = "registrationYear")
    private String registrationYear;

    @Column(name = "licensePlate")
    private String licensePlate;

    @OneToOne(mappedBy = "vehicle")
    private Reservation reservation;

    public Vehicle() {
    }

    public Vehicle(int id, String type, String houseProducer, String model, String registrationYear, String licensePlate) {
        this.id = id;
        this.type = type;
        this.houseProducer = houseProducer;
        this.model = model;
        this.registrationYear = registrationYear;
        this.licensePlate = licensePlate;
    }

    public Vehicle(String type, String houseProducer, String model, String registrationYear, String licensePlate) {
        this.type = type;
        this.houseProducer = houseProducer;
        this.model = model;
        this.registrationYear = registrationYear;
        this.licensePlate = licensePlate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHouseProducer() {
        return houseProducer;
    }

    public void setHouseProducer(String houseProducer) {
        this.houseProducer = houseProducer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(String registrationYear) {
        this.registrationYear = registrationYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
