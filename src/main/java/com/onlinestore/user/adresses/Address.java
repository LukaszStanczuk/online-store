package com.onlinestore.user.adresses;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String country;
    String city;
    String street;
    String postalCode;
    String houseNumber;
    String apartmentNumber;

}
