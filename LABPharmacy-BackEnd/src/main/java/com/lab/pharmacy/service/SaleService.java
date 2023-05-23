package com.lab.pharmacy.service;

import com.lab.pharmacy.dto.DefaultResponse;
import com.lab.pharmacy.dto.SaleRequest;
import com.lab.pharmacy.dto.SaleResponse;
import com.lab.pharmacy.exception.BadRequestException;
import com.lab.pharmacy.exception.NotFoundException;
import com.lab.pharmacy.model.Product;
import com.lab.pharmacy.model.Sale;
import com.lab.pharmacy.repository.ProductRepository;
import com.lab.pharmacy.repository.SaleRepository;
import com.lab.pharmacy.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<DefaultResponse> insert (SaleRequest saleRequest) {
        try {

            Sale sale = new Sale(
                      userRepository.findById(saleRequest.getSellerId()).get()
                    , userRepository.findById(saleRequest.getBuyerId()).get()
                    , productRepository.findById(saleRequest.getProductId()).get()
                    , saleRequest.getPrice()
                    , saleRequest.getQuantity()
                    , saleRequest.getSaleDate()
                    , saleRequest.getTotalPrice()
                    , saleRequest.getPaymentMethod());

            saleRepository.save(sale);
            SaleResponse newSale = new SaleResponse(
                    sale.getId(),
                    sale.getIdSeller().getId(),
                    sale.getIdBuyer().getId(),
                    sale.getIdProduct().getProductName(),
                    sale.getIdProduct().getId(),
                    sale.getPrice(),
                    sale.getQuantity(),
                    sale.getSaleDate(),
                    sale.getTotalPrice(),
                    sale.getPaymentMethod()
            );
            Product product = productRepository.findById(saleRequest.getProductId()).get();
            product.setStockQuantity(product.getStockQuantity() - saleRequest.getQuantity());
            if (product.getStockQuantity()< 0){
                throw new BadRequestException("O produto não tem essa quantidade no estoque");
            }
            productRepository.save(product);

            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.CREATED.value()
                            , "Sale registered successfully!"
                            , newSale
                    ),
                    HttpStatus.CREATED);

        } catch (Exception e){
            throw new BadRequestException("Verifique o preenchimento dos campos");
        }
    }

    public ResponseEntity<DefaultResponse> update(Long id, SaleRequest saleRequest) {
        try {
            Sale sale = saleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Sale not found"));

            sale.setIdSeller(userRepository.findById(saleRequest.getSellerId())
                    .orElseThrow(() -> new NotFoundException("Seller not found")));
            sale.setIdBuyer(userRepository.findById(saleRequest.getBuyerId())
                    .orElseThrow(() -> new NotFoundException("Buyer not found")));
            sale.setIdProduct(productRepository.findById(saleRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found")));
            sale.setPrice(saleRequest.getPrice());
            sale.setQuantity(saleRequest.getQuantity());
            sale.setSaleDate(saleRequest.getSaleDate());
            sale.setTotalPrice(saleRequest.getTotalPrice());
            sale.setPaymentMethod(saleRequest.getPaymentMethod());

            saleRepository.save(sale);
            SaleResponse newSale = new SaleResponse(
                    sale.getId(),
                    sale.getIdSeller().getId(),
                    sale.getIdBuyer().getId(),
                    sale.getIdProduct().getProductName(),
                    sale.getIdProduct().getId(),
                    sale.getPrice(),
                    sale.getQuantity(),
                    sale.getSaleDate(),
                    sale.getTotalPrice(),
                    sale.getPaymentMethod()
                    );
            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.OK.value()
                            , "Sale updated successfully!"
                            , newSale
                    ),
                    HttpStatus.OK);


        } catch (Exception e) {
            throw new BadRequestException("Não foi possível atualizar a venda de id " + id);
        }
    }




    public ResponseEntity<DefaultResponse> getAllSales() {
        try {
            List<SaleResponse> response = new ArrayList<>();
            for (Sale sale : saleRepository.findAll()
            ) {
                SaleResponse newSale = new SaleResponse(
                        sale.getId(),
                        sale.getIdSeller().getId(),
                        sale.getIdBuyer().getId(),
                        sale.getIdProduct().getProductName(),
                        sale.getIdProduct().getId(),
                        sale.getPrice(),
                        sale.getQuantity(),
                        sale.getSaleDate(),
                        sale.getTotalPrice(),
                        sale.getPaymentMethod()
                );
                response.add(newSale);
            }
            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.CREATED.value()
                            , "Vendas encontradas com sucesso"
                            , response
                    ),
                    HttpStatus.CREATED);
        } catch (Exception e){
            throw new NotFoundException("Não ha vendas no sistema");
        }

    }

    public ResponseEntity<DefaultResponse> getSaleById(Long id) {
        try {
            Sale sale = saleRepository.findById(id).get();


            SaleResponse response = new SaleResponse(
                    sale.getId(),
                    sale.getIdSeller().getId(),
                    sale.getIdBuyer().getId(),
                    sale.getIdProduct().getProductName(),
                    sale.getIdProduct().getId(),
                    sale.getPrice(),
                    sale.getQuantity(),
                    sale.getSaleDate(),
                    sale.getTotalPrice(),
                    sale.getPaymentMethod());

            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.CREATED.value()
                            , ("Venda de id " + id + " encontrada com sucesso")
                            , response
                    ),
                    HttpStatus.CREATED);

        } catch (Exception e){
            throw new NotFoundException("Venda não encontrada");

        }
    }

    public ResponseEntity<DefaultResponse> deleteSaleById(Long id) {
        try {
            saleRepository.deleteById(id);
            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.CREATED.value()
                            , "Sale deleted successfully!"
                            , ("Venda de id " + id + " deletada com sucesso")
                    ),
                    HttpStatus.CREATED);
        } catch (Exception e){
            throw new NotFoundException("Nenhum venda com id " + id + " foi encontrado");
        }
    }

}
