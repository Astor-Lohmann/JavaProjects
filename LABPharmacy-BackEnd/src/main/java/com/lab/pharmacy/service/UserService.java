package com.lab.pharmacy.service;

import com.lab.pharmacy.dto.*;
import com.lab.pharmacy.exception.BadRequestException;
import com.lab.pharmacy.exception.NotFoundException;
import com.lab.pharmacy.exception.ServerSideException;
import com.lab.pharmacy.model.Address;
import com.lab.pharmacy.model.User;
import com.lab.pharmacy.repository.AddressRepository;
import com.lab.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public AddressRepository addressRepository;

    public ResponseEntity<DefaultResponse> save (UserRequest userRequest){
        try {
            Address address = new Address(
                    userRequest.getCep(),
                    userRequest.getStreet(),
                    userRequest.getNumber(),
                    userRequest.getNeighborhood(),
                    userRequest.getCity(),
                    userRequest.getState(),
                    userRequest.getComplement(),
                    userRequest.getLatitude(),
                    userRequest.getLongitude());

            Address savedAdress = addressRepository.save(address);

            User newUser = new User(
                    userRequest.getFullName(),
                    userRequest.getCpf(),
                    userRequest.getBirth(),
                    userRequest.getEmail(),
                    userRequest.getContactNumber(),
                    userRequest.getType(),
                    userRequest.getPassword(),
                    savedAdress,
                    true);

            User user = userRepository.save(newUser);

            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getCpf(),
                    user.getBirth(),
                    user.getEmail(),
                    user.getContactNumber(),
                    user.getType(),
                    user.getIdAddress()
            );
            return new ResponseEntity<>(
                    new DefaultResponse<UserResponse>(
                            HttpStatus.CREATED.value(),
                            "Usuário cadastrado com sucesso",
                            userResponse
                    ),
                    HttpStatus.CREATED);
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }


    public ResponseEntity<DefaultResponse> login(String email, String password){
        try {
            User user = userRepository.getUserByEmail(email);
            if (user == null) {

                throw new BadRequestException("Email ou Senha inválidos!");
            }
            if (!Objects.equals(user.getPassword(), password)) {
                throw new BadRequestException("Email ou Senha inválidos!");
            }
            if (!user.isActive()){
                throw new BadRequestException("Seu cadastro está inativo, contate o administrador");
            }
            Long userID = user.getId();
            return new ResponseEntity<>(
                    new DefaultResponse(
                            HttpStatus.OK.value(),
                            "Usuário cadastrado.",
                            userID
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e){
            throw new ServerSideException(e.getMessage());
        }
    }

    public ResponseEntity<DefaultResponse> getAllUsers(){
        try {
            List<UserResponse> response = new ArrayList<>();
            for (User user : userRepository.findAll()
            ) {

                if (user.isActive()){
                    UserResponse userResponse = new UserResponse(
                            user.getId(),
                            user.getFullName(),
                            user.getCpf(),
                            user.getBirth(),
                            user.getEmail(),
                            user.getContactNumber(),
                            user.getType(),
                            user.getIdAddress()
                    );
                    response.add(userResponse);
                }
                }


            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.CREATED.value()
                            , "Usuários encontradas com sucesso"
                            , response
                    ),
                    HttpStatus.CREATED);
        } catch (Exception e){
            throw new NotFoundException("Não ha usuários no sistema");
        }
    }

    public ResponseEntity<DefaultResponse> getUserById(Long id){
        try {
            User user = userRepository.findById(id).get();


            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getCpf(),
                    user.getBirth(),
                    user.getEmail(),
                    user.getContactNumber(),
                    user.getPassword(),
                    user.getType(),
                    user.getIdAddress());

            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.CREATED.value()
                            , ("Usuário de id " + id + " encontrada com sucesso")
                            , response
                    ),
                    HttpStatus.CREATED);

        } catch (Exception e){
            throw new NotFoundException("Usuário não encontrada");

        }

    }

    public ResponseEntity<DefaultResponse> update(Long id, UserRequest userRequest){
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (!optionalUser.isPresent()) {
                throw new NotFoundException("Usuário não encontrado");
            }

            User user = optionalUser.get();

            // atualiza os campos desejados
            user.setFullName(userRequest.getFullName());
            user.setCpf(userRequest.getCpf());
            user.setBirth(userRequest.getBirth());
            user.setEmail(userRequest.getEmail());
            user.setContactNumber(userRequest.getContactNumber());
            user.setType(userRequest.getType());
            user.setPassword(userRequest.getPassword());

            Address address = user.getIdAddress();
            address.setCep(userRequest.getCep());
            address.setStreet(userRequest.getStreet());
            address.setNumber(userRequest.getNumber());
            address.setNeighborhood(userRequest.getNeighborhood());
            address.setCity(userRequest.getCity());
            address.setState(userRequest.getState());
            address.setComplement(userRequest.getComplement());
            address.setLatitude(userRequest.getLatitude());
            address.setLongitude(userRequest.getLongitude());

            Address savedAddress = addressRepository.save(address);
            user.setIdAddress(savedAddress);

            User savedUser = userRepository.save(user);

            UserResponse userResponse = new UserResponse(
                    savedUser.getId(),
                    savedUser.getFullName(),
                    savedUser.getCpf(),
                    savedUser.getBirth(),
                    savedUser.getEmail(),
                    savedUser.getContactNumber(),
                    savedUser.getType(),
                    savedUser.getIdAddress()
            );
            return new ResponseEntity<>(
                    new DefaultResponse<UserResponse>(
                            HttpStatus.OK.value(),
                            "Usuário atualizado com sucesso",
                            userResponse
                    ),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    public ResponseEntity<DefaultResponse> deleteUserById( Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (!user.isPresent()) {
                throw new NotFoundException("Usuário não encontrado");
            }
            userRepository.deleteById(id);


            return new ResponseEntity<>(
                    new DefaultResponse<>(
                            HttpStatus.OK.value(),
                            ("Usuário de id " + id + " deletado com sucesso")
                            , "ok"
                    ),
                    HttpStatus.OK);

        } catch (Exception e) {
            try{
                Optional<User> user = userRepository.findById(id);
                if (!user.isPresent()) {
                    throw new NotFoundException("Usuário não encontrado");
                }
                user.get().setActive(false);
                userRepository.save(user.get());
                return new ResponseEntity<>(
                        new DefaultResponse<>(
                                HttpStatus.OK.value(),
                                ("Status do Usuário de id " + id + " alterado para inativo")
                                , "ok"
                        ),
                        HttpStatus.OK);



            } catch (Exception error){
               throw new ServerSideException(error.getMessage());
            }

        }

    }

}
