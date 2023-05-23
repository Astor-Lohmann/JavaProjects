package com.lab.pharmacy.dto;

import com.lab.pharmacy.model.Address;
import com.lab.pharmacy.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Setter
@Getter
public class UserResponse {

    private Long id;

    private String fullName;

    private String cpf;

    private Date birth;

    private String email;

    private String contactNumber;
    private String password;

    private AccountType type;

    private Address idAddress;

    public UserResponse(Long id, String fullName, String cpf, Date birth, String email, String contactNumber, AccountType type, Address idAddress) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.birth = birth;
        this.email = email;
        this.contactNumber = contactNumber;
        this.type = type;
        this.idAddress = idAddress;
    }
}
