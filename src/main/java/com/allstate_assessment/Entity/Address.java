package com.allstate_assessment.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "Address_line_1")
    @NotNull(message = "Please provide your AddressLine1")
    private String AddressLine1;

    @Column(name = "Address_line_2")
    private String AddressLine2;


    @Column(name = "country")
    @NotNull(message = "Please provide your country")
    @Pattern(regexp = ("^[a-zA-Z ]*$"), message = "Invalid country name")
    private String country;

    @Column(name = "postcode")
    @NotNull(message = "please provide postcode")
    @Pattern(regexp = ("^[a-zA-Z0-9 ]*$"), message = "Invalid postcode name")
    private String postcode;

}
