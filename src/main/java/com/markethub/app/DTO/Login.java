package com.markethub.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {
    @NotEmpty
    @Email
    private String username;
    @NotEmpty
    @Size(min = 1)
    private String password;
    private boolean rememberMe;
}
