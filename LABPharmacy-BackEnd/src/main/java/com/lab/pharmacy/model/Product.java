package com.lab.pharmacy.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "product_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String productName;

    @NotNull
    @Column
    private String laboratoryName;

    @NotNull
    @Column
    private String productImage;

    @NotNull
    @Column
    private String dosage;

    @Column
    private String productDescription;

    @NotNull
    @Column
    private Float price;

    @NotNull
    @Column
    private String productType;

    @NotNull
    @Column
    private Long stockQuantity;

    @Column(name = "productRegisterDate")
    private Date productRegisterDate;

    @NotNull
    @ManyToOne( fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user")
    private User idUser;


    @Column
    @NotNull
    private boolean isActive;


    public Product(String productName, String laboratoryName, String productImage, String dosage, String productDescription, Float price, String productType, Long stockQuantity, Date productRegisterDate, User idUser, boolean isActive){
        this.productName = productName;
        this.laboratoryName = laboratoryName;
        this.productImage = productImage;
        this.dosage = dosage;
        this.productDescription = productDescription;
        this.price = price;
        this.productType = productType;
        this.stockQuantity = stockQuantity;
        this.productRegisterDate = productRegisterDate;
        this.idUser = idUser;
        this.isActive = isActive;
    }
}
