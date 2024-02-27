package com.totvs.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totvs.app.dto.request.ClienteRequest;
import com.totvs.app.dto.response.ClienteResponse;
import com.totvs.app.facade.ClienteFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@WebMvcTest(value = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteFacade clienteFacade;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/api/clientes";

    @Test
    public void test_criarCliente() throws Exception {

        ClienteRequest request = this.criarClienteRequest();
        ClienteResponse response = this.criarClienteResponse();

        when(this.clienteFacade.criarCliente(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(this.clienteFacade, times(1)).criarCliente(request);
    }

    @Test
    public void test_buscarClientes() throws Exception {

        List<ClienteResponse> response = Arrays.asList(
                criarClienteResponse()
        );

        when(this.clienteFacade.buscarClientes()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(this.clienteFacade, times(1)).buscarClientes();
    }

    private ClienteRequest criarClienteRequest() {
        return ClienteRequest.builder()
                .nome("Higão")
                .bairro("Morro do Mirante")
                .endereco("Rua das Capivaras")
                .telefones(Arrays.asList(
                        "(11) 99999-9999"
                ))
                .build();
    }

    private ClienteResponse criarClienteResponse() {
        return ClienteResponse.builder()
                .id(1)
                .nome("Higão")
                .bairro("Morro do Mirante")
                .endereco("Rua das Capivaras")
                .telefones(Arrays.asList(
                        "(11) 99999-9999"
                ))
                .build();
    }

}
