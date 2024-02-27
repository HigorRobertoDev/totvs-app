package com.totvs.app.service;

import com.totvs.app.model.Cliente;
import com.totvs.app.repository.ClienteRepository;
import com.totvs.app.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Função responsável por salvar os dados do cliente e telefone na base.
     * @param cliente
     * @return
     */
    public Cliente salvarCliente(Cliente cliente) {

        return this.clienteRepository.saveAndFlush(cliente);
    }

    /**
     * Função responsável por buscar todos os clientes cadastrados na base.
     * @return
     */
    public List<Cliente> buscarClientes() {

        return this.clienteRepository.findAll();
    }

}
