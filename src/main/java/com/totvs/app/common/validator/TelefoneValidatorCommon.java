package com.totvs.app.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class TelefoneValidatorCommon implements ConstraintValidator<TelefoneValidator, List<String>> {
    @Override
    public void initialize(TelefoneValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Faz a validação de uma lista de strings
     * @param strings
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {

        if (Objects.isNull(strings) || strings.isEmpty()) {
            return false;
        }

        return !Optional.ofNullable(strings)
                .orElseGet(Collections::emptyList)
                .stream()
                .anyMatch(s -> this.checkString(s) == false);
    }

    /**
     * Verificar se o número casa com o padrão (99) 99999-9999
     * @param arg
     * @return
     */
    private boolean checkString(String arg) {
        return Pattern.compile("^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$").matcher(arg).find();
    }
}
