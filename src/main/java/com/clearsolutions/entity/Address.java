package com.clearsolutions.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column(name = "zip_code")
    private String zipCode;
    //    @NotNull
    //    @NotBlank
    //    @Column(name = "city")
    private String city;
    //    @Column(name = "address")
    private String concreteAddress;
}
