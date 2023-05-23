package com.lab.pharmacy.model;


import com.lab.pharmacy.utils.PaymentMethods;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "sale_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne( fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_seller")
    private User idSeller;
    @ManyToOne( fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_buyer")
    private User idBuyer;


    @ManyToOne( fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @NotNull
    @Column
    private Float price;

    @NotNull
    @Column
    private Long quantity;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "sale_date")
    private Date saleDate;

    @NotNull
    @Column
    private Float totalPrice;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;

    public Sale(User idSeller, User idBuyer, Product idProduct, Float price, Long quantity, Date saleDate, Float totalPrice, PaymentMethods paymentMethod) {
        this.idSeller = idSeller;
        this.idBuyer = idBuyer;
        this.idProduct = idProduct;
        this.price = price;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
}
