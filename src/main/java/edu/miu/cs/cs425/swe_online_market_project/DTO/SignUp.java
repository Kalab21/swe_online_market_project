package edu.miu.cs.cs425.swe_online_market_project.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUp {
    @NotEmpty
    @Size(min = 2)
    private String firstName;
    @NotEmpty
    @Size(min = 2)
    private String lastName;
    @NotEmpty
    @Size(min = 3)
    private String userName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotEmpty
    private String role;
}

