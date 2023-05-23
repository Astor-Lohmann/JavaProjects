package com.lab.pharmacy.model;


import com.lab.pharmacy.utils.AccountType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String fullName;

    @Column
    @NotNull
    private String cpf;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private Date birth;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    @NotNull
    private String contactNumber;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType type;


    @Column
    @NotNull
    private String password;

    @NotNull
    @OneToOne( fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_address")
    private Address idAddress;

    @Column
    @NotNull
    private boolean isActive;




    public User(String fullName, String cpf, Date date, String email, String contactNumber, AccountType type, String password, Address idAddress, boolean isActive) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.birth = date;
        this.email = email;
        this.contactNumber = contactNumber;
        this.type = type;
        this.password = password;
        this.idAddress = idAddress;
        this.isActive = isActive;
    }
}
