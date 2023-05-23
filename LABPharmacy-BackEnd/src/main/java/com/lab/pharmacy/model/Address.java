package com.lab.pharmacy.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "address_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String cep;


    @Column
    @NotNull
    private String street;

    @Column
    @NotNull
    private String number;

    @Column
    @NotNull
    private String neighborhood;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private String state;

    @Column
    private String complement;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    public Address(String cep, String street, String number, String neighborhood, String city, String state, String complement, Double latitude, Double longitude) {
        this.cep = cep;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
