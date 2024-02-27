package com.totvs.app.dto.request;

import com.totvs.app.common.validator.TelefoneValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "{campo.nome}")
    private String nome;

    @NotBlank(message = "{campo.endereco}")
    private String endereco;

    @NotBlank(message = "{campo.bairro}")
    private String bairro;

    @TelefoneValidator
    private List<String> telefones;

}
