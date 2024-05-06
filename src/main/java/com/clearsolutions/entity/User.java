package com.clearsolutions.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotNull
//    @NotBlank
//    @Column(name = "email", unique = true)
    private String email;
//    @NotNull
//    @NotBlank
//    @Column(name = "first_name")
    private String firstName;
//    @NotNull
//    @NotBlank
//    @Column(name = "last_name")
    private String lastName;
//    @NotNull
//    @NotBlank
//    @Column(name = "birth_date")
    private LocalDate birthDate;
//    @ManyToOne
//    @JoinColumn(name = "address_id")
    private Address address;
//    @Column(name = "phone_number")
    private String phoneNumber;
}
