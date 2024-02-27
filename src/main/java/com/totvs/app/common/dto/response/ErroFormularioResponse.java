package com.totvs.app.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErroFormularioResponse extends Response {

    private String campo;
    private Long timeStamp;
    private Integer code;

    public ErroFormularioResponse(String campo, String message, Long timeStamp, Integer statusCode, Integer code) {
        this.campo = campo;
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.code =  code;
    }

}
