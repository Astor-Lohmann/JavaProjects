package com.lab.pharmacy.dto;

import com.lab.pharmacy.utils.PaymentMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {

    private Long sellerId;
    private Long buyerId;
    private Long productId;
    private Float price;
    private Long quantity;

    private Date saleDate;
    private Float totalPrice;
    private PaymentMethods paymentMethod;


}
