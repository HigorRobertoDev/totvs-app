package com.totvs.app.common.exceptions;

import com.totvs.app.common.dto.response.ErroFormularioResponse;
import com.totvs.app.common.dto.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class ApplicationsControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroFormularioResponse>> handle(MethodArgumentNotValidException exception) {
        List<ErroFormularioResponse> dtos = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, Locale.getDefault());
            ErroFormularioResponse erro = new ErroFormularioResponse(e.getField(), mensagem,
                    Instant.now().toEpochMilli(), 400,5);

            dtos.add(erro);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(dtos);
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorResponse> handlerPessoaException(BusinessException e){

        String message = this.messageSource.getMessage(e.getMessage(), e.getArgs(),  LocaleContextHolder.getLocale());

        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorResponse(Instant.now().toEpochMilli(), e.getCode(),
                        e.getStatusCode(), message));
    }

}
