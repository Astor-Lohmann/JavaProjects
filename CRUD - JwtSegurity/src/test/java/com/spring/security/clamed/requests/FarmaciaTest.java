package com.spring.security.clamed.requests;



import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
// Caso queira observar o compartamento do teste no banco de dados desative @Transactional
// Com @Transaction ativado os testes não fazem alteração do estado dos registros no banco de dados
// Sempre após finalizado o teste transação rodará um RollBack voltando o estado do registro(s) no banco
@Transactional

public class FarmaciaTest {

    private URI path;
    private MockHttpServletRequest request;
    private ResultMatcher expectedResult;

    @Autowired
    private MockMvc mock;

    private String jwtToken;


    @Before
    public void setUp() throws Exception{


        String json = "{\"login\": \"astorpalmeira\", \"senha\": \"123456\"}";


        path = new URI("/login");


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON).content(json);


        expectedResult = MockMvcResultMatchers.status().isOk();


        String response = mock.perform(request).andExpect(expectedResult).andReturn().getResponse()
               .getContentAsString();


        JSONObject data = new JSONObject(response);


        jwtToken = data.getString("Authorization");


    }

    @Test
    public void testCadastrar() throws Exception{

        String jsonCadastro = "{\"razaoSocial\": \"Farmacia Teste\", \"cnpj\": \"farmaciateste\", \"nome\": \"farmaciateste\",  \"email\": \"farmaciateste\", \"telefone\": \"farmaciateste\",  \"celular\": \"999999999\", \"idEndereco\": \"1\"}";

        path = new URI("/farmacias");

       MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
               .content(jsonCadastro)
               .header("Content-Type", "application/json")
               .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void testAtualizar() throws Exception {

        String jsonAtualiza = "{\"id\": \"1\", \"razaoSocial\": \"Farmacia Atualiza\", \"cnpj\": \"farmaciaatualiza\", \"nome\": \"farmaciaatualiza\",  \"email\": \"farmaciaatualiza\", \"telefone\": \"farmaciaatualiza\",  \"celular\": \"999999999\", \"idEndereco\": \"1\"}";

        path = new URI("/farmacias");


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(jsonAtualiza)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testRemover() throws Exception {

        path = new URI("/farmacias");


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .param("idFarmacia","1")
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testListar() throws Exception{

        path = new URI("/farmacias");


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void testListarPorNome() throws Exception{

        path = new URI("/farmacias/");


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .param("nome","farmacia1")
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);



    }

}
