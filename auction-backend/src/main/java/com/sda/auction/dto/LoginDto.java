package com.sda.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data // creaza hidden getter and hidden setter
@EqualsAndHashCode
/*@AllArgsConstructor // creaza toate variantele de constructor
@NoArgsConstructor // creaza constructor default*/
public class LoginDto {

    private String jwd;

    @NotEmpty(message = "{error.user.email.notEmpty}")
    @Email(message = "{error.user.email.regex}")
    private String email;

    @NotEmpty(message = "{error.user.password.notEmpty}")
    @Pattern(regexp = "((.*)[A-Z]+(.*))", message = "{error.user.password.regex}")
    @Size(min = 6, message = "{error.user.password.size}")
    private String password;


}