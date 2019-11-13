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
public class UserDto {

    private Integer id; // nu trebuie validat

    /*@NotEmpty(message = "Please insert your first name")
    @Pattern(regexp = "[A-za-z]+", message = "Letters only!")
    private String firstName;

    @NotEmpty(message = "Please insert your last name")
    @Pattern(regexp = "[A-Za-z]+", message = "Letters only!")
    private String lastName;*/

    @NotEmpty(message = "{error.user.firstName.notEmpty}")
    @Pattern(regexp = "[A-za-z]+", message = "{error.user.firstName.regex}")
    private String firstName;

    @NotEmpty(message = "{error.user.lastName.notEmpty}")
    @Pattern(regexp = "[A-Za-z]+", message = "{error.user.lastName.regex}")
    private String lastName;

    @NotEmpty(message = "{error.user.email.notEmpty}")
    @Email(message = "{error.user.email.regex}")
    private String email;

    @NotEmpty(message = "{error.user.password.notEmpty}")
    @Pattern(regexp = "((.*)[A-Z]+(.*))", message = "{error.user.password.regex}")
    @Size(min = 6, message = "{error.user.password.size}")
    private String password;

    @NotEmpty(message = "{error.user.confirmPassword.notEmpty}")
    @Pattern(regexp = "((.*)[A-Z]+(.*))", message = "{error.user.confirmPassword.regex}")
    @Size(min = 6, message = "{error.user.confirmPassword.size}")
    private String confirmPassword;

}
