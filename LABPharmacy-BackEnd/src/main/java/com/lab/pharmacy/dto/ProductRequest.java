package com.lab.pharmacy.dto;

import com.lab.pharmacy.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String laboratoryName;
    private String productImage;
    private String dosage;
    private String productDescription;
    private Float price;
    private String productType;
    private Long stockQuantity;
    private Date productRegisterDate;
    private Long idUser;

}