package com.markethub.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "addresses")
public class Address implements Serializable{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @NotBlank
        private String street;

        @NotBlank
        private String city;

        @NotBlank
        private String state;

        @NotNull
        @Size(min=5, max=5, message="zipcode should have size of five number.")
        private String zipCode;

        private AddressType addressType;

        @NotNull
        @Size(min=10, max=10, message="phone number have size of 10.")
        private long phoneNumber;

        @ManyToOne
        @JoinColumn(name = "user_Id")
        private User user;
    }

