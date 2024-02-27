package com.totvs.app.common.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessErroEnum {

    RUN_TIME_ERROR(0, "erro.run_time", 500),
    TELEFONE_JA_EXISTE(1, "telefone.ja.existe", 400);

    private Integer code;
    private String message;
    private Integer statusCode;

}
