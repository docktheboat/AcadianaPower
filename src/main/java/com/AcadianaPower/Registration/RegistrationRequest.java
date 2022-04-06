package com.AcadianaPower.Registration;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;
}