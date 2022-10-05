package com.cg.model.dto;

import com.cg.model.Role;
import com.cg.model.User;
import lombok.*;
import lombok.experimental.Accessors;
 import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
 import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Validator {

    private Long id;

    @NotBlank(message = "The Name is not Empty")
    private String fullName;


    @NotBlank(message = "The email is not Empty")
    @Email(message = "The email address is invalid ! Ex: sinhcute@co.cc")
    @Size(max = 50, message = "The length of email must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "The password is not Empty")
     @Size(max = 30,message = "Password must greater than 8 characters")
    private String password;

    @NotBlank(message = "The phone is not Empty")
    @Pattern(regexp = "^[0][0-9\\\\-\\\\+]{9}$" ,message = "Phone is 10 number and start equal number 0")
    private String phone;

    @NotBlank(message = "The address is not Empty")
    private String address;
    @Valid
    private RoleDTO role;

    private boolean deleted;

    public UserDTO(Long id, String fullName, String username, String password, String phone, String address, Role role, boolean deleted) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role.toRoleDTO();
        this.deleted = deleted;
    }

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }


    public User toUser() {
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setUsername(username)
                .setPassword(password)
                .setPhone(phone)
                .setAddress(address)
                .setRole(role.toRole());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        String usenameCheck = userDTO.getUsername();
        String fullnameCheck = userDTO.getFullName();
        String passwordCheck = userDTO.getPassword();
        String phoneCheck = userDTO.getPhone();
        String addressCheck = userDTO.getAddress();

        if (phoneCheck.trim().isEmpty()){
            errors.rejectValue("username","username.isEmpty", "vui long nhap email");
        }
    }
}
