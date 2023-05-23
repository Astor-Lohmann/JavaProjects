package com.lab.pharmacy.controller;

import com.lab.pharmacy.dto.DefaultResponse;
import com.lab.pharmacy.dto.ProductRequest;
import com.lab.pharmacy.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/produtos")
public class ProductController {
    @Autowired
    private ProductService service;


    @Operation( summary = "Retorna todos os Produtos Ativos", description = "retorna todos os produtos ativos no sistema")
    @GetMapping
    public ResponseEntity<DefaultResponse> getAllActive(){
        return service.getAllActive();

    }

    @Operation( summary = "Retorna todos os Produtos", description = "retorna todos os produtos ativos no sistema")
    @GetMapping("/all")
    public ResponseEntity<DefaultResponse> getAll(){
        return service.getAll();

    }


    @Operation( summary = "Retorna um Produto a partir do id", description = "retorna um produto a partir do id selecionado")
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> findById(@PathVariable (value="id") Long id){
        return service.findById(id);
    }




    @Operation( summary = "Cadastra um novo Produto", description = "Cadastra um novo produto no sistema")
    @PostMapping
    public ResponseEntity<DefaultResponse> register(@RequestBody ProductRequest request){
        return service.register(request);
    }

    @Operation( summary = "Atualiza um Produto a partir do id", description = "atualiza um produto a partir do id selecionado")
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@RequestBody ProductRequest request, @PathVariable(value="id")Long id){
        return service.update(request, id);
    }

    @Operation( summary = "Deleta um Produto a partir do id", description = "atualiza um produto a partir do id selecionado")
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DefaultResponse> delete(@PathVariable(value="id")Long id){
        return service.delete(id);
    }

}