package com.lab.pharmacy.controller;

import com.lab.pharmacy.dto.DefaultResponse;
import com.lab.pharmacy.dto.SaleRequest;
import com.lab.pharmacy.dto.SaleResponse;
import com.lab.pharmacy.model.Sale;
import com.lab.pharmacy.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @Operation( summary = "Cadastra uma nova Venda", description = "cadastra uma nova venda no banco de dados")
    @PostMapping("/cadastro")
    public ResponseEntity<DefaultResponse> createNewSale(@RequestBody SaleRequest saleRequest) {
        return service.insert(saleRequest);
    }

    @Operation( summary = "Retorna todas as Vendas", description = "retorna todas as vendas do banco de dados")
    @GetMapping
    public ResponseEntity<DefaultResponse> findAllSales(){
        return service.getAllSales();
    }

    @Operation( summary = "Retorna uma Venda a partir do id", description = "Retorna uma venda a partir do id selecionado")
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> findSalesById(@PathVariable("id") Long id) {
        return service.getSaleById(id);
    }

    @Operation( summary = "Atualiza uma Venda a partir do id", description = "Atualiza uma venda a partir do id selecionado")
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateSale(@PathVariable("id") Long id ,@RequestBody SaleRequest saleRequest) {
        return service.update(id, saleRequest);
    }

    @Operation( summary = "Deleta uma Venda a partir do id", description = "Deleta uma venda a partir do id selecionado")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteSaleById(@PathVariable("id") Long id) {
        return service.deleteSaleById(id);

    }

}
