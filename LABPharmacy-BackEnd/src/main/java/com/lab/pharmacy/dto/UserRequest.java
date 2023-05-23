package com.lab.pharmacy.dto;

import com.lab.pharmacy.model.Address;
import com.lab.pharmacy.utils.AccountType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@Getter
public class UserRequest {

    private String fullName;
    private String cpf;
    private Date birth;
    private String email;
    private String contactNumber;
    private AccountType type;
    private String password;
    private String cep;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
    private Double latitude;
    private Double longitude;

}


