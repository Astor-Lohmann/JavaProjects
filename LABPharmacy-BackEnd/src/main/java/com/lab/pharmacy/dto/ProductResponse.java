package com.lab.pharmacy.dto;

import com.lab.pharmacy.model.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String productName;
    private String laboratoryName;
    private String productImage;
    private String dosage;
    private String productDescription;
    private Float price;
    private String productType;
    private Long stockQuantity;
    private Date productRegisterDate;
    private String user;
    private Long userId;

    public ProductResponse(Long id, String productName, String laboratoryName, String productImage, String dosage, String productDescription, Float price, String productType, Long stockQuantity, Date productRegisterDate, String user) {
        this.id = id;
        this.productName = productName;
        this.laboratoryName = laboratoryName;
        this.productImage = productImage;
        this.dosage = dosage;
        this.productDescription = productDescription;
        this.price = price;
        this.productType = productType;
        this.stockQuantity = stockQuantity;
        this.productRegisterDate = productRegisterDate;
        this.user = user;
    }

    public ProductResponse(Long id, String productName, String laboratoryName, String productImage, String dosage, String productDescription, Float price, String productType, Long stockQuantity, Date productRegisterDate, Long userId) {
        this.id = id;
        this.productName = productName;
        this.laboratoryName = laboratoryName;
        this.productImage = productImage;
        this.dosage = dosage;
        this.productDescription = productDescription;
        this.price = price;
        this.productType = productType;
        this.stockQuantity = stockQuantity;
        this.productRegisterDate = productRegisterDate;
        this.userId = userId;
    }
}