package com.project.management.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;
    //unique

    @Column(name = "passwod")
    private String password;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "taxCode")
    private String taxCode;

    @Column(name = "role")
    private boolean role;
    //true= admin, false=customer

    @OneToMany(mappedBy = "user")
    //@Column(name = "reservations")
    private Set<Reservation> reservation;


    public User() {
    }

    public User(int id, String name, String surname, String email,Date birthday, String taxCode, boolean role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.taxCode = taxCode;
        this.role = role;
    }

    public User(String name, String surname, String email, Date birthday, String taxCode, boolean role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.taxCode = taxCode;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public Set<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
