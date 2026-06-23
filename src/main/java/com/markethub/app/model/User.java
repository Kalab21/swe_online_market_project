package com.markethub.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String userName;
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "roleId")})
    private List<Role> roles;

    private boolean approvedSeller;

    @OneToMany(mappedBy = "user")
    private List<Address> address;

    @OneToMany(mappedBy = "user")
    List<Payment> payment;

    @OneToOne
    @JoinColumn(name = "shopping_cart_Id")
    private ShoppingCart shoppingCart;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
