package com.yakut.spring.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_address;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }
}