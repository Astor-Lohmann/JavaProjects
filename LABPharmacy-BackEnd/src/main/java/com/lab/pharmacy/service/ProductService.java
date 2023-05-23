package com.lab.pharmacy.service;

import com.lab.pharmacy.dto.DefaultResponse;
import com.lab.pharmacy.dto.ProductRequest;
import com.lab.pharmacy.dto.ProductResponse;
import com.lab.pharmacy.exception.BadRequestException;
import com.lab.pharmacy.exception.NotFoundException;
import com.lab.pharmacy.model.Product;
import com.lab.pharmacy.model.User;
import com.lab.pharmacy.repository.ProductRepository;
import com.lab.pharmacy.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<DefaultResponse> getAllActive(){
        try{
            List<Product> products = repository.findAll();
            List<ProductResponse> response = new ArrayList<>();
            if (products.size() == 0) {
                throw new NotFoundException();
            }

            for (Product item : products) {

                boolean isActive = item.isActive();
                if (isActive) {
                    response.add(
                            new ProductResponse(
                                    item.getId(),
                                    item.getProductName(),
                                    item.getLaboratoryName(),
                                    item.getProductImage(),
                                    item.getDosage(),
                                    item.getProductDescription(),
                                    item.getPrice(),
                                    item.getProductType(),
                                    item.getStockQuantity(),
                                    item.getProductRegisterDate(),
                                    item.getIdUser().getFullName()
                            )
                    );
                }

            }

            return new ResponseEntity<>(
                    new DefaultResponse<List>(
                            HttpStatus.OK.value(),
                            "Lista de produtos cadastrados",
                            response
                    ),
                    HttpStatus.OK
            );

        }catch (NotFoundException e){
            throw new NotFoundException("Nenhum produto cadastrado.");
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }
    public ResponseEntity<DefaultResponse> getAll(){
        try{
            List<Product> products = repository.findAll();
            List<ProductResponse> response = new ArrayList<>();
            if (products.size() == 0) {
                throw new NotFoundException();
            }

            for (Product item : products) {


                    response.add(
                            new ProductResponse(
                                    item.getId(),
                                    item.getProductName(),
                                    item.getLaboratoryName(),
                                    item.getProductImage(),
                                    item.getDosage(),
                                    item.getProductDescription(),
                                    item.getPrice(),
                                    item.getProductType(),
                                    item.getStockQuantity(),
                                    item.getProductRegisterDate(),
                                    item.getIdUser().getFullName()
                            )
                    );


            }

            return new ResponseEntity<>(
                    new DefaultResponse<List>(
                            HttpStatus.OK.value(),
                            "Lista de produtos cadastrados",
                            response
                    ),
                    HttpStatus.OK
            );

        }catch (NotFoundException e){
            throw new NotFoundException("Nenhum produto cadastrado.");
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }
    public ResponseEntity<DefaultResponse> findById(Long id){
        try {
            Product product = repository.findById(id).get();
            if(product.getId()==null){
                throw new NotFoundException("Produto não encontrado");
            }
            ProductResponse response = new ProductResponse(
                    product.getId(),
                    product.getProductName(),
                    product.getLaboratoryName(),
                    product.getProductImage(),
                    product.getDosage(),
                    product.getProductDescription(),
                    product.getPrice(),
                    product.getProductType(),
                    product.getStockQuantity(),
                    product.getProductRegisterDate(),
                    product.getIdUser().getId()

            );
            return new ResponseEntity<>(
                    new DefaultResponse<ProductResponse>(
                            HttpStatus.OK.value(),
                            "Produto encontrado.",
                            response
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }
    public ResponseEntity<DefaultResponse> register(ProductRequest request){
        try{
            User user = userRepository.findById(request.getIdUser()).get();

            Product product = repository.save(new Product(
                    request.getProductName(),
                    request.getLaboratoryName(),
                    request.getProductImage(),
                    request.getDosage(),
                    request.getProductDescription(),
                    request.getPrice(),
                    request.getProductType(),
                    request.getStockQuantity(),
                    request.getProductRegisterDate(),
                    user,
                    true
            ));
            ProductResponse response = new ProductResponse(
                    product.getId(),
                    product.getProductName(),
                    product.getLaboratoryName(),
                    product.getProductImage(),
                    product.getDosage(),
                    product.getProductDescription(),
                    product.getPrice(),
                    product.getProductType(),
                    product.getStockQuantity(),
                    product.getProductRegisterDate(),
                    product.getIdUser().getFullName()
            );
            return new ResponseEntity<>(
                    new DefaultResponse<ProductResponse>(
                            HttpStatus.OK.value(),
                            "Produto cadastrado com sucesso.",
                            response
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            throw new BadRequestException("Verifique o preenchimento dos campos");
        }
    }

    public ResponseEntity<DefaultResponse> update(ProductRequest request, Long id){
        try{
            User user = userRepository.findById(request.getIdUser()).get();

            Product product = repository.save(new Product(
                    id,
                    request.getProductName(),
                    request.getLaboratoryName(),
                    request.getProductImage(),
                    request.getDosage(),
                    request.getProductDescription(),
                    request.getPrice(),
                    request.getProductType(),
                    request.getStockQuantity(),
                    request.getProductRegisterDate(),
                    user,
                    true
            ));
            ProductResponse response = new ProductResponse(
                    product.getId(),
                    product.getProductName(),
                    product.getLaboratoryName(),
                    product.getProductImage(),
                    product.getDosage(),
                    product.getProductDescription(),
                    product.getPrice(),
                    product.getProductType(),
                    product.getStockQuantity(),
                    product.getProductRegisterDate(),
                    product.getIdUser().getFullName()
            );
            return new ResponseEntity<>(
                    new DefaultResponse<ProductResponse>(
                            HttpStatus.OK.value(),
                            "Produto atualizado com sucesso.",
                            response
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            throw new BadRequestException("Verifique o preenchimento dos campos");
        }
    }

    public ResponseEntity<DefaultResponse> delete(Long id){
        try {

            if(repository.findById(id).get()==null){
                throw new NotFoundException();
            }
            Product product = repository.findById(id).get();
            product.setIdUser(new User());


            repository.delete(product);

            return new ResponseEntity<>(
                    new DefaultResponse<String>(
                            HttpStatus.OK.value(),
                            "Produto de id " + id + " deletado com sucesso.",
                            "ok"
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            try {
                Product product = repository.findById(id).get();
                product.setActive(false);
                repository.save(product);
                return new ResponseEntity<>(
                        new DefaultResponse<String>(
                                HttpStatus.OK.value(),
                                "Status do Produto de id " + id + " atualizado para inativo.",
                                "ok"
                        ),
                        HttpStatus.OK);

            } catch (Exception error){
                throw new BadRequestException("Não é possível deletar um produto que está referenciado na tabela vendas");
            }

        }
    }
}
