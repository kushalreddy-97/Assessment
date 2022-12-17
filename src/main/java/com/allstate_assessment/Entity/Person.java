package com.allstate_assessment.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "Please provide your last name")
    private String lastName;

    @Column(name = "phone_number")
    @Pattern(regexp = ("^\\d+$"), message = "Invalid phone Number")
    @Size(min = 11, max = 13)
    private String phoneNumber;

    @Column(name = "mobile_number")
    @Pattern(regexp = ("^\\d+$"), message = "Invalid mobile Number")
    private String mobileNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id", referencedColumnName = "person_id")
    @Valid
    private List<Address> address = new ArrayList<>();

}
