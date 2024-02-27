package com.totvs.app.facade;

import com.totvs.app.dto.request.ClienteRequest;
import com.totvs.app.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteFacade {

    ClienteResponse criarCliente(ClienteRequest request);

    List<ClienteResponse> buscarClientes();

}
