package com.clearsolutions.entity;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipCode;
    private String city;
    private String address;
}
