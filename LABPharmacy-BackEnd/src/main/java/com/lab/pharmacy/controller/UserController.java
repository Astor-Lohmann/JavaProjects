package com.lab.pharmacy.controller;

import com.lab.pharmacy.dto.DefaultResponse;
import com.lab.pharmacy.dto.LoginRequest;
import com.lab.pharmacy.dto.UserRequest;
import com.lab.pharmacy.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation( summary = "Cadastrar Usuário", description = "Cadastra um novo usuário no sistema")
    @PostMapping("/cadastro")
    public ResponseEntity<DefaultResponse> save (@RequestBody UserRequest request) {
        return service.save(request);

    }


    @Operation( summary = "Faz o Login do Usuário", description = "Faz o login de um usuário cadastrado e ativo no sistema")
    @PostMapping("/login")
    public ResponseEntity<DefaultResponse> login(@RequestBody LoginRequest request) {
        return service.login(request.getEmail(), request.getPassword());
    }


    @Operation( summary = "Atualiza um Usuário a partir do id", description = "Atualiza um usuário no sistema")
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update (@PathVariable Long id,@RequestBody UserRequest request){
        return service.update(id, request);
    }

    @Operation( summary = "Retorna todos os Usuários", description = "Retorna todos os usuários ativos do banco de dados")
    @GetMapping
    public ResponseEntity<DefaultResponse> getAllUsers() {
        return service.getAllUsers();
    }

    @Operation( summary = "Retorna o Usuário a partir do id", description = "Retorna o usuário com id correspondente")
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable Long id) {
        return service.getUserById(id);
    }


    @Operation( summary = "Deleta o Usuário a partir do id", description = "Deleta ou torna o usuário inativo no sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }
}
